<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
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


		아이디 : <input type = "text" id="logid" value="<%=id %>" readonly/> <br /> 
		비밀번호 : <input type = "password" id="password"/><br /> 
		비밀번호 확인 : <input type = "password" id="password_confirm"/><br />
		이름: <input type = "text" id="name" value="<%=name %>"/> <br />
		<input type="button" onclick="member_update();"  value="수정" /> <a href="/tupyo">취소</a>

	
	<script>
		function member_update(){
			var id = $("#logid").val();
			var password = $("#password").val();
			var password_confirm = $("#password_confirm").val();
			var name = $("#name").val();
			if(id == ""){
				alert("아이디를 입력해 주세요");
			}else if(password == "" || password_confirm == "")
			{
				alert("비밀번호를 입력해 주세요");
			}
			else if(name == ""){
				alert("이름을 입력해 주세요.");
			}
			else if(password!=password_confirm){
				alert("비밀번호가 일치하지 않습니다.");
			}else{
				$.post("/tupyo/m_update_complete", {
					id: id,
					password: password,
					name: name
				})
				.done(function(msg){
						alert("수정 완료.");
						location.href = "/tupyo";
					})
				.fail(function(xhr, status, error){
					// error handling
					alert("error");
				});
			}
		}
	</script>
	
</body>
</html>