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
        	<img src="${rmtResPath}/static/images/back.png" onclick="back('${referer}')" width="13" height="22"/>
        </a>
        <div class="head-content">报名的活动</div>
    </div>
	<!-- end -->
	
	<!-- 主体 -->
    <div class="main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="userId" name="userId" value="${USER_INFO.id }"/>
    	<div class="outer">
    	<c:choose>
    		<c:when test="${empty jsonview.activityList}">
    			<div class="textCenter mat15">
		        	<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
		            <div class="ui-tips-box mat10">
		            	<span class="color94">点击</span>
		                <a onclick="Activity.backClassify()" class="colorBlue mar5">活动</a>
		                <span class="mat15 color94">寻找你想参加的活动吧</span>
		            </div>
        		</div>
    		</c:when>
    		<c:otherwise>
    			<div class="ul-box inner" >
    			   <div id="activity_popu">
			        	<ul class="data-list">
			        		<c:forEach items="${jsonview.activityList }" var="activity">
				        		<li class="pd5">
					                <a class="item itemwidth" href="${ctx }/activity/getActivityById.do?id=${activity.id }">
					            		<img src="${sysConfig.ossResUrl}${activity.show_image }" width="100%" height="156"/>
					                    <div class="classify-li-title">${activity.title }</div>
					                    <div class="classify-li-date fontSize14">
					                    	<img src="${rmtResPath}/static/images/date_icon.png" width="10" height="10"/>
					                        <span>${activity.begintime }--</span>
					                        <span>${activity.endtime }</span>
					                    </div>
						            </a>
				                </li>
			                </c:forEach>
			            </ul>
			          </div>
        		</div>
    		</c:otherwise>
    	</c:choose>
    </div>
  </div>
    <!-- end -->
</body>
<script type="text/javascript">
$(function(){
	$(".outer").height(window.innerHeight-($(".header").height()+9));
	Activity.droploadSponsor();
});
</script>
</html>