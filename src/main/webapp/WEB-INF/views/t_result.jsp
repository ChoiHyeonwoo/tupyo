<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
	String pk_id = (String)session.getAttribute("pk_id");
	String name= (String)session.getAttribute("name");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>	
<script>
	var id = '<%=id%>';
	if (id == 'null')
		{
			alert("로그인 후에 이용바랍니다.");
			location.href = "/tupyo";
		}
 	function tupyo_delete(){
 		if(id != '${writer_id}'){
 			alert("투표 등록자만 삭제할 수 있습니다.");
 			return;
 		}
 		
 		if(confirm("정말로 삭제 하시겠습니까?")){
 			$.post("/tupyo/t_delete",{
 				t_id : ${t_id}
 			})
 			.done(function(data) {
 			    if(data =="success"){
 	 				alert( "삭제가 완료되었습니다." );
 	 			    location.href="/tupyo"
 			    }
 			});
 		}else{
 			return;
 		}
 	}
 	function tupyo_update(){
 		var t_id = '${t_id}';
 		if(id != '${writer_id}'){
 			alert("투표 등록자만 수정할 수 있습니다.");
 			return;
 		}
 		
 		 var form = $('<form></form>');
 	     form.attr('action', "/tupyo/t_update");
 	     form.attr('method', 'post');
 	     form.appendTo('body');
 	     
 	     var idx = $('<input type="hidden" value="${t_id}" name="t_id">');
 
 	     form.append(idx);
 	     form.submit();
 		
//        $.get("/tupyo/t_update", {

  //          	t_id: t_id,
 //        
  //         success: function() {
  //         	location.href="/tupyo/t_update"
  //         }
 //      });
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
	<c:if test="${empty mtcns}">
		<tr>
			<td style="text-align:center; height:80"  colspan="2">기록이 없습니다.</td>
		</tr>       
    </c:if>
	<c:forEach items="${mtcns}" var="dto">
	<tr>
		<td>${dto.select_content}</td>
		<td>${dto.select_num}</td>
	</tr>
	</c:forEach>

</table>

<br />

	<a href = "javascript:history.back()" >돌아가기</a>&nbsp;&nbsp;<a href = "/tupyo" >리스트로 가기</a>&nbsp;&nbsp;<a href = "javascript:tupyo_delete()" >투표 삭제</a>&nbsp;&nbsp;<a href = "javascript:tupyo_update()" >투표 수정</a>

</body>
</html>