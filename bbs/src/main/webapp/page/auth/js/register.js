function register() {
	var errorMsg = $("#error_message");
	var email = $("#email").val();
	var name = $("#name").val();
	var password = $("#password").val();
	var code = $("#code").val();

	if (email.length == 0) {
		errorMsg.html("邮箱不能为空");
		return;
	}
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!reg.test(email)) {
		errorMsg.html("邮箱格式不正确");
		return;
	}
	if (name.length == 0) {
		errorMsg.html("昵称不能为空");
		return;
	}
	if (name.length > 10) {
		errorMsg.html("昵称长度长度不能大于10");
		return;
	}
	if (password.length == 0) {
		errorMsg.html("密码不能为空");
		return;
	}
	if (password.length < 6 || password.length > 11) {
		errorMsg.html("密码长度应为6到11个字符");
		return;
	}
	if (code.length == 0) {
		errorMsg.html("验证码不能为空");
		return;
	}

	var form = $("#registerForm");
	form.submit();
}

function sendEmail(){
	var email = $("#email").val();
	$.ajax({
		type : 'POST',
		url : ctx + '/sendEmailCode.do',
		data : {
			email : email
		},
		dataType : 'json',
		timeout : 30000,
		context : $('body'),
		success : function(data) {
			if (data.code == "ok") {
				alert('验证码已经发送到您的手机，请注意查收!');
			} else {
				alert(data.msg);
			}
		},
		error : function(xhr, type) {
			alert('提交失败!');
		}
	});
}
