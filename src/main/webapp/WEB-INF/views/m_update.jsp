<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<% 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pk_id = (String)session.getAttribute("pk_id");
		String name= (String)session.getAttribute("name");
		
		out.println(id);
		out.println(name);
		out.println(pk_id);
	%>
</head>
<body>
	M_UPDATE.JSP PAGE

	<form action="/tupyo/m_update_complete" method="post">
		아이디 : <input type = "text" name="logid" value="<%=id %>"/> <br /> 
		비밀번호 : <input type = "password" name="password"/><br /> 
		비밀번호 확인 : <input type = "password" name="password_confirm"/><br />
		이름: <input type = "text" name="name" value="<%=name %>"/> <br />
		<input type="submit"  value="수정" /> <a href="/tupyo">취소</a>
	</form>
	
</body>
</html>