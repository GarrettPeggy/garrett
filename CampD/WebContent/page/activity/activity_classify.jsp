<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- 导航栏 -->
    <div class="nav-box retina-1px-border-bottom" id="activity_nav">
    	<ul class="nav-list clearfix" id="activity_head">
        	<li onclick="Activity.category(${systemConst.ENTREPRENEURSHIP })">
            	<img src="${rmtResPath}/static/images/business_icon_01.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.ENTREPRENEURSHIP] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.BUSINESS })">
            	<img src="${rmtResPath}/static/images/business_icon_02.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.BUSINESS] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.PLAYING })">
            	<img src="${rmtResPath}/static/images/business_icon_03.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.PLAYING] }</p>
            </li>
            <li onclick="Activity.category(${systemConst.MAKE_FRIENDS })">
            	<img src="${rmtResPath}/static/images/business_icon_04.png" width="48" height="48"/>
                <p>${systemConst.categoryMap[systemConst.MAKE_FRIENDS] }</p>
            </li>
        </ul>
    </div>
	<!-- end -->
    <!-- 主体 -->
    <div class="main">
    	<div class="ul-box" id="activity_main">
        	<ul class="data-list" id="activity_list">
        		<c:choose>
        			<c:when test="${empty jsonview.activityList }">
        				<!-- 无主办活动 -->
        				<div class="textCenter mat15">
        					<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
            				<div class="ui-tips-box mat10">
            					<span class="color94">还没有任何活动哦!点击</span>
                				<a href="###" class="colorBlue">主办活动</a><br/>
                				<p class="mat15 color94">提交你的活动需求吧</p>
            				</div>
        				</div>
        				<!-- end -->
        			</c:when>
        			<c:otherwise>
        				<c:forEach items="${jsonview.activityList }" var="activity">
        					<li class="pd5">
			                	<img src="${rmtResPath}/static/images/example_img_big.png" width="100%" height="116"/>
			                    <div class="classify-li-title">
			                    	<c:out value="${activity.title }" default="无标题"></c:out>
			                    </div>
			                    <div class="classify-li-desc color94 fontSize14">${activity.requirement }</div>
			                    <div class="classify-li-date fontSize14">
			                    	<img src="${rmtResPath}/static/images/date_icon.png" width="10" height="10"/>
			                        <span>${activity.begintime }--${activity.endtime }</span>
			                    </div>
			                </li>
        				</c:forEach>
        			</c:otherwise>
        		</c:choose>
            </ul>
        </div>
    </div>
    <!-- end -->
</body>
</html>