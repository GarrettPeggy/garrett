<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="15%" class="center">用户名</th>
			<th width="15%" class="center">手机号</th>
			<th width="15%" class="center">角色</th>
			<th width="15%" class="center">注册时间</th>
			<th width="15%" class="center">最后登录时间</th>
			<th width="25%" class="center">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${userListMap.userList}" var="user">
			<tr>
				<td class="center">${user.userName}</td>
				<td class="center">${user.mdn}</td>
				<td class="center">${user.roleName}</td>
				<td class="center">${user.register_time}</td>
				<td class="center">${user.login_time}</td>
				<td class="center">
					<c:if test="${user.roleName eq sysConfig.role_common}">
						<a href="javascript:User.setRole('${user.id}','${sysConfig.role_super_admin}');">设为管理员</a>
					</c:if>
					<c:if test="${user.roleName eq sysConfig.role_admin}">
						<a href="javascript:User.setRole('${user.id}','${sysConfig.role_super_admin}');">设为注册用户</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
</table>
${pageInfo.html}
