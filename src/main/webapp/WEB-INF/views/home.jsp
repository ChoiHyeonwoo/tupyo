<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page session="false" %>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<html>
<head>
	<title>Home</title>
</head>

<body>		
	<% 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pk_id = (String)session.getAttribute("pk_id");
		String name= (String)session.getAttribute("name");
		
		out.println(id);
		out.println(name);
		out.println(pk_id);
	%>
	
		<script>
		window.onload = function(){
			var id =<%=id%>;

			if(id==null){	
				$("#log_check").html("로그인");
				$("#log_check").attr("href", "/tupyo/m_login");
				$("#member_check").html("회원가입하기");
				$("#member_check").attr("href", "/tupyo/m_register");
				$("#member_drop").hide();
				$("#log_view").hide();
			}
			
		}
			
		</script>
<h1>
	투표목록입니다.
</h1>

		<c:forEach items="${title}" var="dto">
		
			투표 제목 : ${dto.title}<br />
			투표 작성자 : ${dto.writer}<br />
			투표 중복허용 여부 : ${dto.is_duplicated}<br />
			투표 등록 날짜 : ${dto.reg_date}<br />

		<a href="/tupyo/execute?id=${dto.id}"> 투표하러가기 </a>
		<br/>
	</c:forEach>
	<br />
	
	<a href = "/tupyo/register">투표 등록하기</a><br />
	<!-- <a href = "/tupyo/m_login">로그인</a> -->
	&nbsp;&nbsp;<a id="log_check" href = "/tupyo/m_logout">로그아웃</a>&nbsp;&nbsp;
	<!--  <a href = "/tupyo/m_register">회원가입 하기</a> --><br/>
	<a id="member_check" href= "/tupyo/m_check_update">회원정보수정</a>&nbsp;&nbsp;<a id="member_drop" href = "/tupyo/m_check_destroy">회원탈퇴</a>
	<a id="log_view" href="/tupyo/m_check_log">내 로그이력 보기</a>
	

</body>
</html>
