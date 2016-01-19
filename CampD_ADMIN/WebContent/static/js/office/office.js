/**
 * 办公空间管理
 */
var Office = {
	imageFormat : 'jpg,png,PNG,JPG'
};

/**
 * 搜索空间列表
 */
Office.searchList=function(){
	submitSearch('officeListForm', 'load_content', BASE_PATH + '/office/list.do',function() {
		pageInfo("officeListForm",Office.searchList);
	});
};
Office.searchListByForm = function(){
	$("#curPage").val(1);
	Office.searchList();
};

/**
 * 更新空间类型
 */
Office.updateStatus=function(id,status){
	var params = {
		"id":id,
		"status":status
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/office/updateStatus.do", params, function(data) {
		Office.searchList();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};

/**
 * 上传场地展示图片
 */
Office.uploadOfficePic = function(currentObject, uploadForm){
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
		Dialog.alertInfo('头像文件格式不正确,只能是如下后缀名：'+Office.imageFormat,true);
	}
};

/**
 * 删掉当前图片，并且更新页面oldPath属性
 */
Office.deleteCurPic = function(currentObject){
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
 * 添加场地图片至OSS
 */
Office.addOfficePicToOSS = function(){
	
	var realPath = $("#realPath").val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Office.addOffice();
	} else if(Validator.validForm("addOfficeForm")){// 否则先上传到oss，然后再提交表单
		var params = {
			"realPath":realPath
		};
		submitSave(BASE_PATH + '/upload/uploadImageToOSS.do', params, function(res){
			// 如果上传成功则去提交表单
			Office.addOffice();
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 添加场地信息
 */
Office.addOffice = function(){
	if(Validator.validForm("addOfficeForm")){
		
		var show_images = Space.getSpaceImages();
		$("#show_images").val(show_images);
		// 必须上传场地图片
		if(isEmpty(show_images)){
			Dialog.alertInfo("请至少上传一张场地照片",false);
			return;
		}
		
		submitForm("addOfficeForm",BASE_PATH + '/office/add.do', function(data){
				window.location.href = BASE_PATH + "/office/toList.do?belongTo="+$("#belongTo").val();
			}, function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 更新场地图片至OSS
 */
Office.updateOfficePicToOSS = function(){
	
	var realPath = $("#realPath").val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Office.updateOffice();
	} else if(Validator.validForm("updateOfficeForm")){// 否则先上传到oss，然后再提交表单
		var params = {
			"realPath":realPath
		};
		submitSave(BASE_PATH + '/upload/uploadImageToOSS.do', params, function(res){
			// 如果上传成功则去提交表单
			Office.updateOffice();
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 更新场地信息
 */
Office.updateOffice = function(){
	if(Validator.validForm("updateOfficeForm")){
		
		var show_images = Space.getSpaceImages();
		$("#show_images").val(show_images);
		// 必须上传场地图片
		if(isEmpty(show_images)){
			Dialog.alertInfo("请至少上传一张场地照片",false);
			return;
		}
		
		submitForm("updateOfficeForm",BASE_PATH + '/office/update.do', function(data){
				var oldPath = $("#oldPath").val();
				if(isEmpty(oldPath)){
					window.location.href = BASE_PATH + "/office/toList.do?belongTo="+$("#belongTo").val();
				}else{
					var params = { "oldPath": oldPath };
					submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) { 
						window.location.href = BASE_PATH + "/office/toList.do?belongTo="+$("#belongTo").val();
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
 * 获取场地图片的链接集合
 */
Office.getSpaceImages = function(){
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
