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

	%>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td>
			<td><input type = "text" id="logid" value="<%=id %>" readonly/></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type = "text" id="name" value="<%=name %>"/></td>
		</tr>
	</table>
		<input type="button" onclick="member_update();"  value="수정" /> <a href="/tupyo">취소</a>

	
	<script>
		function member_update(){
			var id = $("#logid").val();
			var name = $("#name").val();
			
			if(name == 'null' || name =='(null)'){
				alert("부적절한 이름 입니다.");
				return;
			}
			var blank_pattern = /^\s+|\s+$/g;
			if( name.replace( blank_pattern, '' ) == "" ){
			    alert("이름에 공백만 입력되었습니다");
			    return;
			}
			if(id == ""){
				alert("아이디를 입력해 주세요");
			}
			else if(name == ""){
				alert("이름을 입력해 주세요.");
			}
			else{
				$.post("/tupyo/m_update_complete", {
					id: id,
					name: name
				})
				.done(function(msg){
						alert("수정 완료.");
						location.href = "/tupyo";
					})
				.fail(function(xhr, status, error){
					// error handling
					alert("에러 발생. 잠시후에 시도하세요.");
				});
			}
		}
	</script>
	
</body>
</html>