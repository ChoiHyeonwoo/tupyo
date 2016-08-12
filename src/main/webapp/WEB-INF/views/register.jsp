<%@ page session="false" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script>
	<% 
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");
	String pk_id = (String)session.getAttribute("pk_id");
	String name= (String)session.getAttribute("name");
	%>
	
	var id ='<%=id%>';
	
	if(id=='null'){	
		alert("�α��� ���� �ϼ���");
		location.href = "/tupyo";
}
	
</script>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>��ǥ����</td>
			<td><input type="text" id="poll_title" /></td>
		</tr>
		<tr>
			<td>�ߺ� ����</td>
			<td width="100"><input type="radio" name="repoll" value="yes" checked="checked" /> ��� </td>
			<td width="100"><input type="radio" name="repoll" value="no" />�Ұ�</td>
		</tr>
		<tr>
			<td>���� ��ǥ ����</td>
			<td width="100"><input type="radio" name="multi" value="yes" checked="checked" /> ��� </td>
			<td width="100"><input type="radio" name="multi" value="no" />�Ұ�</td>
		</tr>
		<tr>
			<td><input type="button" onclick="add_html()" value="�׸��߰�" /></td>
			<td><input type="button" onclick="delete_html()" value="�׸�����" /></td>
		</tr>
	</table>
	<div class="items">
		<input type="text" id="items0"/><br />
		<input type="text" id="items1"/><br />
	</div>

	<input type="button" value="��ǥ���" onclick="reg_poll()" />
	<script>
		var item_number = 2;
		function add_html(){
			$("div").append('<input type="text" id="items'+item_number+'"/> <br id="br'+item_number+'" />');
			item_number++;
		}
		function delete_html(){
			if(item_number<=2){
				alert("���̻� ���� �� �����ϴ�.");
				return;
			}
			item_number--;
			$("#items"+item_number).remove();
			$("#br"+item_number).remove();
		}

		function reg_poll(){
			var blank_pattern = /^\s+|\s+$/g;
			
			var poll_title = $("#poll_title").val();
			var writer = "<%=name%>";
			var duplicated = $(':radio[name="repoll"]:checked').val();
			var multi = $(':radio[name="multi"]:checked').val();
			var item_arr = new Array();
			
			
			if(poll_title==""){
				alert("��ǥ������ �ۼ����ּ���");
			}else if( poll_title.replace( blank_pattern, '' ) == "" ){
			    alert("��ǥ���� ���鸸 �ԷµǾ����ϴ�");
			}
			else if(duplicated ==""){
				alert("�ߺ����θ� �������ּ���");
			}else if(multi ==""){
				alert("���� ��ǥ ���θ� �������ּ���");
			}
			else if(item_arr == {}){
				alert("�׸��� �Է��� �ּ���.");
			}else if(item_arr != {}){
				for (var i = 0; i < item_number; i++){	
					if($("#items"+i+"").val()==""){
						alert("�׸��� �Է��� �ּ���.");
						return;
					}else if( $("#items"+i+"").val().replace( blank_pattern, '' ) == "" ){
					    alert("��ǥ�׸� ���鸸 �ԷµǾ����ϴ�");
					    return;
					}else{
						item_arr.push($("#items"+i+"").val());
					}
				}
			}
			else{
				$.ajaxSettings.traditional = true;
				
				$.post("/tupyo/register_confirm", {
					"title": poll_title,
					"writer": writer,
					"duplicated": duplicated,
					"multi": multi,
					"item_number": item_number,
					"item_arr[]": item_arr
				})
				.done(function(msg){
						alert("��ǥ��� �Ϸ�.");
						location.href = "/tupyo";
					})
				.fail(function(xhr, status, error){
					// error handling
					alert("���� �߻�. ����Ŀ� �õ����ּ���.");
				});
			}
		}
	</script>

</body>
</html>
