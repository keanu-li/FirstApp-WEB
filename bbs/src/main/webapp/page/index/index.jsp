<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/common.jsp"%>
<link rel="stylesheet" href="${ctx}/page/include/css/topicItem.css" />
<link rel="stylesheet" href="${ctx}/page/include/css/page.css" />

<title>首页</title>
</head>
<body>
	<div class="wrapper">
		<%@ include file="../common/header.jsp"%>
		<div class="container">
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<ul class="nav nav-pills">
								<c:forEach items="${sections}" var="section">
									<li><a href="${ctx}?sectionId=${section.id}">${section.name}</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="panel-body paginate-bot">
							<c:forEach items="${topicPage.list}" var="apiJsonTopic">
							<div class="media">
								<div class="media-left">
									<a href="#"><img src="${ctx}/page/include/avatar.png" class="avatar" alt=""></a>
								</div>
								<div class="media-body">
									<div class="title">
										<a href="${ctx}/topic/${apiJsonTopic.id}">${apiJsonTopic.title}</a>
									</div>
									<p class="gray">
										<span class="label label-primary">精华</span>
										<span>•</span>
										<span><a href="#">${apiJsonTopic.member.name}</a></span> 
										<span class="hidden-sm hidden-xs">•</span> 
										<span class="hidden-sm hidden-xs">${apiJsonTopic.replyCounts}个回复</span> 
										<span class="hidden-sm hidden-xs">•</span> 
										<span class="hidden-sm hidden-xs">${apiJsonTopic.viewCounts}次浏览</span> 
										<span>•</span> 
										<span>1分钟前</span>
									</p>
								</div>
							</div>
							</c:forEach>
							<div class="divide mar-top-5"></div>
							<c:if test="${topicPage.totalCount>topicPage.pageSize}">
							 <ul class="pagination pagination-sm">
								<c:if test="${topicPage.firstPage == false}">
									<li><a href="${ctx}?pageNo=1&pageSize=${topicPage.pageSize}&sectionId=${sectionId}">&lt;&lt;</a></li>
									<li><a href="${ctx}?pageNo=${topicPage.prePage}&pageSize=${topicPage.pageSize}&sectionId=${sectionId}">&lt;</a></li>
								</c:if>
								<c:forEach items="${pages}" var="page">
									<c:choose>
										<c:when test="${page == topicPage.pageNo}">
											<li class="active"><a class="disabled">${page}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${ctx}?pageNo=${page}&pageSize=${topicPage.pageSize}&sectionId=${sectionId}">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${topicPage.lastPage == false}">
									<li><a href="${ctx}?pageNo=${topicPage.nextPage}&pageSize=${topicPage.pageSize}&sectionId=${sectionId}">&gt;</a></li>
									<li><a href="${ctx}?pageNo=${topicPage.totalPage}&pageSize=${topicPage.pageSize}&sectionId=${sectionId}">&gt;&gt;</a></li>
								</c:if>
							 </ul>
							</c:if>
						</div>
					</div>
				</div>
				<div class="col-md-3 hidden-sm hidden-xs">
					<%@ include file="../include/welcome.jsp"%>
				</div>
			</div>
		</div>
		<%@ include file="../common/footer.jsp"%>
	</div>
</body>
</html>