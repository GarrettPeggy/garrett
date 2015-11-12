<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- end -->
    
    <!-- 搜索条件 -->
    <div class="search-box retina-1px-border-bottom retina-1px-border-top">
    	<ul class="clearfix search-parent-list">
        	<li>
            	<span>费用</span>
            </li>
            <li>
            	<span>类型</span>
            </li>
            <li>
            	<span>区域</span>
            </li>
            <li>
            	<span>容量</span>        
            </li>
        </ul>
        <div>
            <!-- 费用搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.cost('',this)">全部</li>
                    <li onclick="Space.cost('1',this)">免费</li>
                    <li onclick="Space.cost('2',this)">收费</li>
                </ul>
            </div>
            <!-- end -->
            <!-- 类型搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.type('',this)">全部</li>
                    <c:forEach items="${systemConst.spaceTypeMap}" var="spaceType">
                    	<li onclick="Space.type(${spaceType.key},this)">${spaceType.value}</li>
					</c:forEach>
                </ul>
            </div>
            <!-- end -->
            <!-- 区域搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix" id="search_province">
                    <li class="active" onclick="Space.address('',this)">全部</li>
                </ul>
            </div>
            <!-- end -->
            <!-- 区域搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.capacity('','',this)">全部</li>
                    <li onclick="Space.capacity(10,30,this)">10-30人</li>
                    <li onclick="Space.capacity(30,50,this)">30-50人</li>
                    <li onclick="Space.capacity(50,70,this)">50-70人</li>
                    <li onclick="Space.capacity(70,90,this)">70-90人</li>
                    <li onclick="Space.capacity(90,110,this)">90-110人</li>
                    <li onclick="Space.capacity(110,150,this)">110-150人</li>
                    <li onclick="Space.capacity(150,200,this)">150-200人</li>
                    <li onclick="Space.capacity(200,300,this)">200-300人</li>
                    <li onclick="Space.capacity(300,500,this)">300-500人</li>
                    <li onclick="Space.capacity(500,700,this)">500-700人</li>
                    <li onclick="Space.capacity(700,1000,this)">700-1000人</li>
                </ul>
            </div>
            <!-- end -->
        </div>
    </div>
    
    <!-- 蒙层 需要显示时删除hide -->
    <div class="mc hide" id="space_mc"></div>
    <!-- end -->
    <!-- end -->
    <div class="main mat7" id="space_main">
    	<input type="hidden" id="pageSize" name="pageSize" value="${pageInfo.pageSize }"/>
    	<input type="hidden" id="curPage" name="curPage" value="${pageInfo.curPage }"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="${pageInfo.pageLimit }"/>
    	<input type="hidden" id="cost" name="cost" value=""/>
    	<input type="hidden" id="spaceType" name="spaceType" value=""/>
    	<input type="hidden" id="area" name="area" value=""/>
    	<input type="hidden" id="minCapacity" name="minCapacity" value=""/>
    	<input type="hidden" id="maxCapacity" name="maxCapacity" value=""/>
    	<c:choose>
    		<c:when test="${empty jsonview.resultList }">
    			<!-- 当没有场地时 -->
		        <div class="ground-no" id="ground-no-hide">
		        	<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
		            <p>抱歉，没有找到合适的场地</p>
		            <p>请浏览其他场地吧</p>
		        </div>
    		</c:when>
    		<c:otherwise>
    			<!-- 当有场地时 -->
    			<div class="ul-box">
		        	<ul class="data-list ground-list" id="space_highlevel">
		        		<c:forEach items="${jsonview.resultList }" var="space">
		        			<li class="clearfix">
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
				                    			<c:out value="${fn:substring(space.adress, 0, 11)}..." /> 
				                    		</c:if>
				                    		<c:if test="${fn:length(space.adress) <= 10}">
				                    			 <c:out value="${space.adress}" /> 
				                    		</c:if>
				                        </div>
				                        <div class="dlr-cost clearfix">
				                            <div class="fl">
				                                                                        费用：<span class="co"><fmt:parseNumber integerOnly="true" value="${space.cost}" />元/小时</span>
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
		        	<c:if test="${pageInfo.pageSize > pageInfo.curPage }">
	        			<div id="loadMore_li">
	        				<button id="loadMore" name="loadMore" class="btn btn-xs btn-light bigger loadBtn" onclick="Space.loadMore()">加载更多</button>
	        			</div>
	        		</c:if>
        		</div>
    		</c:otherwise>
    	</c:choose>
    </div>
</body>

<script type="text/javascript">
$(function(){
	// 初始化区域选择
	Space.initProvince();
});
</script>

</html>