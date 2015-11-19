<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
</head>
<%
	String requirement = request.getParameter("requirement");
	pageContext.setAttribute("requirement", requirement);
%>
<body class="ke-content">
	${requirement }
</body>
</html>