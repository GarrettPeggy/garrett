<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	<link rel="stylesheet" href="${locResPath}/static/js/citySelect/city.css?_v=${vs}" />
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
			<jsp:param value="150101" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"办公空间","href":"#"},{"name":"发布空间","href":"${ctx}/officeSpace/toAdd.do"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="addOfficeSpaceForm">
								<div class="page-header"> <h1> 发布空间信息 </h1> </div>
								<flagToken:token tokenName="addOfficeSpaceForm"/><!-- 防止表单重复提交 -->
								<input type="hidden" id="status" name="status" value="1"/><!--  0表示隐藏，1表示显示 -->
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label for="title" class="col-xs-12 col-sm-2 control-label no-padding-right">空间总称</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="name" id="name" class="width-100" notnull="true" maxlength="60" >
									                    <i class="ace-icon fa fa-leaf"></i> 
								                    </span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>	
											
											<div class="form-group">
												<label for="adress" class="col-xs-12 col-sm-2 control-label no-padding-right">所在地区</label>
												<div class="col-xs-12 col-sm-6 infolist"> 
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
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>		
											
											<div class="form-group">
							                    <label for="showImage" class="col-xs-12 col-sm-2 control-label no-padding-right">活动海报</label>
							                    <input type="hidden" id="flag" name="flag" value="0"/><!-- 1表示是修改界面  0表示是新增界面 -->
							                    <input type="hidden" id="logo" name="logo" value="" />
												<input type="hidden" id="realPath" name="realPath"/>
							                    <div class="col-xs-12 col-sm-9"> 
							                      <span class="block input-icon input-icon-right" id="pic_container">
						                      		  	<div class="avatar-title clearfix">
											    			<input type="file" class="btn-orange-s pull-lef" onchange="OfficeSpace.uploadLogoPic(this);" id="cropImg" name="cropImg"/>
											    		</div>
							                      </span> 
							                    </div>
							                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
							                </div>
							                
							                <div class="clearfix form-actions">
												<div class="col-md-offset-3 col-md-9">
													<button class="btn btn-primary" type="button" onclick="OfficeSpace.uploadPicToOSS();">
														<i class="ace-icon fa fa-check bigger-110"></i>
														发布空间
													</button>
													&nbsp; &nbsp; &nbsp;
													<button class="btn" type="reset">
														<i class="ace-icon fa fa-undo bigger-110"></i>
														重置表单
													</button>
												</div>
											</div>
											
										</div>
									</div>
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
		$.fn.citySelect(['#province', '#city', '#area'],['北京市' , '北京市' , '东城区']);
	});
</script>
</html>
