<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<%@ include file="/page/common/jsCss.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/header.js?_v=${vs}"></script>
</head>
<body>
	<!-- 头部 -->
	<div class="header ac-detail-header clearfix">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">关于我们</div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
       <div class="campany">
          <div class="campanypic1 fl">
                <img  src="${rmtResPath}/static/images/logo.png" height="92" width="147"/>
          </div>
          <div class="campanypic2 fl">
                <img  src="${rmtResPath}/static/images/cd_logo_rights.png" height="52" width="147"/>
          </div>
       </div>
       <div class="description clearfix">
           <p>上海徊燕信息科技有限公司旗下产品：</p>
           <p style="font-size:16px;"><b>CD (Camp David) 营</b></p>
           <p><span style="font-size:15px;">www.cdying.cn</span> (微信公众号已认证，直接搜索“CD营”即可)。</p>
           <p>
                       本项目始于2015年5月，致力于构建基于线下和线上活动的互联网生态环境，
                       立于活动，搭建更大范围的内的协作沟通的互联网平台。
                       降低公司、组织或者个人的活动举办成本和难度，
                       同时提升用户的活动体验。
           </p>
           <p>
                       目前从活动场地和活动礼品着手，最新推出了专门针对众创空间项目招商的版本。
                       希望更多有志于在中国的互联网大时代有所作为的人员加入我们。
           </p>
           <p>联系电话：15601925235</p>
       </div>
    </div>
</body>
</html>