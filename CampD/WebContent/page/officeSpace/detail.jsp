<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>${officeMap.officeInfo.name }</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" href="${locResPath}/static/common/swipe/css/swipe.css?_v=${vs}"/>
	<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/swipe/js/swipe.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/share/jquery.qrcode.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/share/share.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix ">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">空间详情</div>
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/guanzhu.png" onclick="Header.shareCD()" width="28" height="28"/>
        </div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
	    <!-- 活动概括与地点 -->
    	<div class="ac-detail-title retina-1px-border-bottom">
	    	<!-- 场地轮播图 -->
	    	<div class="addWrap">
		        <div class="img-tap-show-space swipe" id="mySwipe">
		        	<div class="swipe-wrap" onclick="Space.showMax();">
		        		<c:if test="${!empty officeMap.officeInfo.show_images}">
						    <c:forEach var="image_src" items="${fn:split(officeMap.officeInfo.show_images, ',')}" varStatus="status">
						    	<div>
							    	<a href="javascript:;">
							    		<img src="${sysConfig.ossResUrl}${image_src}" class="img-responsive" alt="${ status.index + 1}"/>
							    	</a>
						    	</div>
						    </c:forEach>
						</c:if>
		           </div>
		        </div>
		        <ul id="position"  style="z-index:1;bottom:122px;">
		        	<c:if test="${!empty officeMap.officeInfo.show_images}">
		        		<c:forEach var="image_src" items="${fn:split(officeMap.officeInfo.show_images, ',')}" varStatus="status">
		        			<c:if test="${status.index == 0}">
		        				<li class="cur"></li>
		        			</c:if>
		        			<c:if test="${status.index != 0}">
		        				<li></li>
		        			</c:if>
		        		</c:forEach>
		        	</c:if>
				</ul>	
				<div class="worddescription">${officeMap.officeInfo.name }</div>
	        </div>
	        <!-- end -->
            <div class="adt-desc">
                 <dl class="fontSize14 color94 clearfix mat15" >
                    <dt class="fl newfl"><img  src="${rmtResPath}/static/images/CHANGDI_jiaotong.png" width="17" height="17"/></dt>
                    <dd class="fl newfr1" >${officeMap.officeInfo.traffic }</dd> 
                </dl>
                <dl class="fontSize14  clearfix color94 newmat" onclick="toBaiDuMap('${officeMap.officeInfo.address}')">
                    <dt class="fl newfl"><img src="${rmtResPath}/static/images/CHANGDI_dress.png"  width="17" height="17"/></dt>
                    <dd class="fl newfr2" >${officeMap.officeInfo.address }</dd> 
                    <dd><img class="fr mappic"  src="${rmtResPath}/static/images/baidumap.png" width="30" height="26"/></dd>
               </dl> 
            </div>
        </div>
        <!-- end -->
        <!-- 活动主办方、赞助方 -->
        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
        	<div class="adm-line clearfix bt-line" >
            	<span class="fl">费用</span>
            	<c:if test="${officeMap.officeInfo.cost eq 0}"><span class="fr color94 red" style="color:red;">免费</span></c:if>
				<c:if test="${officeMap.officeInfo.cost gt 0}"><span class="fr color94"><fmt:parseNumber integerOnly="true" value="${officeMap.officeInfo.cost}" />元/人月</span></c:if>
            </div>
            <div class="adm-line clearfix">
            	<span class="fl">电话</span>
                <span class="fr color94">${officeMap.officeInfo.contact}</span>
            </div>
        </div>

        <!-- end -->
        <!-- 活动详细介绍 -->
        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
        	<div class="add-title bt-line">
            	详细介绍
            </div>
            <div class="add-desc">
            	${officeMap.officeInfo.description }
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
    
    <!-- 点击图片放大  需要显示时删除hide -->
	<div class="max hide" id="picMax" onclick="Space.hideMax();">
	   <div class="addWrap addWrap1 showmax">
		        <div class="img-tap-show-space swipe" id="mySwipe1">
		        	<div class="swipe-wrap">
		        		<c:if test="${!empty officeMap.officeInfo.show_images}">
						    <c:forEach var="image_src" items="${fn:split(officeMap.officeInfo.show_images, ',')}" varStatus="status">
						    	<div>
							    	<a href="javascript:;">
							    		<img src="${sysConfig.ossResUrl}${image_src}" alt="${ status.index + 1}" width="100%" />
							    	</a>
						    	</div>
						    </c:forEach>
						</c:if>
		           </div>
		        </div>
		        <div class="worddescription1">${officeMap.officeInfo.name }</div>
		        <ul id="position1"  style="z-index:1;">
		        	<c:if test="${!empty officeMap.officeInfo.show_images}">
		        		<c:forEach var="image_src" items="${fn:split(officeMap.officeInfo.show_images, ',')}" varStatus="status">
		        			<c:if test="${status.index == 0}">
		        				<li class="cur"></li>
		        			</c:if>
		        			<c:if test="${status.index != 0}">
		        				<li></li>
		        			</c:if>
		        		</c:forEach>
		        	</c:if>
				</ul>	
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
   	         <li class="retina-1px-border-bottom1"  onclick="Header.classify()">
   	          	<img class="tu" src="${rmtResPath}/static/images/activity.png" width="22" height="22">活动
             </li>
             <li class="retina-1px-border-bottom1"  onclick="Header.spaceIndex()">
   	            <img class="tu" src="${rmtResPath}/static/images/place.png" width="22" height="22">场地
             </li>
             <li class="retina-1px-border-bottom1"  onclick="Header.toGiftIndex()">
   	            <img class="tu" src="${rmtResPath}/static/images/initiate.png" width="22" height="22">礼品
             </li>
             <li class="retina-1px-border-bottom1" onclick="Header.toContact()">
        	   <img src="${rmtResPath}/static/images/contact.png" width="22" height="22"/>
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
		Space.tapShow('position','mySwipe');
		// 底部按钮居中
		$("#submit_tel").css("left",$(".newfoot-left-icon").width()+"px");
		Header.initFootIcon();
		$("#picMax").css("left",window.screen.width);
	});
</script>
</html>
