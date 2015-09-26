<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
//js中请求后台的base路径
var BASE_PATH = '${ctx}';
//js中远程静态文件base路径(包括:css,字体,图片)
var RMT_RES_BASE_PATH = '${rmtResPath}';
//js中本地静态文件base路径(包括:js,特殊的本地图片,所有插件的文件)
var LOC_RES_BASE_PATH = '${locResPath}';
</script>

<link rel="stylesheet" href="${rmtResPath}/static/css/bootstrap.min.css?_v=${vs}" />
<link rel="stylesheet" href="${rmtResPath}/static/css/font-awesome.min.css?_v=${vs}" />
<link rel="stylesheet" href="${rmtResPath}/static/css/ace-fonts.min.css?_v=${vs}" />
<link rel="stylesheet" href="${rmtResPath}/static/css/ace.min.css?_v=${vs}" class="ace-main-stylesheet" id="main-ace-style" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${rmtResPath}/static/css/ace-part2.min.css?_v=${vs}" class="ace-main-stylesheet" />
<![endif]-->

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${rmtResPath}/static/css/ace-ie.min.css?_v=${vs}" />
<![endif]-->

<link rel="shortcut icon" href="${rmtResPath}/static/images/favicon.ico"/>
<link rel="stylesheet" href="${rmtResPath}/static/css/campD.css?_v=${vs}" />
<!-- inline styles related to this page -->


<!-- ace settings handler -->
<script src="${locResPath}/static/js/ace-extra.min.js?_v=${vs}"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
	<script src="${locResPath}/static/js/html5shiv.min.js?_v=${vs}"></script>
	<script src="${locResPath}/static/js/respond.min.js?_v=${vs}"></script>
<![endif]-->

<!-- basic scripts -->

<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document .write("<script src='${locResPath}/static/js/jquery.min.js?_v=${vs}'>"+ "<"+"/script>");
	</script>
<!-- <![endif]-->

<!--[if IE]>
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${locResPath}/static/js/jquery1x.min.js?_v=${vs}'>"+"<"+"/script>");
	</script>
<![endif]-->
<script type="text/javascript">
	if ('ontouchstart' in document.documentElement){
		document.write("<script src='${locResPath}/static/js/jquery.mobile.custom.min.js?_v=${vs}'>" + "<"+"/script>");
	}
</script>
<script src="${locResPath}/static/js/bootstrap.min.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/js/common/jquery.form.js?_v=${vs}"></script>
<script src="${locResPath}/static/js/ace-elements.min.js?_v=${vs}"></script>
<script src="${locResPath}/static/js/ace.min.js?_v=${vs}"></script>

<!-- user common scripts -->
<script type="text/javascript" src="${locResPath}/static/js/common/validation.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/js/common/common.js?_v=${vs}"></script>

