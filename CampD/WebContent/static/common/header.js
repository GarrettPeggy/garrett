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
 * 跳转到更新页面
 */
Header.toUpdate=function(){
	window.location.href = BASE_PATH + "/"+"user/toUpdate.do";
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
	$("#cd_logo").bind("click",function(){ //点击logo，跳转到首页
		window.location.href = BASE_PATH + "/";
	});
};

/**
 * 轮播图
 */
Header.tapShow=function(){
	
	if(null != document.getElementById('position')){
		
		var bullets = document.getElementById('position').getElementsByTagName('li');
		if(null != bullets){
			var banner = Swipe(document.getElementById('mySwipe'), {
				auto: 2000,//自动滑动，单位为毫秒
				continuous: true,//是否循环滑动，默认值为true
				disableScroll:false,//停止任何触及此容器上滚动页面，默认值flase
				callback: function(pos) {
					var i = bullets.length;
					while (i--) {
						bullets[i].className = ' ';
					}
					bullets[pos].className = 'cur';
				}
			});
		}
	}

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
		//$("#img-tap-show-ul").empty();
		$(".swipe-wrap").empty();
		$("#position").empty();
		var tapShowList=json.sysConfigList;
		//把数据写到页面上
		if(null!=tapShowList && tapShowList.length>0){
			var length=tapShowList.length;//<li><a href='#' target='_blank'><img src='"+OSS_RES_URL+tapShowList[i].value_val+"' width='100%' height='125' alt='"+(i+1)+"'/></a></li>
			for(var i=0;i<length;i++){//
				$(".swipe-wrap").append("<div><a href='javascript:;'><img class='img-responsive' src='"+OSS_RES_URL+tapShowList[i].value_val+"' alt='"+(i+1)+"'/></a></div>");
				if(i==0){
					$("#position").append("<li class='cur'></li>");
				}else{
					$("#position").append("<li></li>");
				}
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