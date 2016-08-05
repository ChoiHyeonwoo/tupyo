<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		아이디 : <input type = "text" id="logid"/> <br /> 
		비밀번호 : <input type = "password" id="password"/><br /> 
		비밀번호 확인 : <input type = "password" id="password_confirm"/><br />
		이름: <input type = "text" id="name"/> <br />
		<input type="button" onclick="member_reg()" value="회원가입" /> <input type="button" value="취소" />
<script>
	// 다시 수정 
		function member_reg(){
		
			var id = $("#logid").val();
			var password = $("#password").val();
			var password_confirm = $("#password_confirm").val();
			var name = $("#name").val();
						
			$.post("/tupyo/m_confirm", {
				id: id,
				password: password,
				name: name
			});
			alert("가입 성공. 로그인을 해주세요.");
			location.href ="/tupyo";
		
	}
</script>
</body>
</html>