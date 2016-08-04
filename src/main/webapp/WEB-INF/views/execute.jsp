<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
</head>
<body>
	execute.jsp page
	<script>
		alert(${id});
	</script>
	<br/>

	<br/>

	<input type="radio" name="survay" value="agree"> 찬성 <br/>
	<input type="radio" name="survay" value="disagree"> 반대 <br/>
	<input type="button" value ="결과 확인" onclick="result_get()">

	
	<script>
	
		function result_get(){
			var survay = $(':radio[name="survay"]:checked').val();
			$.get("/tupyo/result", {
				id: ${id},
				survay: survay
			});
			location.href ="/tupyo";

		}
	</script>
</body>

</html>