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
        <div class="head-content">热门活动</div>
    </div>
    
    <!-- 主体 -->
    <div class="main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="actType" name="actType" value="${actType }"/>
    	<div class="ul-box">
        	<ul class="data-list" id="activity_popu">
        		<c:choose>
        			<c:when test="${empty jsonview.activityList }">
		        		<li class="pd5">
		        			对不起，暂时没有你所需要查询的数据
		        		</li>
	                </c:when>
	                <c:otherwise>
		                <c:forEach items="${jsonview.activityList }" var="activity">
			            	<li class="pd5">
			                	<img src="${rmtResPath}/static/images/example_img_big.png" width="100%" height="116"/>
			                    <div class="classify-li-title"><c:out value="${activity.title }" default="无标题"></c:out></div>
			                    <div class="classify-li-desc color94 fontSize14">
			                    	<a href="${ctx }/activity/getActivityById.do?id=${activity.id }">${activity.requirement }</a>
			                    </div>
			                    <div class="classify-li-date fontSize14">
			                    	<img src="${rmtResPath}/static/images/date_icon.png" width="10" height="10"/>
			                        <span>${activity.begintime }--</span>
			                        <span>${activity.endtime }</span>
			                    </div>
			                </li>
			            </c:forEach>
	                </c:otherwise>
                </c:choose>
            </ul>
            <c:if test="${pageInfo.pageSize > pageInfo.curPage }">
            	<div id="activity_more">
            		<button id="activityLoadMore" name="activityLoadMore" class="btn btn-xs btn-light bigger loadBtn" onclick="Activity.loadMore()">加载更多</button> 
            	</div>
            </c:if>
        </div>
    </div>
    <!-- end -->
    
</body>
</html>