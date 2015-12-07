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
            </li>
            <li>
            	<span>活动类型</span>
            </li>
            <li>
            	<span>城市</span>
            </li>
        </ul>
     </div>
     <div class="main mat7" id="gift_main">
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="6"/>
    	<input type="hidden" id="cost" name="cost" value=""/>
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