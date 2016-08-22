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
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<title>Insert title here</title>
<script>
	var grade = "<%=grade %>";
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  	window.location = "/tupyo/admin_login";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);

	window.onload = function(){
		$("#authority").val("${mdto.grade}");
		if(grade != "1"){
			$("#authority").not(":selected").attr("disabled", "disabled");
		}
		if(grade == "3"){
			  $("#name").attr("readonly",true);
			  $("#password").attr("readonly",true);
			  $("#password_confirm").attr("readonly",true);
		}
		
	}
	function info_modify(){
		if(grade=="3"){
			alert("수정 처리 권한이 없습니다.");
			return;
		}
		var pk_id = "${mdto.pk_mid}";
		var id = "${mdto.id}";
		
		var name = $("#name").val();

		var authority = $("#authority").val();
		
		var blank_pattern = /^\s+|\s+$/g;
		if(name == 'null' || name =='(null)'){
			alert("부적절한 이름 입니다.");
			return;
		}
		
		if(name == ""){
			alert("이름을 입력해 주세요.");
			$("#name").focus();
		}
		else if( name.replace( blank_pattern, '' ) == "" ){
		    alert("이름에 공백만 입력되었습니다");
		    $("#name").focus();
		}
		else{
			$.post("/tupyo/admin_info_update", {
				pk_id: pk_id,
				id : id,
				name: name,
				grade : authority
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
	function user_drop(){
		if(grade=="3"){
			alert("탈퇴 처리 권한이 없습니다.");
			return;
		}
		
		if (confirm("정말 탈퇴 처리 하시겠습니까??") == true){    
			var pk_id = "${mdto.pk_mid}";
			var id = "${mdto.id}";
			
			$.post("/tupyo/admin_user_drop", {
				pk_id: pk_id
			})
			.done(function(msg){
					alert("탈퇴 처리 완료.");
					location.href = "/tupyo/admin_main";
				})
			.fail(function(xhr, status, error){
				// error handling
				alert("에러 발생. 잠시후에 시도하세요.");
			});
		}else{   //취소
		    return;
		}
		
	}
	function log_view(){
		window.open()
		 var form = $('<form></form>');
 	     form.attr('action', "/tupyo/admin_log_view");
 	     form.attr('method', 'post');
 	     form.appendTo('body');
 	     
 	     var hidden = $('<input type="hidden" value="${mdto.id}" name="id">');
 
 	     form.append(hidden);
 	     form.submit();
	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td>
			<td>${mdto.id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" id ="name" name="name" value="${mdto.name}" /></td>
		</tr>
		<tr>
			<td>권한</td>
			<td>
				<select name="authority" id ="authority">
					<option value="1">최고 관리자</option>
					<option value="2">중간 관리자</option>
					<option value="3">하위 관리자</option>
					<option value="9">일반 사용자</option>
				</select>
			</td>
		</tr>
	</table>
 <input type = "button" value ="수정" onclick = "info_modify()"/>&nbsp;&nbsp;<input type = "button" value ="탈퇴" onclick="user_drop()" />&nbsp;&nbsp;<input type = "button" onclick="log_view()" value ="로그보기" />
</body>
</html>