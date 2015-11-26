<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>${jsonview.activityInfo.title }</title>
		<%@ include file="/page/common/meta.jsp" %>
		<%@ include file="/page/common/jsCss.jsp" %>
		<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
		<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
		<script type="text/javascript" src="${locResPath}/static/common/share/jquery.qrcode.min.js?_v=${vs}"></script>
		<script type="text/javascript" src="${locResPath}/static/common/share/share.js?_v=${vs}"></script>
	</head>
	<body class="rea-body">
		<!-- 头部 -->
		<div class="header ac-detail-header clearfix">
	    	<a class="head-left-icon">
	        	<img src="${rmtResPath}/static/images/back.png" width="13" height="22" onclick="back()"/>
	        </a>
	        <div class="head-content">活动详情</div>
	        <div class="head-right-icon">
	        	<img src="${rmtResPath}/static/images/guanzhu.png" onclick="Activity.share()" width="28" height="28"/>
	        </div>
    	</div>
		<!-- end -->
		<!-- 主体 -->
	    <div class="main">
	    	<input type="hidden" id="user" name="user" value="${USER_INFO}"/>
	    	<input type="hidden" id="activityId" name="activityId" value="${jsonview.activityInfo.id}"/>
		    <!-- 活动概括与地点 -->
	    	<div class="ac-detail-title retina-1px-border-bottom">
		        <img src="${sysConfig.ossResUrl}${jsonview.activityInfo.show_image }" width="100%" height="156"/>
	           
	            <div class="hdname">${jsonview.activityInfo.title }</div>
	          
	            <div class="adt-desc">
	              <!--    <div class="fontSize17 retina-1px-border-bottom">${jsonview.activityInfo.title }</div> -->
	                <div class="fontSize14 timedate ">
	                    <span class="fl time"><img src="${rmtResPath}/static/images/HD_date.png" width="17" height="17"/></span>
	                    <span class="fontSize13 date " >
	                    	<fmt:parseDate value="${jsonview.activityInfo.begintime}" var="begintime" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    	<fmt:parseDate value="${jsonview.activityInfo.endtime}" var="endtime" pattern="yyyy-MM-dd HH:mm:ss"/>
	                    	<fmt:formatDate value="${begintime}" pattern="MM月dd日  HH:mm"/>&nbsp;--
	                    	<fmt:formatDate value="${endtime}" pattern="MM月dd日  HH:mm"/>
	                    </span>
	                </div>
	                <div class="fontSize14  dress">
	                    <span class="fl address"><img  src="${rmtResPath}/static/images/CHANGDI_dress.png"  width="17" height="17"/></span>
	                	<span class="fontSize13">${jsonview.activityInfo.adress }</span>
	                </div>   
	            </div>
	            
	        </div>
	        <!-- end -->
	        <!-- 活动主办方、赞助方 -->
	        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
	        	<div class="adm-line retina-1px-border-bottom clearfix newheight">
	        		<span class="fl sponsor">主办方:</span>
	        		<span class="fl sponserInfo">${jsonview.activityInfo.sponsor }</span>
	            </div>
	          
	            <div class="adm-line clearfix newheight">
	            	<span class="fl sponsor">联合方:</span>
                	<span class="fl sponserInfo">${jsonview.activityInfo.assistance }</span>
	            </div>
	            
	        </div>
	        <!-- end -->
	        <!-- 活动详细介绍 -->
	        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
	        	<div class="add-title retina-1px-border-bottom">
	            	详细介绍
	            </div>
	            <div class="add-desc newadd-desc">
	            	${jsonview.activityInfo.requirement }
	            </div>
	        </div>
	        <!-- end -->
	        
	          
	     	     
	        <!-- 按钮 -->
	        <c:choose>
	        	<c:when test="${empty USER_INFO }"><!-- 用户没有登录，显示报名按钮 -->
				   <!-- 活动详情footer -->
			       <div class="btn-box footer">
			           <div class="head-left-icon newfoot-left-icon">
			        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
			          </div> 
			           
			          <button class="btn orange-btn btnheight" id="submit_act" type="button" onclick="Activity.sign()">立即报名</button>
			          <div class="head-right-icon newfoot-right-icon">
			        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
			          </div>
			       </div>	
	        	</c:when>
	        	
	        	<c:when test="${!empty USER_INFO && flag==0 }"><!-- 不是该用户的活动，显示按钮 -->
				    <div class="btn-box footer">
			          <div class="head-left-icon newfoot-left-icon">
			        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
			          </div>
			          
			        	<button class="btn orange-btn  btnheight" id="submit_act"  type="button" onclick="Activity.sign()">立即报名</button>
			         
			          <div class="head-right-icon newfoot-right-icon">
			        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
			          </div>
			        </div>	
	        	</c:when>
	        	
	        	<c:when test="${!empty USER_INFO && flag==1 }"><!-- 是该用户的活动，不显示按钮 -->
	        	</c:when>
	        	
	        	<c:when test="${!empty USER_INFO && flag==2 }"><!-- 不是该用户的活动，但是该用户已经报名 -->
					<div class="btn-box footer">
			          <div class="head-left-icon newfoot-left-icon">
			        	<img src="${rmtResPath}/static/images/nav_icon.png" width="27" height="14">
			          </div>
			          <button class="btn gray-btn btnheight" id="submit_act" type="button" disabled="disabled">已报名</button>
			          <div class="head-right-icon newfoot-right-icon">
			        	<img src="${rmtResPath}/static/images/user_icon_grey.png" width="17" height="17">
			          </div>
			        </div>	
	        	</c:when>
	        	
	        	<c:otherwise></c:otherwise>
	        </c:choose>
	        <!-- end -->
    	</div>
    	<!-- end -->
    
	    <!-- 蒙层 需要显示时删除hide -->
		<div class="mc hide" id="signUp_mc"></div>
	    <!-- end -->
    
		<!-- 提示框 需要显示时删除hide -->
	    <div class="tc-modal sign-succ-modal hide" id="signUp_modal">
	    	<img src="${rmtResPath}/static/images/yes.png" width="49" height="49" class="sure-icon"/>
	    	<div class="tc-modal-title">
	        	<img src="${rmtResPath}/static/images/close_icon.png" width="16" height="16" onclick="Activity.closeSignUp()"/>
	        </div>
	        <div class="tc-modal-content">
	        	<div class="modal-line ">
	            	<span class="modal-line1 newmodal-line1 ">恭喜您</span>
	                <span class="modal-line2 newmodal-line2 ">报名成功！</span>
	            </div>
	            <div class="modal-line pdt10 fontSize13 color94 ">
	            	您可以在[已报名的活动]<br/>中查看相关信息哟
	            </div>
	        </div>
	    </div>
	    <!-- end -->

		<!-- 分享 需要显示时删除hide -->
	    <div class="tc-modal share-modal hide newtc-modal" id="activity_share">
	    	<img class="ma" alt="二维码" src="${rmtResPath}/static/images/icon/cd_qrcode.jpg">
			<!-- <div id="code" style="margin: 20px 20px 20px 20px"></div> -->
	       <!--  <div class="bottom-close retina-1px-border-top">
	        	<a onclick="Activity.cancel()">取&nbsp;&nbsp;消</a>
	        </div>
	         -->
	         <span class="more">关注我们，获取更多活动资源</span>
	         <img class="close" src="${rmtResPath}/static/images/login/closed.png"  onclick="Activity.cancel()"/>
	    </div>
	    <!-- end -->
	    
	    <!-- footer left-icon -->
        <div class="newindex-nav hide" id="avtivity_nav" >
   	         <ul>
       	         <li class="retina-1px-border-bottom1" onclick="Activity.classify()">
        	         <img class="tu" src="${rmtResPath}/static/images/activity.png" width="22" height="22">活动
                 </li>
                 <li class="retina-1px-border-bottom1" onclick="Space.classify()">
       	            <img class="tu" src="${rmtResPath}/static/images/place.png" width="22" height="22">场地
                 </li>
            </ul>
        </div>
	    
	    <!-- footer right-icon-->
	    <div class="footer-newperson-right hide" id="activity_person">
     	  <ul>
         	<li class="line clearfix" onclick="Activity.signUp()">
             	<img src="${rmtResPath}/static/images/p1.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">报名的活动</div>
          </li>
          <%-- <li class="line clearfix" onclick="Activity.sponsored()">
             	<img src="${rmtResPath}/static/images/p2.png" width="41" height="41" class="fl left-img"/>
              <div class="fl right-text">举办的活动</div>
          </li> --%>
          <c:if test="${empty USER_INFO}">
	          <li class="line clearfix" onclick="Header.toLogin()">
	             	<img src="${rmtResPath}/static/images/p3.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">登录</div>
	          </li>
          </c:if>
          <!-- 以下两个通过增加hide样式显示隐藏 -->
          <c:if test="${!empty USER_INFO}">
          	  <li class="line clearfix" onclick="Header.toUpdate()">
	             	<img src="${rmtResPath}/static/images/p3.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">${USER_INFO.userName}</div>
	          </li>
	          <li class="line clearfix" onclick="Header.toQuit()">
	             	<img src="${rmtResPath}/static/images/p4.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">退出</div>
	          </li>
          </c:if>
          <c:if test="${empty USER_INFO}">
	          <li class="line clearfix" onclick="Header.toRegister()">
	             	<img src="${rmtResPath}/static/images/p5.png" width="41" height="41" class="fl left-img"/>
	              <div class="fl right-text">注册</div>
	          </li>
          </c:if>
        </ul>
      </div>
	    
	</body>
	<script type="text/javascript">
	
		$(function(){
			// 底部按钮居中
			$("#submit_act").css("left",$(".newfoot-left-icon").width()+"px");
			Header.initFootIcon();
		});
		
		 //活动分享
		Activity.share=function(){
			var isHide = $("#activity_share").hasClass("hide");
			if(isHide){
				$("#activity_share").removeClass("hide");
				$("#activity_share").height($(document).height());
			} else{
				$("#activity_share").addClass("hide");
			}
			//Share.qcode();
		};
	</script>
</html>