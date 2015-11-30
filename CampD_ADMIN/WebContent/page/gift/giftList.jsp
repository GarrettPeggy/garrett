<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<link rel="stylesheet" href="${rmtResPath}/static/css/bootstrap-datetimepicker.min.css" />
	<link rel="stylesheet" href="${locResPath}/static/js/citySelect/city.css?_v=${vs}" />
	<%@ include file="/page/common/jsCss.jsp"%>
	<script src="${locResPath}/static/js/date-time/moment.min.js"></script>
	<script src="${locResPath}/static/js/date-time/locale/zh-cn.js"></script>
	<script src="${locResPath}/static/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
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
			<jsp:param value="140102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"礼品管理","href":"#"},{"name":"礼品列表","href":"${ctx}/gift/toList.do"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="giftListForm">
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
															<label>礼品名称</label> 
															<input type="text" name="name" value="" placeholder="礼品名称" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label>公司名称</label> 
															<input type="text" name=providerName value="" placeholder="公司名称" class="col-xs-12 col-sm-12" />
														</div>
													</div>
												</div>
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
											</div>
											
											<div class="row">
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label for="form-field-select-1">主营业务</label> 
															<select class="form-control" name="mainBusiness">
																<option value="">全部</option>
																<c:forEach items="${systemConst.mainBusinessMap}" var="mainBusiness">
																	<option value="${mainBusiness.key}">${mainBusiness.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												<div class="col-xs-12 col-sm-3">
													<div class="widget-body">
														<div class="widget-main">
															<label for="form-field-select-1">活动类型要求</label> 
															<select class="form-control" name="workFor">
																<option value="">全部</option>
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
															<label for="form-field-select-1">礼品特征</label> 
															<select class="form-control" name="level">
																<option value="">全部</option>
																<c:forEach items="${systemConst.giftLevelMap}" var="level">
																	<option value="${level.key}">${level.value}</option>
																</c:forEach>
															</select>
														</div>
													</div>
												</div>
												
											</div>
											
											<div class="row">
												
												<div class="col-xs-12 col-sm-6">
													<div class="widget-body">
														<div class="widget-main">
															<label>公司地区</label> 
															<div class="infolist"> 
																<span class="block input-icon input-icon-right liststyle">
											                      	<span id="providerProvince">
									                                    <i>请选择省份</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
									                                    </ul>
									                                    <input type="hidden" name="providerProvince" class="curValue" value="">
									                                </span>
									                                <span id="providerCity">
									                                    <i>请选择城市</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
									                                    </ul>
									                                    <input type="hidden" name="providerCity" class="curValue" value="">
									                                </span>
									                                <span id="providerArea">
									                                    <i>请选择地区</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
									                                    </ul>
									                                    <input type="hidden" name="providerArea" class="curValue" value="">
									                                </span>
									                      		</span> 
									                      	</div>
														</div>
													</div>
												</div>
												
												<div class="col-xs-12 col-sm-6">
													<div class="widget-body">
														<div class="widget-main">
															<label>适用活动地区</label> 
															<div class="infolist"> 
																<span class="block input-icon input-icon-right liststyle">
											                      	<span id="workForProvince">
									                                    <i>请选择省份</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
									                                    </ul>
									                                    <input type="hidden" name="workForProvince" class="curValue" value="">
									                                </span>
									                                <span id="workForCity">
									                                    <i>请选择城市</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
									                                    </ul>
									                                    <input type="hidden" name="workForCity" class="curValue" value="">
									                                </span>
									                                <span id="workForArea">
									                                    <i>请选择地区</i>
									                                    <ul>
									                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
									                                    </ul>
									                                    <input type="hidden" name="workForArea" class="curValue" value="">
									                                </span>
									                      		</span> 
									                      	</div>
														</div>
													</div>
												</div>
												
											</div>

											<div class="widget-toolbox padding-8 clearfix">
												<div class="search-con-btn">
													<button class="btn btn-xs btn-danger" onclick="Gift.searchListByForm()" type="button">
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
	$.fn.citySelect(['#providerProvince', '#providerCity', '#providerArea'],['' , '' , '']);
	$.fn.citySelect(['#workForProvince', '#workForCity', '#workForArea'],['' , '' , '']);
	$(".infolist .liststyle span ul").css("left","-26px");
	Gift.searchList();
});
</script>
</html>