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
	<script type="text/javascript" src="${locResPath}/static/js/office/office.js?_v=${vs}"></script>
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
			<jsp:param value="150102" name="leftMenuSelectId"/>
		</jsp:include>
		
		<div class="main-content">
			<div class="main-content-inner">
				<jsp:include page="/page/common/breadcrumbs.jsp" flush="true">
					<jsp:param value='[{"name":"办公空间","href":"#"},{"name":"空间列表","href":"${ctx}/officeSpace/toList.do"},{"name":"空间场地列表","href":"${ctx}/office/toList.do?belongTo=${officeMap.officeInfo.belong_to}"},{"name":"编辑空间场地","href":"${ctx}/office/toEdit.do?id=${id}"}]' name="navigationItems" />
				</jsp:include>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form id="updateOfficeForm">
								<flagToken:token tokenName="updateOfficeForm"/>
								<input type="hidden" name="id" value="${id}"><!-- 标识符 -->
								<input type="hidden" name="belongTo" id="belongTo" value="${officeMap.officeInfo.belong_to}"><!-- 场地所属空间 -->
								<c:if test="${not empty officeMap.officeInfo.originalDiscriptionURL}">
									<input type="hidden" name="originalDiscriptionURL" value="${officeMap.officeInfo.originalDiscriptionURL}"><!-- 默认场地都是普通场地 -->
								</c:if>
								<div class="page-header"> <h1> 编辑空间场地信息 </h1> </div>
					            <div class="row">
					              <div class="col-xs-12">
					                <div class="form-horizontal">
					                
					                  <div class="form-group">
					                    <label for="name" class="col-xs-12 col-sm-2 control-label no-padding-right">空间名称</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="name" id="name" class="width-100" value="${officeMap.officeInfo.name}" notnull="true" maxlength="50" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                     </div>
					                     <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contactor" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contactor" id="contactor" class="width-100" value="${officeMap.officeInfo.contactor}" notnull="true" maxlength="15" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="contact" class="col-xs-12 col-sm-2 control-label no-padding-right">联系人方式</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="contact" id="contact" class="width-100" value="${officeMap.officeInfo.contact}" datatype="telOrPhone" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-sm-2 control-label no-padding-right">空间类型</label>
					                    <div class="col-md-3">
					                      <div class="row">
					                        <div class="col-sm-6">
					                          <select class="form-control" name="type">
					                            <c:forEach items="${systemConst.officeSpaceTypeMap}" var="officeSpaceType">
													<option value="${officeSpaceType.key}" ${officeSpaceType.key eq officeMap.officeInfo.type?'selected="selected"':''}>${officeSpaceType.value}</option>
												</c:forEach>
					                          </select>
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="cost" class="col-xs-12 col-sm-2 control-label no-padding-right">费用</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="cost" id="cost" class="width-100" value="${officeMap.officeInfo.cost}" datatype="money" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"></div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="cost" class="col-xs-12 col-sm-2 control-label no-padding-right">费用单位</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="unit" id="unit" class="width-100" value="${officeMap.officeInfo.unit}" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"></div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="adress" class="col-xs-12 col-sm-2 control-label no-padding-right">所在地区</label>
					                    <div class="col-xs-12 col-sm-6 infolist"> 
					                      <span class="block input-icon input-icon-right liststyle">
						                      	<span id="province">
				                                    <i>请选择省份</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择省份</a></li>
				                                    </ul>
				                                    <input type="hidden" name="province" class="curValue" value="${officeMap.officeInfo.province}">
				                                </span>
				                                <span id="city">
				                                    <i>请选择城市</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择城市</a></li>
				                                    </ul>
				                                    <input type="hidden" name="city" class="curValue" value="${officeMap.officeInfo.city}">
				                                </span>
				                                <span id="area">
				                                    <i>请选择地区</i>
				                                    <ul>
				                                        <li><a href="javascript:void(0)" alt="">请选择地区</a></li>
				                                    </ul>
				                                    <input type="hidden" name="area" class="curValue" value="${officeMap.officeInfo.area}">
				                                </span>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="adress" class="col-xs-12 col-sm-2 control-label no-padding-right">详细地址</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="address" id="address" class="width-100" value="${officeMap.officeInfo.address}" notnull="true" maxlength="100" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="traffic" class="col-xs-12 col-sm-2 control-label no-padding-right">交通概况</label>
					                    <div class="col-xs-12 col-sm-3"> 
					                      <span class="block input-icon input-icon-right">
						                      <input type="text" name="traffic" id="traffic" class="width-100" value="${officeMap.officeInfo.traffic}" maxlength="100" notnull="true" >
						                      <i class="ace-icon fa fa-leaf"></i> 
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label class="col-xs-12 col-sm-2 control-label no-padding-right">地铁</label>
					                    <div class="col-sm-9">
					                      <div class="row">
					                        <div class="checkbox" id="rail-way">
												<input type="hidden" name="railways" id="railways" value="${officeMap.officeInfo.railways}">
					                        </div>
					                      </div>
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="description" class="col-xs-12 col-sm-2 control-label no-padding-right">空间介绍</label>
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right">
						                      <textarea rows="4" class="form-control limited" name="description" id="description">${officeMap.officeInfo.description}</textarea>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="form-group">
					                    <label for="show_images" class="col-xs-12 col-sm-2 control-label no-padding-right">空间照片</label>
					                    <input type="hidden" id="show_images" name="showImages" value="">
					                    <input type="hidden" name="realPath" id="realPath" value=""><!-- 新文件的物理路径-->
					                    <input type="hidden" name="oldPath" id="oldPath" value=""><!-- 新文件的物理路径-->
					                    <div class="col-xs-12 col-sm-9"> 
					                      <span class="block input-icon input-icon-right" id="pic_container">
						                      	<div class="avatar-title clearfix">
											    	<input type="file" class="btn-orange-s pull-lef" onchange="Office.uploadOfficePic(this,'updateOfficeForm');" id="cropImg" name="cropImg">
											    </div>
											    <c:if test="${!empty officeMap.officeInfo.show_images}">
												    <c:forEach var="image_src" items="${fn:split(officeMap.officeInfo.show_images, ',')}">
												    	<div class="col-sm-3 clearfix" style="margin-top: 10px;margin-bottom: 10px;">
												    		<div class="avatar-x">
												    			<div id="addPic">
												    				<img class="space-img" src="${sysConfig.ossResUrl}${image_src}" width="200" height="200">
												    			</div>
												    			<div class="avatar-bar"></div>
												    		</div>
												    		<button style="margin-left: 157px;margin-top: 10px;" class="btn btn-xs btn-success" id="button_0" onclick="Office.deleteCurPic(this);" type="button"><span class="bigger-110">删除</span></button>
												    	</div>
												    </c:forEach>
												</c:if>
					                      </span> 
					                    </div>
					                    <div class="help-block col-xs-12 col-sm-reset inline"> </div>
					                  </div>
					                  
					                  <div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<a href="javascript:Office.updateOfficePicToOSS();" class="btn btn-primary btn-lg active" role="button">保存</a>
											<a href="${ctx}/office/toList.do?belongTo=${officeMap.officeInfo.belong_to}" class="btn btn-default btn-lg active" role="button">取消</a>
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
    $.fn.citySelect(['#province', '#city', '#area'],['${officeMap.officeInfo.province}' , '${officeMap.officeInfo.city}' , '${officeMap.officeInfo.area}']);
    Office.initRailways();
    Office.checkRailways();
});
</script>
</html>