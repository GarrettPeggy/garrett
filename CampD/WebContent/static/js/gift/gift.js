var Gift={
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
	    "pageLimit":3,
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
				/*var workFor = gift.work_for;//逗号隔开
				var category = "";
				var workForArray = workFor.split(',');
				for (var j = 0; j < workForArray.length; j++) {
					category += '<span>'+Gift.workFors[workForArray[j]]+'</span>';
				}*/
				$("#present_first_pop").append($('<a href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><div class="giftpic rightbd fl retina-1px-border-bottom"><img src="'+OSS_RES_URL+showImage+'" width="142" height="95"/><div class="giftword">'+name+'</div></div></a>'));
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
			return;
		}
		
		for(var i=0;i<length;i++){
			
			var gift = giftList[i];
			var showImage=gift.show_image;
			var name = gift.name;
			var workFor = gift.work_for;//逗号隔开
			var category = "";
			var workForArray = workFor.split(',');
			for (var j= 0; j < workForArray.length; j++) {
				category += '<span>'+Gift.workFors[workForArray[j]]+'</span>';
			}
			$("#gift_highlevel").append('<li class="pd5"><a href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><img class="giftpic" src="'+OSS_RES_URL+showImage+'" width="100%" height="116"/><div class="giftword ">'+name+'</div><div class="giftrange"><img src="'+BASE_PATH+'/static/images/drop.png" width="10"height="12"/>'+category+'</div></a></li>');
		};
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		if(curPage<pageSize){
			$("#loadMore_li").remove();
			$("#gift_highlevel").parent().append("<div id='loadMore_li'><button id='loadMore' name='loadMore' class='btn btn-xs btn-light bigger loadBtn' onclick='Gift.loadMoreHighLevel()'>加载更多...</button></div>");
		}else{
			$("#loadMore_li").remove();
			$("#curPage").val(1);
		}
	}, function(data) {
		systemLoaded();
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
        	$("#space_mc").css("height",$("#gift_index").height());
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
		'workForCity':$('#workForCity').val()   
	};
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	ajaxSearch(BASE_PATH + "/gift/list.do",params,function(json){
		var giftList=json.giftList;
		var length = giftList.length;
		if(curPage==1 && length==0){
			$("#gift_index").append('<div class="ground-no "><img src="'+BASE_PATH+'/static/images/no_data.png" width="41" height="41"/><p>抱歉，没有找到合适的礼品</p><p>请浏览其他礼品吧</p></div>');
			return;
		}
		//把数据写到页面上
       for(var i=0;i<length;i++){
			var gift = giftList[i];
			var showImage=gift.show_image;
			var name = gift.name;
			$("#gift_index").append('<a href="'+BASE_PATH+'/gift/getById.do?id='+gift.id+'"><div class="giftpic rightbd fl retina-1px-border-bottom"><img src="'+OSS_RES_URL+showImage+'" width="142" height="95"/><div class="giftword">'+name+'</div></div></a>');
		};
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		if(curPage<pageSize){
			$("#loadMore_li").remove();
			$("#gift_index").parent().append("<div id='loadMore_li'><button id='loadMore' name='loadMore' class='btn btn-xs btn-light bigger loadBtn' onclick='Gift.loadMore()'>加载更多...</button></div>");
		}else{
			$("#loadMore_li").remove();
			$("#curPage").val(1);
		}
	}, function(data) {
		systemLoaded();
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
	}else{
		$("#workfor-list").find("ul li:eq("+(index-4)+")").addClass("active");
		$("#workfor-list").find("ul li:not(:eq("+(index-4)+"))").removeClass("active");
		$("#scroller").find("ul li").removeClass("active");
	}
	$('#curPage').val(1);
	$('#workFor').val(workFor);
	$("#gift_index").empty();
	Gift.searchIndex(false);
};
/*适用城市*/			
Gift.workForCity=function (workForCity, curObj){
	$('#curPage').val(1);
	$('#workForCity').val(workForCity);
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	$("#gift_index").empty();
	Gift.searchIndex(false);
};
/**
 * 场地适用活动选择
 */
Gift.workForHerder=function(){
	/*适用活动下拉列表*/
	$(".slidedown").click(function(){
       $("#workfor-list").slideToggle("fast",function(){
    	   $("#searchbox").css("margin-top",$(this).is(':hidden')?"0px":"45px");
       });
    });
	$(".workforlist").width($("#scrolllist").width()-$(".slidedown").width());
	$("#scroller").width($("#scrolllist").width());
};

		
		
	
