<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ZH">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="/page/common/meta.jsp"%>
	<%@ include file="/page/common/jsCss.jsp"%>
	
	<!-- 编辑器初始化 -->
	<link rel="stylesheet" href="${ctx}/static/js/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${ctx}/static/js/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${ctx}/static/js/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${ctx}/static/js/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${ctx}/static/js/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var descriptionEditor = K.create('#description', {
				width : '700px',
				height: '200px',
				cssPath : '${ctx}/static/js/kindeditor/plugins/code/prettify.css',
				uploadJson : '${ctx}/page/kindeditor/file_upload_json.jsp',
				fileManagerJson : '${ctx}/page/kindeditor/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					// 编辑器创建完成之后执行的回调
				},
				allowImageUpload: function() {
					// 图片上传成功之后调用此回调方法
				},
				afterChange:function() {// 保证每次图片都是最新的
					this.sync();// 将编辑器内容同步到textarea
					var content = $('#description').val();
					$('#description').text(content.replace(/\/campD_admin/g, '${sysConfig.ossResUrl}'));
					$('#description').val(content.replace(/\/campD_admin/g, '${sysConfig.ossResUrl}'));
				}
			});
			prettyPrint();
		});
	</script>
	
	<link rel="stylesheet" href="${locResPath}/static/js/citySelect/city.css?_v=${vs}" />
	<script type="text/javascript" src="${locResPath}/static/js/gift/gift.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/city.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="${locResPath}/static/js/citySelect/jquery.cityselect.js?_v=${vs}"></script>
</head>

<body class="no-skin">
	<jsp:include page="/page/common/navbar.jsp" flush="true"></jsp:include>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>

		<jsp:include page="/page/common/sidebar.jsp" flush="true">
			<jsp:param value="140101" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"礼品管理","href":"#"},{"name":"添加礼品","href":"${ctx}/gift/toAdd.do"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="addGiftForm">
								<input type="hidden" name="level" value="${systemConst.GIFT_LEVEL_0 }"><!-- 默认礼品都是普通礼品-->
								<flagToken:token tokenName="addGiftForm"/>
								<div class="page-header"> <h1> 发布礼品信息 </h1> </div>
					            <div class="row">
					              <div class="col-xs-12">
					                <div class="form-horizontal">
					                
					                  <div class="form-group">
					                    <label for="name" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="name" id="name" class="width-100" notnull="true" maxlength="50" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="providerName" class="col-xs-12 col-sm-2 control-label no-padding-right">公司名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="providerName" id="providerName" class="width-100" notnull="true" maxlength="50" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-xs-12 col-sm-2 control-label no-padding-right">公司所在地区</label>
					                    <div class="col-xs-12 col-sm-6 infolist"> 
					                      <span class="block input-icon input-icon-right liststyle">
						                      	<span id="providerProvince">
				                                    <i>请选择省份</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
				                                    </ul>
				                                    <input type="hidden" name="providerProvince" class="curValue" value="">
				                                </span>
				                                <span id="providerCity">
				                                    <i>请选择城市</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
				                                    </ul>
				                                    <input type="hidden" name="providerCity" class="curValue" value="">
				                                </span>
				                                <span id="providerArea">
				                                    <i>请选择地区</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
				                                    </ul>
				                                    <input type="hidden" name="providerArea" class="curValue" value="">
				                                </span>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="providerAdress" class="col-xs-12 col-sm-2 control-label no-padding-right">公司地址</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="providerAdress" id="providerAdress" class="width-100" notnull="true" maxlength="100" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">主营业务</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="mainBusiness">
					                            <c:forEach items="${systemConst.mainBusinessMap}" var="mainBusiness">
													<option value="${mainBusiness.key}">${mainBusiness.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contactor" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contactor" id="contactor" class="width-100" notnull="true" maxlength="15" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contact" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人方式</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contact" id="contact" class="width-100" datatype="telOrPhone" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">礼品形态</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="form">
					                            <c:forEach items="${systemConst.formMap}" var="form">
													<option value="${form.key}">${form.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">适合活动类型</label>
					                    <div class="col-sm-9">
					                      <div class="row">
					                        <div class="checkbox">
					                            <c:forEach items="${systemConst.categoryMap}" var="category">
					                            	<label onclick="Gift.checkWorkforInfo(event,this);" style="padding-bottom: 10px;">
														<input name="work-for" class="ace ace-checkbox-${category.key}" value="${category.key}" type="checkbox">
														<span class="lbl">${category.value}</span>
													</label>
												</c:forEach>
												<input type="hidden" name="workFor" id="workFor" value="">
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">活动适用地区</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="workForCity">
					                            <option value="上海市">上海市</option>
					                            <option value="北京市">北京市</option>
					                            <option value="广州市">广州市</option>
					                            <option value="深圳市">深圳市</option>
					                            <option value="杭州市">杭州市</option>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="showImage" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品图片</label>
					                    <input type="hidden" id="flag" name="flag" value="0"/><!-- 1表示是修改界面  0表示是新增界面 -->
					                    <input type="hidden" id="showImage" name="showImage" value="" />
					                    <input type="hidden" id="realPath" name="realPath"/>
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right" id="pic_container">
				                      		  	<div class="avatar-title clearfix">
									    			<input type="file" class="btn-orange-s pull-lef" onchange="Gift.uploadPic(this);" id="cropImg" name="cropImg"/>
									    		</div>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
						              </div>
					                  
					                  <div class="form-group">
					                    <label for="description" class="col-xs-12 col-sm-2 control-label no-padding-right">礼品介绍</label>
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right">
						                      <textarea rows="4" class="form-control limited" name="description" id="description"></textarea>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-primary" onclick="Gift.uploadPicToOSS();" type="button">
												<i class="ace-icon fa fa-check bigger-110"></i> 发布
											</button>
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i> 重置
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
    //$.fn.citySelect(['#workForProvince', '#workForCity', '#workForArea'],['北京市' , '北京市' , '东城区']);
    $.fn.citySelect(['#providerProvince', '#providerCity', '#providerArea'],['北京市' , '北京市' , '东城区']);
});
</script>
</html>