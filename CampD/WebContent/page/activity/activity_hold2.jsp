<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" href="${locResPath}/static/js/citySelect/city.css?_v=${vs}" />
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/jquery.cityselect.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header clearfix hold-ac-header retina-1px-border-bottom">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">举办活动</div>
        <a class="head-right-icon clearfix" href="tel:15601925235">
        	<img src="${rmtResPath}/static/images/call_phone_icon.png" width="18" height="18"/>
            <span>咨询</span>
        </a>
    </div>
	<!-- end -->
	<!-- 主体 -->
	 <div class="main">
		 <form id="loginForm">
	    	<div class="sub-ac-form">
	        	<!-- <a class="line clearfix" href="###"> -->
	        	<div class="line clearfix">
	                <div class="fl">活动类型</div>&nbsp;&nbsp;&nbsp;&nbsp;
	                <select id="categoryId">
	                	<option value="">---请选择---</option>
	                	<option value="0">创业</option>
	                	<option value="1">商务</option>
	                	<option value="2">玩乐</option>
	                	<option value="3">交友</option>
	                </select>
	            </div>  
	            <div class="line clearfix">
	            	<div class="fl">活动人数</div>&nbsp;&nbsp;&nbsp;&nbsp;
	                <input type="text" id="actNum" name="actNum" class="text-input-2"/>
	            </div>
	            <div class="line clearfix" id="actc">
	            	<div class="fl">所在地区</div>&nbsp;&nbsp;&nbsp;&nbsp;
	            	<div class="col-xs-12 col-sm-3 infolist"> 
						<span class="block input-icon input-icon-right liststyle">
                     		<span id="province">
                                 <i>请选择省份</i>
                                 <ul>
                                     <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
                                 </ul>
                                 <input type="hidden" name="province" id="province" class="curValue" value="">
                             </span>
                             <span id="city">
                                 <i>请选择城市</i>
                                 <ul>
                                     <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
                                 </ul>
                                 <input type="hidden" name="city" id="city" class="curValue" value="">
                             </span>
                             <span id="area">
                                 <i>请选择地区</i>
                                 <ul>
                                     <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
                                 </ul>
                                 <input type="hidden" name="area" id="area" class="curValue" value="">
                             </span>
                   		</span> 
                   	</div>
	            </div>
	            <div class="line clearfix-2" style="height: 100px;">
	            	<div class="fl">活动需求</div>&nbsp;&nbsp;&nbsp;&nbsp;
	                <textarea rows="6" class="ac-desc-text-2" id="requirement" name="requirement"></textarea>
	            </div>
	        </div>
	        <div class="btn-box">
	        	<button class="btn orange-btn" type="button" onclick="Activity.actSub()">立即提交</button>
	        </div>
	    </form>
    </div>
    
    <!-- 蒙层 需要显示时删除hide -->
    <div class="mc hide"></div>
    <!-- end -->
    
    <!-- 提示框 需要显示时删除hide -->
    <div class="tc-modal sign-succ-modal hide">
    	<img src="${rmtResPath}/static/images/yes.png" width="49" height="49" class="sure-icon"/>
    	<div class="tc-modal-title">
        	<img src="${rmtResPath}/static/images/close_icon.png" onclick="Activity.close()" width="16" height="16"/>
        </div>
        <div class="tc-modal-content">
        	<div class="modal-line">
            	<span class="modal-line1">恭喜您</span>
                <span class="modal-line2">提交成功！</span>
            </div>
            <div class="modal-line pdt10 fontSize13 color94 clearfix">
            	我们马上会和您确认哟！<br/>
                <img src="${rmtResPath}/static/images/forword_icon.png" class="fr" width="42" height="23"/>
            </div>
        </div>
    </div>
    <!-- end -->
	
</body>
<script type="text/javascript">
	$(function(){
		$.fn.citySelect(['#province', '#city', '#area'],['北京市' , '北京市' , '东城区']);
	});
</script>
</html>