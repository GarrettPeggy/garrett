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
	    "pageLimit":5,
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
	window.location.href = BASE_PATH + "/" +"activity/getActivityListByParam.do?status=1&categoryId="+categoryValue+"&curPage=1&pageLimit=6&isUserAuth=false";
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

//

/**
 * 活动搜索
 */
Activity.search=function(url,isUserAuth){
	var pageLimit = parseInt($("#pageLimit").val());
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
		for(var i=0;i<activityList.length;i++){
			var city=activityList[i].city;
			if(null == city){
				city = "";
			}
			
			var begintimeDate = DateUtil.parseDate(activityList[i].begintime,"yyyy-MM-dd HH:mm:ss");
			var begintime = DateUtil.fomatDate(begintimeDate,"MM月dd日 HH:mm");
			var begintimeWeek = DateUtil.getWeekDay(begintimeDate);
			
			if($("#isSponsored").length > 0){//判断是不是要举办的活动界面  $("#isSponsored").length==0代表是已报名的活动    $("#isSponsored").length>0代表的是要举办的活动
				if(null !=activityList[i].show_image && "" != activityList[i].show_image){
					$("#activity_popu").append('<li class="clearfix"><a href="'+BASE_PATH+'/activity/getActivityById.do?id='+activityList[i].id+'"><div class="data-li-left"><img src="'+OSS_RES_URL+activityList[i].show_image+'" width="91" height="91"/></div><div class="data-li-right"><div class="dlr-title retina-1px-border-bottom"><span class="dlrt1">'+Activity.catagory[activityList[i].category_id]+'</span>&nbsp;&nbsp;&nbsp;<span class="dlrt2"><font color="#638ee0">'+activityList[i].act_num+'</font>人</span>&nbsp;&nbsp;&nbsp;<span class="dlrt3">'+city+'</span></div></div></a></li>');
				}else{
					$("#activity_popu").append('<li class="clearfix"><a href="'+BASE_PATH+'/activity/getActivityById.do?id='+activityList[i].id+'"><div class="data-li-left"><img src="'+REMOTE_RES_PATH+'/static/images/empty_image.png" width="91" height="91"/></div><div class="data-li-right"><div class="dlr-title retina-1px-border-bottom"><span class="dlrt1">'+Activity.catagory[activityList[i].category_id]+'</span>&nbsp;&nbsp;&nbsp;<span class="dlrt2"><font color="#638ee0">'+activityList[i].act_num+'</font>人</span>&nbsp;&nbsp;&nbsp;<span class="dlrt3">'+city+'</span></div></div></a></li>');
				}
			}else{//已报名的活动
				$("#activity_popu").append("<li class='pd5'><a href='"+BASE_PATH+"/activity/getActivityById.do?id="+activityList[i].id+"'><img src='"+OSS_RES_URL+activityList[i].show_image+"' width='100%' height='156'/><div class='classify-li-title'>"+(null==activityList[i].title ? "无标题" : activityList[i].title )+"</div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+begintime+"&nbsp;&nbsp;"+begintimeWeek+"</span></div></a></li>");
			}
			
		};
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		var curPage = $("#curPage").val();
		if(curPage<pageSize){
			$("#activity_more").remove();
			$("#activity_popu").parent().append("<div id='activity_more'><button id='activityLoadMore' name='activityLoadMore' class='btn btn-xs btn-light bigger loadBtn' onclick='Activity.loadMore()'>加载更多...</button></div>");
		} else{
			$("#activity_more").remove();
			$("#curPage").val(1);
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
	
//	var params = {
//		"categoryId":categoryId,//活动类型
//		"actNum":actNum,//活动人数
//		"province":province,//省份
//		"city":city,//城市
//		"area":area,//区域
//		"requirement":requirement, //活动需求
//		"actType":0,//默认普通活动
//		"status":0,//活动状态  0表示未发布
//		"contact":$("#contact").val()
//	};
	// 记住参数提交的格式一定要正确，否则会报error错误。
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
//	submitSave(BASE_PATH + "/activity/add.do", params, function(data) {
//		systemLoaded();
//		$(".mc").removeClass("hide");
//		$(".sign-succ-modal").removeClass("hide");
//		$(".mc").height($(document).height());
//		$("#Activity.actSub").attr("onclick","void(0)");
//		//window.location.href = BASE_PATH + "/";
//	}, function(data) {
//		systemLoaded();
//		alert(data.returnMsg);
//	});
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
