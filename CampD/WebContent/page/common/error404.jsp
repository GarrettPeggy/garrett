<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="${rmtResPath}/5/0/static/css/base.css?_v=${vs}" />
<link rel="stylesheet" type="text/css" href="${rmtResPath}/5/0/static/css/global.css?_v=${vs}" />
<link rel="stylesheet" type="text/css" href="${rmtResPath}/5/0/static/css/main.css?_v=${vs}" />
<link rel="shortcut icon"
	href="${locResPath}/web/meta/images/login/logo.png" />
<title>错误通知</title>
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
			window.location.href = BASE_PATH+'/main/index.do';
		}
	}
</script>
</head>
<body onload="timeout();">
	<div class="box-404">
		<div class="box-404left">
			<span class="pic-404"></span>
			<div class="chengtext">
				<h3>Not Found！</h3>
				<p>
					哎呀！这个页面怎么有问题。一信通提醒您 <br> 伸个懒腰喝杯水，过一会再来试试吧！
				</p>
				<p class="fon12">
					<em id="times">5</em>秒后,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/main/index.do">返回首页</a>
				</p>
			</div>
		</div>
	</div>
	<!-- <div class="foot_404">© 2009-2014 上海帜讯信息技术有限公司</div> -->
	<div class="foot_404"></div>

</body>
</html>