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
		���̵� : <input type = "text" id="logid"/> <input type="button" value="�ߺ�Ȯ��" onclick="id_check()"><a id="id_check_result"></a> <br /> 
		��й�ȣ : <input type = "password" id="password"/><br /> 
		��й�ȣ Ȯ�� : <input type = "password" id="password_confirm"/><br />
		�̸�: <input type = "text" id="name"/> <br />
		<input type="button" onclick="member_reg()" value="ȸ������" /> <input type="button" value="���" />
<script>
	// �ٽ� ���� 
		function id_check(){
		var logid = $("#logid").val();
		alert(logid);
			$.ajax({
				method: "POST",
				url: "/tupyo/m_check_id",
				data: {
					id: logid
				},
				success: function(result){
					alert(result);
					if(result=="fail"){
						$("#id_check_result").html("���Ұ��� ���̵� �Դϴ�. �ٸ� ���̵� �Է��� �ּ���.");
					}
					else{
						$("#id_check_result").html("��밡���� ���̵� �Դϴ�.");
					}
				},
				//beforeSend:showRequest,
						
			});
		}
		
		function member_reg(){

			var id = $("#logid").val();
			var password = $("#password").val();
			var password_confirm = $("#password_confirm").val();
			var name = $("#name").val();
			
			if(id == ""){
				alert("���̵� �Է��� �ּ���");
			}else if(password == "" || password_confirm == "")
			{
				alert("��й�ȣ�� �Է��� �ּ���");
			}
			else if(name == ""){
				alert("�̸��� �Է��� �ּ���.");
			}
			else if(password!=password_confirm){
				alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
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