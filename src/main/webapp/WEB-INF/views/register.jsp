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
	
	out.println(id);
	out.println(name);
	out.println(pk_id);
	%>
	
	var id =<%=id%>;
	
	if(id==null){	
		alert("�α��� ���� �ϼ���");
		location.href = "/tupyo";
}
	
</script>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<title>Insert title here</title>
</head>
<body>
	register.jsp
	<br />
	
	��ǥ���� : <input type="text" id="poll_title" /><br />
	�ߺ� ����: <input type="radio" name="repoll" value="yes" checked="checked" /> ��� <input type="radio" name="repoll" value="no" />�Ұ�<br />
	���� ��ǥ ����: <input type="radio" name="multi" value="yes" checked="checked" /> ��� <input type="radio" name="multi" value="no" />�Ұ�<br />
	<input type="button" onclick="add_html()" value="�׸��߰�" />&nbsp;&nbsp;<input type="button" onclick="delete_html()" value="�׸�����" />
	<div class="items">
		<input type="text" id="items0"/><input type="text" id="items1"/>
	</div>
	<input type="button" value="�׸�Ȯ��" onclick="check_item()" />
	<input type="button" value="��ǥ���" onclick="reg_poll()" />
	<script>
		var item_number = 2;
		function add_html(){
			$("div").append('<input type="text" id="items'+item_number+'"/>');
			item_number++;
		}
		function delete_html(){
			if(item_number<=2){
				alert("���̻� ���� �� �����ϴ�.");
				return;
			}
			item_number--;
			$("#items"+item_number).remove();
			
		}

		function reg_poll(){
			var poll_title = $("#poll_title").val();
			var writer = "<%=name%>";
			var duplicated = $(':radio[name="repoll"]:checked').val();
			var multi = $(':radio[name="multi"]:checked').val();
			var item_arr = new Array();
			for (var i = 0; i < item_number; i++){	
				item_arr.push($("#items"+i+"").val());
				if($("#items"+i+"").val()==""){
					alert("�׸��� �ۼ��� �ּ���.");
					return;
				}
			}
			if(poll_title ==""){
				alert("��ǥ������ �ۼ����ּ���");
			}else if(duplicated ==""){
				alert("�ߺ����θ� �������ּ���");
			}else if(multi ==""){
				alert("���� ��ǥ ���θ� �������ּ���");
			}else if(item_arr == {}){
				alert("�׸��� �߰��� �ּ���.");
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
					alert("error");
				});
			}
		}
	</script>

</body>
</html>
