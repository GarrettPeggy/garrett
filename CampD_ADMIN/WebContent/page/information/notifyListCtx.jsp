<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="30%" class="center">通知内容</th>
			<th width="50%" class="center">通知链接</th>
			<th width="20%" class="center">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${sysConfig.sysConfigList}" var="sysConfig">
			<input type="hidden" name="keyVal" value="${sysConfig.key_val}"><!-- 首页通知配置类型   -->
			<tr>
				<td class="center">${sysConfig.value_val}</td>
				<td class="center">${sysConfig.url_val}</td>
				<td class="center">
					<button class="btn btn-xs btn-info" onclick="toOpenEditNotify('${sysConfig.sys_type}','${sysConfig.key_val}');" type="button">
						<span class="bigger-110">编辑</span>
					    <i class="ace-icon fa fa-edit icon-on-right"></i>
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
