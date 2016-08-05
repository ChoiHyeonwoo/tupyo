<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.sql.*" %>
<%@ page session="false" %>
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
<h1>
	투표목록입니다.
</h1>

		<c:forEach items="${title}" var="dto">
		
			투표 제목 : ${dto.title}<br />
			찬성 : ${dto.agree}<br />
			반대 : ${dto.disagree}<br />
			
		<a href="/tupyo/execute?id=${dto.id}"> 투표하러가기 </a>
		<br/>
	</c:forEach>
	<br />
	
	<a href = "/tupyo/register">투표 등록하기</a><br />
	<a href = "/tupyo/m_login">로그인</a>&nbsp;&nbsp;<a href = "/tupyo/m_logout">로그아웃</a>&nbsp;&nbsp; <a href = "/tupyo/m_register">회원가입 하기</a><br/>
	<a href = "/tupyo/m_check_update">회원정보수정</a>
</body>
</html>
