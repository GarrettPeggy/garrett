<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
</head>
<body onload="timeout();">
	<!-- 头部 -->
	<div class="header clearfix">
    	<%-- <a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a> --%>
        <div class="head-content">注册成功</div>
    </div>
	<!-- end -->
    <!-- 主体 -->
    <div class="main textCenter mat15">
    	<div class="forward-img-box mab10">
        	<img src="${rmtResPath}/static/images/yes.png" width="49" height="49"/>
        </div>
        <div class="forward-tip1 mab10">
        	<span class="forward-tip1-l">恭喜您</span>
            <span class="forward-tip1-r">注册成功！</span>
        </div>
        <div class="forward-tip2">
        	跳转中
            <img src="${rmtResPath}/static/images/forward.png" width="43" height="38"/>
        </div>
    </div>
	<!-- end -->
</body>
<script type="text/javascript">
function timeout(){
	setTimeout(function(){
		window.location.href = BASE_PATH;
	},2000);
}
</script>
</html>