<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
	<head>
		<%@ include file="/page/common/meta.jsp" %>
		<%@ include file="/page/common/jsCss.jsp" %>
		<link rel="stylesheet" type="text/css" href="${locResPath}/static/css/nativeShare.css?_v=${vs}" />
		<script type="text/javascript">
			var rmtResPath="${rmtResPath}";
			var ctx="${ctx}";
			//alert("${jsonview}");
		</script>
		<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
		<script type="text/javascript" src="${locResPath}/static/common/nativeShare.js?_v=${vs}"></script>
	</head>
	<body class="rea-body">
		<!-- 头部 -->
		<div class="header ac-detail-header clearfix">
	    	<a class="head-left-icon">
	        	<img src="${rmtResPath}/static/images/back.png" width="13" height="22" onclick="back()"/>
	        </a>
	        <div class="head-content">活动详情</div>
	        <div class="head-right-icon">
	        	<img src="${rmtResPath}/static/images/share_icon.png" onclick="Activity.share()" width="23" height="23"/>
	        </div>
    	</div>
		<!-- end -->
		<!-- 主体 -->
	    <div class="main">
		    <!-- 活动概括与地点 -->
	    	<div class="ac-detail-title retina-1px-border-bottom">
		        <img src="${rmtResPath}/static/images/example_img_big.png" width="100%" height="116"/>
	            <div class="adt-desc">
	                <div class="fontSize17 retina-1px-border-bottom">${jsonview.activityInfo.title }</div>
	                <div class="fontSize14">
	                    <span>${jsonview.activityInfo.begintime }--</span>
	                    <span>${jsonview.activityInfo.endtime }</span>
	                </div>
	                <div class="fontSize14 mat7 pdb10">
	                	${jsonview.activityInfo.adress }
	                </div>
	            </div>
	        </div>
	        <!-- end -->
	        <!-- 活动主办方、赞助方 -->
	        <div class="ac-detail-mechanism retina-1px-border-bottom retina-1px-border-top">
	        	<div class="adm-line retina-1px-border-bottom">
	            	主办方:${jsonview.activityInfo.sponsor }
	            </div>
	            <div class="adm-line">
	            	赞助方:${jsonview.activityInfo.assistance }
	            </div>
	        </div>
	        <!-- end -->
	        <!-- 活动详细介绍 -->
	        <div class="ac-detail-desc retina-1px-border-bottom retina-1px-border-top">
	        	<div class="add-title retina-1px-border-bottom">
	            	${jsonview.activityInfo.requirement }
	            </div>
	            <div class="add-desc">
	            	活动描述字段没有  活动描述字段没有  活动描述字段没有
	            </div>
	        </div>
	        <!-- end -->
	        <!-- 按钮 -->
	        <div class="btn-box">
	        	<button class="btn orange-btn" type="button" onclick="Activity.sign()">立即报名</button>
	        	<input type="hidden" id="activityId" value="${jsonview.activityInfo.id}"/>
	        </div>
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
	        	<div class="modal-line">
	            	<span class="modal-line1">恭喜您</span>
	                <span class="modal-line2">报名成功！</span>
	            </div>
	            <div class="modal-line pdt10 fontSize13 color94">
	            	您可以在[已报名的活动]<br/>中查看相关信息哟
	            </div>
	        </div>
	    </div>
	    <!-- end -->

		<!-- 分享 需要显示时删除hide -->
	    <div class="tc-modal share-modal hide" id="activity_share">
	    	<div id="nativeShare"></div>
	    	<script>
			    var config = {
			        url:'http://blog.wangjunfeng.com',// 分享的网页链接
			        title:'王俊锋的个人博客',// 标题
			        desc:'王俊锋的个人博客',// 描述
			        img:'http://www.wangjunfeng.com/img/face.jpg',// 图片
			        img_title:'王俊锋的个人博客',// 图片标题
			        from:'王俊锋的博客' // 来源
			    };
	    		var share_obj = new nativeShare('nativeShare',config);
			</script>
	    	<%-- 
	    	<ul class="clearfix">
	        	<li>
	            	<img src="${rmtResPath}/static/images/shaer_icon_04.png" width="58" height="58"/>
	                <p class="mat7">收藏</p>
	            </li>
	            <li>
	            	<img src="${rmtResPath}/static/images/shaer_icon_01.png" width="58" height="58"/>
	                <p class="mat7">微信朋友圈</p>
	            </li>
	            <li>
	            	<img src="${rmtResPath}/static/images/shaer_icon_02.png" width="58" height="58"/>
	                <p class="mat7">微信好友</p>
	            </li>
	            <li>
	            	<img src="${rmtResPath}/static/images/shaer_icon_03.png" width="58" height="58"/>
	                <p class="mat7">QQ好友</p>
	            </li>
	        </ul>
	         --%>
	        <div class="bottom-close retina-1px-border-top">
	        	<a onclick="Activity.cancel()">取&nbsp;&nbsp;消</a>
	        </div>
	    </div>
	    <!-- end -->
	</body>
</html>