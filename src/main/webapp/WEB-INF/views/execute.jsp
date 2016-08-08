<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	<% 
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
	String pk_id = (String)session.getAttribute("pk_id");
	String name= (String)session.getAttribute("name");
	
	out.println(id);
	out.println(name);
	out.println(pk_id);
	%>
	
	var id =<%=id%>;

	if(id==null){	
		alert("로그인 먼저 하세요");
		location.href = "/tupyo";
}
	
</script>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
</head>


<body>
	execute.jsp page
	<br/>

	<br/>

	<input type="radio" name="survay" value="agree" /> 찬성 <br />
	<input type="radio" name="survay" value="disagree" /> 반대 <br />
	<input type="button" value ="결과 확인" onclick="result_get()" />


	<script>
		function result_get(){
			var survay = $(':radio[name="survay"]:checked').val();
			$.ajax({
				method: "GET",
				url: "/tupyo/result",		
				data:{ 
					id: ${id},
					survay: survay
				},
			success: function(e){

				var result = e.split(',');

				if(result[0]=="available" || result[1]=="yes"){
					alert("투표 완료");
					location.href ="/tupyo";
				}else{
					alert("이미 투표한 항목입니다.");
				}
				}
			});

		}
	</script>
</body>

</html>