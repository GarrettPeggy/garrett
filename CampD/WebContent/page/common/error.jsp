<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${rmtResPath}/static/css/common.css?_v=${vs}" />
	<script type="text/javascript" src="${locResPath}/static/common/jquery/jquery-1.11.3.min.js?_v=${vs}"></script>
	<link rel="shortcut icon" href="${rmtResPath}/static/images/CD-20x20.png"/>
</head>

<script type="text/javascript">
	var BASE_PATH = '${ctx}';
	var t = 10;
	var interval;
	function timeout(){
		//$(".nofood").height($(document).height());
		//(".nofood").width($(document).width());
		//interval = setInterval(jishi,1000);
	}
	
	function jishi(){
		document.getElementById('times').innerHTML= --t;
		if(t<=0){
			clearInterval(interval);
			window.location.href = "/";
		}
	}
</script>

<body onload="timeout();">
 <!--    <div class="nofood">
       <p>可能原因：</p>
       <ul>
          <li>网络信号弱</li>
          <li>找不到请求页面</li>
       </ul>
       <div class="point">
       	<em id="times">10</em>秒后,&nbsp;<a href="${ctx}">返回首页</a>
       	<img src="${rmtResPath}/static/images/new.png" />
       </div>
   </div>-->
  <div class="error" >
  <div class="pic">
    <img src="/static/images/wrong.png" />
    
     <div class="word" >
       <p >可能原因：</p>
       <ul>
          <li>网络信号弱</li>
          <li>找不到请求页面</li>
       </ul>
     </div>  
     <div class="point" >
       <a href="${ctx}" >点击刷新</a>
       	<img src="http://camp-images.oss-cn-shanghai.aliyuncs.com/static/images/new.png" />
     </div>
 </div>
</div>

</body>
</html>