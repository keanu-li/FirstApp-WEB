<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<title>错误</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/page/error/css/error.css" />

</head>
<body>
	<div class="errorImg">
		<img alt="" src="${ctx}/page/error/img/error.png">
	</div>
	<div class="errorInfo">${_exception.statusObject.msg}</div>
	<div class="errorButton">
		<a href="javascript:history.back();">返回</a>
	</div>
</body>
</html>