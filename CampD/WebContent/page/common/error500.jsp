<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css"
	href="${rmtResPath}/5/0/static/css/base.css?_v=${vs}" />
<link rel="stylesheet" type="text/css"
	href="${rmtResPath}/5/0/static/css/global.css?_v=${vs}" />
<link rel="stylesheet" type="text/css"
	href="${rmtResPath}/5/0/static/css/main.css?_v=${vs}" />
<link rel="shortcut icon"
	href="${locResPath}/web/meta/images/login/logo.png" />
<title>错误通知</title>
</head>
<body>
	<div class="nopage">
		<div class="nopage-pic"></div>
		<dl>
			<dt>咦~页面不见了~</dt>
			<dd>${errorJsonView.returnMsg}</dd>
			<dd>请核对您输入的页面地址是否正确哦~</dd>
			<br>
			<dd>
				<a href="${ctx}/main/index.do">返回首页</a>
			</dd>
			<br>
			<!-- <dd style="font-size: 12px;">© 2009-2014 上海帜讯信息技术有限公司</dd> -->
			<dd style="font-size: 12px;"></dd>
		</dl>
	</div>
</body>
</html>