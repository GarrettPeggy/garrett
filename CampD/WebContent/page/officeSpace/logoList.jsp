<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js"></script>
	<title>CD营活动平台办公空间</title>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- end -->
    <div class="workfor retina-1px-border-bottom retina-1px-border-top" id="scrolllist">
	      <div id="scroller">
		    	<ul class="clearfix search-parent-list workforlist" >
		    		<input type="hidden" id="area" name="area" value=""/>
		            <li class="active" onclick="OfficeSpace.area(this)" value="0">全部</li>
		        </ul>
	       </div> 
     </div>
     <div class="slidedown">
          <img class="morelist" src="${rmtResPath}/static/images/down.png" width="20" height="11"/>
     </div>
    <!-- 搜索条件 -->
    <div class="search-box clearfix">
          <!-- 区域搜索下拉内容 -->
          <div class="search-detail workfor-list hide" id="workfor-list">
             <ul class="clearfix"></ul>
          </div>
          <!-- end -->
    </div>
    
    <!-- 蒙层 需要显示时删除hide -->
    <div class="mc hide" id="space_mc"></div>
    <!-- end -->
    <!-- end -->
    <div class="main mat7" id="space_main" style="position:relative;z-index:40;">
    	<input type="hidden" id="pageSize" name="pageSize" value=""/>
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="status" name="status" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="20"/>
	   <div class="logolist" >
          <div id="logolist">
          	
          </div>
          <div class="loadmorelogo hide" id="loadmorelogo" onclick="OfficeSpace.loadMoreLogo()"><img src="${rmtResPath}/static/images/loadmorelogo.png" width="35" height="10"/></div>
	   </div>
	   <div class="qrcode">
	      <img class="qrcodeimg" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg" width="175" height="175"/>
	      <div><a href="#">关注我们</a>，获取更多活动资源</div>
	   </div>
    </div>
</body>

<script type="text/javascript">
	$(function(){
		// 初始化区域选择
		OfficeSpace.list();
		OfficeSpace.moreArea();
		OfficeSpace.initAreaLogo();
	});
</script>
</html>