<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="8%" class="center">活动标题</th>
			<th width="8%" class="center">城市</th>
			<th width="8%" class="center">活动范畴</th>
			<th width="10%" class="center">地址</th>
			<th width="8%" class="center">活动类型</th>
			<th width="15%" class="center">开始时间</th>
			<th width="15%" class="center">结束时间</th>
			<th width="8%" class="center">发布状态</th>
			<th width="20%" class="center">操作</th>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${resultMap.activityList}" var="activity">
			<tr>
				<td class="center">${activity.title}</td>
				<td class="center">${activity.act_city}</td>
				<td class="center">${systemConst.categoryMap[fn:trim(activity.category_id)] }</td>
				<td class="center">${activity.adress}</td>
				<td class="center">${systemConst.actTypeMap[fn:trim(activity.act_type)] }</td>
				<td class="center">${activity.begintime}</td>
				<td class="center">${activity.endtime}</td>
				<td class="center">${systemConst.actstatusMap[fn:trim(activity.status)] }</td>
				<td class="center">
					<button>
						
					</button>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
	
</table>
${pageInfo.html}