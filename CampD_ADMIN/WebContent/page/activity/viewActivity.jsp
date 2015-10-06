<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<link rel="stylesheet" href="${rmtResPath}/static/css/bootstrap-datetimepicker.min.css" />
	<%@ include file="/page/common/jsCss.jsp"%>
	<script src="${locResPath}/static/js/date-time/moment.min.js"></script>
	<script src="${locResPath}/static/js/date-time/locale/zh-cn.js"></script>
	<script src="${locResPath}/static/js/date-time/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="${locResPath}/static/js/activity/activity.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		
		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="110101" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"活动管理","href":"#"},{"name":"活动列表","href":"#"},{"name":"活动查看","href":"${ctx}/activity/toViewActivity.do?id=${id }"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="page-header"> <h1> 查看活动信息 </h1> </div>
							<div class="row">
								<div class="col-xs-12">
									<div class="form-horizontal">
									
										<div class="form-group">
											<label for="title" class="col-xs-12 col-sm-3 control-label no-padding-right">活动标题</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="title" id="title" class="width-100" value="${activityMap.activityInfo.title }" disabled="disabled" notnull="true" maxlength="100" >
							                    </span>
											</div>
										</div>
										
										<div class="form-group">
											<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">活动副标题</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="subTitle" id="subTitle" value="${activityMap.activityInfo.sub_title }" disabled="disabled" class="width-100" maxlength="100" >
							                    </span>
											</div>
										</div>
										
										<div class="form-group">
											<label for="actCity" class="col-sm-3 control-label no-padding-right">活动城市</label>
											<div class="col-md-3">
												<div class="row">
													<div class="col-sm-6">
														<select class="form-control" name="actCity" id="actCity" disabled="disabled">
								                            <c:if test="${activityMap.activityInfo.act_city=='上海' }"><option value="上海" selected="selected">上海</option></c:if>
															<c:if test="${activityMap.activityInfo.act_city=='北京' }"><option value="北京" selected="selected">北京</option></c:if>
								                            <c:if test="${activityMap.activityInfo.act_city=='广州' }"><option value="广州" selected="selected">广州</option></c:if>
								                            <c:if test="${activityMap.activityInfo.act_city=='深圳' }"><option value="深圳" selected="selected">深圳</option></c:if>
								                            <c:if test="${activityMap.activityInfo.act_city=='成都' }"><option value="成都" selected="selected">成都</option></c:if>
								                            <c:if test="${activityMap.activityInfo.act_city=='武汉' }"><option value="武汉" selected="selected">武汉</option></c:if>
								                         </select>
													</div>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<label for="adress" class="col-xs-12 col-sm-3 control-label no-padding-right">活动地址</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="adress" id="adress" value="${activityMap.activityInfo.adress }" disabled="disabled" notnull="true" class="width-100" maxlength="200" />
							                    </span>
											</div>
										</div>
										
										<div class="form-group">
											<label for="actNum" class="col-xs-12 col-sm-3 control-label no-padding-right">活动人数</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                    <input type="text" name="actNum" id="actNum" value="${activityMap.activityInfo.act_num }" disabled="disabled" notnull="true" class="width-100" />
							                    </span>
											</div>
										</div>
										
										<div class="form-group">
											<label for="sponsor" class="col-xs-12 col-sm-3 control-label no-padding-right">举办方</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                     <input type="text" name="sponsor" id="sponsor" value="${activityMap.activityInfo.sponsor }" disabled="disabled" class="width-100" notnull="true" maxlength="200" />
							                    </span> 
											</div>
										</div>
										
										<div class="form-group">
											<label for="categoryId" class="col-sm-3 control-label no-padding-right">活动范畴</label>
											<div class="col-md-3">
												<div class="row">
													<div class="col-sm-6">
														<select class="form-control" name="categoryId" id="categoryId" disabled="disabled">
															<c:forEach items="${systemConst.categoryMap}" var="category">
																<option value="${category.key}" selected="${category.key == activityMap.activityInfo.category_id?'selected':''}">${category.value}</option>
															</c:forEach>
								                         </select>
													</div>
												</div>
											</div>
										</div>
										
										<div class="form-group">
											<label for="beginTime" class="col-xs-12 col-sm-3 control-label no-padding-right">开始时间</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                     <input type="text" name="beginTime" id="beginTime" value="${activityMap.activityInfo.begintime }" disabled="disabled" class="input-sm form-control input-daterange-startDate" notnull="true" maxlength="200" >
							                    </span> 
											</div>
										</div>
										
										<div class="form-group">
											<label for="endTime" class="col-xs-12 col-sm-3 control-label no-padding-right">结束时间</label>
											<div class="col-xs-12 col-sm-3">
												<span class="block input-icon input-icon-right">
								                     <input type="text" name="endTime" id="endTime" value="${activityMap.activityInfo.endtime }" disabled="disabled" class="input-sm form-control input-daterange-endDate" notnull="true" maxlength="200" >
							                    </span> 
											</div>
										</div>
										
										<div class="form-group">
						                    <label for="showImage" class="col-xs-12 col-sm-3 control-label no-padding-right">活动海报</label>
						                    <div class="col-xs-12 col-sm-9"> 
						                      <span class="block input-icon input-icon-right" id="pic_container">
					                      		  	<div class="col-sm-3 clearfix" id="pic_div" style="margin-top: 10px;margin-bottom: 10px;">
											    		<div class="avatar-x">
											    			<div id="addPic">
											    				<img class="space-img" src="${sysConfig.ossResUrl}${activityMap.activityInfo.show_image}" width="200" height="200" />
											    			</div>
											    			<div class="avatar-bar"></div>
											    		</div>
											    	</div>
						                      </span> 
						                    </div>
						                </div>
						                
						                <div class="form-group">
											<label for="sponsor" class="col-xs-12 col-sm-3 control-label no-padding-right">活动需求</label>
								            <textarea rows="4" cols="50" name="requirement" id="requirement" notnull="true" style="width:500px; resize:none;" maxlength="300" disabled="disabled">${activityMap.activityInfo.requirement }</textarea> 
										</div>
										
										<div class="clearfix form-actions">
											<div class="col-md-offset-3 col-md-9">
												<a href="${ctx}/activity/toActivityList.do" class="btn btn-default btn-lg active" role="button">返回列表</a>
											</div>
									   </div>
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/page/common/footer.jsp" flush="true"></jsp:include>
	</div>
</body>
</html>