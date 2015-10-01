<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
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
        <div class="head-content">活动类型</div>
        <a class="head-right-icon clearfix">
        	储存
        </a>
    </div>
	<!-- end -->
	
	<div class="main">
    	<div class="sub-ac-form type-form">
        	<div class="line clearfix active">
            	<div class="fl">创业</div>
                <div class="fr"></div>
            </div>
            <div class="line clearfix">
            	<div class="fl">商务</div>
                <div class="fr"></div>
            </div>
            <div class="line clearfix">
            	<div class="fl">交友</div>
                <div class="fr"></div>
            </div>
            <div class="line clearfix">
            	<div class="fl">玩乐</div>
                <div class="fr"></div>
            </div>
        </div>
        <!-- 
        <div class="btn-box">
        	<button class="btn orange-btn">立即提交</button>
        </div>
         -->
    </div>
	
</body>
</html>