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