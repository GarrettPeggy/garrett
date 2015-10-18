/**
 * 后台的活动管理
 */

var Activity={
		imageFormat : 'jpg,png,PNG,JPG'
};

/**
 * 上传活动海报图片
 */
Activity.uploadActivityPic = function(currentObject){
	var flag = $("#flag").val();//flag为0表示是新增页面的海报图片   1表示是修改页面的海报图片
	var fakepath = $(currentObject).val();
	$("#fakepath").val(fakepath);//把选择到的图片放到页面上进行判断更新活动时，是否选择了图片
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	
	var form = "addActivityInfoForm";
	
	if(flag == 1){
		form = "editActivityInfoForm";
	}
	
	if((Activity.imageFormat).indexOf(ext)!=-1){
		submitForm(form, BASE_PATH + '/upload/uploadCropImg.do', function(res){
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			$("#realPath").val(res.realPath);//把物理路径放到页面上
			var pic_src=res.tmpPath;
			var index=res.realPath.lastIndexOf("images");
			var imagePath=res.realPath.substring(index).replace(/\\/g,"/");
			$("#showImage").val(imagePath);
			if(res && pic_src){
				if($("#pic_div").length>0){
					$("#pic_div").find(".space-img").attr("src",pic_src);
				}else{
					$("#pic_container").append('<div class="col-sm-3 clearfix" id="pic_div" style="margin-top: 10px;margin-bottom: 10px;"><div class="avatar-x"><div id="addPic"><img class="space-img" src="'+pic_src+'" width="200" height="200"></div><div class="avatar-bar"></div></div></div>');
				}
			}
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}else{
		Dialog.alertInfo('海报图片文件格式不正确,只能是如下后缀名：'+Activity.imageFormat,true);
	}
};

/**
 * 上传文件到Oss
 */
Activity.uploadPicToOSS=function(){
	var flag = $("#flag").val();
	var realPath = $("#realPath").val();
	var params = {"realPath":realPath};
	if(null != realPath && ""!=realPath){
		submitSave(BASE_PATH + "/upload/uploadImageToOSS.do", params, function(data) {
			if(flag==0){
				Activity.saveActivity();//保存活动
			}else if(flag==1){
				Activity.updateActivity();//更新活动
			}
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	}else{
		if(flag==0){
			Activity.saveActivity();//保存活动
		}else if(flag==1){
			Activity.updateActivity();//更新活动
		}
	}
};

/**
 * 保存活动信息
 */
Activity.saveActivity=function(){
	var realPath = $("#realPath").val();
	var requirement = $("#requirement").val().trim();
	if(null == realPath || "" == realPath){
		Dialog.alertInfo("没有活动海报，不能发布活动哦！\\(^o^)/",false);
		return;
	}
	
	if(null == requirement || "" == requirement){
		Dialog.alertInfo("活动需求必填哦！\\(^o^)/",false);
		return;
	}
	
	if(Validator.validForm("addActivityInfoForm")){
		submitForm("addActivityInfoForm",BASE_PATH + '/activity/add.do',function(data){
				window.location.href = BASE_PATH + "/activity/toActivityList.do";
			},function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
	
};

/**
 * 修改活动信息
 */
Activity.updateActivity = function(){
	
	var realPath = $("#realPath").val();
	var oldPath = $("#oldPath").val();
	var requirement = $("#requirement").val().trim();
	if((null == realPath || "" == realPath) && (null == oldPath || "" == oldPath)){
		Dialog.alertInfo("没有活动海报，不能发布活动哦！\\(^o^)/",false);
		return;
	}
	
	if(null == requirement || "" == requirement){
		Dialog.alertInfo("活动需求必填哦！\\(^o^)/",false);
		return;
	}
	
	if(Validator.validForm("editActivityInfoForm")){
		var fakepath = $("#fakepath").val();
		var oldPath = $("#oldPath").val();
		if(null == fakepath || "" == fakepath){
			$("#showImage").val(oldPath);
		}
		submitForm("editActivityInfoForm",BASE_PATH + '/activity/update.do',function(data){
				Activity.deletePicFromOSS();//活动修改成功后就删除图片
			},
			function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 删除OSS上面的图片
 */
Activity.deletePicFromOSS=function(){
	var oldPath=$("#oldPath").val();
	var fakepath=$("#fakepath").val();
	var params = {"oldPath":oldPath};
	if(null != oldPath && ""!=oldPath){
		if(null != fakepath && ""!=fakepath){
			submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) {
				window.location.href = BASE_PATH + "/activity/toActivityList.do";//图片删除成功后就跳转页面
			}, function(data) {
				Dialog.alertError(data.returnMsg);
			});
		}else{
			window.location.href = BASE_PATH + "/activity/toActivityList.do";//没有更新路径，就不执行删除方法，直接跳转页面
		}
	}else{
		window.location.href = BASE_PATH + "/activity/toActivityList.do";//没有路径，就不执行删除方法，直接跳转页面
	}
	
};

/**
 * 更新活动类型
 */
Activity.updateActivityType=function(id,actType){
	var params = {
			"id":id,
			"actType":actType
		};
		// 记住参数提交的格式一定要正确，否则会报error错误。
		submitSave(BASE_PATH + "/activity/updateActType.do", params, function(data) {
			Activity.searchActivityList();
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
};

/**
 * 搜索活动列表
 */
Activity.searchActivityList=function(){
	submitSearch('activityListForm', 'load_content', BASE_PATH + '/activity/getActivityList.do',function() {
		pageInfo("activityListForm",Activity.searchActivityList);
	});
};


/**
 * 验证省市区是否为空
 */
Activity.validateAdress = function(){
	
	var isPass = true;
	
	var province = $("input[name='province']").val();
	var city = $("input[name='city']").val();
	var area = $("input[name='area']").val();
	
	if(isEmpty(province)){
		Dialog.alertInfo("请选择省份！");
		isPass = false;
	}
	
	if(isEmpty(city)){
		Dialog.alertInfo("请选择城市！");
		isPass = false;
	}
	
	if(isEmpty(area)){
		Dialog.alertInfo("请选择区域！");
		isPass = false;
	}
	
	return isPass;
};