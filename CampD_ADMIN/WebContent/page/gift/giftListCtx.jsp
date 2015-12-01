<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<table id="sample-table-1" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th width="8%" class="center">礼品名称</th>
			<th width="10%" class="center">公司名称</th>
			<th width="8%" class="center">公司地区</th>
			<th width="8%" class="center">公司地址</th>
			<th width="8%" class="center">主营业务</th>
			<th width="8%" class="center">联系人</th>
			<th width="8%" class="center">联系方式</th>
			<th width="8%" class="center">礼品形态</th>
			<th width="10%" class="center">活动类型要求</th>
			<th width="8%" class="center">活动城市要求</th>
			<th width="16%" class="center">操作</th>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${giftListMap.giftList}" var="gift">
			<tr>
				<td class="center">${gift.name}</td>
				<td class="center">${gift.provider_name}</td>
				<td class="center">${gift.provider_province}&nbsp;&nbsp;${gift.provider_city}&nbsp;&nbsp;${gift.provider_area}</td>
				<td class="center">${gift.provider_adress}</td>
				<td class="center">${systemConst.mainBusinessMap[gift.main_business]}</td>
				<td class="center">${gift.contactor}</td>
				<td class="center">${gift.contact}</td>
				<td class="center">${systemConst.formMap[fn:trim(gift.form)]}</td>
				<td class="center">
					<c:if test="${not empty gift.work_for}">
	                	<c:forEach var="category" items="${ fn:split(gift.work_for, ',') }">
	                		${systemConst.categoryMap[category]}&nbsp;&nbsp;
	                    </c:forEach>
                    </c:if>
				</td>
				<td class="center">${gift.work_for_province}&nbsp;&nbsp;${gift.work_for_city}&nbsp;&nbsp;${gift.work_for_area}</td>
				<td class="center">
					<a href="${ctx}/gift/toEdit.do?id=${gift.id}">修改</a>&nbsp;
					<a href="${ctx}/gift/toView.do?id=${gift.id}">查看</a>&nbsp;
					<c:if test="${systemConst.GIFT_LEVEL_0 eq gift.level}">
						<a href="javascript:Gift.updateLevel('${gift.id}','${systemConst.GIFT_LEVEL_1}')">标记精美</a>
					</c:if>
					<c:if test="${systemConst.GIFT_LEVEL_1 eq gift.level}">
						<a href="javascript:Gift.updateLevel('${gift.id}','${systemConst.GIFT_LEVEL_0}')">取消精美</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		${pageInfo.noDataHtml}
	</tbody>
</table>
${pageInfo.html}
