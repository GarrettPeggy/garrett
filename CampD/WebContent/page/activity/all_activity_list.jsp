<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
    <script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>
<body>	
	<!-- 头部 -->
	<div class="header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">${systemConst.categoryMap[categoryId] }活动</div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
    	<input type="hidden" id="pageSize" name="pageSize" value=""/>
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="6"/>
    	<input type="hidden" id="categoryId" name="categoryId" value="${categoryId }"/>
    	<div class="outer">
	    	<div class="ul-box inner">
	        	<ul class="data-list" id="activity_popu">
	        		
	            </ul>
	        </div>
       </div>
    </div>
    <!-- end -->
</body>
<script type="text/javascript">
$(function(){
	$(".outer").height(window.innerHeight-($(".header").height()+9));
	Activity.search("/activity/getActivityList.do",false);
	setTimeout(function(){
		Activity.droploadPage();
	},1000);
});
</script>
</html>