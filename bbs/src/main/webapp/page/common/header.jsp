<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
              aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" style="color:#fff;" href="/">FirstApp.Me</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse header-navbar">
        <form class="navbar-form navbar-left" role="search" action="/search" method="get">
          <div class="form-group">
            <input type="text" class="form-control" name="q" value="回车搜索" placeholder="回车搜索">
          </div>
        </form>
      <ul class="nav navbar-nav navbar-right">
        <li>
          <a href="/donate">捐赠</a>
        </li>
        <li>
          <a href="/apidoc">API</a>
        </li>
        <li>
          <a href="/about">关于</a>
        </li>
        <li class="hidden-md hidden-lg">
            <a href="/topic/create">发布话题</a>
          </li>
          <li>
            <a href="/notification/list">通知 <span class="badge" id="badge"></span></a>
            <script>
              setInterval(function () {
                $.ajax({
                  url: "/api/notification/notRead",
                  async: true,
                  cache: false,
                  type: "get",
                  dataType: "json",
                  success: function (data) {
                    if(data.code == 200 && data.detail > 0) {
                      $("#badge").text(data.detail);
                    }
                  }
                })
              }, 120000)
            </script>
          </li>
          <li >
            <a href="/user/${_principal}">
            admin
              <span class="badge" id="badge"></span>
            </a>
          </li>
          <li>
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"
               data-hover="dropdown">
              设置
              <span class="caret"></span>
            </a>
            <span class="dropdown-arrow"></span>
            <ul class="dropdown-menu">
              <li><a href="/user/setting">个人资料</a></li>
              <li><a href="/logout">退出</a></li>
            </ul>
          </li>
      </ul>
    </div>
  </div>
</nav>