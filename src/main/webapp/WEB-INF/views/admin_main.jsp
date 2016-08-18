<%@ page session ="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	HttpSession session = request.getSession();
	String pk_id = (String)session.getAttribute("pk_id");
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
	int grade = ((Integer)session.getAttribute("grade")).intValue();
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<style>
  table {
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
<script>
	var grade = "<%= grade %>";
	
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  	window.location = "/tupyo/admin_login";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);
	function get_user_detail_info(pk_id){
		 var form = $('<form></form>');
 	     form.attr('action', "/tupyo/admin_user_detail");
 	     form.attr('method', 'post');
 	     form.appendTo('body');
 	     
 	     var hidden = $('<input type="hidden" value="'+pk_id+'" name="pk_id">');
 
 	     form.append(hidden);
 	     form.submit();
	}
	function go_member_register(){
		if (grade == "3"){
			alert("회원 등록에 대한 권한이 없습니다.")
			return;
		}
		location.href = "/tupyo/admin_member_register";
		
	}
	function logout(){
		$.post("/tupyo/admin_logout", {
		
		})
		.done(function(msg){
				
				location.href = "/tupyo/admin_login";
			})
		.fail(function(xhr, status, error){
			// error handling
			alert("에러 발생. 잠시후에 시도하세요.");
		});
	}
</script>
</head>
<body>
<h1>tupyo 회원 리스트</h1>
	<table>
		<tr>
			<td style="text-align:center">아이디</td>
			<td style="text-align:center">이름</td>
			<td style="text-align:center">가입일</td>
			<td style="text-align:center">등록자</td>
			<td style="text-align:center">권한</td>
		</tr>
	<c:if test="${empty mdtos}">
		<tr>
			<td style="text-align:center; height:70" colspan="6">등록된 회원이 없습니다.</td>
		</tr>       
    </c:if>
	<c:forEach items="${mdtos}" var="dto">

		<tr>
			<td><a href="javascript:get_user_detail_info('${dto.pk_mid}')"> ${dto.id} </a></td>
			<td>${dto.name}</td>
			<td style="text-align:center">${dto.reg_date}</td>
			<td>${dto.reg_person}</td>
			<c:choose>
				<c:when test="${dto.grade == 2}">
					<td style="text-align:center"> 중간 관리자</td>
				</c:when>
				<c:when test="${dto.grade == 3}">
					<td style="text-align:center"> 하위 관리자</td>
				</c:when>
				<c:otherwise>
					<td style="text-align:center"> 일반 사용자</td>
				</c:otherwise>	
			</c:choose>
		</tr>

	</c:forEach>
	</table>
	<input type="button" onclick="go_member_register()" value="사용자 등록" />&nbsp;&nbsp;	<input type="button" onclick="logout()" value="로그아웃" />
</body>
</html>