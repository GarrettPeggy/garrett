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
                    <li class="active" onclick="Space.cost(0)">全部</li>
                    <li onclick="Space.cost(1)">免费</li>
                    <li onclick="Space.cost(2)">收费</li>
                </ul>
            </div>
            <!-- end -->
            <!-- 类型搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.type(0)">全部</li>
                    <li onclick="Space.type(1)">众创空间</li>
                    <li onclick="Space.type(2)">咖啡厅</li>
                    <li onclick="Space.type(3)">公司会议室</li>
                    <li onclick="Space.type(4)">社区场地</li>
                    <li onclick="Space.type(5)">商业广场</li>
                </ul>
            </div>
            <!-- end -->
            <!-- 区域搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.address('全部')">全部</li>
                    <li onclick="Space.address('浦东')">浦东</li>
                    <li onclick="Space.address('黄埔')">黄浦</li>
                    <li onclick="Space.address('普陀')">普陀</li>
                    <li onclick="Space.address('静安')">静安</li>
                    <li onclick="Space.address('闵行')">闵行</li>
                </ul>
            </div>
            <!-- end -->
            <!-- 区域搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Space.capacity(-1,-1)">全部</li>
                    <li onclick="Space.capacity(10,30)">10-30人</li>
                    <li onclick="Space.capacity(30,50)">30-50人</li>
                    <li onclick="Space.capacity(50,70)">50-70人</li>
                    <li onclick="Space.capacity(70,90)">70-90人</li>
                    <li onclick="Space.capacity(90,110)">90-110人</li>
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
    
    <div class="main mat7 hide" id="space_main_ajax">
    	<!-- 当没有场地时 -->
        <div class="ground-no hide" id="space_no">
        	<img src="${rmtResPath}/static/images/no_data.png" width="41" height="41"/>
            <p>抱歉，没有找到合适的场地</p>
            <p>请浏览其他场地吧</p>
        </div>
        <!-- end -->
    	<!-- 当有场地时 -->
    	<div class="ul-box" id="space_yes">
        	<ul class="data-list ground-list" id="space_cost_ul">
        		<%-- 
            	<li class="clearfix">
                	<div class="data-li-left">
                    	<img src="${rmtResPath}/static/images/ground_ex.png" width="91" height="63"/>
                    </div>	
                    <div class="data-li-right">
                    	<div class="dlr-title" id="space_type">
                        	聚创空间
                        </div>
                        <div class="dlr-address">
                                                                虹桥路-地铁三号线
                        </div>
                        <div class="dlr-cost clearfix">
                            <div class="fl">
                               	 费用：<span class="co">免费</span>
                            </div>
                            <div class="fr">
                                <span class="co">1-30</span>人
                            </div>
                        </div>
                    </div>	
                </li>
                 --%>
            </ul>
        </div>
        <!-- end -->
    </div>
</body>
</html>