<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/page/include/css/topicItem.css" />
<link rel="stylesheet" href="${ctx}/page/include/css/page.css" />

<title>登录</title>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
        					<a href="${ctx}">主页</a> / 登录
      					</div>
      					<div class="panel-body">
      					    <form class="m-t" role="form" action="index.html">
                				<div class="form-group">
                   					 <input type="email" class="form-control" placeholder="用户名" required="">
                				</div>
                				<div class="form-group">
                    				<input type="password" class="form-control" placeholder="密码" required="">
                				</div>
                				<button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
                				<p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a></p>
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