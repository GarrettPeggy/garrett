<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
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
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="categoryId" name="categoryId" value="${categoryId }"/>
    	<div class="ul-box">
        	<ul class="data-list" id="activity_popu">
        		<c:choose>
	        		<c:when test="${empty jsonview.activityList }">
	        			<div class="textCenter mat15">
		        			<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
            				<div class="ui-tips-box mat10">
            					<span class="color94">还没有任何活动哦!点击</span>
                				<a href="${ctx}/activity/addUI.do" class="colorBlue">主办活动</a><br/>
                				<p class="mat15 color94">提交你的活动需求吧</p>
            				</div>
		        		</div>
	        		</c:when>
	        		<c:otherwise></c:otherwise>
	            </c:choose>
            </ul>
        </div>
    </div>
    <!-- end -->
</body>
<script type="text/javascript">
$(function(){
	Activity.search("/activity/getActivityList.do",false);
});
</script>
</html>