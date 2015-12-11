<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- end -->

    <!-- 搜索条件 -->
    <div class="search-box retina-1px-border-bottom retina-1px-border-top">
    	<ul class="clearfix search-parent-list giftlist">
        	<li>
            	  <span>主营业务</span>
            	  <span class="conrner"></span>
            </li>
            <li>
            	  <span>活动类型</span>
            	  <span class="conrner"></span>
            </li>
            <li>
            	  <span>城市</span>
            	  <span class="conrner"></span>
            </li>
        </ul>
        <div>
        <!-- 主营业务搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Gift.mainBusiness('',this)">全部</li>
                    <c:forEach items="${systemConst.mainBusinessMap}" var="mainBusiness">
                    	<li onclick="Gift.mainBusiness(${mainBusiness.key},this)">${mainBusiness.value}</li>
					</c:forEach>
                </ul>
            </div>
     <!-- 适用活动类型搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Gift.workFor('',this)">全部</li>
                    <c:forEach items="${systemConst.categoryMap}" var="category">
                    	<li onclick="Gift.workFor(${category.key},this)">${category.value}</li>
					</c:forEach>
                </ul>
            </div>
     <!-- 适用城市搜索下拉内容 -->
            <div class="search-detail hide">
                <ul class="clearfix">
                    <li class="active" onclick="Gift.workForCity('',this)">全部</li>
                      <c:if test="${!empty sysConfig.biz_open_city}">
							<c:forEach var="biz_open_city" items="${fn:split(sysConfig.biz_open_city, ',')}">
								<li  onclick="Gift.workForCity('${biz_open_city}',this)">${biz_open_city}</li>
						    </c:forEach>
					 </c:if>
                    
                </ul>
            </div>
         </div>  
     </div>
     <!-- 蒙层 需要显示时删除hide -->
    <div class="mc hide" id="space_mc"></div>
    <!-- end -->
     <div class="main mat7" id="gift_main">
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="6"/>
    	<input type="hidden" id="mainBusiness" name="mainBusiness" value=""/>
    	<input type="hidden" id="workFor" name="workFor" value=""/>
    	<input type="hidden" id="workForCity" name="workForCity" value=""/>
	   	 <div class="ul-box">
	          <ul class="data-list ground-list" id="gift_index">
	             
		      </ul>
	     </div>
      </div>
</body>

<script type="text/javascript">
$(function(){
	// 初始化区域选择
	Gift.searchIndex(false);
});
</script>

</html>