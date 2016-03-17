var Header = {
	home_tra_pic:"0",  //首页轮播图类型
	home_notify:"1"
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
/**
 * 跳转到联系我们界面
 */
Header.toContact=function(){
	window.location.href = BASE_PATH + "/" +"page/common/contact.jsp";
};
/**
 * 跳转到footer-rightmenu界面
 */
Header.toRightMenu=function(){
	window.location.href = BASE_PATH + "/" +"page/common/rightmenu.jsp";
};
/**
 * 已报名的活动 在用户活动表中就应该查询得到  就是查询我的报名活动
 */
Header.signUp=function(){
	window.location.href = BASE_PATH + "/" +"activity/getMyTakeAnActive.do?&curPage=1&pageLimit=6&isUserAuth=true";
};

/**
 * 活动分类页面
 */
Header.classify=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListClassify.do";
};

/**
 * 跳转到场地首页
 */
Header.spaceIndex=function(){
	window.location.href = BASE_PATH + "/" +"space/getSpaceInfoList.do?curPage=1&pageLimit=6&isUserAuth=false";
};

/**
 * 跳转到办公空间首页
 */
Header.officeSpaceIndex=function(){
	window.location.href = BASE_PATH + "/" +"officeSpace/toList.do";
};

Header.initHeadIcon=function(){
	$(".head-right-icon").bind("click",function(){
		$("#rightmenu-mc").removeClass("hide");
		$("#rightmenu-mc").height($(document).height());
	});
	$("#cd_logo").bind("click",function(){ //点击logo，跳转到首页
		window.location.href = "/";
	});
};

Header.initFootIcon=function(){
	// 底部左边菜单显示和隐藏
	$(".newfoot-left-icon").bind("click",function(){
		if($("#avtivity_nav").hasClass("hide")){
			$("#avtivity_nav").removeClass("hide");
		} else{
			$("#avtivity_nav").addClass("hide");
		}
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
				var url_val = tapShowList[i].url_val;
				url_val = url_val==null || isEmpty(url_val)?'javascript:void();':url_val;
				$(".swipe-wrap").append("<div><a href='"+url_val+"'><img style='height:156px;' class='img-responsive' src='"+OSS_RES_URL+tapShowList[i].value_val+"' alt='"+(i+1)+"'/></a></div>");
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
		alert(data.returnMsg);
	});
	
};

/**
 * 加载首页通知
 */
Header.loadNotify=function(){
	
	var params={"type":Header.home_notify}; 
	
	ajaxSearch(BASE_PATH + "/common/findSysConfigs.do",params,function(json){
		systemLoaded();
		var notifyList=json.sysConfigList;
		//把数据写到页面上
		if(null!=notifyList && notifyList.length>0){
			var length=notifyList.length;
			for(var i=0;i<length;i++){//
				var url_val = notifyList[i].url_val;
				url_val = url_val==null || isEmpty(url_val)?'javascript:void();':url_val;
				var value_val = notifyList[i].value_val;
				value_val = value_val==null || isEmpty(value_val)?'':value_val;
				value_val = value_val.length<=20?value_val:value_val.substr(0,20)+"...";
				$("#newsword").append('<a href="'+url_val+'">'+value_val+'</a>');
			}
		}
		
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
	
};

//分享平台公众号的二维码
Header.shareCD=function(){
	var isHide = $("#guanzhu").hasClass("hide");
	if(isHide){
		$("#guanzhu").removeClass("hide");
		$("#guanzhu").height($(document).height());
	} else{
		$("#guanzhu").addClass("hide");
	}
};
Header.cancelShareCD=function(){
	   $("#guanzhu").addClass("hide");
};
/**
 * 跳转到gift index界面
 */
Header.toGiftIndex=function(){
	window.location.href = BASE_PATH + "/gift/indexList.do";
};
/**
 * 跳转到foot关于我们
 */
Header.toAboutUs=function(){
	window.location.href = BASE_PATH + "/"+"page/common/aboutUs.jsp";
};
/**
 * 跳转到foot版权声明
 */
Header.toRights=function(){
	window.location.href = BASE_PATH + "/"+"page/common/rights.jsp";
};