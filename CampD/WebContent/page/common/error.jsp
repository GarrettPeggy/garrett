<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${rmtResPath}/static/css/common.css?_v=${vs}" />
	<link rel="shortcut icon" href="${rmtResPath}/static/images/CD-20x20.png"/>
</head>
<style type="text/css">
.nofood{
	width:640px;
	height:1136px;
	background:url(http://camp-images.oss-cn-shanghai.aliyuncs.com/images/20151104/wrong.png);
	font-size:34px;
	color:#FFF;
	
}
.nofood p{
	font-family:微软雅黑;
	padding:520px 0 0 153px;
	
}
.nofood ul{
	width:50%;
	margin:0 auto;
	margin-top:20px;
	line-height:60px;
	
}
.nofood ul li{
	list-style-type:disc;
	font-family:微软雅黑;
}
.nofood a{

	color:#FFF;
}
.point{
	width:43%;
	margin:35px auto;
}
.point a{
	padding-right:8px;
}
	
</style>

<script type="text/javascript">
	var BASE_PATH = '${ctx}';
	var t = 5;
	var interval;
	function timeout(){
		interval = setInterval(jishi,1000);
	}
	
	function jishi(){
		document.getElementById('times').innerHTML= --t;
		if(t<=0){
			clearInterval(interval);
			window.location.href = BASE_PATH;
		}
	}
</script>

<body onload="timeout();">
   <div class="nofood">
       <p>可能原因：</p>
       <ul>
          <li>网络信号弱</li>
          <li>找不到请求页面</li>
       </ul>
       <div class="point">
       	<em id="times">5</em>秒后,&nbsp;<a href="${ctx}">返回首页</a>
       	<!-- <img src="http://camp-images.oss-cn-shanghai.aliyuncs.com/images/20151104/new.png" /> -->
       </div>
   </div>
</body>
</html>