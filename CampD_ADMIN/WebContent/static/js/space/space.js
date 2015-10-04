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
Space.uploadSpacePic = function(currentObject){
	var fakepath = $(currentObject).val();
	var ext = fakepath.substring(fakepath.lastIndexOf('.')+1);
	if((Space.imageFormat).indexOf(ext)!=-1){
		submitForm('addSpaceInfoForm', BASE_PATH + '/upload/uploadCropImg.do', function(res){
			
			// 保证能连续上传同一张图片
			$(currentObject).val("");
			
			if(res && res.tmpPath){
				$("#pic_container").append('<div class="col-sm-3 clearfix" style="margin-top: 10px;margin-bottom: 10px;"><div class="avatar-x"><div id="addPic"><img class="space-img" src="'+res.tmpPath+'" width="200" height="200"></div><div class="avatar-bar"></div></div><button style="margin-left: 157px;margin-top: 10px;" class="btn btn-xs btn-success" id="button_0" onclick="Space.deleteCurPic(this);" type="button"><span class="bigger-110">删除</span></button></div>');
			}
		},function(res){
			Dialog.alertErrorCodeMsg(res.returnCode);
		});
	}else{
		Dialog.alertInfo('头像文件格式不正确,只能是如下后缀名：'+Space.imageFormat,true);
	}
};

/**
 * 删掉当前图片
 */
Space.deleteCurPic = function(currentObject){
	$(currentObject).parent().remove();
};

/**
 * 控制场地图片个数
 */
Space.controlPicNum = function(event,currentObject){
	if($(".space-img").length>=4){
		stopDefault(event||window.event);
		Dialog.alertInfo('展示图片不能超过4个！');
	}
};

/**
 * 添加场地信息
 */
Space.addSpace = function(){
	if(Validator.validForm("addSpaceInfoForm")){
		
		Space.getSpaceImages();
		
		submitForm("addSpaceInfoForm",BASE_PATH + '/space/add.do',
			function(data){
				window.location.href = BASE_PATH + "/space/toList.do";
			},
			function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 更新场地信息
 */
Space.updateSpace = function(){
	if(Validator.validForm("updateSpaceInfoForm")){
		
		Space.getSpaceImages();
		
		submitForm("updateSpaceInfoForm",BASE_PATH + '/space/update.do',
			function(data){
				window.location.href = BASE_PATH + "/space/toList.do";
			},
			function(data){
				Dialog.alertError(data.returnMsg);
			}
		);
	}
};

/**
 * 获取场地图片的链接集合
 */
Space.getSpaceImages = function(){
	var $space_imgs = $(".space-img");
	var $spaceImg = null, show_images = "";
	for (var i = 0; i < $space_imgs.length; i++) {
		$spaceImg = $($space_imgs[i]);
		if(i==0){
			show_images += $spaceImg.attr("src");
		} else {
			show_images += "," + $spaceImg.attr("src");
		}
	}
	$("#show_images").val(show_images);
};