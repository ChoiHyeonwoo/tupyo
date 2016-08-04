<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<form action="/" method="POST">
		아이디 : <input type = "text" name="id"/> <br />
		비밀번호 : <input type = "password" name="password"/><br /> 
		비밀번호 확인 : <input type = "password" name="password_confirm"/><br />
		이름: <input type = "text" name="name"/> <br />
		<input type="submit" value="회원가입"> <input type="button" value="취소">
	</form>
</body>
</html>