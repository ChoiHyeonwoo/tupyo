<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	m_login.jsp 페이지

	<form action = "/tupyo/m_check" method="POST">
		아이디 : <input type= "text" name="logid" /><br />
		패스워드 : <input type= "password" name="logpw" /><br />
		<input type="submit" value="로그인" /><a href = "/tupyo">취소</a>
	</form>
	
	
</body>
</html>