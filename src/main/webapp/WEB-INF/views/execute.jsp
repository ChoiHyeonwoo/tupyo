<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "java.util.ArrayList" %>
<%@ page import ="com.ex.tupyo.TupyoItemDTO" %>
<%@ page import="org.json.JSONArray" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>

	<% 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pk_id = (String)session.getAttribute("pk_id");
		String name= (String)session.getAttribute("name");

		out.println(id);
		out.println(name);
		out.println(pk_id);
	%>
<script>
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
		투표 제목 : ${tdto.title}<br />
		투표 작성자 : ${tdto.writer}<br />
		투표 중복허용 여부 : ${tdto.is_duplicated}<br />
		투표 다중선택 허용 여부 : ${tdto.is_multi_check}<br />
		투표 등록 날짜 : ${tdto.reg_date}<br />
	<br/>
	<div>
	
	</div>	
	<input type="button" id="result" value ="결과 확인" onclick="result_get()" />
	<br/>


	<script>
		<c:set var="is_multi_check" value="${tdto.is_multi_check}" />
			<c:choose>
			<c:when test="${is_multi_check == 'yes'}">
			<c:forEach items="${tidtos}" var="info">
				
				$("div").append('<input type="checkbox" name="survay" value="${info.t_item_content}" />${info.t_item_content} 현재 득표 : ${info.t_item_selected} 표');
				$("#result").attr('onclick', "result_set()");
			</c:forEach>

			
			</c:when>
			<c:otherwise>
				<c:forEach items="${tidtos}" var="info">
				
					$("div").append('<input type="radio" name="survay" value="${info.t_item_content}" />${info.t_item_content} 현재 득표 : ${info.t_item_selected} 표');
			
				</c:forEach>

			</c:otherwise>
			</c:choose>
			function result_set(){

			
				 var chked_val = new Array();
				  $(":checkbox[name='survay']:checked").each(function(pi,po){
				    chked_val.push(po.value);
				  });
				  
				  
				$.ajaxSettings.traditional = true;
				$.ajax({
					method: "POST",
					url: "/tupyo/result_multi",		
					data:{ 
						"id": ${id},
						"item_arr[]": chked_val
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