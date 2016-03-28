<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><!-- 我的 -->
<link rel="stylesheet" href="${locResPath}/static/common/swipe/css/swipe.css?_v=${vs}"/>
<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/common/swipe/js/swipe.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/js/space/space.js?_v=${vs}"></script>
<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
    <!-- 头部 -->
	<div class="header clearfix classify-head retina-1px-border-bottom positionR newheader" id="activity_header">
        <div class="newhead-left-icon">
        	<img src="${rmtResPath}/static/images/cd_logo.png" id="cd_logo" width="56" height="20" />
        </div>
        <div class="searchAll">
            <input class="searchBorder" id="searchKey" type="text" style="-webkit-appearance:none;"/>
        </div> 
        <div class="head-right-icon">
        	<img src="${rmtResPath}/static/images/user2.png" width="20" height="20"/>
        </div>
    </div>
    <img class="searchPic" onclick="Header.searchKey();"  src="${rmtResPath}/static/images/search.png" width="15" height="15"/>
    <img class="searchDel" onclick="Header.delSearchKey();" src="${rmtResPath}/static/images/close.png" width="16" height="16"/>
    
    <!-- end -->
    
     <!-- 右上角菜单 -->
   <div class="rightmenu-mc hide" id="rightmenu-mc" style="position:fixed;">
     <span class="rightcorner"></span>
     <div class="rightmenu">
       <ul>
          <li class="retina-1px-border-bottom" onclick="Header.signUp()">
             	<img src="${rmtResPath}/static/images/signup2.png" width="25" height="25" class="fl mat8" />
                             报名的活动
          </li>
          <c:if test="${empty USER_INFO}">
	          <li class="retina-1px-border-bottom" onclick="Header.toLogin()">
	             <img src="${rmtResPath}/static/images/login2.png" width="25" height="25" class="fl mat8" />
	                        登录
	          </li>
          </c:if>
          <!-- 以下两个通过增加hide样式显示隐藏 -->
          <c:if test="${!empty USER_INFO}">
          	  <li class="retina-1px-border-bottom" onclick="Header.toUpdate()">
	              <img src="${rmtResPath}/static/images/login2.png" width="25" height="25" class="fl mat8" />
	              ${USER_INFO.userName}
	          </li>
	          <li class="retina-1px-border-bottom" onclick="Header.toQuit()">
	              <img src="${rmtResPath}/static/images/quit2.png" width="25" height="25" class="fl mat8" />
	                          退出
	          </li>
          </c:if>
          <c:if test="${empty USER_INFO}">
	          <li class="retina-1px-border-bottom" onclick="Header.toRegister()">
	              <img src="${rmtResPath}/static/images/register2.png" width="25" height="25" class="fl mat8" />
	                         注册
	          </li>
          </c:if>
        </ul>
     </div>
  </div>
<!-- end -->
<script type="text/javascript">
	$(function(){
		Header.initHeadIcon();
	});
</script>

