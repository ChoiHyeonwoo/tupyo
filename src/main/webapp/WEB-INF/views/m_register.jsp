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
			
			if(id == ""){
				alert("아이디를 입력해 주세요")
			}else if(password == "" || password_confirm == "")
			{
				alert("비밀번호를 입력해 주세요")
			}
			else if(name == ""){
				alert("이름을 입력해 주세요.")
			}
			else if(password!=password_confirm){
				alert("비밀번호가 일치하지 않습니다.")
			}
			else{
				$.post("/tupyo/m_confirm", {
					id: id,
					password: password,
					name: name
				});
			}
			
	}
</script>
</body>
</html>