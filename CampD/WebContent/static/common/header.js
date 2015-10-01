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
		$("#activity_main").addClass("m-active");
		$("#activity_header").addClass("m-active");
		$("#activity_mao").removeClass("hide");
		$("#activity_person").removeClass("hide");
	});
	
};

/**
 * 轮播图
 */
Header.tapShow=function(){
	//调用Luara示例
    $(".img-tap-show").luara({
    	interval:5000,
    	selected:"seleted"
    });
};

$(function(){
	Header.initHeadIcon();
	Header.tapShow();
});