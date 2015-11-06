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
	<div class="header clearfix mab0">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">账号信息</div>
    </div>
	<!-- end -->
    <div class="main">
    	<form id="editForm">
    		<input type="hidden" name="userId" id="userId" value="${USER_INFO.id}">
    		<input type="hidden" name="email" id="email" value="${USER_INFO.email}">
    		<div class="complex-form">
	            <div class="complex-line retina-1px-border-bottom">
	                <div class="left-tip">
	                	<img src="${rmtResPath}/static/images/user_icon_blue.png" width="18" height="18" class="mar15"/>
	                    <span>姓名：</span>
	                </div>
	                <div class="center-input">
	                	<input type="text" class="text-input" name="userName" id="userName" value="${USER_INFO.userName}"/>
	                </div>
	                <div class="right-op">
	                	<img src="${rmtResPath}/static/images/clear_input.png" width="22" height="22"/>
	                </div>
	            </div>
	            <div class="complex-line mab25">
	                <div class="left-tip">
	                	<img src="${rmtResPath}/static/images/phone_icon_blue.png" width="14" height="25" class="mar15"/>
	                    <span>手机：</span>
	                </div>
	                <div class="center-input">
	                	<input type="tel" class="text-input" name="mdn" id="mdn" disabled="disabled" value="${USER_INFO.mdn}"/>
	                </div>
	                <!-- 暂时屏蔽 -->
	                <%-- <div class="right-op">
	                	<img src="${rmtResPath}/static/images/clear_input.png" width="22" height="22"/>
	                </div> --%>
	            </div>
	            <div class="line textCenter">
	               <button class="btn orange-btn" type="button" onclick="User.update();">保存</button>
	            </div>
	        </div>
        </form>
    </div>
</body>
<script type="text/javascript">
// 当点击清除图标的时候清空输入框
$(".right-op").click(function(){
	$(this).parent().find("input").val("");
});
</script>
</html>