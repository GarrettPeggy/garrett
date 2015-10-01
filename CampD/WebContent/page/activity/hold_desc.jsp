<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header clearfix hold-type-header retina-1px-border-bottom">
    	<a class="head-left-icon" href=###>
        	取消
        </a>
        <div class="head-content">活动需求</div>
        <a class="head-right-icon clearfix">
        	完成
        </a>
    </div>
	<!-- end -->
	
	<div class="main">
    	<div class="sub-desc-form">
        	<textarea placeholder="请输入文字" rows="6" class="ac-desc-text"></textarea>
        </div>
    </div>
	
</body>
</html>