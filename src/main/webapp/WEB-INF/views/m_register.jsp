<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>���̵� </td>
			<td><input type = "text" id="logid"/></td>
			<td><input type="button" value="�ߺ�Ȯ��" onclick="id_check()"></td>
			<td><a id="id_check_result"></a></td>
		</tr>
		<tr>
			<td>��й�ȣ </td>
			<td><input type = "password" id="password"/></td>
		</tr>
		<tr>
			<td>��й�ȣ Ȯ��</td>
			<td><input type = "password" id="password_confirm"/></td>
		</tr>
		<tr>
			<td>�̸�</td>
			<td><input type = "text" id="name"/></td> 
		</tr>
	</table>
		<input type="button" onclick="member_reg()" value="ȸ������" /> <input type="button" value="���" onclick="cancel()"/>
<script>
	function cancel(){
		location.href = "/tupyo";		
	}
	function id_check(){
		var logid = $("#logid").val();
		if(logid == 'null' || logid =='(null)'){
			alert("�������� ���̵� �Դϴ�.");
			return;
		}
		
		var blank_pattern = /^\s+|\s+$/g;
		if( logid.replace( blank_pattern, '' ) == "" ){
		    alert("���̵� ���鸸 �ԷµǾ����ϴ�");
		    return;
		}
			$.ajax({
				method: "POST",
				url: "/tupyo/m_check_id",
				data: {
					id: logid
				},
				success: function(result){

					if(result=="fail"){
						$("#id_check_result").html("���Ұ��� ���̵� �Դϴ�. �ٸ� ���̵� �Է��� �ּ���.");
					}
					else{
						$("#id_check_result").html("��밡���� ���̵� �Դϴ�.");
					}
				},
			});
		}
		
		function member_reg(){
			var blank_pattern = /^\s+|\s+$/g;
			var id = $("#logid").val();
			if(id == 'null' || id =='(null)'){
				alert("�������� ���̵� �Դϴ�.");
				return;
			}
			var id_check = '';
			$.ajax({
				method: "POST",
				async: false,
				url: "/tupyo/m_check_id",
				data: {
					id: id
				},
				success: function(result){
					if(result=="fail"){
						id_check='fail';
						alert("���̵� �ߺ�üũ�� Ȯ���� �ּ���.");
						return;
					}
				}
			});

			var password = $("#password").val();
			var password_confirm = $("#password_confirm").val();
			var name = $("#name").val();
			if(password == 'null' || password =='(null)'){
				alert("�������� ��й�ȣ �Դϴ�.");
				return;
			}if(name == 'null' || name =='(null)'){
				alert("�������� �̸� �Դϴ�.");
				return;
			}
			
			if(id == ""){
				alert("���̵� �Է��� �ּ���");
			}else if( id.replace( blank_pattern, '' ) == "" ){
			    alert("���̵� ���鸸 �ԷµǾ����ϴ�");
			}else if( password.replace( blank_pattern, '' ) == "" || password_confirm.replace( blank_pattern, '' ) == ""){
			    alert("�н����忡 ���鸸 �ԷµǾ����ϴ�");
			}else if(password == "" || password_confirm == "")
			{
				alert("��й�ȣ�� �Է��� �ּ���");
			}else if(name == ""){
				alert("�̸��� �Է��� �ּ���.");
			}
			else if(id_check == 'fail'){
				return;
			}
			else if(password.length < 8)
			{
				alert("��й�ȣ 8�ڸ� �̻� �Է��� �ּ���");
			}
			else if(id.length < 8)
			{
				alert("���̵�� 8�ڸ� �̻� �Է��� �ּ���");
			}
			else if(password!=password_confirm){
				alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}else if( name.replace( blank_pattern, '' ) == "" ){
			    alert("�н����忡 ���鸸 �ԷµǾ����ϴ�");
			}
			else if($("#id_check_result").html()=="" || $("#id_check_result").html()=="���Ұ��� ���̵� �Դϴ�. �ٸ� ���̵� �Է��� �ּ���."){
				alert("���̵� �ߺ�üũ�� Ȯ���� �ּ���.");
			}
			else{
				$.post("/tupyo/m_confirm", {
					id: id,
					password: password,
					name: name
				})
				.done(function(msg){
						alert("���ԿϷ�. �α��� ���ֽñ� �ٶ��ϴ�.");
						location.href = "/tupyo";
				})

			}
			
	}
</script>
</body>
</html>