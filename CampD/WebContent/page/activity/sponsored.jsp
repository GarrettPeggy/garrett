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
        	<img src="${rmtResPath}/static/images/back.png" onclick="back('${referer}')" width="13" height="22"/>
        </a>
        <div class="head-content">举办的活动</div>
    </div>
    <!-- end -->
    <!-- 主体 -->
    <div class="main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="creatorId" name="creatorId" value="${USER_INFO.id }"/>
    	<input type="hidden" id="isSponsored" name="isSponsored"/>
    	<c:choose>
    		<c:when test="${empty jsonview.activityList}">
    			<div class="textCenter mat15">
		        	<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
		            <div class="ui-tips-box mat10">
		            	<span class="color94">点击</span>
		                <a onclick="Activity.hold()" class="colorBlue">举办活动</a>
		                <p class="mat15 color94">提交你的活动需求吧</p>
		            </div>
        		</div>
    		</c:when>
    		<c:otherwise>
    			<div class="ul-box">
		        	<ul class="data-list" id="activity_popu">
		        		<c:forEach items="${jsonview.activityList }" var="activity">
			        		<a href="${ctx }/activity/getActivityById.do?id=${activity.id }">
				            	<li class="clearfix">
				            		<div class="data-li-left">
					            		<c:choose>
					            			<c:when test="${!empty activity.show_image && ''!=activity.show_image }">
					            				<img src="${sysConfig.ossResUrl}${activity.show_image }" width="91" height="91"/>
					            			</c:when>
					            			<c:otherwise>
					            				<img src="${rmtResPath}/static/images/empty_image.png" width="91" height="91" />
					            			</c:otherwise>
					                	</c:choose>
				                	</div>	
				                	<div class="data-li-right">
				                    	<div class="dlr-title retina-1px-border-bottom">
				                        	<span class="dlrt1">${systemConst.categoryMap[fn:trim(activity.category_id)] }</span>&nbsp;&nbsp;&nbsp;
				                            <span class="dlrt2"><font color="#638ee0">${activity.act_num}</font>人</span>&nbsp;&nbsp;&nbsp;
				                            <span class="dlrt3">${activity.city }</span>
				                        </div>
				                        <%-- <div class="dlr-detail color94 fontSize14">
					                        <c:if test="${fn:length(activity.requirement) > 20}">
				                    			<c:out value="${fn:substring(activity.requirement, 0, 21)}......" /> 
				                    		</c:if>
				                    		<c:if test="${fn:length(activity.requirement) <= 20}">
				                    			 <c:out value="${activity.requirement}" /> 
				                    		</c:if>
				                        </div> --%>
				                    </div>
				                </li>
				            </a>
		                </c:forEach>
		            </ul>
		            <c:if test="${pageInfo.pageSize > pageInfo.curPage }">
		            	<div id="activity_more">
		            		<button id="activityLoadMore" name="activityLoadMore" class="btn btn-xs btn-light bigger loadBtn" onclick="Activity.loadMore()">加载更多...</button> 
		            	</div>
		            </c:if>
        		</div>
    		</c:otherwise>
    	</c:choose>
    </div>
</body>
</html>