<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="30%" class="center">通知内容</th>
			<th width="70%" class="center">通知链接</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${sysConfig.sysConfigList}" var="sysConfig">
			<tr>
				<td class="center">${sysConfig.value_val}</td>
				<td class="center">${sysConfig.url_val}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
