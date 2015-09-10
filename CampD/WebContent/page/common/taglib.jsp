<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/flaginfoTokenLib" prefix="flagToken" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("httpProtocol", request.getScheme());
%>
<c:set var="locResPath" value="${pageContext.request.contextPath}"/>
<c:set var="rmtResPath" value="${httpProtocol}://${sysConfig.rmtResUrl}"/>
<c:set var="vs" value="${sysConfig.jsCssVersion}"/>

