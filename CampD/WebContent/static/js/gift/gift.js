var Gift={
	dropload:null,
	workFors:{// 活动类型
		"0":"创业",
		"1":"商务",
		"2":"生活",
		"3":"社交",
		"4":"科技",
		"5":"课程",
		"6":"运动",
		"7":"娱乐",
		"8":"亲子",
		"9":"文化",
		"10":"音乐",
		"11":"电影",
		"12":"公益",
		"13":"校园"
	},
	form:{// 礼品形态
		"0":"实物",
		"1":"礼券",
		"2":"兑换码",
		"3":"红包"
	},
	mainBusiness:{//主营业务
		"0":"注册",
		"1":"法律",
		"2":"财务",
		"3":"服务器",
		"4":"知识产权",
		"5":"创投",
		"6":"软件外包",
		"7":"场地",
		"8":"媒体",
		"9":"营推",
		"10":"管理咨询",
		"11":"人力资源"
	}
};
/**
 * 首页加载的精美礼品
 */
Gift.list=function(){
	var params={
		"level":1,
	    "curPage":1,
	    "pageLimit":4,
	    "isUserAuth":false,
	    "isRand":1
	};
	ajaxSearch(BASE_PATH + "/gift/list.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  礼品查询设置为空
		$("#present_first_pop").empty();
		var giftList=json.giftList;
		//把数据写到页面上
		var length = giftList.length;
		if(null!=giftList && length>0){
			for(var i=0;i<length;i++){
				var gift = giftList[i];
				var showImage=gift.show_image;
				var name = gift.name;
				$("#present_first_pop").append($('<a href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><div class="giftpic rightbd fl retina-1px-border-bottom"><img src="'+OSS_RES_URL+showImage+'" width="98%" height="120"/><div class="giftword">'+name+'</div></div></a>'));
			}
		}else{
			$("#present_first_pop").append($("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>"));
		}
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
};

/**
 * 搜索精美礼品
 */
Gift.searchHighlevel=function(isUserAuth){
	var pageLimit = parseInt($("#pageLimit").val());
	var curPage = $("#curPage").val();
	var params = {
	    "curPage":curPage,
	    "pageLimit":pageLimit,
	    'isUserAuth':isUserAuth,
		'level':$("#level").val()    
	};

	ajaxSearch(BASE_PATH + "/gift/list.do",params,function(json){
		var giftList=json.giftList;
		var length = giftList.length;
	
		if(curPage==1 && length==0){
			$("#gift_highlevel").append('<div class="ground-no "><img src="'+BASE_PATH+'/static/images/no_data.png" width="41" height="41"/><p>抱歉，没有找到合适的礼品</p><p>请浏览其他礼品吧</p></div>');
			Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
			return;
		}
		for(var i=0;i<length;i++){
			var gift = giftList[i];
			var showImage=gift.show_image;
			var name = gift.name;
			$("#gift_highlevel").append('<div class="giftpic rightbd  retina-1px-border-bottom item"><a class="item opacity" href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><img src="'+OSS_RES_URL+showImage+'" width="98%" height="120"/><div class="giftword">'+name+'</div></a></div>');
		};
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		$("#pageSize").val(pageSize);
		
		Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
	}, function(data) {
		systemLoaded();
		Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
		alert(data.returnMsg);
	});
};

Gift.loadMoreHighLevel = function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Gift.searchHighlevel(false);
};

Gift.highlevel=function(){
	window.location.href = BASE_PATH + "/gift/highLevelList.do";
};
/**
 * 下拉小三角
 */
Gift.setSelect=function(){
	$("#searchbox li").each(function(index,item){
        $(item).bind("click",function(){
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").removeClass("hide");
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").prevAll().addClass("hide");
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").nextAll().addClass("hide");
        	$("#space_mc").css("height",window.screen.height);
        	$("#space_mc").removeClass("hide");
        	$("#workfor-list").css("display","none");	
            $("#searchbox").css("margin-top","0px");
        });
	});
};

/*礼品首页*/
Gift.searchIndex=function(isUserAuth){
	var pageLimit = parseInt($("#pageLimit").val());
	var curPage = $("#curPage").val();
	var params = {
	    "curPage":curPage,
	    "pageLimit":pageLimit,
	    'isUserAuth':isUserAuth,
		'workFor':$('#workFor').val(),
		'mainBusiness':$('#mainBusiness').val(),
		'workForCity':$('#workForCity').val(),
		'form':$('#form').val()
	};
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	ajaxSearch(BASE_PATH + "/gift/list.do",params,function(json){
		var giftList=json.giftList;
		var length = giftList.length;
		if(curPage==1 && length==0){
			$("#gift_index").append('<div class="ground-no "><img src="'+BASE_PATH+'/static/images/no_data.png" width="41" height="41"/><p>抱歉，没有找到合适的礼品</p><p>请浏览其他礼品吧</p></div>');
			Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
			return;
		}
		//把数据写到页面上
       for(var i=0;i<length;i++){
			var gift = giftList[i];
			var showImage=gift.show_image;
			var name = gift.name;
			$("#gift_index").append('<div class="giftpic rightbd  retina-1px-border-bottom item"><a class="item opacity" href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><img  src="'+OSS_RES_URL+showImage+'" width="98%" height="120"/><div class="giftword">'+name+'</div></a></div>');
		};
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		$("#pageSize").val(pageSize);
		
		Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
	}, function(data) {
		systemLoaded();
		Gift.dropload?Gift.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
		alert(data.returnMsg);
	});
};
/*加载更多礼品*/
Gift.loadMore= function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Gift.searchIndex(false);
};
/*主营业务*/
Gift.mainBusiness=function(mainBusiness, curObj){
	$('#curPage').val(1);
	$('#mainBusiness').val(mainBusiness);//mainBusiness的值为 "0":"注册","1":"法律","2":"财务","3":"服务器"...
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	// 更新提示选项
	$("#choose").find(".cho-mainBusiness").remove();
	if(!isEmpty(mainBusiness)){
		$("#choose").append('<div class="fl chose cho-mainBusiness" onclick="Gift.deleteChoose(this)"><img class="cpic" src="'+BASE_PATH+'/static/images/border.png"  height="35"><div class="cword">'+$(curObj).text()+'</div></div>');
	}
	Gift.setOuterHeight();
	$("#gift_index").empty();
	Gift.searchIndex(false);
};
/*适用活动类型*/
Gift.workFor=function (workFor, curObj){
	var index = $(curObj).attr("value");
	if(index<4){
		$("#scroller").find("ul li:eq("+index+")").addClass("active");
		$("#scroller").find("ul li:not(:eq("+index+"))").removeClass("active");
		$("#workfor-list").find("ul li").removeClass("active");
		// 更新提示选项
		$("#choose").find(".cho-workFor").remove();
		if(index != 0){
			$("#choose").append('<div class="fl chose cho-workFor" onclick="Gift.deleteChoose(this)"><img class="cpic" src="'+BASE_PATH+'/static/images/border.png" height="35" ><div class="cword">'+$(curObj).text()+'</div></div>');
		}
	}else{
		$("#workfor-list").find("ul li:eq("+(index-4)+")").addClass("active");
		$("#workfor-list").find("ul li:not(:eq("+(index-4)+"))").removeClass("active");
		$("#scroller").find("ul li").removeClass("active");
		// 更新提示选项
		$("#choose").find(".cho-workFor").remove();
		$("#choose").append('<div class="fl chose cho-workFor" onclick="Gift.deleteChoose(this)"><img class="cpic" src="'+BASE_PATH+'/static/images/border.png"  height="35"><div class="cword">'+$(curObj).text()+'</div></div>');
	}
	Gift.setOuterHeight();
	$('#curPage').val(1);
	$('#workFor').val(workFor);
	$("#gift_index").empty();
	Gift.searchIndex(false);

};
/*礼品形态*/			
Gift.form=function (form, curObj){
	$('#curPage').val(1);
	$('#form').val(form);
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	// 更新提示选项
	$("#choose").find(".cho-form").remove();
	if(!isEmpty(form)){
		$("#choose").append('<div class="fl chose cho-form" onclick="Gift.deleteChoose(this)"><img class="cpic" src="'+BASE_PATH+'/static/images/border.png"  height="35"><div class="cword">'+$(curObj).text()+'</div></div>');
	}
	$("#gift_index").empty();
	Gift.searchIndex(false);
	Gift.setOuterHeight();
};
/*适用城市*/			
Gift.workForCity=function (workForCity, curObj){
	$('#curPage').val(1);
	$('#workForCity').val(workForCity);
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	// 更新提示选项
	$("#choose").find(".cho-workForCity").remove();
	if(!isEmpty(workForCity)){
		$("#choose").append('<div class="fl chose cho-workForCity" onclick="Gift.deleteChoose(this)"><img class="cpic" src="'+BASE_PATH+'/static/images/border.png"  height="35"><div class="cword">'+$(curObj).text()+'</div></div>');
	}
	$('#curPage').val(1);
	$("#gift_index").empty();
	Gift.searchIndex(false);
	Gift.setOuterHeight();
};

/**
 * 取消某一个过滤选项
 */
Gift.deleteChoose=function(curObj){
	var $curObj = $(curObj);
	if($curObj.hasClass("cho-workFor")){// 选择的是活动类型，workFor
		$("#choose").find(".cho-workFor").remove();// 移出当前选项
		$("#scrolllist,#workfor-list").find('li').removeClass('active');
		$($("#scrolllist").find('li')[0]).addClass('active');
		// 刷新页面
		$('#workFor').val('');
	}else if($curObj.hasClass("cho-mainBusiness")){// 选择的是主营业务，mainBusiness 
		$("#choose").find(".cho-mainBusiness").remove();// 移出当前选项
		$("#cho-mainBusiness").find('li').removeClass('active');
		$($("#cho-mainBusiness").find('li')[0]).addClass('active');
		$('#mainBusiness').val('');
	}else if($curObj.hasClass("cho-form")){// 选择的是礼品形态，form
		$("#choose").find(".cho-form").remove();// 移出当前选项
		$("#cho-form").find('li').removeClass('active');
		$($("#cho-form").find('li')[0]).addClass('active');
		// 刷新页面
		$('#form').val('');
	} else if($curObj.hasClass("cho-workForCity")){// 选择的是活动城市，workForCity 
		$("#choose").find(".cho-workForCity").remove();// 移出当前选项
		$("#cho-workForCity").find('li').removeClass('active');
		$($("#cho-workForCity").find('li')[0]).addClass('active');
		$('#workForCity').val('');
	}
	$("#gift_index").empty();
	$('#curPage').val(1);
	Gift.searchIndex(false);
	Gift.setOuterHeight();
};

/**
 * 场地适用活动选择
 */
Gift.workForHerder=function(){
	/*适用活动下拉列表*/
	$(".slidedown").click(function(){
       $("#workfor-list").slideToggle("fast",function(){
    	   $("#searchbox").css("margin-top",$(this).is(':hidden')?"0px":"45px");
    	   $(".outer").height(window.innerHeight-($("#activity_header").height()+$(".search-box").height()+$("#scrolllist").height()+1));
       });
    });
	$(".workforlist").width($("#scrolllist").width()-$(".slidedown").width());
	$("#scroller").width($("#scrolllist").width());
};
/*点击图片变大*/
Gift.showMax=function(){
	$("#picMax").removeClass("hide");
	$("#picMax").animate({left:"0"},100);
	/*让showmax图片垂直居中*/
	$(".showmax").css("margin-top",($(window).innerHeight() - $(".max img").innerHeight())/2);
};
Gift.hideMax=function(){
	$("#picMax").animate({left:window.screen.width},300);
	setTimeout('$("#picMax").addClass("hide");',300);
};		
/*下拉加载*/
Gift.droploadPage=function(){
	Gift.dropload= $('.inner').dropload({
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
	    	$("#gift_index").empty();
	    	Gift.searchIndex(false);
	    },
	    loadDownFn:function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				Gift.loadMore();
			}else{
				$(".dropload-load").empty();
				$(".dropload-load").append('<span class="loading"></span>没有更多了...');
				setTimeout(function(){
					Gift.dropload?Gift.dropload.resetload():$.noop();
				},1000);
			}
	    }
	});
	
};
/*精美礼品下拉加载*/
Gift.droploadHigh=function(){
	Gift.dropload= $('.inner').dropload({
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
	    	$("#gift_highlevel").empty();
	    	Gift.searchHighlevel(false);
	    },
	    loadDownFn:function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				Gift.loadMoreHighLevel();
			}else{
				$(".dropload-load").empty();
				$(".dropload-load").append('<span class="loading"></span>没有更多了...');
				setTimeout(function(){
					Gift.dropload?Gift.dropload.resetload():$.noop();
				},1000);
			}
	    }
	});
};
/**
 * 给滑动加载最外层div设置高度
 */
Gift.setOuterHeight = function(){
	$(".outer").height(window.innerHeight-($("#activity_header").height()+$(".search-box").height()+$("#scrolllist").height()+6));
};