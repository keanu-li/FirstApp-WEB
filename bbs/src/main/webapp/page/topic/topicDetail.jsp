<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/jquery.atwho.min.css" />
<script type="text/javascript"
	src="${ctx}/static/js/jquery.atwho.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/lodash.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/libs/editor/editor.css" />
<link rel="stylesheet" href="${ctx}/page/topic/css/topicDetail.css" />
<style>
.CodeMirror {
	height: 150px;
}
</style>
<script type="text/javascript" src="${ctx}/static/js/highlight.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/libs/webuploader/webuploader.withoutimage.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/markdownit.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/editor/editor.js"></script>
<script type="text/javascript" src="${ctx}/static/libs/editor/ext.js"></script>
<title>话题详情</title>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-body topic-detail-header">
							<div class="media">
								<div class="media-body">
									<h2 class="topic-detail-title">${topic.title}</h2>
									<p class="gray">
									   <span class="label label-primary">置顶</span>
                					   <span>•</span>
                					   <span class="label label-success">精华</span>
                					   <span>•</span>
                					   <span><a href="#">${topic.member.name}</a></span>
                					   <span>•</span>
                					   <span>1分钟前</span>
                					   <span>•</span>
                					   <span>100次点击</span>
                					   <span>•</span>
                					   <span>来自 <a href="#">${topic.section.name}</a></span>
                					   <span>•</span>
                					   <span><a href="#">编辑</a></span>
                					   <span>•</span>
                					   <span><a href="javascript:if(confirm('确定要删除吗？'))location.href='#'">删除</a></span>
                					   <span>•</span>
                					   <span><a href="javascript:if(confirm('确定要取消置顶吗？'))location.href='#'">取消置顶</a></span>
									   <span><a href="javascript:if(confirm('确定要置顶吗？'))location.href='#'">置顶</a></span>
									   <span>•</span>
									   <span><a href="javascript:if(confirm('确定要取消加精吗？'))location.href='#'">取消加精</a></span>
									   <span><a href="javascript:if(confirm('确定要加精吗？'))location.href='#'">加精</a></span>
									</p>
								</div>
								<div class="media-right">
            						<img src="${ctx}/page/include/avatar.png" class="avatar-lg"/>
          						</div>
							</div>
						</div>
						<div class="divide"></div>
						<div class="panel-body topic-detail-content">
       						${topic.content}
        				</div>
        				<div class="panel-footer">
        					<a href="javascript:window.open('http://service.weibo.com/share/share.php?url=#?r=&title=', '_blank', 'width=550,height=370'); recordOutboundLink(this, 'Share', 'weibo.com');">分享微博</a>&nbsp;
        					<a href="#">取消收藏</a>
        					<a href="#">加入收藏</a>
        					<span class="pull-right">100个收藏</span>
        				</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">100 条回复</div>
						<div class="panel-body paginate-bot">
						</div>
					</div>
					<div class="panel panel-default">
					    <div class="panel-heading">
          						添加一条新回复
          					<a href="javascript:;" id="goTop" class="pull-right">回到顶部</a>
       					</div>
       					<div class="panel-body">
       					    <form action="/reply/save" method="post" id="replyForm">
            					<input type="hidden" name="" value=""/>
            					<input type="hidden" value="" name="topicId"/>
            					<div class="form-group">
              						<textarea name="content" id="content" rows="5" class="form-control"></textarea>
            					</div>
            					<button type="button" onclick="replySubmit()" class="btn btn-default">回复</button>
            					<span id="error_message"></span>
          					</form>
       					</div>
					</div>
				</div>
				<div class="col-md-3 hidden-sm hidden-xs">
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
	<script type="text/javascript">
		$('pre code').each(function(i, block) {
			hljs.highlightBlock(block);
		});

		var editor = new Editor({
			element : $("#content")[0],
			status : []
		});
		editor.render();

		var $input = $(editor.codemirror.display.input);
		$input.keydown(function(event) {
			if (event.keyCode === 13 && (event.ctrlKey || event.metaKey)) {
				event.preventDefault();
				if (editor.codemirror.getValue().length == 0) {
					$("#error_message").html("回复内容不能为空");
				} else {
					$("#replyForm").submit();
				}
			}
		});

		// at.js 配置
		var codeMirrorGoLineUp = CodeMirror.commands.goLineUp;
		var codeMirrorGoLineDown = CodeMirror.commands.goLineDown;
		var codeMirrorNewlineAndIndent = CodeMirror.commands.newlineAndIndent;
		var data = [];
		<#list replies as reply>
		data.push('${reply.user.username}');
		</#list>
		data = _.unique(data);
		$input.atwho({
			at : "@",
			data : data
		}).on('shown.atwho', function() {
			CodeMirror.commands.goLineUp = _.noop;
			CodeMirror.commands.goLineDown = _.noop;
			CodeMirror.commands.newlineAndIndent = _.noop;
		}).on('hidden.atwho', function() {
			CodeMirror.commands.goLineUp = codeMirrorGoLineUp;
			CodeMirror.commands.goLineDown = codeMirrorGoLineDown;
			CodeMirror.commands.newlineAndIndent = codeMirrorNewlineAndIndent;
		});
		// END at.js 配置
	</script>
</body>
</html>