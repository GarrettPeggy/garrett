<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="1401012" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"礼品管理","href":"#"},{"name":"礼品列表","href":"${ctx}/gift/toList.do"},{"name":"预览礼品","href":"#"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="editGiftForm">
								<div class="page-header"> <h1> 浏览礼品信息 </h1> </div>
					            <div class="row">
					              <div class="col-xs-12">
					                <div class="form-horizontal">
					                
					                  <div class="form-group">
					                    <label for="name" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="name" id="name" value="${giftMap.giftMap.name }" class="width-100" notnull="true" maxlength="50" disabled="disabled">
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="providerName" class="col-xs-12 col-sm-2 control-label no-padding-right">公司名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="providerName" id="providerName" value="${giftMap.giftMap.provider_name }" class="width-100" notnull="true" maxlength="50" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-xs-12 col-sm-2 control-label no-padding-right">公司所在地区</label>
					                    <div class="col-xs-12 col-sm-6"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" value="${giftMap.giftMap.provider_province } ${giftMap.giftMap.provider_city } ${giftMap.giftMap.provider_area }" class="width-100" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i>
					                      </span>  
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="providerAdress" class="col-xs-12 col-sm-2 control-label no-padding-right">公司地址</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="providerAdress" id="providerAdress" value="${giftMap.giftMap.provider_adress }" class="width-100" notnull="true" maxlength="100" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">主营业务</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="mainBusiness" disabled="disabled">
					                            <c:forEach items="${systemConst.mainBusinessMap}" var="mainBusiness">
													<option value="${mainBusiness.key}" ${mainBusiness.key eq giftMap.giftMap.main_business?'selected="selected"':''}>${mainBusiness.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contactor" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contactor" id="contactor" value="${giftMap.giftMap.contactor }" class="width-100" notnull="true" maxlength="15" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contact" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人方式</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contact" id="contact" value="${giftMap.giftMap.contact }" class="width-100" datatype="telOrPhone" notnull="true" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">礼品形态</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="form" disabled="disabled">
					                            <c:forEach items="${systemConst.formMap}" var="form">
													<option value="${form.key}" ${form.key eq giftMap.giftMap.form?'selected="selected"':''}>${form.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">适合活动类型</label>
					                    <div class="col-sm-9">
					                      <div class="row">
					                        <div class="checkbox">
					                            <c:forEach items="${systemConst.categoryMap}" var="category">
					                            	<label style="padding-bottom: 10px;">
														<input name="work-for" class="ace ace-checkbox-${category.key} work-for-${category.key}" value="${category.key}" type="checkbox" disabled="disabled">
														<span class="lbl">${category.value}</span>
													</label>
												</c:forEach>
												<input type="hidden" name="workFor" id="workFor" value="${giftMap.giftMap.work_for }">
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-xs-12 col-sm-2 control-label no-padding-right">活动适用地区</label>
					                    <div class="col-xs-12 col-sm-6 infolist"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" value="${giftMap.giftMap.work_for_city }" class="width-100" disabled="disabled" >
						                      <i class="ace-icon fa fa-leaf"></i>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="showImage" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品图片</label>
					                    <input type="hidden" id="flag" name="flag" value="1"/><!-- 1表示是修改界面  0表示是新增界面 -->
					                    <input type="hidden" id="showImage" name="showImage" value="" />
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right" id="pic_container">
									    		<c:if test="${not empty giftMap.giftMap.show_image}">
										    		<div class="col-sm-3 clearfix" id="pic_div" style="margin-top: 10px;margin-bottom: 10px;">
											    		<div class="avatar-x">
											    			<div id="addPic">
											    				<img class="space-img" src="${sysConfig.ossResUrl}${giftMap.giftMap.show_image}" width="200" height="200" />
											    			</div>
											    			<div class="avatar-bar"></div>
											    		</div>
											    	</div>
										    	</c:if>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
						              </div>
					                  
					                  <div class="form-group">
					                    <label for="description" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品介绍</label>
					                    <div class="col-xs-12 col-sm-9">
											<div class="widget-box ui-sortable-handle">
												<div class="widget-body">
													<div class="widget-main padding-6">
														<div class="tab-content">
															<div id="home" class="tab-pane in active">
																${giftMap.giftMap.description}
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<a href="${ctx}/gift/toList.do" class="btn btn-default btn-lg active" role="button">返回列表</a>
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
    initWorkFor();
});

//初始化使用活动选择
initWorkFor = function(){
	var workFor = '${giftMap.giftMap.work_for}';
	var workForArray = workFor.split(',');
	for (var i = 0; i < workForArray.length; i++) {
		var value = workForArray[i];
		if(!isEmpty(value)){
			$(".work-for-"+value).attr("checked", true);
		}
	}
};
</script>
</html>