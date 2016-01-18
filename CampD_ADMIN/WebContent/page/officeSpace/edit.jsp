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
			<jsp:param value="150102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"办公空间","href":"#"},{"name":"空间列表","href":"#"},{"name":"空间修改","href":"${ctx}/officeSpace/toEdit.do?id=${id}"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="editOfficeSpaceForm">
								<input type="hidden" id="activityId" name="id" value="${id}"/><!-- 活动id -->
								<div class="page-header"> <h1> 修改空间信息 </h1> </div>
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label for="title" class="col-xs-12 col-sm-2 control-label no-padding-right">空间总称</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="name" id="name" value="${officeSpaceMap.officeInfo.name }" class="width-100" notnull="true" maxlength="100" >
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
						                                    <input type="hidden" name="province" class="curValue" value="${officeSpaceMap.officeInfo.province}">
						                                </span>
						                                <span id="city">
						                                    <i>请选择城市</i>
						                                    <ul>
						                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
						                                    </ul>
						                                    <input type="hidden" name="city" class="curValue" value="${officeSpaceMap.officeInfo.city}">
						                                </span>
						                                <span id="area">
						                                    <i>请选择地区</i>
						                                    <ul>
						                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
						                                    </ul>
						                                    <input type="hidden" name="area" class="curValue" value="${officeSpaceMap.officeInfo.area}">
						                                </span>
							                      	</span> 
							                    </div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
							                    <label for="showImage" class="col-xs-12 col-sm-2 control-label no-padding-right">活动海报</label>
							                    <input type="hidden" id="flag" name="flag" value="1"/><!-- 1表示是修改界面  0表示是新增界面 -->
							                    <input type="hidden" id="logo" name="logo" value="" />
												<input type="hidden" id="realPath" name="realPath" />
												<input type="hidden" id="fakepath" name="fakepath" /><!-- 更新活动时判断是否有选择替换图片 -->
												<input type="hidden" id="oldPath" name="oldPath" value="${officeSpaceMap.officeInfo.logo }"/><!-- 图片的原来的路径 -->
							                    <div class="col-xs-12 col-sm-9"> 
							                      <span class="block input-icon input-icon-right" id="pic_container">
						                      		  	<div class="avatar-title clearfix">
											    			<input type="file" class="btn-orange-s pull-lef" onchange="OfficeSpace.uploadLogoPic(this);" id="cropImg" name="cropImg"/>
											    		</div>
											    		<c:if test="${not empty officeSpaceMap.officeInfo.logo}">
											    		<div class="col-sm-3 clearfix" id="pic_div" style="margin-top: 10px;margin-bottom: 10px;">
												    		<div class="avatar-x">
												    			<div id="addPic">
												    				<img class="space-img" src="${sysConfig.ossResUrl}${officeSpaceMap.officeInfo.logo}" width="200" height="200" />
												    			</div>
												    			<div class="avatar-bar"></div>
												    		</div>
												    	</div>
												    	</c:if>
							                      </span> 
							                    </div>
							                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
							                </div>
							                
											<div class="clearfix form-actions">
												<div class="col-md-offset-3 col-md-9">
													<button class="btn btn-primary" type="button" onclick="OfficeSpace.uploadPicToOSS();">
														<i class="ace-icon fa fa-check bigger-110"></i>
														保存
													</button>
													&nbsp; &nbsp; &nbsp;
													<button class="btn" type="reset" disabled="disabled">
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
		$.fn.citySelect(['#province', '#city', '#area'],['${officeSpaceMap.officeInfo.province}' , '${officeSpaceMap.officeInfo.city}' , '${officeSpaceMap.officeInfo.area}']);
	});
</script>
</html>
