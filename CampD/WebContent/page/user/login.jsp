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
    	<a class="head-left-icon" href=###>
        	<img src="${rmtResPath}/static/images/back.png" width="13" height="22"/>
        </a>
        <div class="head-content">登录</div>
    </div>
	<!-- end -->
    <div class="main">
    	<form id="loginForm">
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
					<button class="btn orange-btn" onclick="User.register();">确认登录</button>
	            </div>
	            <p>
	            	<a href="${ctx}/user/toRegister.do">没有账号，点此注册</a>
	            </p>
	        </div>
        </form>
    </div>
</body>
</html>