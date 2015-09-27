<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<link rel="stylesheet" href="${rmtResPath}/static/css/bootstrap-datetimepicker.min.css" />
	<%@ include file="/page/common/jsCss.jsp"%>
	<script src="${locResPath}/static/js/date-time/moment.min.js"></script>
	<script src="${locResPath}/static/js/date-time/locale/zh-cn.js"></script>
	<script src="${locResPath}/static/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${locResPath}/static/js/user/user.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="100100" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param   value='[{"name":"用户管理","href":"${ctx}/user/toList.do"}]'  name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="userListForm">
								<div class="widget-container-col ui-sortable">
									<div class="widget-box widget-color-blue2 ui-sortable-handle">
										<div class="widget-header">
											<h5 class="widget-title">
												<i class="fa fa-search"></i> 搜索
											</h5>

											<div class="widget-toolbar">
												<a href="#" data-action="collapse"> <i
													class="1 ace-icon fa bigger-125 fa-chevron-up"></i>
												</a>
											</div>

										</div>

										<div class="widget-body" style="display: block;">
											<div class="row">
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>用户名</label> 
															<input type="text" name="userName" value="" placeholder="用户名称" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>手机号</label> 
															<input type="text" name="mdn" value="" placeholder="手机号" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>注册时间</label>
															<div class="input-daterange input-group">
																<input type="text" class="input-sm form-control input-daterange-startDate" value="" name="beginRegTime" />
																<span class="input-group-addon"> 
																	<i class="fa glyphicon-minus"></i>
																</span> 
																<input type="text" class="input-sm form-control input-daterange-endDate" value="" name="endRegTime" />
															</div>
														</div>
													</div>
												</div>
											    <div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>最后登录时间</label>
													<div class="input-daterange input-group">
														<input type="text" class="input-sm form-control input-daterange-startDate" value="" name="beginLoginTime" />
														<span class="input-group-addon"> 
															<i class="fa glyphicon-minus"></i>
														</span> 
														<input type="text" class="input-sm form-control input-daterange-endDate"value=""  name="endLoginTime" />
													</div>
														</div>
													</div>
												</div>
											</div>

											<div class="widget-toolbox padding-8 clearfix">
												<div class="search-con-btn">
													<button class="btn btn-xs btn-danger" onclick="User.searchUserList()" type="button">
														<span class="bigger-110">搜索</span> 
														<i class="ace-icon fa fa-search icon-on-right"></i>
													</button>

													<button class="btn btn-xs btn-info" type="reset">
														<span class="bigger-110">重置</span>
													    <i class="ace-icon fa fa-refresh icon-on-right"></i>
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
	DateUtil.initDatePicker();
	User.searchUserList();
});
</script>
</html>