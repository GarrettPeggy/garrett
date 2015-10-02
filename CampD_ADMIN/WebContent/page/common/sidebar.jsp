<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<div id="sidebar" class="sidebar responsive">

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>
			<span class="btn btn-info"></span>
			<span class="btn btn-warning"></span>
			<span class="btn btn-danger"></span>
		</div>
	</div><!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class="" id="leftMenu100100">
			<a href="${ctx}/user/toList.do">
				<img src="${locResPath}/static/images/tachometer.png"/>
				<span class="menu-text"> 用户管理 </span>
			</a>
			<b class="arrow"></b>
		</li>
	
		<li class="" id="leftMenu110100">
			<a href="#" class="dropdown-toggle">
				 <img src="${locResPath}/static/images/myepal.png"/>
				 <span class="menu-text"> 活动管理 </span>
				 <b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
			<ul class="submenu">
				<li class="" name="subMenu110100" id="leftMenu110101">
					<a href="${ctx}/user/toList.do">
						<i class="menu-icon fa fa-caret-right"></i>
						发布活动
					</a>
					<b class="arrow"></b>
				</li>
				<li class="" name="subMenu110100" id="leftMenu110102">
					<a href="${ctx}/user/toList.do">
						<i class="menu-icon fa fa-caret-right"></i>
						活动列表
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
	
		<li class="" id="leftMenu120100">
			<a href="#" class="dropdown-toggle">
				<!-- <i class="menu-icon fa fa-users"></i> -->
				<img src="${locResPath}/static/images/look.png"/>
				<span class="menu-text"> 场地管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
			<ul class="submenu">
				<li class="" name="subMenu120100" id="leftMenu120101">
					<a href="${ctx}/space/toAdd.do">
						<i class="menu-icon fa fa-caret-right"></i>
						发布场地
					</a>
					<b class="arrow"></b>
				</li>
				<li class="" name="subMenu120100" id="leftMenu120102">
					<a href="${ctx}/space/toList.do">
						<i class="menu-icon fa fa-caret-right"></i>
						场地列表
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
	
	
		<li class="" id="leftMenu130100">
			<a href="#" class="dropdown-toggle">
				<img src="${locResPath}/static/images/statement .png"/>
				<span class="menu-text"> 资讯管理</span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
			<ul class="submenu">
				<li class="" name="subMenu130100" id="leftMenu130101">
					<a href="${ctx}/information/toHomePicConfig.do">
						<i class="menu-icon fa fa-caret-right"></i>
						首页轮播图
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
	</ul>
	
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
		
		var leftMenuSelectId = '${param.leftMenuSelectId}';
	</script>
</div>

