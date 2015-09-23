<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<%@page import="com.campD.portal.util.SystemMessage"%>

<div id="navbar" class="navbar navbar-default">

	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
			<span class="sr-only">Toggle sidebar</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>

		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<img class="nav-user-photo" src="${locResPath}/static/images/logo.png"/>
					CD运营平台
				</small>
			</a>
		</div>

		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue">
					<a data-toggle="dropdown" href="javascript:void();" class="dropdown-toggle">
						
						<img class="nav-user-photo" src="${locResPath}/static/avatars/user.jpg" alt="agent user Photo"/>
						<span class="user-info">
							<small>欢迎光临,</small>
							${USER_INFO.userName}
						</span>

						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

						<li>
							<a href="javascript:toViewUserInfo();">
								<i class="ace-icon fa fa-user"></i>
								个人中心
							</a>
						</li>

						<li class="divider"></li>

						<li>
							<a href="${ctx}/user/quit.do">
								<i class="ace-icon fa fa-power-off"></i>
								退出
							</a>
						</li>
					</ul>
				</li>

				<!-- /section:basics/navbar.user_menu -->
			</ul>
		</div>

		<!-- /section:basics/navbar.dropdown -->
	</div><!-- /.navbar-container -->
</div>

<script type="text/javascript">
/*
 * 查看用户个人信息
 */
toViewUserInfo = function(){
	var url = BASE_PATH + '/user/toUpdateUserInfo.do';
	Dialog.ajaxOpenDialog(url,{},"toUpdateUserInfo",function(){
		
	},null);
};
</script>
