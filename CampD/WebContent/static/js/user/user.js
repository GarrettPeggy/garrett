/**
 * 用户管理
 */
var User = {};

/**
 * 注册方法
 */
User.register = function(){
	
	if (User.checkLogin()) {// 校验输入信息
		systemLoading("", true, "注册中,请稍后");
		var params = $("#registerForm").serialize();
		submitSave(BASE_PATH + "/user/register.do", params, function(data) {
			systemLoaded();
			window.location.href = BASE_PATH + "/";
		}, function(data) {
			systemLoaded();
			alert(data.returnMsg);
		});
	} else {
		systemLoaded();
	}
	
};

/**
 * 登陆方法
 */
User.login = function(){
	
	if (User.checkLogin()) {// 校验输入信息
		systemLoading("", true, "登录中,请稍后");
		var params = $("#loginForm").serialize();
		submitSave(BASE_PATH + "/user/login.do", params, function(data) {
			systemLoaded();
			window.location.href = BASE_PATH + "/";
		}, function(data) {
			systemLoaded();
			alert(data.returnMsg);
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
		alert("用户名不能为空!");
		return false;
	} else if (mdn == "" || mdn == $("#mdn").attr('placeholder')) {
		alert("手机号不能为空!");
		return false;
	} else if(!isPhone(mdn)){
		alert("手机号格式不正确!");
		return false;
	}
	return true;
};
