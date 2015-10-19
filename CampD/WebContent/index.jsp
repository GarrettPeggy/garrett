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
        	<%-- 
	        <ul id="img-tap-show-ul">
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="1"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="2"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="3"/></li>
	            <li><img src="${rmtResPath}/static/images/index_img_tap.png" width="100%" height="125" alt="4"/></li>
	        </ul>
	        <ol id="img-tap-show-ol">
	            <!-- <li></li>
	            <li></li>
	            <li></li>
	            <li></li> -->
	        </ol>
	        <div style="clear:both;"></div>
	         --%>	
	         <ul id="img-tap-show-ul">
	         	<!--
			    <li><a href="#" target="_blank"><img src="images/1.jpg" alt="科e互联网站建设团队"></a></li>
				<li><a href="#" target="_blank"><img src="images/2.jpg" alt="网页特效集锦"></a></li>
				<li><a href="#" target="_blank"><img src="images/3.jpg" alt="JS代码素材库"></a></li>
				<li><a href="#" target="_blank"><img src="images/4.jpg" alt="用心建站用心服务"></a></li>
				<li><a href="#" target="_blank"><img src="images/5.jpg" alt="学会用才能学会写"></a></li>
				-->
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
