/**
 * 资讯管理
 */
var Gift = {
	imageFormat : 'jpg,png,PNG,JPG'
};

/**
 * 上传礼品海报图片
 */
Gift.uploadPic = function(currentObject){
	var flag = $("#flag").val();//flag为0表示是新增页面的海报图片   1表示是修改页面的海报图片
	var fakepath = $(currentObject).val();
	$("#fakepath").val(fakepath);//把选择到的图片放到页面上进行判断更新活动时，是否选择了图片
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	
	var form = "addGiftForm";
	
	if(flag == 1){
		form = "editGiftForm";
	}
	
	if((Gift.imageFormat).indexOf(ext)!=-1){
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
		Dialog.alertInfo('海报图片文件格式不正确,只能是如下后缀名：'+Gift.imageFormat,true);
	}
};

/**
 * 上传文件到Oss
 */
Gift.uploadPicToOSS=function(){
	var flag = $("#flag").val();
	var realPath = $("#realPath").val();
	var params = {"realPath":realPath};
	if(null != realPath && ""!=realPath){
		submitSave(BASE_PATH + "/upload/uploadImageToOSS.do", params, function(data) {
			if(flag==0){
				Gift.save();//保存礼品
			}else if(flag==1){
				Gift.update();//更新礼品
			}
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	}else{
		if(flag==0){
			Gift.save();//保存礼品
		}else if(flag==1){
			Gift.update();//更新礼品
		}
	}
};

/**
 * 保存活动信息
 */
Gift.save=function(){
	var realPath = $("#realPath").val();
	if(null == realPath || "" == realPath){
		Dialog.alertInfo("没有礼品海报，不能发布活动哦！\\(^o^)/",false);
		return;
	}

	var description = $("#description").val().trim();
	if(null == description || "" == description){
		Dialog.alertInfo("礼品描述必填哦！\\(^o^)/",false);
		return;
	}
	
	Gift.getWorkforInfo();
	Gift.getWorkforCity();
	if(Validator.validForm("addGiftForm")){
		submitForm("addGiftForm",BASE_PATH + '/gift/add.do',function(data){
				window.location.href = BASE_PATH + "/gift/toList.do";
			},function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
	
};

/**
 * 修改礼品信息
 */
Gift.update = function(){
	
	var realPath = $("#realPath").val();
	var oldPath = $("#oldPath").val();
	if((null == realPath || "" == realPath) && (null == oldPath || "" == oldPath)){
		Dialog.alertInfo("没有礼品海报，不能发布活动哦！\\(^o^)/",false);
		return;
	}

	var description = $("#description").val().trim();
	if(null == description || "" == description){
		Dialog.alertInfo("礼品描述必填哦！\\(^o^)/",false);
		return;
	}
	
	Gift.getWorkforInfo();
	Gift.getWorkforCity();
	if(Validator.validForm("editGiftForm")){
		var fakepath = $("#fakepath").val();
		var oldPath = $("#oldPath").val();
		if(null == fakepath || "" == fakepath){
			$("#showImage").val(oldPath);
		}
		submitForm("editGiftForm",BASE_PATH + '/gift/update.do',function(data){
				Gift.deletePicFromOSS();//活动修改成功后就删除图片
			},
			function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 活动类型更新
 */
Gift.getWorkforInfo = function(){
	var $checkedWorkforArray = $('input[name="work-for"]:checked');
	var workFor = "";
	for (var i = 0; i < $checkedWorkforArray.length; i++) {
		workFor += i==0?$($checkedWorkforArray[i]).val():","+$($checkedWorkforArray[i]).val();
	}
	$("#workFor").val(workFor);
};

/**
 * 适用活动城市更新
 */
Gift.getWorkforCity = function(){
	var $checkedWorkforArray = $('input[name="work-for-city"]:checked');
	var workForCity = "";
	for (var i = 0; i < $checkedWorkforArray.length; i++) {
		workForCity += i==0?$($checkedWorkforArray[i]).val():","+$($checkedWorkforArray[i]).val();
	}
	$("#workForCity").val(workForCity);
};

/**
 * 删除OSS上面的图片
 */
Gift.deletePicFromOSS=function(){
	var oldPath=$("#oldPath").val();
	var fakepath=$("#fakepath").val();
	var params = {"oldPath":oldPath};
	if(null != oldPath && ""!=oldPath){
		if(null != fakepath && ""!=fakepath){
			submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) {
				window.location.href = BASE_PATH + "/gift/toList.do";//图片删除成功后就跳转页面
			}, function(data) {
				Dialog.alertError(data.returnMsg);
			});
		}else{
			window.location.href = BASE_PATH + "/gift/toList.do";//没有更新路径，就不执行删除方法，直接跳转页面
		}
	}else{
		window.location.href = BASE_PATH + "/gift/toList.do";//没有路径，就不执行删除方法，直接跳转页面
	}
	
};

/**
 * 更新礼品级别
 */
Gift.updateLevel=function(id,level){
	var params = {
		"id":id,
		"level":level
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/gift/updateLevel.do", params, function(data) {
		Gift.searchList();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};

/**
 * 检查礼品适用活动
 */
Gift.checkWorkforInfo = function(event, currentObj){
	var $currentObj = $(currentObj).find('input[name="work-for"]');
	var isChecked = $currentObj.get(0).checked;
	if(!isChecked){
		var $checkedWorkforArray = $('input[name="work-for"]:checked');
		var checkedLength = $checkedWorkforArray.length;
		if(checkedLength>=3){
			stopDefault(event);
			Dialog.alertInfo("使用活动最多只能选3个！");
		}
	}
};

/**
 * 搜索礼品列表
 */
Gift.searchList=function(){
	submitSearch('giftListForm', 'load_content', BASE_PATH + '/gift/list.do',function() {
		pageInfo("giftListForm",Gift.searchList);
	});
};

Gift.searchListByForm = function(){
	$("#curPage").val(1);
	Gift.searchList();
};