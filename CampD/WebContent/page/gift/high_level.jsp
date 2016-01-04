<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>${jsonview.spaceInfo.name }</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix ">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">精美礼品</div>
    </div>
	<!-- end -->
	<!-- 主体 -->
	<div class="main mat7" id="gift_main">
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="10"/>
    	<input type="hidden" id="level" name="level" value="1"/>
    	<input type="hidden"  id="pageSize" name="pageSize" value=""/>
    	<div class="outer">
	        <div class="gift-list-pic inner">
	          <div class="gift-inner" id="gift_highlevel"></div>
	        </div>
        </div>
    </div>   
</body>
<script type="text/javascript">
$(function(){
	$(".outer").height(window.innerHeight-($(".header").height()+7));
	Gift.searchHighlevel(false);
	setTimeout(function(){
		Gift.droploadHigh();
	},1000);
	
});


</script>
</html>
