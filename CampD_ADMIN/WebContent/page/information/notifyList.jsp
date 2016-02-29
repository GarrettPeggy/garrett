<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="130102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param   value='[{"name":"首页通知配置","href":"${ctx}/information/notify/toList.do"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="notifyListForm">
								<input type="hidden" name="type" value="1"><!-- 首页通知配置类型   -->
								
								<div class="widget-container-col ui-sortable">
									<div class="widget-box widget-color-blue2 ui-sortable-handle">
										<div class="widget-header">
											<h5 class="widget-title">
												<i class="fa fa-search"></i> 操作
											</h5>
											<div class="widget-toolbar">
												<a href="#" data-action="collapse">
													<i class="1 ace-icon fa bigger-125 fa-chevron-up"></i>
												</a>
											</div>
										</div>
										
										<div class="widget-body" style="display: block;">
											<div class="widget-toolbox padding-8 clearfix">
												<div class="search-con-btn">
													<button class="btn btn-xs btn-warning" onclick="toAddNotify();" type="button">
														<span class="bigger-110">添加通知</span>
													    <i class="ace-icon fa fa-plus icon-on-right"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-xs-12" id="load_content"> </div>
								</div>
							</form>
						</div>
			        </div>
				 </div>
			</div>
		</div>
		<jsp:include page="/page/common/footer.jsp" flush="true"></jsp:include>
	</div>
</body>

<script type="text/javascript">
$(function(){
	searchNotifyList();
});

searchNotifyList = function(){
	submitSearch('notifyListForm', 'load_content', BASE_PATH + '/information/notify/list.do',function() {
		
	});
};

toAddNotify = function(){
	alert("这里打开添加通知的对话框！");
};
</script>

</html>