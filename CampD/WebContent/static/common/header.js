var Header = {
	home_tra_pic:"0"  //首页轮播图类型
};
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
	$(".head-content").bind("click",function(){ //点击logo，跳转到首页
		window.location.href = BASE_PATH + "/";
	});
};

/**
 * 轮播图
 */
Header.tapShow=function(){
	
    $(".img-tap-show").yxMobileSlider({
    	width:$(document).width(),  //容器的宽度  不指定的话默认就是640
    	height:125,//容器的高度     不指定的话默认就是320
    	during:5000  //轮播的间隔时间   不指定默认就是5000毫秒
    });
};

/**
 * 加载轮播图
 */
Header.loadTapShow=function(){
	
	var params={"type":Header.home_tra_pic}; 
	
	ajaxSearch(BASE_PATH + "/common/findSysConfigs.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表项
		$("#img-tap-show-ul").empty();
		var tapShowList=json.sysConfigList;
		//把数据写到页面上
		if(null!=tapShowList && tapShowList.length>0){
			var length=tapShowList.length;
			for(var i=0;i<length;i++){
				$("#img-tap-show-ul").append("<li><a href='#' target='_blank'><img src='"+OSS_RES_URL+tapShowList[i].value_val+"' width='100%' height='125' alt='"+(i+1)+"'/></a></li>");
			}
			Header.tapShow();
		}
		
	},function(data){
		systemLoaded();
		alert("呵呵，错了吧-->"+data.returnMsg);
	});
	
};

$(function(){
	Header.initHeadIcon();
	Header.loadTapShow();
});