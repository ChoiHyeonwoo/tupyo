<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script>

	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  	window.location = "/tupyo/admin_login";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);

	function password_modify(){
		var password = $("#password").val();
		var password_confirm= $("#password_confirm").val();
		if(password ==""){
			alert("비밀번호를 입력해 주세요.");
			$("#password").focus();
		}
		else if(password.replace( blank_pattern, '' ) == "" ){
			alert("비밀번호에 공백만 입력 되었습니다.");
			$("#password").val("");
			$("#password").focus();
		}else if(password_confirm == ""){
			alert("비밀번호 확인을 입력해 주세요.");
			$("#password_confirm").focus();
		}else if(password_confirm.replace( blank_pattern, '' ) == "" ){
			alert("비밀번호 확인에 공백만 입력 되었습니다.");
			$("#password_confirm").val("");
			$("#password_confirm").focus();
		}else if(password.length < 8){
			alert("비밀번호는 8자리 이상 입력해 주세요.");
			$("#password").val("");
			$("#password_confirm").val("");
			$("#password").focus();
		}
		else if(password != password_confirm){
			alert("비밀번호가 서로 다릅니다.");
			$("#password").val("");
			$("#password_confirm").val("");
			$("#password").focus();
		}else{
			$.post("/tupyo/admin_password_update", {
				pk_id: pk_id,
				password : password
			})
			.done(function(msg){
					alert("수정 완료.");
					location.href = "/tupyo/admin_main";
				})
			.fail(function(xhr, status, error){
				// error handling
				alert("에러 발생. 잠시후에 시도하세요.");
			});
		}
	}

</script>
</head>
<body>
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id ="password" name="password" /></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" id ="password_confirm" name="password_confirm" /></td>
		</tr>
	</table>
	<input type="button" name="password_modify" onclick = "password_modify()" />
	
	
</body>
</html>