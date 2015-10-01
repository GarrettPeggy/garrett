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
        <div class="head-content">要举办的活动</div>
    </div>
    <!-- end -->
    <!-- 主体 -->
    <div class="main">
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
		        	<ul class="data-list">
		        		<c:forEach items="${jsonview.activityList }" var="activity">
			            	<li class="clearfix">
			            		<div class="data-li-left">
			                		<a href="${ctx }/activity/getActivityById.do?id=${activity.id }">
			                			<img src="${rmtResPath}/static/images/list_show.png" width="91" height="91"/>
			                		</a>
			                	</div>	
			                	<div class="data-li-right">
			                    	<div class="dlr-title retina-1px-border-bottom">
			                        	<span class="dlrt1">${systemConst.categoryMap[fn:trim(activity.category_id)] }</span>
			                            <span class="dlrt2"><font color="#638ee0">${activity.act_num}</font>人</span>
			                            <span class="dlrt3">${activity.act_city }</span>
			                        </div>
			                        <div class="dlr-detail color94 fontSize14">
			                        	${activity.requirement }
			                        </div>
			                    </div>
			                </li>
		                </c:forEach>
		            </ul>
        		</div>
    		</c:otherwise>
    	</c:choose>
    </div>
</body>
</html>