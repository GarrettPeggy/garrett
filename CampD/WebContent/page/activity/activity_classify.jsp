<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
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
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<div class="ul-box" id="activity_main">
        	<ul class="data-list" id="activity_popu">
        		<c:choose>
        			<c:when test="${empty jsonview.activityList }">
        				<!-- 无主办活动 -->
        				<div class="textCenter mat15">
        					<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
            				<div class="ui-tips-box mat10">
            					<span class="color94">还没有任何活动哦!点击</span>
                				<a href="${ctx}/activity/addUI.do" class="colorBlue">主办活动</a><br/>
                				<p class="mat15 color94">提交你的活动需求吧</p>
            				</div>
        				</div>
        				<!-- end -->
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
