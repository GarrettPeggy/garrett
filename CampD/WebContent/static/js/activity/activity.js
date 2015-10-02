/**
 * 活动js
 */

var Activity={
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
	    "isUserAuth":false
    };
	
	ajaxSearch(BASE_PATH + "/activity/getActivityList.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  活动查询设置为空
		$("#activity_first_pop").html("");
		var activity_html=[];
		var activityList=json.activityList;
		//把数据写到页面上
		if(null!=activityList && activityList.length>0){
			for(var i=0;i<activityList.length;i++){
				activity_html.push("<li class='pd5'><img src='"+REMOTE_RES_PATH+"/static/images/example_img_big.png' width='100%' height='116'/><div class='classify-li-title'>"+(null==activityList[i].title ? "无标题" : activityList[i].title )+"</div><div class='classify-li-desc color94 fontSize14'><a href='"+BASE_PATH+"/activity/getActivityById.do?id="+activityList[i].id+"'>"+activityList[i].requirement+"</a></div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+activityList[i].begintime+"</span>--<span>"+activityList[i].endtime+"</span></div></li>");
			}
		}else{
			activity_html.push("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>");
		}
		$("#activity_first_pop").html(activity_html.join(""));
	},function(data){
		systemLoaded();
		alert("呵呵，错了吧-->"+data.returnMsg);
	});
	
};

/**
 * 活动分类页面
 */
Activity.classify=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListClassify.do?curPage=1&pageLimit=3&isUserAuth=false";
};

/**
 * 举办活动时的活动类型 
 */
Activity.type=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityType.do";
};
/**
 * 举办活动时的活动城市
 */
Activity.city=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityCity.do";
};
/**
 * 举办活动时的活动人数
 */
Activity.people=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityPeople.do";
};

/**
 * 举办活动的时候活动需求
 */
Activity.desc=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityDesc.do";
};

/**
 * 各种活动再分类显示
 */
Activity.category=function(categoryValue){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByParam.do?categoryId="+categoryValue+"&curPage=1&pageLimit=3&isUserAuth=false";
};

/**
 * 热门活动显示
 */

Activity.populer=function(actType){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByParam.do?actType="+actType+"&curPage=1&pageLimit=3&isUserAuth=false";
};

/**
 * 热门活动加载更多
 */
Activity.loadMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Activity.search("/activity/getActivityList.do",false);
};
/**
 * 我的报名活动
 */
Activity.loadMyActivityMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Activity.search("/activity/getMyTakeAnActiveAjax.do",true);
};

//

/**
 * 活动搜索
 */
Activity.search=function(url,isUserAuth){
	var params = {
	    "curPage":$("#curPage").val(),
	    "pageLimit":$("#pageLimit").val(),
	    'isUserAuth':isUserAuth,
		'actType':$("#actType").length==0 ? '' : $("#actType").val(),
		'categoryId':$("#categoryId").length==0 ? '' : $("#categoryId").val(),
		'creatorId':$("#creatorId").length==0 ? '' : $("#creatorId").val(),
		'userId':$("#userId").length==0 ? '' : $("#userId").val()
	};

	ajaxSearch(BASE_PATH + url,params,function(json){
		var activityList=json.activityList;
		for(var i=0;i<activityList.length;i++){
			if($("#isSponsored").length > 0){//判断是不是要举办的活动界面  $("#isSponsored").length==0代表是要举办的活动
				$("#activity_popu").append('<li class="clearfix"> <div class="data-li-left"> <a href="'+BASE_PATH+'/activity/getActivityById.do?id=${activity.id }"> <img src="'+REMOTE_RES_PATH+'/static/images/list_show.png" width="91" height="91"/> </a> </div> <div class="data-li-right"> <div class="dlr-title retina-1px-border-bottom"> <span class="dlrt1">'+Activity.catagory[activityList[i].category_id]+'</span> <span class="dlrt2"><font color="#638ee0">'+activityList[i].act_num+'</font>人</span> <span class="dlrt3">'+activityList[i].act_city+'</span> </div> <div class="dlr-detail color94 fontSize14"> ' + activityList[i].requirement + '</div></div></li>');
			}else{
				$("#activity_popu").append("<li class='pd5'><img src='"+REMOTE_RES_PATH+"/static/images/example_img_big.png' width='100%' height='116'/><div class='classify-li-title'>"+(null==activityList[i].title ? "无标题" : activityList[i].title )+"</div><div class='classify-li-desc color94 fontSize14'><a href='"+BASE_PATH+"/activity/getActivityById.do?id="+activityList[i].id+"'>"+activityList[i].requirement+"</a></div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+activityList[i].begintime+"</span>--<span>"+activityList[i].endtime+"</span></div></li>");
			}
			
		};
		
		var $loadMore_li = $("#activity_more");
		var pageSize = $("#pageSize").val();
		var curPage = $("#curPage").val();
		if(curPage<pageSize){
			$("#activity_popu").parent().append($loadMore_li);
		} else{
			$("#activity_more").remove();
		}
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};
/**
 * 已报名的活动 在用户活动表中就应该查询得到  就是查询我的报名活动
 */

Activity.signUp=function(){
	window.location.href = BASE_PATH + "/" +"activity/getMyTakeAnActive.do?&curPage=1&pageLimit=3&isUserAuth=true";
};

/**
 * 要举办的活动，就是自己创建的活动，要举办的
 */

Activity.sponsored=function(){
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByUserId.do?&curPage=1&pageLimit=3&isUserAuth=true";
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
	systemLoading(null, true, "提交中,请稍等");
	var categoryId=$("#categoryId").val();
	var actNum=$("#actNum").val();
	var actCity=$("#actCity").val();
	var requirement=$("#requirement").val();
	if(categoryId=="" || null==categoryId){
		alert("请选择活动范畴");
		return;
	}
	if(actNum=="" || null==actNum){
		alert("请填写活动人数");
		return;
	}
	if(actCity=="" || null==actCity){
		alert("请选择活动城市");
		return;
	}
	if(requirement=="" || null==requirement){
		alert("请填写活动需求");
		return;
	}
	
	var params = {
		"categoryId":categoryId,//活动类型
		"actNum":actNum,//活动人数
		"actCity":actCity,//活动城市
		"requirement":requirement, //活动需求
		"actType":0//默认普通活动
	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
	submitSave(BASE_PATH + "/activity/add.do", params, function(data) {
		systemLoaded();
		$(".mc").removeClass("hide");
		$(".sign-succ-modal").removeClass("hide");
		//window.location.href = BASE_PATH + "/";
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};
/**
 * 关闭活动需求提交成功按钮
 */
Activity.close=function(){
	$(".sign-succ-modal").addClass("hide");
	$(".mc").addClass("hide");
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
	var params={"categoryId":$("#activityId").val()};
	ajaxSearch(BASE_PATH + "/activity/takeAnActive.do",params,function(json){
		systemLoaded();
//		var userId=json.userId;
//		if(null!=userId && userId.length>0){
//			$("#signUp_mc").removeClass("hide");
//			$("#signUp_modal").removeClass("hide");
//		}else{
//			window.location.href = BASE_PATH + "/" + "user/toLogin.do";
//		}
		$("#signUp_mc").removeClass("hide");
		$("#signUp_modal").removeClass("hide");
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};

/**
 * 关闭报名成功按钮
 */
Activity.closeSignUp=function(){
	$("#signUp_modal").addClass("hide");
	$("#signUp_mc").addClass("hide");
};

/**
 * 活动分享
 */
Activity.share=function(){
	$("#activity_share").removeClass("hide");
};

/**
 * 分享的取消按钮
 */
Activity.cancel=function(){
	$("#activity_share").addClass("hide");
};


