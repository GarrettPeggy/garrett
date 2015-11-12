<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" href="${locResPath}/static/common/swipe/css/swipe.css?_v=${vs}"/>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/swipe/js/swipe.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/share/jquery.qrcode.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/share/share.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix">
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
		        <ul id="position">
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
	        </div>
	        <!-- end -->
            <div class="adt-desc">
                <div class="fontSize17">${jsonview.spaceInfo.name }</div>
              <!--   <div class="fontSize14 color94 clearfix">
                    <span class="fl">交通概况</span>
                    <span class="fl">${jsonview.spaceInfo.traffic }</span>
                </div>
                <div class="fontSize14 mat7 pdb10 clearfix color94">
                	<span class="fl">具体地址</span>
                    <span class="fl">${jsonview.spaceInfo.adress }</span>
                </div> -->
                 <dl class="fontSize14 color94 clearfix">
                           <dt class="fl newfl">交通概况</dt>
                           <dd class="newfr">${jsonview.spaceInfo.traffic }</dd> 
                </dl>
                <dl class="fontSize14 mat7 pdb10 clearfix color94" style="margin-right:8px;">
                        <dt class="fl newfl">具体地址</dt>
                        <dd class="newfr">${jsonview.spaceInfo.adress }</dd> 
               </dl>
                
            </div>
        </div>
        <!-- end -->
        <!-- 活动主办方、赞助方 -->
        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
        	<div class="adm-line retina-1px-border-bottom clearfix">
            	<span class="fl">费用</span>
                <span class="fr color94"><fmt:parseNumber integerOnly="true" value="${jsonview.spaceInfo.cost}" />元/小时</span>
                <!-- 把金额每3位画一个逗号 -->
                <%-- <fmt:formatNumber type="number" value="${jsonview.spaceInfo.cost}" maxFractionDigits="0"/> --%>
            </div>
            <div class="adm-line clearfix">
            	<span class="fl">容量</span>
                <span class="fr color94">${jsonview.spaceInfo.capacity }人</span>
            </div>
        </div>
        <!-- end -->
        <!-- 活动详细介绍 -->
        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
        	<div class="add-title retina-1px-border-bottom">
            	详细介绍
            </div>
            <div class="add-desc">
            	${jsonview.spaceInfo.description }
            </div>
        </div>
        <!-- end -->
        <!-- 按钮 -->
        <div class="btn-box">
        	<a class="btn orange-btn" href="tel:15601925235">电话</a>
        </div>
        <!-- end -->
    </div>
    <!-- end -->
    
    <!-- 蒙层 需要显示时删除hide -->
	<div class="mc hide"></div>
    <!-- end -->

	<!-- 分享 需要显示时删除hide -->
    <div class="tc-modal share-modal hide" id="space_share">
        <img class="ma" alt="二维码" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg" >
		<!-- <div id="code" style="margin: 20px 20px 20px 20px"></div> -->
       <!--  <div class="bottom-close retina-1px-border-top">
         	<a onclick="Space.cancel()">取&nbsp;&nbsp;消</a>        
             </div> 
        -->
          <span class="more">关注我们，获取更多活动资源</span>
	      <img class="close"  src="${rmtResPath}/static/images/login/closed.png"  onclick="Space.cancel()"/>
    </div>
    <!-- end -->
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
	});
</script>
</html>
