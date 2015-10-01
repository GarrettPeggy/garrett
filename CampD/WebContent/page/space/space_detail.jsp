<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" type="text/css" href="${locResPath}/static/css/nativeShare.css?_v=${vs}" />
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/nativeShare.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">场地详情</div>
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/share_icon.png" onclick="Space.share()" width="23" height="23"/>
        </div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
	    <!-- 活动概括与地点 -->
    	<div class="ac-detail-title retina-1px-border-bottom">
	        <img src="${rmtResPath}/static/images/example_img_big.png" width="100%" height="116"/>
            <div class="adt-desc">
                <div class="fontSize17">${jsonview.spaceInfo.name }</div>
                <div class="fontSize14 color94 clearfix">
                    <span class="fl">交通概况</span>
                    <span class="fr">${jsonview.spaceInfo.traffic }</span>
                </div>
                <div class="fontSize14 mat7 pdb10 clearfix color94">
                	<span class="fl">具体地址</span>
                    <span class="fr">${jsonview.spaceInfo.adress }</span>
                </div>
            </div>
        </div>
        <!-- end -->
        <!-- 活动主办方、赞助方 -->
        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
        	<div class="adm-line retina-1px-border-bottom clearfix">
            	<span class="fl">费用</span>
                <span class="fr color94">${jsonview.spaceInfo.cost }/小时</span>
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
    	<div id="nativeShare"></div>
    	<script>
		    var config = {
		        url:'http://blog.wangjunfeng.com',// 分享的网页链接
		        title:'王俊锋的个人博客',// 标题
		        desc:'王俊锋的个人博客',// 描述
		        img:'http://www.wangjunfeng.com/img/face.jpg',// 图片
		        img_title:'王俊锋的个人博客',// 图片标题
		        from:'王俊锋的博客' // 来源
		    };
    		var share_obj = new nativeShare('nativeShare',config);
		</script>
    	<%-- 
    	<ul class="clearfix">
        	<li>
            	<img src="${rmtResPath}/static/images/shaer_icon_04.png" width="58" height="58"/>
                <p class="mat7">收藏</p>
            </li>
            <li>
            	<img src="${rmtResPath}/static/images/shaer_icon_01.png" width="58" height="58"/>
                <p class="mat7">微信朋友圈</p>
            </li>
            <li>
            	<img src="${rmtResPath}/static/images/shaer_icon_02.png" width="58" height="58"/>
                <p class="mat7">微信好友</p>
            </li>
            <li>
            	<img src="${rmtResPath}/static/images/shaer_icon_03.png" width="58" height="58"/>
                <p class="mat7">QQ好友</p>
            </li>
        </ul>
         --%>
        <div class="bottom-close retina-1px-border-top">
        	<a onclick="Space.cancel()">取&nbsp;&nbsp;消</a>
        </div>
    </div>
    <!-- end -->
</body>
</html>