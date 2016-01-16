<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>七宝德必易园为大家提供不仅仅是免费活动场地还有茶点水果 and 精美礼品哦！</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<style type="text/css">
		.contain{padding:5px;margin:0px;background:#fff;}
		.idea{font-size:16px;line-height:25px;font-family:'微软雅黑';padding:5px 5px;position:relative;margin-top:51px;}
	    .timetitle{padding:5px 5px;position:relative;font-size:14px; }
	    .timetitle .time{color:#878787;display:inline-block;}
	    .timetitle .title{color:#4f73a0;margin-left:5px;display:inline-block;}
        .timetitle .fitact{font-size:13px;line-height:25px;}
        .timetitle .fitact .fitactword{color:#404040;}
        .timetitle .fitact .fitactlist{color:#b51919;}
        .marb10{margin-bottom:10px;}
        .piclist{padding-left:2px;width:100%;margin-bottom:20px;}
        .piclist .pictitle{color:#fff;padding-left:10px;line-height:35px;background:#00589c;border-radius:4px 4px 0px 0px;}
        .piclist  .lists{border:1px solid #00589c;padding:10px 10px 0px 10px;border-radius:0px 0px 4px 4px;}
        .piclist  .lists .picnum{margin-bottom:8px;}
        .cdqrcode{width:100%;text-align:center;margin-bottom: 70px;}
        .cdqrcode .focus{font-size:12px;}
        .mat10{margin-top:10px;}
        .mat20{margin-top:20px;}
        .mat5{margin-top:5px;}
    </style>
</head>
<body>
<!-- 头部我的中心信息这一部分 -->
	<%@ include file="/page/common/head.jsp" %>
    <!--右上角菜单  -->
    <div class="rightmenu-mc hide">
      <span class="rightcorner"></span>
      <div class="rightmenu">
        <ul>
           <li class="retina-1px-border-bottom">
             <img src="${rmtResPath}/static/images/signup2.png" width="22" height="22">
             报名的活动
           </li>
           <li class="retina-1px-border-bottom">
             <img src="${rmtResPath}/static/images/login2.png" width="22" height="22">
             登录
           </li>
           <li class="retina-1px-border-bottom">
             <img src="${rmtResPath}/static/images/register2.png" width="22" height="22">
             注册
           </li>
           <li>
             <img src="${rmtResPath}/static/images/quit2.png" width="22" height="22">
             退出
           </li>
        </ul> 
     </div>
   </div>
	<!-- end -->
   <div class="contain">
      <div class="idea"><strong>为大家提供不仅仅是免费活动场地还有茶点水果 and 精美礼品哦！</strong></div>
	  <div class="timetitle">
	      <div class="time" id="time"></div>
	      <div class="title">德必易园+CD营</div>
	      <img src="${rmtResPath}/static/images/custom/debiyiyuan-img/pic_01.png" width="100%" height="202"/>
	  </div>
	  <div class="timetitle">
	     <div class="fitact">
	        <span class="fitactword">适宜活动：</span>
	        <span class="fitactlist">创业，互联网，科技，商务，文创</span>
	     </div>
	     <div class="fitact">
	        <span class="fitactword">活动人数：</span>
	        <span class="fitactlist" >20-30人</span>
	     </div>
	     <img class="marb10" src="${rmtResPath}/static/images/custom/debiyiyuan-img/pic_02.png" width="100%" height="202"/>
	   </div>
  
	   <div class="piclist" >
	      <div class="pictitle">七宝德必易园</div>
	      <div class="lists">
		       <img class="picnum"  src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_01.png" width="100%" height="206"/>
		       <img class="picnum"   src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_02.png" width="100%" height="206"/>
		       <img class="picnum"   src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_03.png" width="100%" height="206"/>
	     </div>
	   </div>
	   <div class="piclist" >
	      <div class="pictitle">甘泉德必易园</div>
	      <div class="lists">
		       <img class="picnum"  src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_04.png" width="100%" height="206"/>
		       <img class="picnum"  src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_05.png" width="100%" height="206"/>
		       <img class="picnum"  src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_06.png" width="100%" height="206"/>
	     </div>
	   </div>
	   <div class="piclist">
	       <div class="pictitle" >茶点礼品</div>
	       <div class="lists">
		       <img class="picnum" src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_lp_01.png" width="100%" height="206"/>
		       <img class="picnum" src="${rmtResPath}/static/images/custom/debiyiyuan-img/dby_lp_02.png" width="100%" height="206"/>
	     </div>
	   </div>
	   <div class="cdqrcode">
	      <img class="mat10" src="${rmtResPath}/static/images/custom/debiyiyuan-img/logoslogan.png" width="70%" height="120"/>
	      <div class="mat20 focus" ><span style="color:#5f88c9;">关注我们，</span><span style="color:#313131;">获取更多活动资源</span></div>
	      <img class="mat5" src="${rmtResPath}/static/images/custom/debiyiyuan-img/ewm.png" width="150" height="150"/>
	  </div>
  </div>
   <!--footer-->
     <div class="btn-box footer">
          <div class="head-left-icon newfoot-left-icon">
        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
          </div>
          <a class="btn orange-btn btnheight" id="submit_tel"  href="tel:15601925235"><img class="cellphone" src="${rmtResPath}/static/images/tel_02.png"/>联系电话</a>
          <div class=" newfoot-right-icon" onclick="Header.toRightMenu()">
        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
          </div>
      </div>
    </div>
    <!-- footer left-icon -->
    <div class="newindex-nav hide" id="avtivity_nav" >
         <ul>
   	         <li class="retina-1px-border-bottom1"  onclick="Header.classify()">
   	          	<img class="tu" src="${rmtResPath}/static/images/activity.png" width="22" height="22">活动
             </li>
             <li class="retina-1px-border-bottom1"  onclick="Header.spaceIndex()">
   	            <img class="tu" src="${rmtResPath}/static/images/place.png" width="22" height="22">场地
             </li>
              <li class="retina-1px-border-bottom1"  onclick="Header.toGiftIndex()">
   	            <img class="tu" src="${rmtResPath}/static/images/initiate.png" width="22" height="22">礼品
             </li>
             <li class="retina-1px-border-bottom1" onclick="Header.toContact()">
        	   <img src="${rmtResPath}/static/images/contact.png" width="22" height="22"/>
                              联系我们
        	</li>
       	</ul>
    </div>
  
</body>
<script type="text/javascript">
$(function(){
	// 底部按钮居中
	$("#submit_tel").css("left",$(".newfoot-left-icon").width()+"px");
	Header.initFootIcon();
	$("#time").text(DateUtil.fomatDate(new Date(), 'yyyy-MM-dd'));
});
</script>
</html>