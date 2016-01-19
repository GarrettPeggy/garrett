<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="20%" class="center">空间名称</th>
			<th width="20%" class="center">省&nbsp;市&nbsp;区</th>
			<th width="60%" class="center">操作</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${officeSpaceListMap.resultList}" var="officeSpace">
			<tr>
				<td class="center">${officeSpace.name}</td>
				<td class="center">${officeSpace.province}&nbsp;${officeSpace.city}&nbsp;${officeSpace.area}</td>
				<td class="center">
					<a href="${ctx}/officeSpace/toEdit.do?id=${officeSpace.id}">修改</a>&nbsp;
					<a href="${ctx}/officeSpace/toView.do?id=${officeSpace.id}">查看</a>&nbsp;
					<c:if test="${systemConst.OFFICE_SPACE_STATUS_1 eq officeSpace.status}">
						<a href="javascript:OfficeSpace.updateStatus('${officeSpace.id}','${systemConst.OFFICE_SPACE_STATUS_0}')">隐藏</a>
					</c:if>
					<c:if test="${systemConst.OFFICE_SPACE_STATUS_0 eq officeSpace.status}">
						<a href="javascript:OfficeSpace.updateStatus('${officeSpace.id}','${systemConst.OFFICE_SPACE_STATUS_1}')">显示</a>
					</c:if>&nbsp;
					<a href="${ctx}/office/toAdd.do?belongTo=${officeSpace.id}">添加场地</a>&nbsp;
					<a href="${ctx}/office/toList.do?belongTo=${officeSpace.id}">场地列表</a>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
	
</table>
${pageInfo.html}