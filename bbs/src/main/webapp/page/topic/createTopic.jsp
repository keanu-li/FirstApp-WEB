<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/static/libs/editor/editor.css" />
<link rel="stylesheet" href="${ctx}/page/topic/css/createTopic.css" />
<script type="text/javascript"
	src="../static/libs/webuploader/webuploader.withoutimage.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/markdownit.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/editor/editor.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/editor/ext.js"></script>
<script type="text/javascript" src="${ctx}/page/topic/js/createTopic.js"></script>
<title>发布话题</title>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<a href="/">主页</a> / 发布话题
						</div>
						<div class="panel-body">
							<form method="post" action="${ctx}/topic/addTopic.do" id="topicForm">
								<input type="hidden" name="" value="" />
								<div class="form-group">
									<label for="title">标题</label>
									 <input type="text"
										class="form-control" id="title" name="title">
								</div>
								<div class="form-group">
									<label for="title">内容</label>
									<textarea name="content" id="content" rows="15"
										class="form-control">
									</textarea>
								</div>
								<div class="form-group">
									<label for="title">版块</label> 
									<select name="sectionId" id="sectionId"
										class="form-control">
										<c:forEach items="${sections}" var="section">
											<option value="${section.id}">${section.name}</option>
										</c:forEach>
									</select>
								</div>
								<button type="button" onclick="publishTopic();"
									class="btn btn-default">发布</button>
								<span id="error_message"></span>
							</form>
						</div>
					</div>
				</div>

				<div class="col-md-3 hidden-sm hidden-xs">
					<div class="panel panel-default">
						<div class="panel-heading">
							<b>话题发布指南</b>
						</div>
						<div class="panel-body">
							<p>• 话题内容支持 Markdown 文本标记语法</p>
							<p>• 发布话题奖励 5 积分，但是被管理员删除话题将会扣除作者 7 积分</p>
							<p>• 发布话题之前,可以点击预览查看</p>
							<p>• ctrl+b 粗体</p>
							<p>• ctrl+i 斜体</p>
							<p>• ctrl+k 插入链接</p>
							<p>• ctrl+alt+i 插入图片</p>
							<p>• ctrl+' 插入引用</p>
							<p>• ctrl+alt+l 有序列表</p>
							<p>• ctrl+l 无序列表</p>
							<p>• 截图在编辑器里直接粘贴即可上传(IE10+)</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<script type="text/javascript">
		var editor = new Editor({
			element : $("#content")[0],
			status : []
		});
		editor.render();
	</script>
</body>
</html>