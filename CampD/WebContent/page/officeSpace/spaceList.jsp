<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<% 
	String belongTo  = request.getParameter("belongTo"); 
	if(belongTo != null){
		pageContext.setAttribute("belongTo",belongTo); 
	}
%> 
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js"></script>
</head>
<body>
	<!-- 头部  -->
	<div class="header clearfix">
    	<a class="head-left-icon" >
        	<img src="${rmtResPath}/static/images/back.png" width="13" height="22" onclick="back()"/>
        </a>
        <div class="head-content">${name}</div>
    </div>
    <!-- end -->
    <!--logo分布区域显示-->
    <div class="workfor retina-1px-border-bottom retina-1px-border-top" id="scrolllist">
	      <div id="scroller">
		    	<ul class="clearfix search-parent-list workforlist" >
		            <li class="active" value="0" onclick="OfficeSpace.space(this)">全部</li>
		        </ul>
	       </div> 
     </div>
     <div class="slidedown">
          <img class="morelist" src="${rmtResPath}/static/images/down.png" width="20" height="11"/>
     </div>
     <!-- 搜索条件 -->
    <div class="search-box clearfix" style="margin-top:46px;">
          <!-- 区域搜索下拉内容 -->
          <div class="search-detail workfor-list hide" id="workfor-list">
             <ul class="clearfix"></ul>
          </div>
          <!-- end -->
    </div>
    <!-- end -->
    <div class="main mat7" id="space_main" style="position:relative;z-index:40;">
    	<input type="hidden" id="pageSize" name="pageSize" value=""/>
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="status" name="status" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="6"/>
    	<input type="hidden" id="belongTo" name="belongTo" value="${empty belongTo?'':belongTo}"/>
    	<input type="hidden" id="area" name="area" value=""/>
    	<div class="outer">
  			<div class="ul-box inner mat-5">
	        	<ul class="data-list ground-list" id="creatorSpaceList">
	        		
	        	</ul>
      		</div>
    	</div>
    </div>
</body>

<script type="text/javascript">
	$(function(){
		// 初始化区域选择 
		OfficeSpace.searchByArea();
		OfficeSpace.search();
		OfficeSpace.setOuterHeight();
		OfficeSpace.droploadPage(); 
	});
</script>
</html>