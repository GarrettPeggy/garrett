<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
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
    			<div class="ul-box">
		        	<ul class="data-list ground-list">
		        		<c:forEach items="${jsonview.resultList }" var="space">
		        			<li class="clearfix">
			                	<div class="data-li-left">
			                    	<a href="${ctx }/space/getSpaceInfoById.do?id=${space.id }">
			                    		<img src="${rmtResPath}/static/images/ground_ex.png" width="91" height="63"/>
			                    	</a>
			                    </div>	
			                    <div class="data-li-right">
			                    	<div class="dlr-title">
			                        	${systemConst.spaceTypeMap[fn:trim(space.space_type)] }
			                        </div>
			                        <div class="dlr-address">
			                           	${space.adress }
			                        </div>
			                        <div class="dlr-cost clearfix">
			                            <div class="fl">
			                                                                        费用：<span class="co">${space.cost }</span>
			                            </div>
			                            <div class="fr">
			                                <span class="co">${space.capacity }</span>人
			                            </div>
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