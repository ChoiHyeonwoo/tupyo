<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js" integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s=" crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

</head>
<body>

<table>
	<tr>
		<td>���̵�</td>
		<td><input type= "text" id="logid" /></td>
	</tr>
	<tr>
		<td>�н�����</td>
		<td><input type= "password" id="logpw" /></td>
	</tr>
</table>

	<input type="button" onclick="formCHK()" value="�α���" /><a href = "/tupyo">���</a>

	<script>
	function formCHK() {
			var id = $("#logid").val();
			var password = $("#logpw").val();
			
			if(id==""){
				alert("���̵� �Է��� �ּ���.");
				
			}else if(password==""){
				alert("��й�ȣ�� �Է��� �ּ���");
				
			}else{
				$.ajax({
					method: "POST",
					url: "/tupyo/m_check",
					data: {
						logid: id,
						logpw: password
					},
					success: function(result){
						if(result=="fail"){
							alert("�α��� ����. ���̵�� �н����带 Ȯ���ϼ���.");
							return;
						}else if(result=="drop"){
							alert("Ż���� �����Դϴ�.");
							return;
						}else{
							alert("�α��� ����");
							location.href="/tupyo";
						}
					}
				});
			}
	}

	</script>
	
</body>
</html>