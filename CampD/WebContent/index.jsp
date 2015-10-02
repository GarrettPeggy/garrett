<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<!-- <meta name=”viewport” content=”width=device-width, initial-scale=1″> -->
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/user/user.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部我的中心信息这一部分 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- 主体 -->
    <div class="main" id="activity_main">
    	<!-- 轮播图 -->
        <div class="img-tap-show">
	        <ul>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="1"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="2"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="3"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="4"/></li>
	        </ul>
	        <ol>
	            <li></li>
	            <li></li>
	            <li></li>
	            <li></li>
	        </ol>
	        <div style="clear:both;"></div>
        </div>
        <!-- end -->
        <!-- 热门活动 -->
        <div class="contain">
			<div class="c-top clearfix">
            	<div class="fl">热门活动</div>
                <div class="fr" onclick="Activity.populer(1)">更多&gt;</div>
            </div>
            <div class="ul-box">
                <ul class="data-list" id="activity_first_pop">
                </ul>
            </div>
            
            <!-- 精品场地 -->
            <div class="c-top clearfix">
            	<div class="fl">精品场地</div>
                <div class="fr" onclick="Space.hightLevel(1)">更多&gt;</div>
            </div>
            <div class="ul-box">
                <ul class="data-list" id="space_first_pop">
                </ul>
            </div>
            
        </div>
        <!-- end -->
    </div>
    <!-- end -->
    
</body>
</html>

<script type="text/javascript">
$(function(){
	Activity.list();
	Space.list();
});
</script>
