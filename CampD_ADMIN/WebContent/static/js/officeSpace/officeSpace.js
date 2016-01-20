/**
 * 办公空间管理
 */
var OfficeSpace = {
	imageFormat : 'jpg,png,PNG,JPG'
};

/**
 * 搜索空间列表
 */
OfficeSpace.searchList=function(){
	submitSearch('officeSpaceListForm', 'load_content', BASE_PATH + '/officeSpace/list.do',function() {
		pageInfo("officeSpaceListForm",OfficeSpace.searchList);
	});
};
OfficeSpace.searchListByForm = function(){
	$("#curPage").val(1);
	OfficeSpace.searchList();
};

/**
 * 上传空间logo图片
 */
OfficeSpace.uploadLogoPic = function(currentObject){
	var flag = $("#flag").val();//flag为0表示是新增页面的海报图片   1表示是修改页面的海报图片
	var fakepath = $(currentObject).val();
	$("#fakepath").val(fakepath);//把选择到的图片放到页面上进行判断更新活动时，是否选择了图片
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	var form = flag == 1?"editOfficeSpaceForm":"addOfficeSpaceForm";
	
	if((OfficeSpace.imageFormat).indexOf(ext)!=-1){
		submitForm(form, BASE_PATH + '/upload/uploadCropImg.do', function(res){
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			$("#realPath").val(res.realPath);//把物理路径放到页面上
			var pic_src=res.tmpPath;
			var index=res.realPath.lastIndexOf("images");
			var imagePath=res.realPath.substring(index).replace(/\\/g,"/");
			$("#logo").val(imagePath);
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
		Dialog.alertInfo('海报图片文件格式不正确,只能是如下后缀名：'+OfficeSpace.imageFormat,true);
	}
};

/**
 * 上传文件到Oss
 */
OfficeSpace.uploadPicToOSS=function(){
	var flag = $("#flag").val();
	var realPath = $("#realPath").val();
	var params = {"realPath":realPath};
	if(null != realPath && ""!=realPath){
		submitSave(BASE_PATH + "/upload/uploadImageToOSS.do", params, function(data) {
			if(flag==0){
				OfficeSpace.save();//保存活动
			}else if(flag==1){
				OfficeSpace.update();//更新活动
			}
		}, function(data) {
			Dialog.alertError(data.returnMsg);
		});
	}else{
		if(flag==0){
			OfficeSpace.save();//保存活动
		}else if(flag==1){
			OfficeSpace.update();//更新活动
		}
	}
};

/**
 * 保存空间信息
 */
OfficeSpace.save=function(){
	var realPath = $("#realPath").val();
	if(null == realPath || "" == realPath){
		Dialog.alertInfo("没有空间logo，不能发布哦！\\(^o^)/",false);
		return;
	}
	
	if(Validator.validForm("addOfficeSpaceForm")){
		OfficeSpace.getAreaInfo();
		submitForm("addOfficeSpaceForm",BASE_PATH + '/officeSpace/add.do',function(data){
				window.location.href = BASE_PATH + "/officeSpace/toList.do";
			},function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
	
};

/**
 * 修改空间信息
 */
OfficeSpace.update = function(){
	
	var realPath = $("#realPath").val();
	var oldPath = $("#oldPath").val();
	if((null == realPath || "" == realPath) && (null == oldPath || "" == oldPath)){
		Dialog.alertInfo("没有活动海报，不能发布活动哦！\\(^o^)/",false);
		return;
	}
	
	if(Validator.validForm("editOfficeSpaceForm")){
		var fakepath = $("#fakepath").val();
		var oldPath = $("#oldPath").val();
		if(null == fakepath || "" == fakepath){
			$("#logo").val(oldPath);
		}
		OfficeSpace.getAreaInfo();
		submitForm("editOfficeSpaceForm",BASE_PATH + '/officeSpace/update.do',function(data){
			OfficeSpace.deletePicFromOSS();//活动修改成功后就删除图片
			},
			function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 活动类型和基础设施更新
 */
OfficeSpace.getAreaInfo = function(){
	var $checkedAreaArray = $('input[name="area-span"]:checked');
	var area = "";
	for (var i = 0; i < $checkedAreaArray.length; i++) {
		area += i==0?$($checkedAreaArray[i]).val():","+$($checkedAreaArray[i]).val();
	}
	$("#area").val(area);
};

/**
 * 删除OSS上面的图片
 */
OfficeSpace.deletePicFromOSS=function(){
	var oldPath=$("#oldPath").val();
	var fakepath=$("#fakepath").val();
	var params = {"oldPath":oldPath};
	if(null != oldPath && ""!=oldPath){
		if(null != fakepath && ""!=fakepath){
			submitSave(BASE_PATH + "/upload/deleteImageToOSS.do", params, function(data) {
				window.location.href = BASE_PATH + "/officeSpace/toList.do";//图片删除成功后就跳转页面
			}, function(data) {
				Dialog.alertError(data.returnMsg);
			});
		}else{
			window.location.href = BASE_PATH + "/officeSpace/toList.do";//没有更新路径，就不执行删除方法，直接跳转页面
		}
	}else{
		window.location.href = BASE_PATH + "/officeSpace/toList.do";//没有路径，就不执行删除方法，直接跳转页面
	}
	
};

/**
 * 更新空间类型
 */
OfficeSpace.updateStatus=function(id,status){
	var params = {
		"id":id,
		"status":status
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/officeSpace/updateStatus.do", params, function(data) {
		OfficeSpace.searchList();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};

OfficeSpace.toAddOfficeSpace = function(){
	window.location.href = BASE_PATH + "/officeSpace/toAdd.do";
};