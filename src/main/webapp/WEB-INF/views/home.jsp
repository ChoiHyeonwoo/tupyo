<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page session="false" %>
	<% 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if("".equals(id) || id == null){
			id = "";
		}
		String pk_id = (String)session.getAttribute("pk_id");
		String name= (String)session.getAttribute("name");
		int grade = 9;
		if(session.getAttribute("grade")!=null){
			 grade = ((Integer)session.getAttribute("grade")).intValue();
		}
	%>
<html>
<head>
	<title>Home</title>
<style>
  table {
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<script>

	window.onload = function(){
			var id = '<%=id%>';

			if(id==''){
				$("#log_check").html("로그인");
				$("#log_check").attr("href", "/tupyo/m_login");
				$("#member_check").html("회원가입하기");
				$("#member_check").attr("href", "/tupyo/m_register");
				$("#member_drop").hide();
				$("#log_view").hide();
				$("#password_change").hide();
			}
		var search_option = "<c:out value="${search_option}"></c:out>";	
			if (search_option!=null && search_option!=""){
				 $("select").val(search_option).attr("selected", "selected");
			}
		}
	
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  window.location = "/tupyo";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);
		
	function log_check(){
			var id = '<%=id%>';
			
			if(id==''){
				alert("로그인 후에 진행 바랍니다");
			}
			
		}

		function search_poll(){
			var id = '<%=id%>';

			if(id==''){	
				alert("로그인을 해주시기 바랍니다.");
				return;
			}			
			var option = $("#option").val();
			var content = $("#content").val();

			if (option==""){
				alert("검색할 항목을 설정해 주세요");
				return;	
			}

			var blank_pattern = /^\s+|\s+$/g;
			if( content.replace( blank_pattern, '' ) == "" ){
				if(option!="total"){
					alert("항목을 입력해 주세요");
				    return;
				}

			}
			document.tupyo.submit();
		}
		
		function go_tupyo_execute(t_id){
			
			var id = '<%=id%>';
			
			if(id==''){
				alert("로그인 후에 진행 바랍니다");
				return;
			}
			
			document.tupyo.action="/tupyo/execute";
			document.tupyo.t_id.value = t_id;
			document.tupyo.submit();

			
		}
		function go_tupyo_result(t_id){
			
			var id = '<%=id%>';
			
			if(id==''){
				alert("로그인 후에 진행 바랍니다");
				return;
			}
			
			document.tupyo.action="/tupyo/t_result";
			document.tupyo.t_id.value = t_id;
			document.tupyo.submit();
			
		}
		function go_tupyo_register(){
			
			var id = '<%=id%>';
			var grade = '<%=grade%>'
			if(id==''){
				alert("로그인 후에 진행 바랍니다");
				return;
			}
			if(grade == '9'){
				alert("투표등록 권한이 없습니다.");
				return;
			}
			location.href="/tupyo/register";
		}
	</script>
</head>
<body>		
	<h1>
		투표목록입니다.
	</h1>
	<form action="" name="tupyo" id="tupyo" method="post"> 
	
		<select id="option" name="option" >
		  <option value="total" selected="selected">전체검색</option>
		  <option value="title">제목</option>
		  <option value="writer">작성자</option>
		  <option value="reg_date">날짜</option>
		</select>
		<input type="hidden" name="t_id" id="t_id" />
		<input type="text" name="content" id="content" value="${search_content}" />
		<a href="#" onclick="search_poll()">검색</a>
	</form>	
	
	
	<table>
		<tr>
			<td style="text-align:center">투표 제목</td>
			<td style="text-align:center">투표 작성자</td>
			<td style="text-align:center">투표 등록 날짜</td>
			<td style="text-align:center">투표 링크</td>
		</tr>
	<c:if test="${empty tupyo_list}">
		<tr>
			<td style="text-align:center; height:70" colspan="6">기록이 없습니다.</td>
		</tr>       
    </c:if>
	<c:forEach items="${tupyo_list}" var="dto">

		<tr>
			<td><a href="javascript:go_tupyo_result('${dto.id}')"> ${dto.title} </a></td>
			<td>${dto.writer}</td>
			<td style="text-align:center">${dto.reg_date}</td>
	
		<td><a href="javascript:go_tupyo_execute('${dto.id}')" > 투표하러가기 </a></td>
		</tr>

	</c:forEach>
	</table>

	<br />
	
	<a href="javascript:go_tupyo_register()">투표 등록하기</a>
	&nbsp;&nbsp;<a id="log_check" href = "/tupyo/m_logout">로그아웃</a>&nbsp;&nbsp;
	<a id="password_change" href = "/tupyo/m_update_password">비밀번호 변경</a>
	<a id="member_check" href= "/tupyo/m_check_update">회원정보수정</a>&nbsp;&nbsp;<a id="member_drop" href = "/tupyo/m_check_destroy">회원탈퇴</a>
	<a id="log_view" href="/tupyo/m_check_log">내 로그이력 보기</a>
	
</body>
</html>
