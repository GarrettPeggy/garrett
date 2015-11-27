<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>${jsonview.spaceInfo.name }</title>
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
        <div class="head-content">场地详情</div>
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/guanzhu.png" onclick="Space.share()" width="28" height="28"/>
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
		        	<div class="swipe-wrap">
		        		<c:if test="${!empty jsonview.spaceInfo.show_images}">
						    <c:forEach var="image_src" items="${fn:split(jsonview.spaceInfo.show_images, ',')}" varStatus="status">
						    	<div>
							    	<a href="javascript:;">
							    		<img src="${sysConfig.ossResUrl}${image_src}" class="img-responsive" alt="${ status.index + 1}"/>
							    	</a>
						    	</div>
						    </c:forEach>
						</c:if>
		           </div>
		        </div>
		        <ul id="position" class="newposition">
		        	<c:if test="${!empty jsonview.spaceInfo.show_images}">
		        		<c:forEach var="image_src" items="${fn:split(jsonview.spaceInfo.show_images, ',')}" varStatus="status">
		        			<c:if test="${status.index == 0}">
		        				<li class="cur"></li>
		        			</c:if>
		        			<c:if test="${status.index != 0}">
		        				<li></li>
		        			</c:if>
		        		</c:forEach>
		        	</c:if>
				</ul>	
				<div class=" worddescription">${jsonview.spaceInfo.name }</div>
	        </div>
	        <!-- end -->
            <div class="adt-desc">
                 <dl class="fontSize14 color94 clearfix mat15">
                    <dt class="fl newfl"><img  src="${rmtResPath}/static/images/CHANGDI_jiaotong.png" width="17" height="17"/></dt>
                    <dd class="fl newfr1">${jsonview.spaceInfo.traffic }</dd> 
                </dl>
                <dl class="fontSize14  clearfix color94 newmat ">
                    <dt class="fl newfl"><img  src="${rmtResPath}/static/images/CHANGDI_dress.png"  width="17" height="17"/></dt>
                    <dd class="fl newfr2">${jsonview.spaceInfo.adress }</dd> 
               </dl> 
                
            </div>
        </div>
        <!-- end -->
        <!-- 活动主办方、赞助方 -->
        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
        	<div class="adm-line clearfix bt_line" >
            	<span class="fl">费用</span>
                <span class="fr color94"><fmt:parseNumber integerOnly="true" value="${jsonview.spaceInfo.cost}" />元/小时</span>
                <!-- 把金额每3位画一个逗号 -->
                <%-- <fmt:formatNumber type="number" value="${jsonview.spaceInfo.cost}" maxFractionDigits="0"/> --%>
            </div>
            <div class="adm-line clearfix " >
            	<span class="fl">容量</span>
                <span class="fr color94">${jsonview.spaceInfo.capacity }人</span>
            </div>
        </div>
        <!--适用活动和基础设施  -->
         <div class="activebase retina-1px-border-bottom retina-1px-border-top">
        	<div class=" retina-1px-border-bottom  padt10 bt_line">
            	<span class="fl percent">适用活动</span>
                <ul class="consider">
                	<c:if test="${not empty jsonview.spaceInfo.work_for}">
	                	<c:forEach var="category" items="${ fn:split(jsonview.spaceInfo.work_for, ',') }">
	                      <li class="considerpic"><img src="${rmtResPath}/static/images/category_${category}.png" width="25" height="25"></li>
	                    </c:forEach>
                    </c:if>
                 </ul>
            </div>
            <div class="padt10">
            	<span class="fl percent">基础设施</span>
                   <ul class="consider">
                    <c:if test="${not empty jsonview.spaceInfo.infrastructure}">
	                   	<c:forEach var="infrastructure" items="${ fn:split(jsonview.spaceInfo.infrastructure, ',') }">
	                      <li class="considerpic"><img src="${rmtResPath}/static/images/infrastructure_${infrastructure}.png" width="25" height="25"></li>
	                    </c:forEach>
                    </c:if>
                 </ul> 
            </div>
        </div>

        <!-- end -->
        <!-- 活动详细介绍 -->
        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
        	<div class="add-title bt_line">
            	详细介绍
            </div>
            <div class="add-desc">
            	${jsonview.spaceInfo.description }
            </div>
        </div>
        <!-- end -->
        <!-- 按钮 -->
        <div class="btn-box footer">
          <div class="head-left-icon newfoot-left-icon">
        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
          </div>
        
        	<a class="btn orange-btn btnheight" id="submit_tel"  href="tel:15601925235"><img class="cellphone" src="${rmtResPath}/static/images/tel_02.png"/>联系电话</a>
         
          <div class="head-right-icon newfoot-right-icon">
        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
          </div>
        </div>
        
        <!-- end -->
    </div>
    <!-- end -->
    
    <!-- 蒙层 需要显示时删除hide -->
	<div class="mc hide"></div>
    <!-- end -->

	<!-- 分享 需要显示时删除hide -->
    <div class="tc-modal share-modal hide newtc-modal" id="space_share">
        <img class="ma" alt="二维码" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg" >
        <span class="more">关注我们，获取更多活动资源</span>
	    <img class="close"  src="${rmtResPath}/static/images/login/closed.png"  onclick="Space.cancel()"/>
    </div>
    <!-- end -->
    
    <!-- footer left-icon -->
	       <div class="newindex-nav hide" id="avtivity_nav" >
   	         <ul>
       	         <li class="retina-1px-border-bottom1"  onclick="Activity.classify()">
       	          	<img class="tu" src="${rmtResPath}/static/images/activity.png" width="22" height="22">活动
                 </li>
                 <li class="retina-1px-border-bottom1"  onclick="Space.classify()">
       	            <img class="tu" src="${rmtResPath}/static/images/place.png" width="22" height="22">场地
                 </li>
                 <li class="retina-1px-border-bottom1" onclick="Header.toContact()">
            	   <img src="${rmtResPath}/static/images/initiate.png" width="22" height="22"/>
                                  联系我们
            	</li>
           	</ul>
           </div>
	    
	    <!-- footer right-icon-->
	    <div class="footer-newperson-right hide" id="activity_person">
     	  <ul>
         	<li class="line clearfix" onclick="Activity.signUp()">
             	<img src="${rmtResPath}/static/images/p1.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">报名的活动</div>
          </li>
          <%-- <li class="line clearfix" onclick="Activity.sponsored()">
             	<img src="${rmtResPath}/static/images/p2.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">举办的活动</div>
          </li> --%>
          <c:if test="${empty USER_INFO}">
	          <li class="line clearfix" onclick="Header.toLogin()">
	             	<img src="${rmtResPath}/static/images/p3.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">登录</div>
	          </li>
          </c:if>
          <!-- 以下两个通过增加hide样式显示隐藏 -->
          <c:if test="${!empty USER_INFO}">
          	  <li class="line clearfix" onclick="Header.toUpdate()">
	             	<img src="${rmtResPath}/static/images/p3.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">${USER_INFO.userName}</div>
	          </li>
	          <li class="line clearfix" onclick="Header.toQuit()">
	             	<img src="${rmtResPath}/static/images/p4.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">退出</div>
	          </li>
          </c:if>
          <c:if test="${empty USER_INFO}">
	          <li class="line clearfix" onclick="Header.toRegister()">
	             	<img src="${rmtResPath}/static/images/p5.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">注册</div>
	          </li>
          </c:if>
        </ul>
      </div>
    
</body>
<script type="text/javascript">
	Space.share=function(){
		//场地分享
		var isHide = $("#space_share").hasClass("hide");
		if(isHide){
			$("#space_share").removeClass("hide");
			$("#space_share").height($(document).height());
		} else{
			$("#space_share").addClass("hide");
		}
		//Share.qcode();
	};
	
	$(function(){
		Space.tapShow();
		// 底部按钮居中
		$("#submit_tel").css("left",$(".newfoot-left-icon").width()+"px");
		Header.initFootIcon();
	});
</script>
</html>
