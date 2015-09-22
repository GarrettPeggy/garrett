<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>

<div class="breadcrumbs" id="breadcrumbs">

	<script type="text/javascript">
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		var navigationItems = '${param.navigationItems}';
	</script>
	
	<ul class="breadcrumb" id="sysBreadCrumb"></ul>
</div>

