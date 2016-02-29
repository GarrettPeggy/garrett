<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>${giftMap.giftMap.name}</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix ">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">礼品详情</div>
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/guanzhu.png" onclick="Header.shareCD()" width="28" height="28"/>
        </div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
	    <!-- 活动概括与地点 -->
    	<div class="giftpic" onclick="Gift.showMax();">
   	        <img class="giftimg"  src="${sysConfig.ossResUrl}${giftMap.giftMap.show_image}" width="100%" height="156"/>
			<div class="worddescription">${giftMap.giftMap.name }</div>
	    </div>  
	    <!-- end -->
          
        <!-- 礼品类型 活动类型 活动城市-->
        <div class="ac-detail-mechanism retina-1px-border-bottom ">
        	<div class="act  clearfix bt-line">
            	<span class="fl actstyle" >礼品类型：</span>
                <span class="fl giftstyle" >${systemConst.formMap[giftMap.giftMap.form]}</span>
            </div>
            <div class="gift-actstyle clearfix bt-line">
            	<span class="fl actstyle">适用活动:</span>
            	<div class="category fr">
                	<c:if test="${not empty giftMap.giftMap.work_for}">
	                	<c:forEach var="category" items="${ fn:split(giftMap.giftMap.work_for, ',') }">
	                     <span><img src="${rmtResPath}/static/images/category_${category}.png" width="25" height="35"></span>
	                    </c:forEach>
                    </c:if>
                 </div>
            </div>
            <div class="act clearfix ">
            	<span class="fl actstyle">适用城市:</span>
                   <div class="city fr">
                     <span>${giftMap.giftMap.work_for_city}</span>
                   </div> 
            </div>
        </div>
        
        <!--  -->
        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
        	<div class="adm-line retina-1px-border-bottom clearfix">
            	<span class="address font">${giftMap.giftMap.provider_name }</span>
            </div>
        	 <div class="adm-line  clearfix">
            	<%-- <img class="fl dresspic" src="${rmtResPath}/static/images/CHANGDI_dress.png" width="17" height="17"> --%>
                <span class="fl">${giftMap.giftMap.provider_adress }</span>
             </div>
        </div> 

        <!-- end -->
        <!-- 活动详细介绍 -->
        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
        	<div class="add-title retina-1px-border-bottom">
            	详细介绍
            </div>
            <div class="add-desc">
            	${giftMap.giftMap.description }
            </div>
        </div>
        <!-- end -->
        <!-- 按钮 -->
        <div class="btn-box footer">
          <div class="head-left-icon newfoot-left-icon">
        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
          </div>
        
          <a class="btn orange-btn btnheight" id="submit_tel"  href="tel:15601925235"><img class="cellphone" src="${rmtResPath}/static/images/tel_02.png"/>联系电话</a>
         
          <div class="head-right-icon newfoot-right-icon" onclick="Header.toRightMenu()">
        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
          </div>
        </div>
        
        <!-- end -->
    </div>
    <!-- end -->
    
    <!-- banner点击时图片变大 显示时left为0，隐藏时left为屏幕宽度-->
	<div class="max hide" id="picMax" onclick="Gift.hideMax();">
		<div class="banner showmax">
   	        <img  src="${sysConfig.ossResUrl}${giftMap.giftMap.show_image}" width="100%"/>
			<span class="hdname1">${giftMap.giftMap.name }</span>
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
    
    <!-- footer left-icon -->
    <div class="newindex-nav hide" id="avtivity_nav" >
         <ul>
   	        <%--  <li class="retina-1px-border-bottom1"  onclick="Header.classify()">
   	          	<img class="tu fl" src="${rmtResPath}/static/images/activity.png" width="22" height="22">活动
             </li> --%>
              <li class="retina-1px-border-bottom1"  onclick="Header.officeSpaceIndex()">
   	            <img class="tu fl" src="${rmtResPath}/static/images/officeSpace.png" width="22" height="22">办公空间
             </li>
             <li class="retina-1px-border-bottom1"  onclick="Header.spaceIndex()">
   	            <img class="tu fl" src="${rmtResPath}/static/images/place.png" width="22" height="22">活动场地
             </li>
              <li class="retina-1px-border-bottom1"  onclick="Header.toGiftIndex()">
   	            <img class="tu fl" src="${rmtResPath}/static/images/initiate.png" width="22" height="22">礼品赞助
             </li>
             <li class="retina-1px-border-bottom1" onclick="Header.toContact()">
        	   <img class="tu fl" src="${rmtResPath}/static/images/contact.png" width="22" height="22"/>
                              联系我们
        	</li>
       	</ul>
    </div>
</body>
<script type="text/javascript">
	
	$(function(){
		// 让全屏显示的图片垂直居中
		$(".max img").css("max-height", window.innerHeight*0.65);
		$(".max img").css("min-height", window.innerHeight*0.35);
		// 底部按钮居中
		$("#submit_tel").css("left",$(".newfoot-left-icon").width()+"px");
		Header.initFootIcon();
		$("#picMax").css("left",window.screen.width);
	});
</script>
</html>
