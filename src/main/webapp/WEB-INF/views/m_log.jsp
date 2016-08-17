<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
  table {
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #bcbcbc;
    padding: 5px 10px;
  }
  </style>
  <script>
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  window.location = "/tupyo";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);
	
  </script>
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
		
	<a href="/tupyo/">돌아가기</a>
</body>
</html>