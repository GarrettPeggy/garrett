<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<link rel="stylesheet" href="${locResPath}/static/js/citySelect/city.css?_v=${vs}" />
	<%@ include file="/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/jquery.cityselect.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="150102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"办公空间","href":"#"},{"name":"空间列表","href":"${ctx}/officeSpace/toList.do"}]' name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="officeSpaceListForm">
								<div class="widget-container-col ui-sortable">
									<div class="widget-box widget-color-blue2 ui-sortable-handle">
										<div class="widget-header">
											<h5 class="widget-title">
												<i class="fa fa-search"></i> 搜索
											</h5>
											<div class="widget-toolbar">
												<a href="#" data-action="collapse">
													<i class="1 ace-icon fa bigger-125 fa-chevron-up"></i>
												</a>
											</div>
										</div>
										
										<div class="widget-body" style="display: block;">
											<div class="row">
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>空间总称</label> 
															<input type="text" name="name" id="name" value="" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												
												<div class="col-xs-12 col-sm-8">
													<div class="widget-body">
														<div class="widget-main">
															<label>所在地区</label> 
															<div class="infolist"> 
																<span class="block input-icon input-icon-right liststyle">
											                      	<span id="province">
									                                    <i>请选择省份</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
									                                    </ul>
									                                    <input type="hidden" name="province" class="curValue" value="">
									                                </span>
									                                <span id="city">
									                                    <i>请选择城市</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
									                                    </ul>
									                                    <input type="hidden" name="city" class="curValue" value="">
									                                </span>
									                                <span id="area">
									                                    <i>请选择地区</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
									                                    </ul>
									                                    <input type="hidden" name="area" class="curValue" value="">
									                                </span>
									                      		</span> 
									                      	</div>
														</div>
													</div>
												</div>
											</div>
											
											<div class="widget-toolbox padding-8 clearfix">
												<div class="search-con-btn">
													<button class="btn btn-xs btn-danger" onclick="OfficeSpace.searchListByForm()" type="button">
														<span class="bigger-110">搜索</span> 
														<i class="ace-icon fa fa-search icon-on-right"></i>
													</button>&nbsp;&nbsp;
													<button class="btn btn-xs btn-info" type="reset">
														<span class="bigger-110">重置</span>
													    <i class="ace-icon fa fa-refresh icon-on-right"></i>
													</button>&nbsp;&nbsp;
													<button class="btn btn-xs btn-warning" onclick="OfficeSpace.toAddOfficeSpace();" type="button">
														<span class="bigger-110">添加总空间</span>
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
		OfficeSpace.searchList();
		$.fn.citySelect(['#province', '#city', '#area'],['' , '' , '']);
		$(".infolist .liststyle span ul").css("left","-26px");
	});
</script>

</html>
