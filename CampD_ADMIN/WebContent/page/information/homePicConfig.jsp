<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${locResPath}/static/js/information/information.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="130101" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param   value='[{"name":"首页轮播图配置","href":"${ctx}/information/toHomePicConfig.do"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-4">
									<form id="addHomePicDialogForm_0" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								        <input type="hidden" name="type" value="${sysConfig.sys_conf_homePic}"><!-- 系统配置类型，0表示首页轮播图配置 -->
								        <input type="hidden" name="key" id="value_0" value="0"><!-- 轮播图顺序，0表示首页轮播图第一张 -->
								        <input type="hidden" name="value" id="imageHiddenPath_0" value="${rmtResPath}/static/images/login/cd_logo.png">
								        <input type="hidden" name="oldPath" id="prin_url_0" value=""><!-- 轮播图的原始路径 -->
								        <input type="hidden" name="realPath" id="realPath_0" value=""><!-- 新文件的物理路径-->
										<div class="avatar-title clearfix">
									    	<input type="file" class="btn-orange-s pull-lef" onchange="Information.uploadPic(this,'0');" id="cropImg" name="cropImg">
									    </div>
									    <div class="clearfix" style="margin-top: 10px;margin-bottom: 10px;">
									    	<div class="avatar-x">
									    	    <div id="addPic">
									    	    	<img id="imagePath_0" src="${rmtResPath}/static/images/login/cd_logo.png" width="200" height="200">
									    	    </div>
									            <div class="avatar-bar"></div>
									        </div>
									    </div>
									    <div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">链接地址：</label>
											<div class="col-xs-12 col-sm-6">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="url" id="url_0" class="width-100" maxlength="300" >
								                    <i class="ace-icon fa fa-leaf"></i>
							                    </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									    <button class="btn btn-xs btn-success" id="button_0" onclick="Information.addHomePicToOSS('0');" type="button">
											<span class="bigger-110">保存</span>
										</button>
							        </form>
								</div>
								<div class="col-xs-4">
									<form id="addHomePicDialogForm_1" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								        <input type="hidden" name="type" value="${sysConfig.sys_conf_homePic}"><!-- 系统配置类型，0表示首页轮播图配置 -->
								        <input type="hidden" name="key" id="value_1" value="1"><!-- 轮播图顺序，0表示首页轮播图第一张 -->
								        <input type="hidden" name="value" id="imageHiddenPath_1" value="${rmtResPath}/static/images/login/cd_logo.png">
								        <input type="hidden" name="oldUrl" id="prin_url_1" value=""><!-- 轮播图的原始路径 -->
								        <input type="hidden" name="realPath" id="realPath_1" value=""><!-- 新文件的物理路径-->
										<div class="avatar-title clearfix">
									    	<input type="file" class="btn-orange-s pull-lef" onchange="Information.uploadPic(this,'1');" id="cropImg" name="cropImg">
									    </div>
									    <div class="clearfix" style="margin-top: 10px;margin-bottom: 10px;">
									    	<div class="avatar-x">
									    	    <div id="addPic">
									    	    	<img id="imagePath_1" src="${rmtResPath}/static/images/login/cd_logo.png" width="200" height="200">
									    	    </div>
									            <div class="avatar-bar"></div>
									        </div>
									    </div>
									    <div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">链接地址：</label>
											<div class="col-xs-12 col-sm-6">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="url" id="url_1" class="width-100" maxlength="300" >
								                    <i class="ace-icon fa fa-leaf"></i>
							                    </span>
											</div>
									        <div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									    <button class="btn btn-xs btn-success" id="button_1" onclick="Information.addHomePicToOSS('1');" type="button">
											<span class="bigger-110">保存</span>
										</button>
							        </form> 
								</div>
								<div class="col-xs-4">
									<form id="addHomePicDialogForm_2" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								        <input type="hidden" name="type" value="${sysConfig.sys_conf_homePic}"><!-- 系统配置类型，0表示首页轮播图配置 -->
								        <input type="hidden" name="key" id="value_2" value="2"><!-- 轮播图顺序，0表示首页轮播图第一张 -->
								        <input type="hidden" name="value" id="imageHiddenPath_2" value="${rmtResPath}/static/images/login/cd_logo.png">
								        <input type="hidden" name="oldUrl" id="prin_url_2" value=""><!-- 轮播图的原始路径 -->
								        <input type="hidden" name="realPath" id="realPath_2" value=""><!-- 新文件的物理路径-->
										<div class="avatar-title clearfix">
									    	<input type="file" class="btn-orange-s pull-lef" onchange="Information.uploadPic(this,'2');" id="cropImg" name="cropImg">
									    </div>
									    <div class="clearfix" style="margin-top: 10px;margin-bottom: 10px;">
									    	<div class="avatar-x">
									    	    <div id="addPic">
									    	    	<img id="imagePath_2" src="${rmtResPath}/static/images/login/cd_logo.png" width="200" height="200">
									    	    </div>
									            <div class="avatar-bar"></div>
									        </div>
									    </div>
									    <div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">链接地址：</label>
											<div class="col-xs-12 col-sm-6">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="url" id="url_2" class="width-100" maxlength="300" >
								                    <i class="ace-icon fa fa-leaf"></i>
							                    </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									    <button class="btn btn-xs btn-success" id="button_2" onclick="Information.addHomePicToOSS('2');" type="button">
											<span class="bigger-110">保存</span>
										</button>
							        </form> 
								</div>
								<div class="col-xs-4" style="margin-top: 40px;">
									<form id="addHomePicDialogForm_3" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								        <input type="hidden" name="type" value="${sysConfig.sys_conf_homePic}"><!-- 系统配置类型，0表示首页轮播图配置 -->
								        <input type="hidden" name="key" id="value_3" value="3"><!-- 轮播图顺序，0表示首页轮播图第一张 -->
								        <input type="hidden" name="value" id="imageHiddenPath_3" value="${rmtResPath}/static/images/login/cd_logo.png">
								        <input type="hidden" name="oldUrl" id="prin_url_3" value=""><!-- 轮播图的原始路径 -->
								        <input type="hidden" name="realPath" id="realPath_3" value=""><!-- 新文件的物理路径-->
										<div class="avatar-title clearfix">
									    	<input type="file" class="btn-orange-s pull-lef" onchange="Information.uploadPic(this,'3');" id="cropImg" name="cropImg">
									    </div>
									    <div class="clearfix" style="margin-top: 10px;margin-bottom: 10px;">
									    	<div class="avatar-x">
									    	    <div id="addPic">
									    	    	<img id="imagePath_3" src="${rmtResPath}/static/images/login/cd_logo.png" width="200" height="200">
									    	    </div>
									            <div class="avatar-bar"></div>
									        </div>
									    </div>
									    <div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">链接地址：</label>
											<div class="col-xs-12 col-sm-6">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="url" id="url_3" class="width-100" maxlength="300" >
								                    <i class="ace-icon fa fa-leaf"></i>
							                    </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									    <button class="btn btn-xs btn-success" id="button_3" onclick="Information.addHomePicToOSS('3');" type="button">
											<span class="bigger-110">保存</span>
										</button>
							        </form> 
								</div>
								<div class="col-xs-4" style="margin-top: 40px;">
									<form id="addHomePicDialogForm_4" class="form-horizontal" role="form" method="post" enctype="multipart/form-data">
								        <input type="hidden" name="type" value="${sysConfig.sys_conf_homePic}"><!-- 系统配置类型，0表示首页轮播图配置 -->
								        <input type="hidden" name="key" id="value_4" value="4"><!-- 轮播图顺序，0表示首页轮播图第一张 -->
								        <input type="hidden" name="value" id="imageHiddenPath_4" value="${rmtResPath}/static/images/login/cd_logo.png">
								        <input type="hidden" name="oldUrl" id="prin_url_4" value=""><!-- 轮播图的原始路径 -->
								        <input type="hidden" name="realPath" id="realPath_4" value=""><!-- 新文件的物理路径-->
										<div class="avatar-title clearfix">
									    	<input type="file" class="btn-orange-s pull-lef" onchange="Information.uploadPic(this,'4');" id="cropImg" name="cropImg">
									    </div>
									    <div class="clearfix" style="margin-top: 10px;margin-bottom: 10px;">
									    	<div class="avatar-x">
									    	    <div id="addPic">
									    	    	<img id="imagePath_4" src="${rmtResPath}/static/images/login/cd_logo.png" width="200" height="200">
									    	    </div>
									            <div class="avatar-bar"></div>
									        </div>
									    </div>
									    <div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">链接地址：</label>
											<div class="col-xs-12 col-sm-6">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="url" id="url_4" class="width-100" maxlength="300" >
								                    <i class="ace-icon fa fa-leaf"></i>
							                    </span>
											</div>
											<div class="help-block col-xs-12 col-sm-reset inline"></div>
										</div>
									    <button class="btn btn-xs btn-success" id="button_4" onclick="Information.addHomePicToOSS('4');" type="button">
											<span class="bigger-110">保存</span>
										</button>
							        </form> 
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
	Information.initHomePic();
});
</script>

</html>