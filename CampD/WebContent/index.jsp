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
	<!-- 头部我的中心信息这一部分 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- 主体 -->
    <div class="main" id="activity_main">
    	<!-- 轮播图 -->
    	<div class="addWrap">
	        <div class="img-tap-show swipe" id="mySwipe">
	        	<div class="swipe-wrap">
				</div>
	        </div>
	        <ul id="position">
			</ul>
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
