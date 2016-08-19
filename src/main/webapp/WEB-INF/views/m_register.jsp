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
	<table>
		<tr>
			<td>아이디 </td>
			<td><input type = "text" id="logid"/></td>
			<td><input type="button" value="중복확인" onclick="id_check()" /></td>
			<td><a id="id_check_result"></a></td>
		</tr>
		<tr>
			<td>비밀번호 </td>
			<td><input type = "password" id="password"/></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type = "password" id="password_confirm"/></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type = "text" id="name"/></td> 
		</tr>
	</table>
		<input type="button" onclick="member_reg()" value="회원가입" /> <input type="button" value="취소" onclick="cancel()"/>
<script>
	var temp_check_id = "";
	
	function cancel(){
		location.href = "/tupyo";		
	}
	function id_check(){
		var logid = $("#logid").val();
		var blank_pattern = /^\s+|\s+$/g;
		
		if(logid == 'null' || logid =='(null)'){
			$("#id_check_result").html("사용불가한 아이디 입니다. 다른 아이디 입력해 주세요.");
			$("#logid").val("");
			$("#logid").focus();
			return;
		}else if(logid == ""){
			$("#id_check_result").html("아이디를 입력해 주세요.");
			$("#logid").focus();
			return;
		}
		else if(logid.length < 8)
		{
			$("#id_check_result").html("8자리 이상 입력해주세요");
			$("#logid").val("");
			$("#logid").focus();
			return;
		}else if( logid.replace( blank_pattern, '' ) == "" ){
			$("#id_check_result").html("공백만 입력되었습니다.");
			$("#logid").val("");
		    $("#logid").focus();
		    return;
		}
	
		
		$.ajax({
			method: "POST",
			url: "/tupyo/m_check_id",
			data: {
				id: logid
			},
			success: function(result){

				if(result=="fail"){
					$("#id_check_result").html("사용불가한 아이디 입니다. 다른 아이디 입력해 주세요.");
					$("#logid").val("");
					$("#logid").focus();
				}
				else{
					$("#id_check_result").html("사용가능한 아이디 입니다.");
					temp_check_id = logid;
				}
			}
		});
	}
		
		function member_reg(){
			var blank_pattern = /^\s+|\s+$/g;
			var id = $("#logid").val();
			if(id == 'null' || id =='(null)'){
				alert("부적절한 아이디 입니다.");
				$("#logid").val("");
				$("#logid").focus();
				return;
			}
			var id_check = '';
			$.ajax({
				method: "POST",
				async: false,
				url: "/tupyo/m_check_id",
				data: {
					id: id
				},
				success: function(result){
					if(result=="fail"){
						id_check='fail';
						$("#id_check_result").html("사용불가한 아이디 입니다. 다른 아이디 입력해 주세요.");
						$("#logid").val("");
						$("#logid").focus();
						return;
					}
				}
			});

			var password = $("#password").val();
			var password_confirm = $("#password_confirm").val();
			var name = $("#name").val();
			if(password == 'null' || password =='(null)'){
				$("#password").val("");
				alert("부적절한 비밀번호 입니다.");
				$("#password").focus();
				return;
			}if(name == 'null' || name =='(null)'){
				
				alert("부적절한 이름 입니다.");
				$("#name").val("");
				$("#name").focus();
				return;
			}

		//	if(id == ""){
			//	alert("아이디를 입력해 주세요");
		//		$("#logid").focus();
		//	}else if( id.replace( blank_pattern, '' ) == "" ){
		//	    alert("아이디에 공백만 입력되었습니다");
		//		$("#logid").focus();
		//	}
			else if(temp_check_id != id || id_check == 'fail' || $("#id_check_result").html()!="사용가능한 아이디 입니다." )  {
				$("#id_check_result").html("중복체크 확인 바랍니다.");
				$("#logid").focus();
			}
		//	else if(id.length < 8)
		//	{
		//		alert("아이디는 8자리 이상 입력해 주세요");
		//		$("#logid").focus();
		//	}
			else if(id_check == 'fail'){
				return;
			}
			else if(password == "")
			{
				alert("비밀번호를 입력해 주세요");
				$("#password").focus();
			}
			else if( password.replace( blank_pattern, '' ) == "" ){
			    alert("비밀번호에 공백만 입력되었습니다");
			    $("#password").val("");
				$("#password").focus();
			}
			else if(password.length < 8)
			{
				alert("비밀번호 8자리 이상 입력해 주세요");
				$("#password").val("");
				$("#password").focus();
			}
			else if(password_confirm == "")
			{
				alert("비밀번호 확인을 입력해 주세요");
				$("#password_confirm").focus();
			}else if(password_confirm.replace( blank_pattern, '' ) == ""){
				alert("패스워드 확인에 공백만 입력되었습니다");
				$("#password").val("");
				$("#password_confirm").focus();
			}
			else if(password!=password_confirm){
				alert("비밀번호가 일치하지 않습니다.");
				$("#password").val("");
				$("#password_confirm").val("");
				$("#password").focus();
			}
			else if(name == ""){
				alert("이름을 입력해 주세요.");
				$("#name").val("");
				$("#name").focus();
			}
			
			else if( name.replace( blank_pattern, '' ) == "" ){
			    alert("이름에 공백만 입력되었습니다");
			    $("#name").val("");
			    $("#name").focus();
			}
			else{
				$.post("/tupyo/m_confirm", {
					id: id,
					password: password,
					name: name
				})
				.done(function(msg){
						alert("가입완료. 로그인 해주시기 바랍니다.");
						location.href = "/tupyo";
				})
			}
			
	}
</script>
</body>
</html>