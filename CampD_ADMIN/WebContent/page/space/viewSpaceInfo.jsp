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
					<jsp:param value='[{"name":"场地管理","href":"#"},{"name":"场地列表","href":"${ctx}/space/toList.do"},{"name":"预览场地","href":"#"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="page-header"> <h1> 预览场地信息 </h1> </div>
				            <div class="row">
				              <div class="col-xs-1"></div>
				              <div class="col-xs-9">
				                
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地名称</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${spaceMap.spaceInfo.name}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">联系人</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${spaceMap.spaceInfo.contactor}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">联系人方式</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${spaceMap.spaceInfo.contact}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地类型</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${systemConst.spaceTypeMap[fn:trim(spaceMap.spaceInfo.space_type)]}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地费用</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline">${spaceMap.spaceInfo.cost}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地容量</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline">${spaceMap.spaceInfo.capacity}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">适合活动</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline">${systemConst.categoryMap[fn:trim(spaceMap.spaceInfo.work_for)]}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地地址</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${spaceMap.spaceInfo.adress}</div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">交通概况</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> ${spaceMap.spaceInfo.traffic}</div>
				                  
								  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地介绍</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline">${spaceMap.spaceInfo.description} </div>
				                  
				                  <label class="col-xs-12 col-sm-4 control-label no-padding-right">场地照片</label>
				                  <div class="help-block col-xs-8 col-sm-reset inline"> 
				                    <c:if test="${!empty spaceMap.spaceInfo.show_images}">
					                  	<c:forEach var="image_src" items="${fn:split(spaceMap.spaceInfo.show_images, ',')}">
									    	<img class="space-img" src="${image_src}" width="200" height="200">>
									    </c:forEach>
								    </c:if>
				                  </div>
				                  
				                  <div class="clearfix">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary" onclick="Space.addSpace();" type="button">
											<i class="ace-icon fa fa-check bigger-110"></i>
											返回列表
										</button>
									</div>
								   </div>
				              </div>
				            </div>
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
	
});
</script>
</html>