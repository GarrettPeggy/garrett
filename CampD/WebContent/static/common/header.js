var Header = {};
/**
 * 跳转到登录页面
 */
Header.toLogin=function(){
	window.location.href = BASE_PATH + "/"+"user/toLogin.do";
};

/**
 * 跳转到注册界面
 */
 Header.toRegister=function(){
	window.location.href = BASE_PATH + "/"+"user/toRegister.do";
};

/**
 * 跳转到注册界面
 */
 Header.toQuit=function(){
	window.location.href = BASE_PATH + "/"+"user/quit.do";
};

Header.initHeadIcon=function(){
	$(".head-left-icon").bind("click",function(){
		$("#avtivity_nav").removeClass("hide");
	});
	$(".head-right-icon").bind("click",function(){
		$("#activity_person").removeClass("hide");
	});
	
};

$(function(){
	Header.initHeadIcon();
});