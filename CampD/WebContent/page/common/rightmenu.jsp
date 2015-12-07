<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
	    <!-- footer right-icon menu -->
	    <div class="wode"  id="activity_person">
          <div class="logoword"><img src="${rmtResPath}/static/images/slogn.png" width="150" height="54"/></div>
          <div class="list-des">
                 <div class="listmar" onclick="Header.signUp()">
                   <div class="list-pic"><img src="${rmtResPath}/static/images/bmhd.png" width="70" height="70"/></div>
                   <div class="list-word">报名活动</div>
                 </div>
                 <c:if test="${empty USER_INFO}">
	                 <div class="listmar" onclick="Header.toLogin()">
	                    <div class="list-pic"><img src="${rmtResPath}/static/images/login.png" width="70" height="70"/></div>
	                    <div class="list-word">登录</div>
	                 </div>
	                 <div class="listmar" onclick="Header.toRegister()"> 
	                    <div class="list-pic"><img src="${rmtResPath}/static/images/register.png" width="70" height="70"/></div>
	                    <div class="list-word">注册</div>
	                 </div>
                 </c:if>
                 <c:if test="${not empty USER_INFO}">
                 <div class="listmar" onclick="Header.toQuit()"> 
	                   <div class="list-pic"><img src="${rmtResPath}/static/images/quit.png" width="70" height="70"/></div>
	                    <div class="list-word">退出</div>
	                 </div>
                 </c:if>
          </div>
          <div class="close" onclick="back()">
          	<img src="${rmtResPath}/static/images/mine_close.png" width="20" height="20">
          </div>
      </div>
    </div>	
</body>
</html>