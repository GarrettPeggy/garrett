<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<link rel="stylesheet" href="${locResPath}/static/common/citySelect/city.css?_v=${vs}" />
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/common/citySelect/jquery.cityselect.js?_v=${vs}"></script>
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
		 <form id="addActivityForm">
		 	<flagToken:token tokenName="addActivityForm"/>
		 	<input type="hidden" id="contact" name="contact" value="${USER_INFO.mdn}"/>
		 	<input type="hidden" id="actType" name="actType" value="0"/>
		 	<input type="hidden" id="status" name="status" value="0"/>
	    	<div class="sub-ac-form">
	    		<dl>
	                <dt>活动类型</dt>
	                <dd>
		                <select id="categoryId" name="categoryId">
		                	<option value="">---请选择---</option>
		                	<c:forEach items="${systemConst.categoryMap}" var="category">
								<option value="${category.key}">${category.value}</option>
							</c:forEach>
		                </select>
	                </dd>
	           </dl>
	           <dl>  
	           	  <dt>活动人数</dt>
	              <dd>
                 	<input type="text" id="actNum" name="actNum" class="text-input-2"/>&nbsp;人
              	  </dd>
               </dl>
               <dl>
	              <dt>所在地区</dt>
	              <dd><!-- style="float: left;margin-left: 112px;margin-top: -23px;" -->
		            <div class="infolist"> 
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
	           </dd>
	          </dl>
	          <dl style=" border:none;">
	             <dt>活动需求</dt>
	             <dd>
                 	<textarea rows="4" class="ac-desc-text-2" id="requirement" name="requirement"></textarea>
              	 </dd>
              </dl>
            </div>
	        <div class="btn-box">
	        	<button class="btn orange-btn" id="Activity.actSub" type="button" onclick="Activity.actSub()">立即提交</button>
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