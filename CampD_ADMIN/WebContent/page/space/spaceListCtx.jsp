<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="10%" class="center">联系人</th>
			<th width="10%" class="center">联系方式</th>
			<th width="10%" class="center">名称</th>
			<th width="10%" class="center">类型</th>
			<th width="10%" class="center">适用活动</th>
			<th width="10%" class="center">费用</th>
			<th width="10%" class="center">特征</th>
			<th width="30%" class="center">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${spaceListMap.resultList}" var="space">
			<tr>
				<td class="center">${space.contactor}</td>
				<td class="center">${space.contact}</td>
				<td class="center">${space.name}</td>
				<td class="center">${systemConst.spaceTypeMap[space.space_type]}</td>
				<td class="center">${systemConst.categoryMap[space.work_for]}</td>
				<td class="center">${space.cost}</td>
				<td class="center">${systemConst.spaceLevelMap['0']}</td>
				<td class="center">
					<a href="javascript:void();">修改</a>
					<a href="javascript:void();">预览</a>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
</table>
${pageInfo.html}
