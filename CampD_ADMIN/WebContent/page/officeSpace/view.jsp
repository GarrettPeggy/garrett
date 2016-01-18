<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js?_v=${vs}"></script>
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
					<jsp:param value='[{"name":"办公空间","href":"#"},{"name":"空间列表","href":"#"},{"name":"空间查看","href":"${ctx}/officeSpace/toView.do?id=${id}"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="page-header"> <h1> 查看空间信息 </h1> </div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-horizontal">
									
										<div class="form-group">
											<label for="title" class="col-xs-12 col-sm-2 control-label no-padding-right">空间总称</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="name" id="name" class="width-100" value="${officeSpaceMap.officeInfo.name }" disabled="disabled" notnull="true" maxlength="100" >
							                    </span>
											</div>
										</div>
										
										<div class="form-group">
											<label for="adress" class="col-xs-12 col-sm-2 control-label no-padding-right">所在地区</label>
						                    <div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="bigAddress" id="bigAddress" value="${officeSpaceMap.officeInfo.province} ${officeSpaceMap.officeInfo.city} ${officeSpaceMap.officeInfo.area}" disabled="disabled" class="width-100" maxlength="100" >
							                    </span>
											</div>
										</div>
										<div class="form-group">
						                    <label for="showImage" class="col-xs-12 col-sm-2 control-label no-padding-right">活动海报</label>
						                    <div class="col-xs-12 col-sm-9"> 
						                      <span class="block input-icon input-icon-right" id="pic_container">
					                      		  	<div class="col-sm-3 clearfix" id="pic_div" style="margin-top: 10px;margin-bottom: 10px;">
											    		<div class="avatar-x">
											    			<div id="addPic">
											    				<img class="space-img" src="${sysConfig.ossResUrl}${officeSpaceMap.officeInfo.logo}" width="200" height="200" />
											    			</div>
											    			<div class="avatar-bar"></div>
											    		</div>
											    	</div>
						                      </span> 
						                    </div>
						                </div>
										
										<div class="clearfix form-actions">
											<div class="col-md-offset-3 col-md-9">
												<a href="${ctx}/officeSpace/toList.do" class="btn btn-default btn-lg active" role="button">返回列表</a>
											</div>
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
</html>