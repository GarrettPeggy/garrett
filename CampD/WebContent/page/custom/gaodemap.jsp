<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=733e1f9284de61d2a8ae5dd00db2338b"></script> 
	<style type="text/css">
	 #container {width: 963px;height: 2000px;}
	</style>
</head>
<body>
	<div id="container"></div>
</body>
<script type="text/javascript">
	var map = new AMap.Map('container',{
	    zoom: 10,
	    center: [116.39,39.9]
	});
</script>
</html>
