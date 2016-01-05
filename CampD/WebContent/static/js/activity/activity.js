/**
 * 活动js
 */

var Activity={
	dropload:null,
	catagory:{
		'0':'创业',
		'1':'商务',
		"2":'玩乐',
		'3':'交友'
	}
};

/**
 * 活动加载 
 */

/**
 * 首页加载的热门活动
 */
Activity.list=function(){
	
	var params={
		"actType":1,
	    "curPage":1,
	    "pageLimit":3,
	    "isUserAuth":false,
	    "status":1//活动状态  0表示未发布 1表示已发布
    };
	
	ajaxSearch(BASE_PATH + "/activity/getActivityList.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  活动查询设置为空
		$("#activity_first_pop").empty();
		var activityList=json.activityList;
		//把数据写到页面上
		if(null!=activityList && activityList.length>0){
			for(var i=0;i<activityList.length;i++){
				var begintimeDate = DateUtil.parseDate(activityList[i].begintime,"yyyy-MM-dd HH:mm:ss");
				var begintime = DateUtil.fomatDate(begintimeDate,"MM月dd日 HH:mm");
				var begintimeWeek = DateUtil.getWeekDay(begintimeDate);
				$("#activity_first_pop").append($("<a href='"+BASE_PATH+"/activity/getActivityById.do?id="+activityList[i].id+"'><li class='pd5'><img src='"+OSS_RES_URL+activityList[i].show_image+"' width='100%' height='156'/><div class='classify-li-title'>"+(null==activityList[i].title ? "无标题" : activityList[i].title )+"</div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+begintime+"&nbsp;&nbsp;"+begintimeWeek+"</span></div></li></a>"));
			}
		}else{
			$("#activity_first_pop").append($("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>"));
		}
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
	
};

/**
 * 获取字符串长度
 */
Activity.getLength=function(str){
	var cArr = str.match(/[^\x00-\xff]/ig);  
    return str.length + (cArr == null ? 0 : cArr.length);  
};

/**
 * 各种活动再分类显示
 */
Activity.category=function(categoryValue){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByParam.do?status=1&categoryId="+categoryValue;
};

/**
 * 热门活动显示
 */

Activity.populer=function(actType){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByActType.do?status=1&actType="+actType+"&curPage=1&pageLimit=6&isUserAuth=false";
};

/**
 * 活动加载更多...
 */
Activity.loadMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	if($("#isSponsored").length > 0){//表示是在要举办的活动的界面
		Activity.search("/activity/getActivityList.do",true);
	}else{
		Activity.search("/activity/getActivityList.do",false);
	}
};
/**
 * 我的报名活动
 */
Activity.loadMyActivityMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Activity.search("/activity/getMyTakeAnActiveAjax.do",true);
};

/**
 * 活动搜索
 */
Activity.search=function(url,isUserAuth){
	var pageLimit = parseInt($("#pageLimit").val());
	var curPage = $("#curPage").val();
	var params = {
	    "curPage":$("#curPage").val(),
	    "pageLimit":pageLimit,
	    'isUserAuth':isUserAuth,
		'actType':$("#actType").length==0 ? '' : $("#actType").val(),
		'categoryId':$("#categoryId").length==0 ? '' : $("#categoryId").val(),
		'creatorId':$("#creatorId").length==0 ? '' : $("#creatorId").val(),
		'userId':$("#userId").length==0 ? '' : $("#userId").val(),
		'status':isUserAuth ? '' : 1    
	};

	ajaxSearch(BASE_PATH + url,params,function(json){
		var activityList=json.activityList;
		
		var length = activityList.length;
		if(curPage==1 && length==0){
			$("#activity_popu").append('<div class="textCenter mat15"><img src="'+BASE_PATH+'/static/images/no_data.png" width="41" height="41"><div class="ui-tips-box mat10"><span class="color94">抱歉，没有找到合适的活动</span><p class="mat15 color94">请浏览其他活动吧</p></div></div>');
			Activity.dropload?Activity.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
			return;
		}
		
		for(var i=0;i<length;i++){
			var city=activityList[i].city;
			if(null == city){
				city = "";
			}
			
			var begintimeDate = DateUtil.parseDate(activityList[i].begintime,"yyyy-MM-dd HH:mm:ss");
			var begintime = DateUtil.fomatDate(begintimeDate,"MM月dd日 HH:mm");
			var begintimeWeek = DateUtil.getWeekDay(begintimeDate);
			
			if($("#isSponsored").length > 0){//判断是不是要举办的活动界面  $("#isSponsored").length==0代表是已报名的活动    $("#isSponsored").length>0代表的是要举办的活动
				if(null !=activityList[i].show_image && "" != activityList[i].show_image){
					$("#activity_popu").append('<li class="clearfix"><a class="item itemwidth" href="'+BASE_PATH+'/activity/getActivityById.do?id='+activityList[i].id+'"><div class="data-li-left item opacity"><img src="'+OSS_RES_URL+activityList[i].show_image+'" width="91" height="91"/></div><div class="data-li-right"><div class="dlr-title retina-1px-border-bottom"><span class="dlrt1">'+Activity.catagory[activityList[i].category_id]+'</span>&nbsp;&nbsp;&nbsp;<span class="dlrt2"><font color="#638ee0">'+activityList[i].act_num+'</font>人</span>&nbsp;&nbsp;&nbsp;<span class="dlrt3">'+city+'</span></div></div></a></li>');
				}else{
					$("#activity_popu").append('<li class="clearfix"><a class="item itemwidth" href="'+BASE_PATH+'/activity/getActivityById.do?id='+activityList[i].id+'"><div class="data-li-left  item opacity"><img src="'+REMOTE_RES_PATH+'/static/images/empty_image.png" width="91" height="91"/></div><div class="data-li-right"><div class="dlr-title retina-1px-border-bottom"><span class="dlrt1">'+Activity.catagory[activityList[i].category_id]+'</span>&nbsp;&nbsp;&nbsp;<span class="dlrt2"><font color="#638ee0">'+activityList[i].act_num+'</font>人</span>&nbsp;&nbsp;&nbsp;<span class="dlrt3">'+city+'</span></div></div></a></li>');
				}
			}else{// 不是要举办的活动
				$("#activity_popu").append("<li class='pd5'><a class='item itemwidth' href='"+BASE_PATH+"/activity/getActivityById.do?id="+activityList[i].id+"'><img class='item opacity' src='"+OSS_RES_URL+activityList[i].show_image+"' width='100%' height='156'/><div class='classify-li-title'>"+(null==activityList[i].title ? "无标题" : activityList[i].title )+"</div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+begintime+"&nbsp;&nbsp;"+begintimeWeek+"</span></div></a></li>");
			}
			
		};
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
        $("#pageSize").val(pageSize);
        Activity.dropload?Activity.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
	}, function(data) {
		systemLoaded();
		Activity.dropload?Activity.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
		alert(data.returnMsg);
	});
};

/**
 * 已报名的活动 在用户活动表中就应该查询得到  就是查询我的报名活动
 */
Activity.signUp=function(){
	window.location.href = BASE_PATH + "/" +"activity/getMyTakeAnActive.do?&curPage=1&pageLimit=6&isUserAuth=true";
};

/**
 * 要举办的活动，就是自己创建的活动，要举办的
 */

Activity.sponsored=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByUserId.do?&curPage=1&pageLimit=6&isUserAuth=true";
};

/**
 * 举办活动,跳转到添加活动界面
 */

Activity.hold=function(){
	window.location.href = BASE_PATH + "/" +"activity/addUI.do";
};

/**
 * 提交活动需求
 */
Activity.actSub=function(){
	var categoryId=$("#categoryId").val();
	var actNum=$("#actNum").val();
	var requirement=$("#requirement").val();
	if(categoryId=="" || null==categoryId){
		alert("请选择活动范畴");
		return;
	}
	if(actNum=="" || null==actNum){
		alert("请填写活动人数");
		return;
	}
	
	var type = "^\\d+$"; 
    var re = new RegExp(type); 
    if(actNum.match(re)==null) { 
    	alert( "人数必须是大于或等于零的整数"); 
    	return;
    }
    
	if(requirement=="" || null==requirement){
		alert("请填写活动需求");
		return;
	}
	
	systemLoading(null, true, "提交中,请稍等");
	submitForm("addActivityForm",BASE_PATH + '/activity/add.do',function(data){
			systemLoaded();
			$(".mc").removeClass("hide");
			$(".sign-succ-modal").removeClass("hide");
			$(".mc").height($(document).height());
			$("#Activity.actSub").attr("onclick","void(0)");
		},function(data){
			systemLoaded();
			alert(data.returnMsg);
		}
	);
};
/**
 * 关闭活动需求提交成功按钮
 */
Activity.close=function(){
	$(".sign-succ-modal").addClass("hide");
	$(".mc").addClass("hide");
	Activity.sponsored();
};

/**
 * 左上角的图片分类导航
 */
Activity.nav=function(){
	$("#avtivity_nav").removeClass("hide");
};

/**
 * 移除class的m-active样式
 */
Activity.removeClas=function(id){
	if($("#"+id+"").hasClass("m-active")){
		$("#"+id+"").removeClass("m-active");
	}
};

/**
 * 添加class的hide属性
 */
Activity.addClas=function(id){
	if($("#"+id+"").hasClass("hide")){
	}else{
		$("#"+id+"").addClass("hide");
	}
};

/**
 * 活动报名
 */
Activity.sign=function(){
	systemLoading("", true, "加载中,请稍等");
	var user=$("#user").val();
	var params={"activityId":$("#activityId").val()};
	if(null==user || ""==user){//如果用户没有登录，就跳转到用户登录界面
		window.location.href = BASE_PATH + "/" +"user/toLogin.do";
	}else{
		ajaxSearch(BASE_PATH + "/activity/takeAnActive.do",params,function(json){
			systemLoaded();
			$("#signUp_mc").css("height",document.body.scrollHeight);
			$("#signUp_mc").removeClass("hide");
			$("#signUp_modal").removeClass("hide");
			// 让屏幕回到最上面
			scroll(0,0);
		}, function(data) {
			systemLoaded();
			alert(data.returnMsg);
		});
	}
};

/**
 * 关闭报名成功按钮
 */
Activity.closeSignUp=function(){
	$("#signUp_modal").addClass("hide");
	$("#signUp_mc").addClass("hide");
	//关闭报名成功按钮后跳转到我的报名活动界面
	Activity.signUp();
};

/**
 * 分享的取消按钮
 */
Activity.cancel=function(){
	$("#activity_share").addClass("hide");
};

/**
 * 验证省市区是否为空
 */
Activity.validateAdress = function(){
	
	var isPass = true;
	
	var province = $("input[name='province']").val();
	var city = $("input[name='city']").val();
	var area = $("input[name='area']").val();
	
	if(isEmpty(province)){
		Dialog.alertInfo("请选择省份！");
		isPass = false;
	}
	
	if(isEmpty(city)){
		Dialog.alertInfo("请选择城市！");
		isPass = false;
	}
	
	if(isEmpty(area)){
		Dialog.alertInfo("请选择区域！");
		isPass = false;
	}
	
	return isPass;
};
/*点击图片放大*/
Activity.showMax=function(){
	$("#picMax").removeClass("hide");
	$("#picMax").animate({left:"0"},100);
};
Activity.hideMax=function(){
	$("#picMax").animate({left:window.screen.width},300);
	setTimeout('$("#picMax").addClass("hide");',300);
};
/*下拉加载*/
Activity.droploadPage=function(){
	Activity.dropload= $('.inner').dropload({
	    domUp:{
	        domClass   : 'dropload-up',
	        domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
	        domUpdate  : '<div class="dropload-update">↑释放更新</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    domDown:{
	        domClass   : 'dropload-down',
	        domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
	        domUpdate  : '<div class="dropload-update">↓释放加载</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    loadUpFn:function(me){
	    	$("#curPage").val(1);
	    	$("#activity_popu").empty();
	    	Activity.search("/activity/getActivityList.do",false);
	    },
	    loadDownFn:function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				Activity.loadMore();
			}else{
				$(".dropload-load").empty();
				$(".dropload-load").append('<span class="loading"></span>没有更多了...');
				setTimeout(function(){
					Activity.dropload?Activity.dropload.resetload():$.noop();
				},1000);
			}
	    }
	});
	
};
/*我报名的活动*/
Activity.droploadSponsor=function(){
	Activity.dropload= $('.inner').dropload({
	    domUp:{
	        domClass   : 'dropload-up',
	        domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
	        domUpdate  : '<div class="dropload-update">↑释放更新</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    domDown:{
	        domClass   : 'dropload-down',
	        domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
	        domUpdate  : '<div class="dropload-update">↓释放加载</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    loadUpFn:function(me){
	    	$("#curPage").val(1);
	    	$("#activity_popu").empty();
	    	Activity.search("/activity/getMyTakeAnActiveAjax.do",true);
	    },
	    loadDownFn:function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				Activity.loadMyActivityMore();
			}else{
				$(".dropload-load").empty();
				$(".dropload-load").append('<span class="loading"></span>没有更多了...');
				setTimeout(function(){
					Activity.dropload?Activity.dropload.resetload():$.noop();
				},1000);
			}
	    }
	});
	
};



