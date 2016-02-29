/**
 * 资讯管理
 */
var Information = {
	sys_conf_homePic:'0', // 首页轮播图类型
	sys_conf_homePic_0:'0',
	sys_conf_homePic_1:'1',
	sys_conf_homePic_2:'2', 
	sys_conf_homePic_3:'3',
	sys_conf_homePic_4:'4', // 首页轮播图第1，2，3，4，5张
	imageFormat : 'jpg,png,PNG,JPG'
};

/**
 * 初始化首页轮播图
 */
Information.initHomePic = function(){
	var params = {
		"type":Information.sys_conf_homePic
	};
	
	ajaxSearch(BASE_PATH + "/common/findSysConfigs.do", params, function(data) {
		// 在此处要加入初始化轮播图的代码，如果之前已经配置过，那么这次必然是更新，如果之前没有配置过，那么这次必然是新增
		var sysConfigList = data.sysConfigList;
		var listLength = sysConfigList.length;
		for(var i=0; i<listLength; i++){
			var sysConfig = sysConfigList[i];
			var key = sysConfig.key_val;
			var value = sysConfig.value_val;
			var url = sysConfig.url_val;
			
			$("#imageHiddenPath_"+key).val(value);
			$("#prin_url_"+key).val(value);
			$("#imagePath_"+key).attr("src", OSS_RES_URL + value);
			$("#url_"+key).val(url);
			
			switch (key) {
				case Information.sys_conf_homePic_0:{
					$("#button_"+key).attr("onclick",'Information.updateHomePicToOSS("0");');
					break;
				}
				case Information.sys_conf_homePic_1:{
					$("#button_"+key).attr("onclick",'Information.updateHomePicToOSS("1");');
					break;
				}	
				case Information.sys_conf_homePic_2:{
					$("#button_"+key).attr("onclick",'Information.updateHomePicToOSS("2");');
					break;
				}
				case Information.sys_conf_homePic_3:{
					$("#button_"+key).attr("onclick",'Information.updateHomePicToOSS("3");');
					break;
				}	
				case Information.sys_conf_homePic_4:{
					$("#button_"+key).attr("onclick",'Information.updateHomePicToOSS("4");');
					break;
				}	
				default:
					break;
			}
		}
		
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};

/**
 * 更新首页轮播图
 */
Information.updateHomePic = function(key){
	submitForm("addHomePicDialogForm_"+key, BASE_PATH + '/common/updateSysConfig.do', function(res){
		
		if(res && res.returnCode == 200){//将原图片路径存入到无用图片表中
			var url = $("#prin_url_"+key).val();
			var newUrl = $("#imageHiddenPath_"+key).val();
			
			if(url != newUrl){
				var params = { "oldPath": url };
				submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) { }, function(data) {
					Dialog.alertError(data.returnMsg);
				});
			}
			
		}
	},function(res){
		Dialog.alertErrorCodeMsg(res.returnCode);
	});
};

/**
 * 更新首页轮播图至OSS
 */
Information.updateHomePicToOSS = function(key){
	
	var realPath = $("#realPath_"+key).val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Information.updateHomePic(key);
	} else{// 否则先上传到oss，然后再提交表单
		submitForm("addHomePicDialogForm_"+key, BASE_PATH + '/upload/uploadImageToOSS.do', function(res){
			// 如果上传成功则去提交表单
			Information.updateHomePic(key);
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 添加首页轮播图
 */
Information.addHomePic = function(key){
	submitForm("addHomePicDialogForm_"+key, BASE_PATH + '/common/addSysConfig.do', function(res){
		
	},function(res){
		Dialog.alertErrorCodeMsg(res.returnCode);
	});
};

/**
 * 添加首页轮播图至OSS
 */
Information.addHomePicToOSS = function(key){
	
	var realPath = $("#realPath_"+key).val();
	if(isEmpty(realPath)){
		// 如果没有更新上传，则直接提交表单
		Information.addHomePic(key);
	} else{// 否则先上传到oss，然后再提交表单
		submitForm("addHomePicDialogForm_"+key, BASE_PATH + '/upload/uploadImageToOSS.do', function(res){
			// 如果上传成功则去提交表单
			Information.addHomePic(key);
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}
};

/**
 * 上传轮播图图片
 * currentObj:当前对象
 * key:轮播图的key值
 */
Information.uploadPic = function(currentObject, key){
	var fakepath = $(currentObject).val();
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	if((Information.imageFormat).indexOf(ext)!=-1){
		submitForm('addHomePicDialogForm_'+key, BASE_PATH + '/upload/uploadCropImg.do', function(res){
			
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			
			if(res && res.tmpPath){
				$("#imageHiddenPath_"+key).val(res.tmpPath.substring(res.tmpPath.lastIndexOf('images/')));
				$("#imagePath_"+key).attr("src",res.tmpPath);
				$("#realPath_"+key).val(res.realPath);
			}
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}else{
		Dialog.alertInfo('头像文件格式不正确,只能是如下后缀名：'+Information.imageFormat,true);
	}
};
