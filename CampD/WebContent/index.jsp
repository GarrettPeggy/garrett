<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
     <title>CD营活动平台</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/js/user/user.js?_v=${vs}"></script>
</head>

<body>
	<!-- 头部我的中心信息这一部分 -->
	<%@ include file="/page/common/head.jsp" %>
    <!-- 主体 -->
    <div class="main" id="activity_main" style="heigh:100%">
    	<!-- 轮播图 -->
    	<div class="addWrap mat51">
	        <div class="img-tap-show swipe" id="mySwipe">
	        	<div class="swipe-wrap">
				</div>
	        </div>
	        <ul id="position" style="top:120px;">
			</ul>
        </div>
        <!-- end -->
	   <div class="mainlist retina-1px-border-bottom">
		      <div class="news">
		          <img class="newspic fl" src="${rmtResPath}/static/images/news.png" width="15" height="15"/>
		          <div class="newsword fl" id="newsword">
		          </div>
		      </div>
		      <div class="clearfix piclist">
			         <%-- <div class="mainpic fl" onclick="Header.classify()">
			            <img class="picture" src="${rmtResPath}/static/images/active2.png" width="44" height="44"/>
			            <div class="word">活动</div>
			         </div> --%>
			         <div class="mainpic fl" onclick="Header.officeSpaceIndex()">
			            <img class="picture" src="${rmtResPath}/static/images/officeSpace2.png" width="44" height="44"/>
			            <div class="word">办公空间</div>
			         </div>
			         <div class="mainpic fl" onclick="Header.spaceIndex()">
			            <img class="picture" src="${rmtResPath}/static/images/space2.png" width="44" height="44"/>
			            <div class="word">活动场地</div>
			         </div>
			         <div class="mainpic fl" onclick="Header.toGiftIndex()">
			            <img class="picture" src="${rmtResPath}/static/images/gift3.png" width="44" height="44"/>
			            <div class="word">礼品赞助</div>
			         </div>
			         <div class="contactus" onclick="Header.toContact()">
                        <img src="${rmtResPath}/static/images/contact_0.png" width="68" height="68"/>
                     </div>
		      </div>  
	   </div>
        
        
        <div class="contain">
        <%-- <!-- 热门活动 -->
			<div class="clearfix index-act">
            	<div class="fl">热门活动</div>
                <div class="fr" onclick="Activity.populer(1)"><img src="${rmtResPath}/static/images/more.png" width="45" height="15"/></div>
            </div>
            <div class="ul-box">
                <ul class="data-list" id="activity_first_pop">
                </ul>
            </div> --%>
            
            <!-- 精品场地 -->
            <div class="firstlist clearfix">
            	<div class="fl">精品场地</div>
                <div class="fr" onclick="Space.hightLevel(1)"><img src="${rmtResPath}/static/images/more.png" width="45" height="15"/></div>
            </div>
            <div class="ul-box space-ul-box">
                <ul class="data-list" id="space_first_pop">
                </ul>
            </div>
            
             <!-- 精品礼品 -->
           <div class="gift">
              <div class="firstlist">
                <span class="hotspace fl">精美礼品</span>
                <span class="fr more" onclick="Gift.highlevel()"><img src="${rmtResPath}/static/images/more.png" width="45" height="15"/></span>
              </div>
              <div class="gift-list-pic clearfix" id="present_first_pop">

              </div>
           </div>
            
             <div class="footdes" >
	                <div class="about">
		                 <div class="aboutus" onclick="Header.toAboutUs()">
			                   <img class="fl bird" src="${rmtResPath}/static/images/bird.png" height="25" width="22" />
			                   <span class="us">关于我们</span>
		                 </div>
		                 <div class="fchar">|</div>
		                 <div class="aboutus" onclick="Header.toRights()">
			                   <img class="fl blink"  src="${rmtResPath}/static/images/link.png" height="23" width="23"/>
			                   <span class="rights">版权声明</span>
		                 </div>
	                </div>
                    <div class="logoimg2" >
	                    <img src="${rmtResPath}/static/images/cd_logo_02.png" width="70%"/>
	                </div>
                    <div class="icp"> 
	                       <p>沪ICP备15047475号</p>
	                </div>
            </div>
        <!-- end -->
    </div>
    <!-- end -->
  </div>  
</body>
<script type="text/javascript">
$(function(){
	Header.loadTapShow();// 加载轮播图
	Header.loadNotify();// 加载首页通知
	Activity.list();
	Space.list();
	Gift.list();
	initNotifyInfo("newsword", 32);
});
</script>

</html>
