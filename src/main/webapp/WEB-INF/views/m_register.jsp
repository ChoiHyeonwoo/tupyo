<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		���̵� : <input type = "text" id="id"/> <br />
		��й�ȣ : <input type = "password" id="password"/><br /> 
		��й�ȣ Ȯ�� : <input type = "password" id="password_confirm"/><br />
		�̸�: <input type = "text" id="name"/> <br />
		<input type="button" onclick="member_reg()" value="ȸ������" /> <input type="button" value="���" />
<script>
	// �ٽ� ���� 
		function member_reg(){
		
			var id = $('#id').val();
			var password = $('#password').val();
			var password_confirm = $('#password_confirm').val();
			var name = $('#name').val();
						
			$.post("/tupyo/m_confirm", {
				id: id,
				password: password,
				name: name
			});
			alert("���� ����. �α����� ���ּ���.");
			location.href ="/tupyo";
		}
</script>
</body>
</html>