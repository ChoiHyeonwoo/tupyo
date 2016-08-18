<%@ page session = "false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<script>
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  	window.location = "/tupyo/admin_login";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);

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
	<table>
	<tr>
		<td>로그 일자</td>
		<td>로그 내용</td>
		<td>ip 주소</td>
	</tr>
		<c:forEach items="${mldtos}" var="dto">
	<tr>
		<td>${dto.log_date}</td>
		<td>${dto.log_content}</td>
		<td>${dto.ip_address}</td>
	</tr>
		</c:forEach>
	</table>
		
	<a href="/tupyo/admin_main">홈으로</a>&nbsp;&nbsp;<a href="javascript:history.back()">이전 으로</a>
</body>
</html>