/**
 * 资讯管理
 */
var Space = {
	imageFormat : 'jpg,png,PNG,JPG'
};

Space.searchSpaceList = function(){
	submitSearch('spaceListForm', 'load_content', BASE_PATH + '/space/list.do',function() {
		pageInfo("spaceListForm",Space.searchSpaceList);
	});
};

/**
 * 上传场地展示图片
 */
Space.uploadSpacePic = function(currentObject, uploadForm){
	var fakepath = $(currentObject).val();
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	if((Space.imageFormat).indexOf(ext)!=-1){
		submitForm(uploadForm, BASE_PATH + '/upload/uploadCropImg.do', function(res){
			
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			
			if(res && res.tmpPath){
				$("#pic_container").append('<div class="col-sm-3 clearfix" style="margin-top: 10px;margin-bottom: 10px;"><div class="avatar-x"><div id="addPic"><img class="space-img" src="'+res.tmpPath+'" width="200" height="200"></div><div class="avatar-bar"></div></div><button style="margin-left: 157px;margin-top: 10px;" class="btn btn-xs btn-success" id="button_0" onclick="Space.deleteCurPic(this);" type="button"><span class="bigger-110">删除</span></button></div>');
				var realPath = $("#realPath").val();
				if(realPath.length==0){
					$("#realPath").val(res.realPath);
				} else{
					$("#realPath").val(realPath+","+res.realPath);
				}
			}
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}else{
		Dialog.alertInfo('头像文件格式不正确,只能是如下后缀名：'+Space.imageFormat,true);
	}
};

/**
 * 删掉当前图片，并且更新页面oldPath属性
 */
Space.deleteCurPic = function(currentObject){
	var $parent = $(currentObject).parent();
	var $img = $parent.find('.space-img');
	var imgSrc = $img.attr("src");
	if(imgSrc.indexOf(OSS_RES_URL)>-1){
		var oldPath = $("#oldPath").val();
		if(oldPath.length==0){
			$("#oldPath").val(imgSrc.substring(imgSrc.lastIndexOf('images/')));
		} else{
			$("#oldPath").val(oldPath+","+imgSrc.substring(imgSrc.lastIndexOf('images/')));
		}
	}
	$parent.remove();
};

/**
 * 控制场地图片个数
 */
Space.controlPicNum = function(event,currentObject){
	if($(".space-img").length>=5){
		stopDefault(event||window.event);
		Dialog.alertInfo('展示图片不能超过5个！');
	}
};

/**
 * 添加场地图片至OSS
 */
Space.addSpacePicToOSS = function(){
	
	var realPath = $("#realPath").val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Space.addSpace();
	} else if(Validator.validForm("addSpaceInfoForm")){// 否则先上传到oss，然后再提交表单
		var params = {
			"realPath":realPath
		};
		submitSave(BASE_PATH + '/upload/uploadImageToOSS.do', params, function(res){
			// 如果上传成功则去提交表单
			Space.addSpace();
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 添加场地信息
 */
Space.addSpace = function(){
	if(Validator.validForm("addSpaceInfoForm")){
		
		var show_images = Space.getSpaceImages();
		$("#show_images").val(show_images);
		// 必须上传场地图片
		if(isEmpty(show_images)){
			Dialog.alertInfo("请至少上传一张场地照片",false);
			return;
		}
		
		submitForm("addSpaceInfoForm",BASE_PATH + '/space/add.do', function(data){
				window.location.href = BASE_PATH + "/space/toList.do";
			}, function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 更新场地图片至OSS
 */
Space.updateSpacePicToOSS = function(){
	
	var realPath = $("#realPath").val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Space.updateSpace();
	} else if(Validator.validForm("updateSpaceInfoForm")){// 否则先上传到oss，然后再提交表单
		var params = {
			"realPath":realPath
		};
		submitSave(BASE_PATH + '/upload/uploadImageToOSS.do', params, function(res){
			// 如果上传成功则去提交表单
			Space.updateSpace();
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 更新场地信息
 */
Space.updateSpace = function(){
	if(Validator.validForm("updateSpaceInfoForm")){
		
		var show_images = Space.getSpaceImages();
		$("#show_images").val(show_images);
		// 必须上传场地图片
		if(isEmpty(show_images)){
			Dialog.alertInfo("请至少上传一张场地照片",false);
			return;
		}
		
		submitForm("updateSpaceInfoForm",BASE_PATH + '/space/update.do', function(data){
				var oldPath = $("#oldPath").val();
				if(isEmpty(oldPath)){
					window.location.href = BASE_PATH + "/space/toList.do";
				}else{
					var params = { "oldPath": oldPath };
					submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) { 
						window.location.href = BASE_PATH + "/space/toList.do";
					}, function(data) {
						Dialog.alertError(data.returnMsg);
					});
				}
			}, function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 验证省市区是否为空
 */
Space.validateAdress = function(){
	
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

/**
 * 获取场地图片的链接集合
 */
Space.getSpaceImages = function(){
	var $space_imgs = $(".space-img");
	var $spaceImg = null, show_images = "";
	for (var i = 0; i < $space_imgs.length; i++) {
		$spaceImg = $($space_imgs[i]);
		var imgSrc = $spaceImg.attr("src");
		if(i==0){
			show_images += imgSrc.substring(imgSrc.lastIndexOf('images/'));
		} else {
			show_images += "," + imgSrc.substring(imgSrc.lastIndexOf('images/'));
		}
	}
	
	return show_images;
};

/**
 * 设置用户角色
 */
Space.updateSpaceLevel = function(id, spaceLevel){
	var params = {
		"id":id,
		"spaceLevel":spaceLevel
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/space/updateLevel.do", params, function(data) {
		Space.searchSpaceList();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};