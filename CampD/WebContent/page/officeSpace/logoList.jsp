<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
     <title>CD营活动平台办公空间</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js"></script>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>
<body>
     <div style="height:1px;overflow:hidden">
        <img src="${rmtResPath}/static/images/weixin_officespace.png"/> 
     </div>
	<!-- 头部 -->
	<%@ include file="/page/officeSpace/head.jsp" %>
	<!-- end -->
	<!-- 全局搜索结果列表 -->
	<div class="searchMc" id="search_all"></div>
    <!-- end -->
        <ul class="bandlist" id="modelSelect">
           <li class="band active">品牌</li>
           <li class="band">区域</li>
           <li class="band">价位</li>
           <li class="band">地铁</li>
        </ul>
	    <div class="workfor workfor1 retina-1px-border-bottom retina-1px-border-top" id="scrolllist">
		      <div id="scroller">
			    	<ul class="clearfix search-parent-list workforlist workforlist1" >
			    		<input type="hidden" id="area" name="area" value=""/>
			            <li class="active" onclick="OfficeSpace.area(this)" value="0">全部</li>
			        </ul>
		       </div> 
	     </div>
	     <div class="slidedown slidedown1">
	          <img class="morelist" src="${rmtResPath}/static/images/down.png" width="20" height="11"/>
	     </div>
	 
    <!-- 搜索条件 -->
    <div class="search-box  clearfix">
       <!-- 区域搜索下拉内容 -->
          <div class="search-detail workfor-list workfor-list1 hide" id="workfor-list">
             <ul class="clearfix"></ul>
          </div>
        <!-- end -->
        <!-- 区域搜索内容 -->
            <div class="search-ul search-detail hide">
                <ul class="clearfix" id="search_province">
			        <li class="active" onclick="OfficeSpace.address('',this)" value="0">全部</li>
                </ul>
            </div>
	    <!-- end -->     
	    <!-- 价位搜索内容 -->
            <div class="search-ul search-detail hide" >
                <ul class="clearfix">
                    <li class="active" onclick="OfficeSpace.cost('','',this)">全部</li>
                    <li onclick="OfficeSpace.cost('0','300',this)">0-300</li>
                    <li onclick="OfficeSpace.cost('300','600',this)">300-600</li>
                    <li onclick="OfficeSpace.cost('600','900',this)">600-900</li>
                    <li onclick="OfficeSpace.cost('900','1200',this)">900-1200</li>
                    <li onclick="OfficeSpace.cost('1200','1500',this)">1200-1500</li>
                    <li onclick="OfficeSpace.cost('1500','1800',this)">1500-1800</li>
                    <li onclick="OfficeSpace.cost('1800','2100',this)">1800-2100</li>
                    <li onclick="OfficeSpace.cost('2100','2400',this)">2100-2400</li>
                    <li onclick="OfficeSpace.cost('2400','2700',this)">2400-2700</li>
                    <li onclick="OfficeSpace.cost('2700','3000',this)">2700-3000</li>
                    <li onclick="OfficeSpace.cost('3000','5000',this)">3000以上</li>
                </ul>
            </div>    
	    <!-- end -->   
	    <!-- 地铁搜索内容 -->
            <div class="search-ul search-detail hide" >
                <ul class="clearfix">
                    <li class="active" onclick="OfficeSpace.line('',this)">全部</li>
                    <li  onclick="OfficeSpace.line('1',this)">1号线</li>
                    <li  onclick="OfficeSpace.line('2',this)">2号线</li>
                    <li  onclick="OfficeSpace.line('3',this)">3号线</li>
                    <li  onclick="OfficeSpace.line('4',this)">4号线</li>
                    <li  onclick="OfficeSpace.line('5',this)">5号线</li>
                    <li  onclick="OfficeSpace.line('6',this)">6号线</li>
                    <li  onclick="OfficeSpace.line('7',this)">7号线</li>
                    <li  onclick="OfficeSpace.line('8',this)">8号线</li>
                    <li  onclick="OfficeSpace.line('9',this)">9号线</li>
                    <li  onclick="OfficeSpace.line('10',this)">10号线</li>
                    <li  onclick="OfficeSpace.line('11',this)">11号线</li>
                    <li  onclick="OfficeSpace.line('12',this)">12号线</li>
                    <li  onclick="OfficeSpace.line('13',this)">13号线</li>
                    <li  onclick="OfficeSpace.line('14',this)">14号线</li>
                    <li  onclick="OfficeSpace.line('15',this)">15号线</li>
                    <li  onclick="OfficeSpace.line('16',this)">16号线</li>
                </ul>
            </div>    
    	<!-- end -->  
    </div>
     
    
  <!-- 蒙层 需要显示时删除hide -->
    <div class="mc hide" id="space_mc"></div>
  <!-- end -->
    <div class="main mat7" id="space_main" style="position:relative;">
    	<input type="hidden" id="pageSize" name="pageSize" value=""/>
    	<input type="hidden" id="curPage" name="curPage" value="1"/>
    	<input type="hidden" id="status" name="status" value="1"/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="20"/>
    	<input type="hidden" id="area" name="area" value=""/> <!-- 区域 -->
    	<input type="hidden" id="minCost" name="minCost" value=""/> <!-- 最小价格-->
    	<input type="hidden" id="maxCost" name="maxCost" value=""/> <!-- 最大价格-->
    	<input type="hidden" id="line" name="line" value=""/><!-- 地铁线路 -->
	   <div class="logolist logolist1">
          <div id="logolist">
          	
          </div>
          <div class="loadmorelogo hide" id="loadmorelogo" onclick="OfficeSpace.loadMoreLogo()"><img src="${rmtResPath}/static/images/loadmorelogo.png" width="35" height="10"/></div>
	   </div>
	   <div class="qrcode">
	      <img class="qrcodeimg" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg" width="175" height="175"/>
	      <div>活动生态，你我共享</div>
	   </div>
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
		OfficeSpace.list();
		OfficeSpace.moreLogoArea();
		OfficeSpace.initAreaLogo();
		OfficeSpace.setSelect();
		OfficeSpace.initProvince();
	});
</script>
</html>