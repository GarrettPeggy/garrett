<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">精品场地</div>
    </div>
    
    <div class="main mat7" id="space_main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="spaceLevel" name="spaceLevel" value="${spaceLevel}"/>
    	<div class="outer">
    	<c:choose>
    		<c:when test="${empty jsonview.resultList }">
    			<!-- 当没有场地时 -->
		        <div class="ground-no hide">
		        	<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
		            <p>抱歉，没有找到合适的场地</p>
		            <p>请浏览其他场地吧</p>
		        </div>
    		</c:when>
    		<c:otherwise>
    			<!-- 当有场地时 -->
    			<div class="ul-box inner">
		        	<ul class="data-list ground-list " id="space_highlevel">
		        		<c:forEach items="${jsonview.resultList }" var="space">
		        			<li class="clearfix item">
		        				<a href="${ctx }/space/getSpaceInfoById.do?id=${space.id }">
				                	<div class="data-li-left">
				                    	<img src="${sysConfig.ossResUrl}${fn:split(space.show_images, ',')[0]}" width="91" height="63"/>
				                    </div>	
				                    <div class="data-li-right">
				                    	<div class="dlr-title">
				                        	<c:if test="${fn:length(space.name) > 10}">
				                    			<c:out value="${fn:substring(space.name, 0, 11)}..." /> 
				                    		</c:if>
				                    		<c:if test="${fn:length(space.name) <= 10}">
				                    			 <c:out value="${space.name}" /> 
				                    		</c:if>
				                        </div>
				                        <div class="dlr-address">
				                           	<c:if test="${fn:length(space.adress) > 10}">
				                    			<%-- <c:out value="${space.province }${space.city }${space.area }${fn:substring(space.adress, 0, 11)}......" /> --%> 
				                    			<c:out value="${fn:substring(space.adress, 0, 11)}......" />
				                    		</c:if>
				                    		<c:if test="${fn:length(space.adress) <= 10}">
				                    			 <%-- <c:out value="${space.province }${space.city }${space.area }${space.adress}" /> --%>
				                    			 <c:out value="${space.adress}" /> 
				                    		</c:if>
				                        </div>
				                        <div class="dlr-cost clearfix">
				                            <div class="fl">
				                                                           费用：
				                                <c:if test="${space.cost eq 0}"><span class="co red" style="color:red;">免费</span></c:if>
				                            	<c:if test="${space.cost gt 0}"><span class="co"><fmt:parseNumber integerOnly="true" value="${space.cost}" />元/小时</span></c:if>
				                            </div>
				                            <div class="fr">
				                                <span class="co">${space.capacity }</span>人
				                            </div>
				                        </div>
				                    </div>	
			                    </a>
			                </li>
		        		</c:forEach>
		        	</ul>
        		</div>
    		</c:otherwise>
    	</c:choose>
     </div>
    </div>
</body>
<script type="text/javascript">
$(function(){
	$(".outer").height(window.innerHeight-($(".header").height()+9));
	Space.droploadPage();  
});
</script>

</html>