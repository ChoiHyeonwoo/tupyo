<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<% 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pk_id = (String)session.getAttribute("pk_id");
		String name= (String)session.getAttribute("name");

	%>	
	<script>
		var id = '<%=id%>';
		if (id == 'null')
			{
				alert("로그인 후에 이용바랍니다.");
				location.href = "/tupyo";
			}
		 
	</script>
	<style>
  table {
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
</style>
</head>
<body>
 <h1>투표 현황</h1>  
	<h3>${t_title}</h3>
	 <table>
 <tr>
 <td>투표 항목</td>
 <td>득표수</td>
 
 </tr>
	<c:forEach items="${tidtos}" var="dto">
<tr>
	<td>${dto.t_item_content}</td>
	<td>${dto.t_item_selected}</td>
</tr>
	</c:forEach>

	</table>

 <br />
 <br />
 <br />
 <h3>나의 투표 기록</h3>   
 <table>
	 <tr>
		 <td>내가 투표한 항목</td>
		 <td>내가 투표한 횟수</td>
	 </tr>

	<c:forEach items="${mtcns}" var="dto">
	<tr>
		<td>${dto.select_content}</td>
		<td>${dto.select_num}</td>
	</tr>
	</c:forEach>

</table>
	<c:if test="${empty mtcns}">
         <h3>투표한 기록이 없습니다.</h3>
    </c:if>

<br />
	<a href = "/tupyo" >돌아가기</a>

</body>
</html>