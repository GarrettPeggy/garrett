<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
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
					<jsp:param value='[{"name":"场地管理","href":"#"},{"name":"场地列表","href":"${ctx}/space/toList.do"},{"name":"编辑场地","href":"#"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="updateSpaceInfoForm">
								<flagToken:token tokenName="updateSpaceInfoForm"/>
								<input type="hidden" name="id" value="${id}"><!-- 默认场地都是普通场地 -->
								<div class="page-header"> <h1> 编辑场地信息 </h1> </div>
					            <div class="row">
					              <div class="col-xs-12">
					                <div class="form-horizontal">
					                
					                  <div class="form-group">
					                    <label for="name" class="col-xs-12 col-sm-3 control-label no-padding-right">场地名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="name" id="name" class="width-100" value="${spaceMap.spaceInfo.name}" notnull="true" maxlength="50" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contactor" class="col-xs-12 col-sm-3 control-label no-padding-right">联系人</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contactor" id="contactor" class="width-100" value="${spaceMap.spaceInfo.contactor}" notnull="true" maxlength="15" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contact" class="col-xs-12 col-sm-3 control-label no-padding-right">联系人方式</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contact" id="contact" class="width-100" value="${spaceMap.spaceInfo.contact}" datatype="phone" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-3 control-label no-padding-right">场地类型</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="spaceType">
					                            <c:forEach items="${systemConst.spaceTypeMap}" var="spaceType">
													<option value="${spaceType.key}" selected="${spaceType.key eq spaceMap.spaceInfo.space_type?'selected':''}">${spaceType.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="cost" class="col-xs-12 col-sm-3 control-label no-padding-right">场地费用</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="cost" id="cost" class="width-100" value="${spaceMap.spaceInfo.cost}" datatype="money" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"><b>元/小时</b></div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="cost" class="col-xs-12 col-sm-3 control-label no-padding-right">场地容量</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="capacity" id="capacity" class="width-100" value="${spaceMap.spaceInfo.capacity}" datatype="number" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"><b>人</b></div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-3 control-label no-padding-right">适合活动</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="workFor">
					                            <c:forEach items="${systemConst.categoryMap}" var="category">
													<option value="${category.key}" selected="${category.key eq spaceMap.spaceInfo.work_for?'selected':''}">${category.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="adress" class="col-xs-12 col-sm-3 control-label no-padding-right">场地地址</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="adress" id="adress" class="width-100" value="${spaceMap.spaceInfo.adress}" notnull="true" maxlength="100" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="traffic" class="col-xs-12 col-sm-3 control-label no-padding-right">交通概况</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="traffic" id="traffic" class="width-100" value="${spaceMap.spaceInfo.traffic}" maxlength="100" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="description" class="col-xs-12 col-sm-3 control-label no-padding-right">场地介绍</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <textarea rows="4" class="form-control limited" name="description" id="description" maxlength="250">${spaceMap.spaceInfo.description}</textarea>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="show_images" class="col-xs-12 col-sm-3 control-label no-padding-right">场地照片</label>
					                    <input type="hidden" id="show_images" name="showImages" value="">
					                    <input type="hidden" name="realPath" id="realPath" value=""><!-- 新文件的物理路径-->
					                    <input type="hidden" name="oldPath" id="oldPath" value=""><!-- 新文件的物理路径-->
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right" id="pic_container">
						                      	<div class="avatar-title clearfix">
											    	<input type="file" class="btn-orange-s pull-lef"  onclick="Space.controlPicNum(event,this);" onchange="Space.uploadSpacePic(this,'updateSpaceInfoForm');" id="cropImg" name="cropImg">
											    </div>
											    <c:if test="${!empty spaceMap.spaceInfo.show_images}">
												    <c:forEach var="image_src" items="${fn:split(spaceMap.spaceInfo.show_images, ',')}">
												    	<div class="col-sm-3 clearfix" style="margin-top: 10px;margin-bottom: 10px;">
												    		<div class="avatar-x">
												    			<div id="addPic">
												    				<img class="space-img" src="${sysConfig.ossResUrl}${image_src}" width="200" height="200">
												    			</div>
												    			<div class="avatar-bar"></div>
												    		</div>
												    		<button style="margin-left: 157px;margin-top: 10px;" class="btn btn-xs btn-success" id="button_0" onclick="Space.deleteCurPic(this);" type="button"><span class="bigger-110">删除</span></button>
												    	</div>
												    </c:forEach>
												</c:if>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<a href="javascript:Space.updateSpacePicToOSS();" class="btn btn-primary btn-lg active" role="button">保存</a>
											<a href="${ctx}/space/toList.do" class="btn btn-default btn-lg active" role="button">取消</a>
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
</html>