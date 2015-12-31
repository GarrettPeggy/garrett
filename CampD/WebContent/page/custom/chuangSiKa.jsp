<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>CD营2016年免费场地推荐（1月第一批）</title>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" href="${locResPath}/static/common/swipe/css/swipe.css?_v=${vs}"/>
	<script type="text/javascript" src="${locResPath}/static/common/swipe/js/swipe.js?_v=${vs}"></script>
	<style type="text/css">
		.bg{position:relative;margin-top:51px;margin-bottom:30px;}
		.space-img{
		position: absolute;
		height: 9%;
  		width: 43%;
		background: rgba(0,0,0,0);-moz-border-radius: 70px;-webkit-border-radius: 70px;border-radius: 70px;}
		#space-img-1{
  			top: 19%;
  			left: 0.5%;
		}
		#space-img-2{
  			  top: 30%;
			  right: 3%;
			  width: 48%;
		}
		#space-img-3{
  			top: 39.5%;
  			left: 0;
  			width: 47%;
		}
		#space-img-4{
  			top: 50%;
			right: 0;
			height: 9%;
		}
		#space-img-5{
  			top: 59%;
  			left: 0;
		}
		.position{
		 padding-right: 8px;
         margin: 0;
		 opacity: 0.4;
		 width: 100%;
		 margin-top: 10%;
		}
		.position li{
		  width: 10px;
		  height: 10px;
		  margin: 0 2px;
		  display: inline-block;
		  -webkit-border-radius: 5px;
		  border-radius: 5px;
		  background-color: #AFAFAF;
		}
		.position li.cur{  
		background-color: #FF0000;
		}
		.max .addWrap1{
		background:#000;
		}
	</style>
</head>
<body>
	<!-- 头部我的中心信息这一部分 -->
	<%@ include file="/page/common/head.jsp" %>
     <div class="bg"> 
       <img src="${rmtResPath}/static/images/custom/aosika-img/newyear.png" height="100%" width="100%"/>
       <div class="space-img" id="space-img-1" onclick="showMax(1);"></div>
       <div class="space-img" id="space-img-2" onclick="showMax(2);"></div>
       <div class="space-img" id="space-img-3" onclick="showMax(3);"></div>
       <div class="space-img" id="space-img-4" onclick="showMax(4);"></div>
       <div class="space-img" id="space-img-5" onclick="showMax(5);"></div>
     </div>
     
     <!-- 点击图片放大   需要显示时删除hide-->
     <!--  场地1-->
    <div class="max hide" id="max-1" onclick="hideMax(1)">
		   <div class="heiht30"></div>
		   <div class="addWrap addWrap1">
		        <div class="img-tap-show-space swipe" style="height:100%;" id="mySwipe-1">
		        	<div class="swipe-wrap">
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_1_1.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_1_2.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_1_3.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_1_4.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_1_5.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
		           </div>
		        </div>
		         <ul id="position-1" class="position"  style="z-index:1;">
		         	<li class="cur"></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
				</ul>	
		 </div>
		 <div class="heiht30" ></div>
     </div>
     <!--  场地2-->
    <div class="max hide" id="max-2" onclick="hideMax(2)">
		   <div class="heiht30"></div>
		   <div class="addWrap addWrap1" style="height:40%;">
		        <div class="img-tap-show-space swipe" style="height:100%;" id="mySwipe-2">
		        	<div class="swipe-wrap">
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_2_1.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_2_2.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_2_3.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_2_4.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_2_5.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
		           </div>
		        </div>
		         <ul id="position-2" class="position"  style="z-index:1;">
		         	<li class="cur"></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
				</ul>	
		 </div>
		 <div class="heiht30"></div>
     </div>
     <!--  场地3-->
    <div class="max hide" id="max-3" onclick="hideMax(3)">
		   <div class="heiht30"></div>
		   <div class="addWrap addWrap1">
		        <div class="img-tap-show-space swipe" style="height:100%;" id="mySwipe-3">
		        	<div class="swipe-wrap">
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_3_1.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_3_2.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_3_3.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_3_4.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_3_5.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
		           </div>
		        </div>
		         <ul id="position-3" class="position"  style="z-index:1;">
		         	<li class="cur"></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
				</ul>	
		 </div>
		 <div class="heiht30"></div>
     </div>
     <!--  场地4-->
    <div class="max hide" id="max-4" onclick="hideMax(4)">
		   <div class="heiht30"></div>
		   <div class="addWrap addWrap1">
		        <div class="img-tap-show-space swipe" style="height:100%;" id="mySwipe-4">
		        	<div class="swipe-wrap">
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_4_1.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_4_2.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_4_3.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_4_4.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_4_5.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
		           </div>
		        </div>
		         <ul id="position-4" class="position" style="z-index:1;">
		         	<li class="cur"></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
				</ul>	
		 </div>
		 <div class="heiht30"></div>
     </div>
     <!--  场地5-->
    <div class="max hide" id="max-5" onclick="hideMax(5)">
		   <div class="heiht30"></div>
		   <div class="addWrap addWrap1">
		        <div class="img-tap-show-space swipe" style="height:100%;" id="mySwipe-5">
		        	<div class="swipe-wrap">
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_5_1.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_5_2.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_5_3.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_5_4.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
				    	<div>
					    	<a href="javascript:void();">
					    		<img src="${rmtResPath}/static/images/custom/aosika-img/chuangsika_5_5.JPG" class="img-responsive" alt="1" style="height: 100%;" />
					    	</a>
				    	</div>
		           </div>
		        </div>
		         <ul id="position-5" class="position" style="z-index:1;">
		         	<li class="cur"></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
		        	<li></li>
				</ul>	
		 </div>
		 <div class="heiht30"></div>
     </div>
     
    <!--footer -->
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
		$(".max").css("left",window.screen.width);
	});
	
	showMax=function(index){
		$("#max-"+index).removeClass("hide");
		$("#max-"+index).animate({left:"0"},100);
		tapShow("position-"+index,"mySwipe-"+index);
	};
	hideMax=function(index){
		$("#max-"+index).animate({left:window.screen.width},300);
		setTimeout('$(".max").addClass("hide");',300);
	};
	tapShow=function(position,mySwipe){
		var bullets = document.getElementById(position).getElementsByTagName('li');
		var banner = Swipe(document.getElementById(mySwipe), {
			auto: 2000,
			continuous: true,
			disableScroll:false,
			callback: function(pos) {
				var i = bullets.length;
				while (i--) {
					bullets[i].className = ' ';
				}
				bullets[pos].className = 'cur';
			}
		});
		return banner;
	};
</script>
</html>