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
					<jsp:param value='[{"name":"活动管理","href":"#"},{"name":"活动添加","href":"${ctx}/activity/addUI.do"}]'  name="navigationItems" />
				</jsp:include>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="addActivityInfoForm">
								<div class="page-header"> <h1> 发布活动信息 </h1> </div>
								<flagToken:token tokenName="addActivityInfoForm"/><!-- 防止表单重复提交 -->
								<div class="row">
									<div class="col-xs-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label for="title" class="col-xs-12 col-sm-3 control-label no-padding-right">活动标题</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="title" id="title" class="width-100" notnull="true" maxlength="100" >
									                    <i class="ace-icon fa fa-leaf"></i> 
								                    </span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>

											<div class="form-group">
												<label for="subTitle" class="col-xs-12 col-sm-3 control-label no-padding-right">活动副标题</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="subTitle" id="subTitle" class="width-100" maxlength="100" >
									                    <i class="ace-icon fa fa-leaf"></i>
								                    </span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>		
											
											<div class="form-group">
												<label for="actCity" class="col-sm-3 control-label no-padding-right">活动城市</label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-6">
															<select class="form-control" name="actCity" id="actCity">
									                            <!-- <option value="">---请选择---</option> -->
									                            <option value="上海">上海</option>
									                            <option value="北京">北京</option>
									                            <option value="广州">广州</option>
									                            <option value="深圳">深圳</option>
									                            <option value="成都">成都</option>
									                            <option value="武汉">武汉</option>
									                         </select>
														</div>
													</div>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>		
											
											<div class="form-group">
												<label for="adress" class="col-xs-12 col-sm-3 control-label no-padding-right">活动地址</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="adress" id="adress" notnull="true" class="width-100" maxlength="200" >
									                    <i class="ace-icon fa fa-leaf"></i> 
								                    </span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
												<label for="actNum" class="col-xs-12 col-sm-3 control-label no-padding-right">活动人数</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                    <input type="text" name="actNum" id="actNum" notnull="true" class="width-100" >
									                    <i class="ace-icon fa fa-leaf"></i> 
								                    </span>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
												<label for="sponsor" class="col-xs-12 col-sm-3 control-label no-padding-right">举办方</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                     <input type="text" name="sponsor" id="sponsor" class="width-100" notnull="true" maxlength="200" >
									                     <i class="ace-icon fa fa-leaf"></i> 
								                    </span> 
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"> </div>
											</div>
											
											<div class="form-group">
												<label for="categoryId" class="col-sm-3 control-label no-padding-right">活动范畴</label>
												<div class="col-md-3">
													<div class="row">
														<div class="col-sm-6">
															<select class="form-control" name="categoryId" id="categoryId">
									                            <!-- <option value="">---请选择---</option> -->
									                            <option value="0">创业</option>
									                            <option value="1">商务</option>
									                            <option value="2">玩乐</option>
									                            <option value="3">交友</option>
									                         </select>
														</div>
													</div>
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"></div>
											</div>
											
											<div class="form-group">
												<label for="beginTime" class="col-xs-12 col-sm-3 control-label no-padding-right">开始时间</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                     <input type="text" name="beginTime" id="beginTime" class="input-sm form-control input-daterange-startDate" notnull="true" maxlength="200" >
									                     <i class="ace-icon fa fa-leaf"></i> 
								                    </span> 
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"> </div>
											</div>
											
											<div class="form-group">
												<label for="endTime" class="col-xs-12 col-sm-3 control-label no-padding-right">结束时间</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                     <input type="text" name="endTime" id="endTime" class="input-sm form-control input-daterange-endDate" notnull="true" maxlength="200" >
									                     <i class="ace-icon fa fa-leaf"></i> 
								                    </span> 
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"> </div>
											</div>
											
											<div class="form-group">
							                    <label for="showImage" class="col-xs-12 col-sm-3 control-label no-padding-right">活动海报</label>
							                    <input type="hidden" id="flag" name="flag" value="0"/><!-- 1表示是修改界面  0表示是新增界面 -->
							                    <input type="hidden" id="showImage" name="showImage" value="" />
							                    <div class="col-xs-12 col-sm-9"> 
							                      <span class="block input-icon input-icon-right" id="pic_container">
						                      		  	<div class="avatar-title clearfix">
											    			<input type="file" class="btn-orange-s pull-lef" onchange="Activity.uploadActivityPic(this);" id="cropImg" name="cropImg"/>
											    		</div>
							                      </span> 
							                    </div>
							                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
							                </div>
							                
							                <div class="form-group">
												<label for="sponsor" class="col-xs-12 col-sm-3 control-label no-padding-right">活动需求</label>
												<div class="col-xs-12 col-sm-3">
													<span class="block input-icon input-icon-right">
									                     <textarea rows="4" cols="50" name="requirement" id="requirement" class="width-100" notnull="true" style="resize:none;" maxlength="300">
									                     </textarea> 
									                     <i class="ace-icon fa fa-leaf"></i> 
								                    </span> 
												</div>
												<div class="help-block col-xs-12 col-sm-reset inline"> </div>
											</div>
							                
							                <div class="clearfix form-actions">
												<div class="col-md-offset-3 col-md-9">
													<button class="btn btn-primary" type="button" onclick="Activity.saveActivity();">
														<i class="ace-icon fa fa-check bigger-110"></i>
														Submit
													</button>
													&nbsp; &nbsp; &nbsp;
													<button class="btn" type="reset">
														<i class="ace-icon fa fa-undo bigger-110"></i>
														Reset
													</button>
												</div>
											</div>
											
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>				
				</div>
			</div>		
		</div>
		<jsp:include page="/page/common/footer.jsp" flush="true"></jsp:include>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		DateUtil.initDatePicker();
	});
</script>
</html>