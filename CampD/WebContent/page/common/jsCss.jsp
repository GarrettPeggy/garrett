<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//js中请求的base路径
	var BASE_PATH = '${ctx}';
	//js中本地静态文件base路径
	var LOC_RES_PATH = '${locResPath}';
	//js中远程静态资源文件base路径
	var REMOTE_RES_PATH = '${rmtResPath}';
	//阿里云oss跟路径
	var OSS_RES_URL = '${sysConfig.ossResUrl}';
	var VS = '${vs}';
</script>
<script type="text/javascript" src="${locResPath}/static/common/jquery/jquery-1.11.3.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/jquery/jquery.form.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/dialog/lhgdialog.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/validation.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/common.js?_v=${vs}"></script>
<link rel="stylesheet" type="text/css" href="${rmtResPath}/static/css/common.css?_v=${vs}" />
<link rel="shortcut icon" href="${rmtResPath}/static/images/CD-20x20.png"/>