<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//js中请求的base路径
	var BASE_PATH = '${ctx}';
	//js中本地静态文件base路径
	var LOC_RES_PATH = '${locResPath}';
	//js中远程静态资源文件base路径
	var REMOTE_RES_PATH = '${rmtResPath}';
	var VS = '${vs}';
</script>
<script type="text/javascript" src="${locResPath}/static/common/jquery/jquery-1.11.3.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/bootstrap/js/bootstrap.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/angularjs/angular.min.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${locResPath}/static/common/bootstrap/css/bootstrap.min.css?_v=${vs}" />
<link rel="stylesheet" type="text/css" href="${locResPath}/static/common/bootstrap/css/bootstrap-theme.min.css?_v=${vs}" />
<link rel="shortcut icon" href="${locResPath}/static/images/login/logo.jpg"/>