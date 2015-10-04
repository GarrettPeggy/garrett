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
	var fakepath = $(currentObject).val();
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	if((Activity.imageFormat).indexOf(ext)!=-1){
		submitForm('addActivityInfoForm', BASE_PATH + '/upload/uploadCropImg.do', function(res){
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			var pic_src=res.tmpPath;
			$("#showImage").val(pic_src);
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
 * 保存活动信息
 */
Activity.saveActivity=function(){
	
	if(Validator.validForm("addActivityInfoForm")){
		
		submitForm("addActivityInfoForm",BASE_PATH + '/activity/add.do',function(data){
				//window.location.href = BASE_PATH + "/activity/toList.do";
				alert('保存成功');
				
			},function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 搜索活动列表
 */
Activity.searchActivityList=function(){
	submitSearch('activityListForm', 'load_content', BASE_PATH + '/activity/getActivityList.do',function() {
		pageInfo("activityListForm",Activity.searchActivityList);
	});
};
