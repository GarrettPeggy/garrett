<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="shortcut icon" href="${resBasePath}/static/images/favicon.ico" />
<%@ include file="/page/common/jsCss.jsp"%>
<title>页面未找到</title>
<script type="text/javascript">
	function timeout() {
		setTimeout(function() {
			location.href = BASE_PATH + '/';
		}, 5000);
	}
</script>
</head>
<body style="background-color: #ececec;" onload="timeout();">
	<div class="other-top">
		<div class="logo-c">
			<img src="${resBasePath}/static/images/logo-c.png" />
		</div>
	</div>

	<div class="other-content">
		<div class="four-pic">
			<img src="${resBasePath}/static/images/error.gif" />
		</div>
		<div class="other-account">
			<p>
				<span>抱歉~您查看的页面出错啦！</span>
			</p>
			<p>SORRY, YOU VIEW THE PAGE ERROR!</p>
		</div>
		<div class="other-goback">
			5秒后返回首页，如果没有请点击<a href="${ctx}/">手动跳转</a>
		</div>
	</div>

	<jsp:include page="/page/common/footer.jsp" flush="true"></jsp:include>
</body>
</html>