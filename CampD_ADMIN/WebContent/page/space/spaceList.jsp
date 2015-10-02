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
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="120102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"场地列表","href":"${ctx}/space/toList.do"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="spaceListForm">
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
															<label>联系人</label> 
															<input type="text" name="contactor" value="" placeholder="联系人" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>联系方式</label> 
															<input type="text" name="contact" value="" placeholder="联系方式" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>场地名称</label> 
															<input type="text" name="name" value="" placeholder="场地名称" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>发布时间</label>
															<div class="input-daterange input-group">
																<input type="text" class="input-sm form-control input-daterange-startDate" value="" name="beginCreateTime" />
																<span class="input-group-addon"> 
																	<i class="fa glyphicon-minus"></i>
																</span> 
																<input type="text" class="input-sm form-control input-daterange-endDate" value="" name="endCreateTime" />
															</div>
														</div>
													</div>
												</div>
												
											</div>
											
											<div class="row">
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label for="form-field-select-1">场地类型</label> 
															<select class="form-control" name="orderState">
																<option value=""></option>
																<c:forEach items="${systemConst.spaceTypeMap}" var="spaceType">
																	<option value="${spaceType.key}">${spaceType.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label for="form-field-select-1">适用活动</label> 
															<select class="form-control" name="orderState">
																<option value=""></option>
																<c:forEach items="${systemConst.categoryMap}" var="category">
																	<option value="${category.key}">${category.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label for="form-field-select-1">场地特征</label> 
															<select class="form-control" name="orderState">
																<option value=""></option>
																<c:forEach items="${systemConst.spaceLevelMap}" var="spaceLevel">
																	<option value="${spaceLevel.key}">${spaceLevel.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												
											</div>

											<div class="widget-toolbox padding-8 clearfix">
												<div class="search-con-btn">
													<button class="btn btn-xs btn-danger" onclick="Space.searchSpaceList()" type="button">
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
	Space.searchSpaceList();
});
</script>
</html>