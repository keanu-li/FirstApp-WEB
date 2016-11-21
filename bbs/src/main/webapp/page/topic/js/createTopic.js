function publishTopic() {
	var errorMsg = $("#error_message");
	var title = $("#title").val();

	if (title.length == 0) {
		errorMsg.html("标题不能为空");
		return;
	}

	if (title.length > 120) {
		errorMsg.html("标题不能超过120个字符")
		return;
	}

	var form = $("#topicForm");
	form.submit();
}
