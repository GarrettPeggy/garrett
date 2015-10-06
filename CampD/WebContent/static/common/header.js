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
		$("#img-tap-show-ol").empty();
		var tapShowList=json.sysConfigList;
		//把数据写到页面上
		if(null!=tapShowList && tapShowList.length>0){
			var length=tapShowList.length;
			for(var i=0;i<length;i++){
				$("#img-tap-show-ul").append("<li><img src='"+OSS_RES_URL+tapShowList[i].value_val+"' width='100%' height='125' alt='"+(i+1)+"'/></li>");
				$("#img-tap-show-ol").append("<li></li>");
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