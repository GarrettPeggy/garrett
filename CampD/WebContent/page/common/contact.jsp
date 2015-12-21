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
	<div class="header ac-detail-header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">联系我们</div>
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/guanzhu.png" onclick="Header.shareCD()" width="28" height="28"/>
        </div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
	    <!-- 活动场地 联系我们 -->
    	<div class="contactdetail" >
           <div class="fl flpic" > 
               <img src="${rmtResPath}/static/images/LXWM_place.png" width="70" height="70"/>
           </div>
           <div class="fl flword " >
              <div class="title">合作租赁，提供场地</div>
                 <a class="btn" href="${ctx}/space/getSpaceInfoList.do?curPage=1&pageLimit=6&isUserAuth=false">活动场地</a>
                 <a class="btn" href="tel:15601925235">联系我们</a>  
           </div>
        </div>
        <div class="contactdetail" >
           <div class="fl flpic" > 
               <img src="${rmtResPath}/static/images/LXWM_present.png" width="70" height="70"/>
           </div>
           <div class="fl flword " >
              <div class="title">赞助活动，提供礼品</div>
                 <a class="btn" href="${ctx}/gift/indexList.do?curPage=1&pageLimit=6&isUserAuth=false">活动礼品</a>
                 <a class="btn" href="tel:15601925235">联系我们</a>  
           </div>
        </div>

    </div>
    <!-- end -->
    <!-- 分享 需要显示时删除hide -->
    <div class="tc-modal share-modal hide newtc-modal" id="guanzhu">
        <img class="ma" alt="二维码" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg" >
        <span class="more">关注我们，获取更多活动资源</span>
	    <img class="close"  src="${rmtResPath}/static/images/login/closed.png"  onclick="Header.cancelShareCD()"/>
    </div>
    <!-- end -->
</body>
</html>