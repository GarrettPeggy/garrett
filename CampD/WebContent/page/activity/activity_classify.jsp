<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>

<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- 导航栏 -->
    <div class="nav-box retina-1px-border-bottom hdmat51" id="activity_nav">
    	<ul class="nav-list clearfix" id="activity_head">
        	<li onclick="Activity.category(${systemConst.ACTIVITY_CATEGORY_0 })">
            	<img src="${rmtResPath}/static/images/business_icon_01.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.ACTIVITY_CATEGORY_0] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.ACTIVITY_CATEGORY_1 })">
            	<img src="${rmtResPath}/static/images/business_icon_02.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.ACTIVITY_CATEGORY_1] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.ACTIVITY_CATEGORY_2 })">
            	<img src="${rmtResPath}/static/images/business_icon_03.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.ACTIVITY_CATEGORY_2] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.ACTIVITY_CATEGORY_3 })">
            	<img src="${rmtResPath}/static/images/business_icon_04.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.ACTIVITY_CATEGORY_3] }</p>
            </li>
        </ul>
    </div>
	<!-- end -->
    <!-- 主体 -->
    <div class="main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="6"/>
    	<div class="outer">
	    	<div class="ul-box inner" id="activity_main">
	        	<ul class="data-list" id="activity_popu">
	        		
	            </ul>
	        </div>
         </div>
    </div>
    <!-- end -->
</body>
<script type="text/javascript">
$(function(){
	Activity.search("/activity/getActivityList.do",false);
	$(".outer").height(window.innerHeight-($(".header").height()+$("#activity_nav").height()+10));
	setTimeout(function(){
		Activity.droploadPage();
	},1000);
});
</script>
</html>
