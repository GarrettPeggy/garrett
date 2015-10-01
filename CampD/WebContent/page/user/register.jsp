<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/user/user.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">注册</div>
    </div>
	<!-- end -->
    <!-- 主体 -->
    <div class="main">
    	<form id="registerForm">
	    	<div class="login-form form-box">
	        	<div class="line retina-1px-border-bottom">
	            	<div class="input-icon">
	                	<img src="${rmtResPath}/static/images/user_icon.png" width="18" height="18">
	                </div>
	            	<input type="text" class="text-input" name="userName" id="userName" placeholder="请输入姓名"/>
	            </div>
	            <div class="line mab20">
	            	<div class="input-icon">
	                	<img src="${rmtResPath}/static/images/phone_icon.png" width="14" height="25">
	                </div>
	            	<input type="tel" class="text-input" name="mdn" id="mdn" placeholder="请输入手机号码"/>
	            </div>
	            <div class="line textCenter mab23">
					<button type="button" class="btn orange-btn" onclick="User.register();">确认注册</button>
	            </div>
	            <p>
	            	<a href="${ctx}/user/toLogin.do">已有账号，点此直接登录</a>
	            </p>
	        </div>
        </form>
    </div>
	<!-- end -->
</body>
</html>