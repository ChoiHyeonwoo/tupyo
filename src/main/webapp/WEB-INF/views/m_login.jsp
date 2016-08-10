<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>


		아이디 : <input type= "text" id="logid" /><br />
		패스워드 : <input type= "password" id="logpw" /><br />
		<input type="button" onclick="formCHK()" value="로그인" /><a href = "/tupyo">취소</a>

	<script>
	function formCHK() {
			var id = $("#logid").val();
			var password = $("#logpw").val();
			
			if(id==""){
				alert("아이디를 입력해 주세요.");
				
			}else if(password==""){
				alert("비밀번호를 입력해 주세요");
				
			}else{
				$.ajax({
					method: "POST",
					url: "/tupyo/m_check",
					data: {
						logid: id,
						logpw: password
					},
					success: function(result){
						if(result=="fail"){
							alert("로그인 실패. 아이디와 패스워드를 확인하세요.");
							return;
						}else if(result=="drop"){
							alert("탈퇴한 계정입니다.");
							return;
						}else{
							alert("로그인 성공");
							location.href="/tupyo";
						}
					}
				});
			}
	}

	</script>
	
</body>
</html>