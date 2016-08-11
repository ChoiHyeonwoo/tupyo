<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
	String pk_id = (String)session.getAttribute("pk_id");
	String name= (String)session.getAttribute("name");
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script>
		var id = '<%=id%>';
		if (id == 'null')
			{
				alert("로그인 후에 이용바랍니다.");
				location.href = "/tupyo";
			}
	</script>
</head>
<body>
<h1>비밀번호변경 페이지입니다.</h1><br />
	<table>
		<tr>
			<td>현재 비밀번호</td>
			<td><input type="password" id="curr_password" /></td>
			<td><p id="errornot"></p></td>
		</tr>
		<tr>
			<td>새 비밀번호</td>
			<td><input type="password" id="new_password" /></td>
		</tr>
		<tr>
			<td>새 비밀번호 확인</td>
			<td><input type="password" id="new_password_confirm" /></td>
		</tr>	
	</table>
<input type="button" onclick="pwd_check()" value="확인"/>

	<script>
	$(function(){
	$("#curr_password").blur(function(){
			var curr_password = $("#curr_password").val();
			$.ajax({
				method: "POST",
				url: "/tupyo/m_password_check",
				data: {
					password: curr_password
				},
				success: function(result){
					if(result=="error"){
						$("#errornot").html("현재 비밀번호를 다시 입력해 주시기 바랍니다.");
					}else{
						$("#errornot").html("");
					}
				},
			});	
		})
		});

	function pwd_check(){
		var curr_password = $("#curr_password").val();
		var new_password = $("#new_password").val();
		var new_password_confirm = $("#new_password_confirm").val();
		var blank_pattern = /^\s+|\s+$/g;
		var password_check = '';

		if( new_password.replace( blank_pattern, '' ) == "" || new_password_confirm.replace( blank_pattern, '' ) == ""){
		    alert("패스워드에 공백만 입력되었습니다");
		}
	


		if(new_password == 'null' || new_password =='(null)'){
			alert("부적절한 비밀번호 입니다.");
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
			});		
		}else if(password_check == 'fail'){
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
				success: function(result){
					if(result=="fail"){
						return;
					}
				},
			});		
		}else if(new_password!=new_password_confirm){
			alert("비밀번호가 일치하지 않습니다.");
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
				success: function(result){
					if(result=="fail"){
						

						return;
					}
				},
			});		
		}else if(new_password==curr_password){
			alert("이전 비밀번호와 같습니다.");
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
				success: function(result){
					if(result=="fail"){
						return;
					}
				},
			});		
		}
		else if(new_password.length < 8)
		{
			alert("비밀번호 8자리 이상 입력해 주세요");
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
				success: function(result){
					if(result=="fail"){
						return;
					}
				},
			});		
		}else if(new_password == "" || new_password_confirm == "")
		{
			alert("비밀번호를 입력해 주세요");
			$.ajax({
				method: "POST",
				url: "/tupyo/m_update_password_log",
				async: false,
				success: function(result){
					if(result=="fail"){
						return;
					}
				},
			});		
		}else if($("#errornot").html() != ""){
			alert("현재 비밀번호를 확인해 주세요");
		}

		else{
			$.post("/tupyo/m_confirm_password", {

				password: new_password,

			})
			.done(function(msg){
					alert("변경 완료.");
					location.href = "/tupyo";
			});

		}
	}
	
	</script>
</body>
</html>