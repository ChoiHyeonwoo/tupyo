<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		out.println(pk_id);
		out.println(name);

	%>
	<script>
		var id = <%=id%>;
		if (id == null)
			{
				alert("로그인 후에 이용바랍니다.");
				location.href = "/tupyo";
			}
		 
	</script>
</head>
<body>
M_destroy_UPDATE.JSP
	
	<h1>비밀번호를 입력해 주시기 바랍니다.</h1>
	<form action="/tupyo/m_destroy" method="POST">
		비밀번호 입력 : <input type="password" name="curr_password" /></br>
		<input type="submit" value="확인"/>
	</form>
</body>
</html>