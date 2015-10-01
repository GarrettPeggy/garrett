/**
 * 用户管理
 */
var User = {};

/**
 * 登陆方法
 */
User.login = function(){
	
	if (User.checkLogin()) {// 校验输入信息
		systemLoading(null, true, "登录中,请稍后");
		var params = {
			"userName":$("#userName").val(),
			"mdn":$("#mdn").val()
		};
		// 记住参数提交的格式一定要正确，否则会报error错误。
		submitSave(BASE_PATH + "/user/login.do", params, function(data) {
			systemLoaded();
			window.location.href = BASE_PATH + "/user/toList.do";
		}, function(data) {
			systemLoaded();
			Dialog.alertInfo(data.returnMsg);
		});
	} else {
		systemLoaded();
	}
	
};

/**
 * 校验登录信息
 */
User.checkLogin = function() {
	var userName = $("#userName").val();//用户名
	var mdn = $("#mdn").val();
	
	if (userName == "" || userName == $("#userName").attr('placeholder')) {
		Dialog.alertInfo("用户名不能为空!");
		return false;
	} else if (mdn == "" || mdn == $("#mdn").attr('placeholder')) {
		Dialog.alertInfo("手机号不能为空!");
		return false;
	} else if(!isPhone(mdn)){
		Dialog.alertError("手机号格式不正确!");
		return false;
	}
	return true;
};

/**
 * 查询用户列表
 */
User.searchUserList = function(){
	submitSearch('userListForm', 'load_content', BASE_PATH + '/user/list.do',function() {
		pageInfo("userListForm",User.searchUserList);
	});
};

/**
 * 设置用户角色
 */
User.setRole = function(userId, roleName){
	var params = {
		"userId":userId,
		"roleName":roleName
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/user/updateRole.do", params, function(data) {
		User.searchUserList();
	}, function(data) {
		Dialog.alertError(data.returnMsg);
	});
};
