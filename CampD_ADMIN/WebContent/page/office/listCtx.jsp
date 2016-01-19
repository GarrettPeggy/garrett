<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="10%" class="center">空间名称</th>
			<th width="10%" class="center">类型</th>
			<th width="10%" class="center">联系人</th>
			<th width="10%" class="center">联系方式</th>
			<th width="10%" class="center">费用</th>
			<th width="10%" class="center">省&nbsp;市&nbsp;区</th>
			<th width="10%" class="center">地址</th>
			<th width="30%" class="center">操作</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${officeListMap.resultList}" var="officeSpace">
			<tr>
				<td class="center">${officeSpace.name}</td>
				<td class="center">${systemConst.officeSpaceTypeMap[officeSpace.type]}</td>
				<td class="center">${officeSpace.contactor}</td>
				<td class="center">${officeSpace.contact}</td>
				<td class="center">${officeSpace.cost}（${officeSpace.unit}）</td>
				<td class="center">${officeSpace.province}&nbsp;${officeSpace.city}&nbsp;${officeSpace.area}</td>
				<td class="center">${officeSpace.address}</td>
				<td class="center">
					<a href="${ctx}/office/toEdit.do?id=${officeSpace.id}">修改</a>&nbsp;
					<a href="${ctx}/office/toView.do?id=${officeSpace.id}">查看</a>&nbsp;
					<c:if test="${systemConst.OFFICE_SPACE_STATUS_1 eq officeSpace.status}">
						<a href="javascript:Office.updateStatus('${officeSpace.id}','${systemConst.OFFICE_SPACE_STATUS_0}')">隐藏</a>
					</c:if>
					<c:if test="${systemConst.OFFICE_SPACE_STATUS_0 eq officeSpace.status}">
						<a href="javascript:Office.updateStatus('${officeSpace.id}','${systemConst.OFFICE_SPACE_STATUS_1}')">显示</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
	
</table>
${pageInfo.html}