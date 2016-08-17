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
</head>
<body>
	<table>
		<tr>
			<td>투표제목</td>
			<td><input type="text" id="poll_title" /></td>
		</tr>
		<tr>
			<td>중복 여부</td>
			<td width="100"><input type="radio" name="repoll" value="yes" checked="checked" /> 허용 </td>
			<td width="100"><input type="radio" name="repoll" value="no" />불가</td>
		</tr>
		<tr>
			<td>다중 투표 여부</td>
			<td width="100"><input type="radio" name="multi" value="yes" checked="checked" /> 허용 </td>
			<td width="100"><input type="radio" name="multi" value="no" />불가</td>
		</tr>
		<tr>
			<td><input type="button" onclick="add_html()" value="항목추가" /></td>
			<td><input type="button" onclick="delete_html()" value="항목제거" /></td>
		</tr>
	</table>
	<div class="items">

	</div>

	<input type="button" value="투표등록" onclick="reg_poll()" />
<script>
	function expireSession()
	{
		alert("세션만료. 로그인을 다시 해주세요.");
	  window.location = "/tupyo";
	}
	setTimeout('expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %>);


		var item_number = ${tdto.item_number};

		$("#poll_title").val("${tdto.title}");
		$("input:radio[name='repoll']:radio[value='${tdto.is_duplicated}']").attr("checked",true);
		$("input:radio[name='multi']:radio[value='${tdto.is_multi_check}']").attr("checked",true);
		var i = 0;
		<c:forEach items="${tidtos}" var="dto"> 
			$("div").append('<input type="text" id="items'+i+'" value="${dto.t_item_content}" /> <br id="br'+i+'" />');
			i++;
		</c:forEach>
			item_number = i;
		
		function add_html(){
			$("div").append('<input type="text" id="items'+item_number+'"/> <br id="br'+item_number+'" />');
			item_number++;
		}
		function delete_html(){
			if(item_number<=2){
				alert("더이상 지울 수 없습니다.");
				return;
			}
			item_number--;
			$("#items"+item_number).remove();
			$("#br"+item_number).remove();
		}

		function reg_poll(){
			var blank_pattern = /^\s+|\s+$/g;
			
			var poll_title = $("#poll_title").val();

			var duplicated = $(':radio[name="repoll"]:checked').val();
			var multi = $(':radio[name="multi"]:checked').val();
			var item_arr = new Array();

			
			if(poll_title==""){
				alert("투표제목을 작성해주세요");
			}else if( poll_title.replace( blank_pattern, '' ) == "" ){
			    alert("투표제목에 공백만 입력되었습니다");
			}
			else if(duplicated ==""){
				alert("중복여부를 선택해주세요");
			}else if(multi ==""){
				alert("다중 투표 여부를 선택해주세요");
			}
			else if(item_arr == {}){
				alert("항목을 입력해 주세요.");
			}
			else{
	
				for (var i = 0; i < item_number; i++){	
					if($("#items"+i+"").val()==""){
						alert("항목을 입력해 주세요.");
						return;
					}else if( $("#items"+i+"").val().replace( blank_pattern, '' ) == "" ){
					    alert("투표항목에 공백만 입력되었습니다");
					    return;
					}else{
						item_arr.push($("#items"+i+"").val());
					}
				}
				
				$.ajaxSettings.traditional = true;
				
				$.post("/tupyo/update_confirm", {
					"t_id": "${t_id}",
					"title": poll_title,
					"duplicated": duplicated,
					"multi": multi,
					"item_number": item_number,
					"item_arr[]": item_arr
				})
				.done(function(msg){
						alert("투표수정 완료.");
						location.href = "/tupyo";
					})
				.fail(function(xhr, status, error){
					// error handling
					alert("에러 발생. 잠시후에 시도해주세요.");
				});
			}
		}
	</script>
</body>
</html>