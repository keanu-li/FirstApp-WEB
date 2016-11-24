<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/page/auth/css/register.css" />
<script type="text/javascript" src="${ctx}/page/auth/js/register.js"></script>
<title>注册</title>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
        					<a href="${ctx}">主页</a> / 注册
      					</div>
      					<div class="panel-body">
      					    <form id="registerForm" class="m-t" role="form" method="post" action="${ctx}/register">
                				<div class="form-group">
                   					 <input id="email" name="email" type="email" class="form-control" placeholder="请输入邮箱" required="">
                				     <span class="input-group-btn"><button class="btn btn-raised btn-default" type="button" id="send_email_btn" onclick="sendEmail()">发送验证码</button></span>
                				</div>
                				<div class="form-group">
                   					 <input id="name" name="name" type="text" class="form-control" placeholder="请输入昵称">
                				</div>
                				<div class="form-group">
                    				<input id="password" name="password" type="password" class="form-control" placeholder="请输入密码">
                				</div>
                				<div class="form-group">
                   					 <input id="code" name="code" type="text" class="form-control" placeholder="请输入验证码">
                				</div>
                				<button type="button" class="btn btn-default" onclick="register();">注册</button>
            					<span id="error_message"></span>
            				</form>
      					</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>