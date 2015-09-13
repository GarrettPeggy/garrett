<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	<script type="text/javascript" src="${locResPath}/static/js/demo/hello.js?_v=${vs}"></script>
</head>
<body ng-app="hello">
	This is a test page.
	<a href="http://localhost:8080/campD/demo/register?userName=王光华&password=garrett">addUser</a>
	<div ng-controller="Hello">
		<p>The ID is {{greeting.id}}</p>
		<p>The content is {{greeting.content}}</p>
		<%=new Date()%>
	</div>
</body>
</html>