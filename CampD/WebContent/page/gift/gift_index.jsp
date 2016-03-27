<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>CD营活动平台礼品赞助</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/dropload/dropload.min.js"></script>
</head>
<body>
     <div style="height:1px;overflow:hidden">
        <img src="${rmtResPath}/static/images/weixin_gift.png"/> 
     </div>
	<!-- 头部 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- end -->
    <!-- 全局搜索结果列表 -->
	<div class="searchMc" id="search_all"></div>
    <!-- end -->
     <div class="workfor retina-1px-border-bottom retina-1px-border-top" id="scrolllist">
      <div id="scroller">
    	<ul class="clearfix search-parent-list workforlist" >
            <li class="active" onclick="Gift.workFor('',this)" value="0">全部</li>
             <c:forEach items="${systemConst.categoryMap}" var="category" varStatus="status">
             	<c:if test="${status.index le 2}">
             	 <li onclick="Gift.workFor(${category.key},this)" value="${status.index+1}">${category.value}</li>
             	</c:if>
             </c:forEach>
        </ul>
       </div> 
     </div>
     <div class="slidedown">
          <img class="morelist" src="${rmtResPath}/static/images/down.png" width="20" height="11"/>
     </div>
    <!-- 搜索条件 -->
    <div class="search-box clearfix">
        <!-- 适用活动类型搜索下拉内容 -->
           <div class="search-detail workfor-list  hide " id="workfor-list">
                 <ul class="clearfix">
	               <c:forEach items="${systemConst.categoryMap}" var="category" varStatus="status">
	               	  <c:if test="${status.index gt 2}">
	               	    <li onclick="Gift.workFor(${category.key},this)" value="${status.index+1}">${category.value}</li>
	               	  </c:if>
	               </c:forEach>
                 </ul>
            </div>
    	 <ul class="clearfix search-parent-list giftlist bt-line" id="searchbox">
        	<li>
            	  <span>主营业务</span>
            	  <span class="conrner"></span>
            </li>
            <li>
            	  <span>礼品形态</span>
            	  <span class="conrner"></span>
            </li>
            <li>
            	  <span>城市</span>
            	  <span class="conrner"></span>
            </li>
        </ul>
        <!-- 已选项列表 -->
        <div class="choose" id="choose"></div>
        <div class="clearfix">
        <!-- 主营业务搜索下拉内容 -->
            <div class="search-ul search-detail hide" id="cho-mainBusiness">
                <ul class="clearfix">
                    <li class="active" onclick="Gift.mainBusiness('',this)">全部</li>
                    <c:forEach items="${systemConst.mainBusinessMap}" var="mainBusiness">
                    	<li onclick="Gift.mainBusiness(${mainBusiness.key},this)">${mainBusiness.value}</li>
					</c:forEach>
                </ul>
            </div>
     <!-- 适用礼品形态搜索下拉内容 -->
            <div class="search-ul search-detail hide" id="cho-form">
                <ul class="clearfix">
                    <li class="active" onclick="Gift.form('',this)">全部</li>
                    <c:forEach items="${systemConst.formMap}" var="form">
                    	<li onclick="Gift.form(${form.key},this)">${form.value}</li>
					</c:forEach>
                </ul>
            </div>
     <!-- 适用城市搜索下拉内容 -->
            <div class="search-ul search-detail hide" id="cho-workForCity">
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
    	<input type="hidden"  id="pageSize" name="pageSize" value=""/>
    	<input type="hidden" id="pageLimit" name="pageLimit" value="8"/>
    	<input type="hidden" id="mainBusiness" name="mainBusiness" value=""/>
    	<input type="hidden" id="workFor" name="workFor" value=""/>
    	<input type="hidden" id="workForCity" name="workForCity" value=""/>
    	<input type="hidden"  id="form" name="form" value=""/>
    	<div class="outer">
	   	  <div class="gift-list-pic inner">
			<div class="gift-inner" id="gift_index"></div>
          </div>
      </div>
    </div>
</body>

<script type="text/javascript">
$(function(){
	// 初始化区域选择
	Gift.setSelect();
	Gift.workForHerder();
	Gift.setOuterHeight();
	Gift.searchIndex(false);
	setTimeout(function(){
		Gift.droploadPage();
	},1000);
});
</script>

</html>