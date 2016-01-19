<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/officeSpace/officeSpace.js"></script>
	<style type="text/css">
		.logolist{width:100%;min-height:290px;background:#fff;padding:15px 5px 15px 5px;display:inline-block;position:relative;}
		.logolist .logoimg{width:20%;margin-right:3%;margin-left:2%;line-height:60px;}
		.morelogo{margin-top:-32px;}
		.logolist .down{position:absolute;bottom:0px;width:100%;background:#fff;text-align:center;line-height:30px;}
		.qrcode{text-align:center;margin-top:15px;margin-bottom:10px;}
		.qrcode .qrcodeimg{margin-bottom:10px;}
		.qrcode a{color:#06F;}
    </style>
</head>
<body>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- end -->
    <div class="workfor retina-1px-border-bottom retina-1px-border-top" id="scrolllist">
      <div id="scroller">
    	<ul class="clearfix search-parent-list workforlist" >
            <li class="active" onclick="OfficeSpace.logo(this)" value="0">全部</li>
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
	   <div class="logolist" id="logolist">
         <%--  <div class="fl logoimg logoimg_1">
	          <a href="${ctx}/page/custom/creatorSpace/creatorSpaceList.jsp?belongTo=0">
	          	<img src="${rmtResPath}/static/images/business_icon_01.png" width="100%" height="60"/>
	          </a>
          </div>
	  </div>
	  <%-- <div class="logolist morelogo">
		   <div id="morelogo" style="display:none;">
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_01.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_02.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_03.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_04.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_01.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_02.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_03.png" width="100%" height="60"/></div>
		          <div class="fl logoimg"><img src="${rmtResPath}/static/images/business_icon_04.png" width="100%" height="60"/></div>
		     </div>
		     <div class="down" onclick="CreatorDataUtil.showmoreLogo()"><img src="${rmtResPath}/static/images/down.png" width="27" height="15"/></div>
	  </div> --%>
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