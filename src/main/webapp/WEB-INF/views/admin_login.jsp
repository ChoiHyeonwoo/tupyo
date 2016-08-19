<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
	if("".equals(id) || id == null){
		id = "";
	}
	String pk_id = (String)session.getAttribute("pk_id");
	String name= (String)session.getAttribute("name");
	int grade = 9;
	if(session.getAttribute("grade")!=null){
		 grade = ((Integer)session.getAttribute("grade")).intValue();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
</head>
<body>
<h1>tupyo 관리자 로그인</h1>
	<table>
	<tr>
		<td>아이디</td>
		<td><input type= "text" id="logid" /></td>
	</tr>
	<tr>
		<td>패스워드</td>
		<td><input type= "password" id="logpw" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button"  onclick="formCHK()" value="로그인" /></td>
	</tr>
</table>

</body>
<script>
	var id = '<%=id%>';
	var grade = '<%=grade%>';
	
	if(id !=''&& grade !='9'){
		alert("관리자 인증 되었습니다.");
		location.href = "/tupyo/admin_main";
	}
	
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
				url: "/tupyo/admin_login_confirm",
				data: {
					logid: id,
					logpw: password
				},
				success: function(result){
					if(result=="fail"){
						alert("로그인 실패. 관리자의 아이디와 패스워드를 확인하세요.");
						return;
					}else if(result=="drop"){
						alert("탈퇴한 계정입니다.");
						return;
					}else{
						alert("로그인 성공");
						location.href="/tupyo/admin_main";
					}
				}
			});
		}
	}

</script>
</html>