/**
 * 场地js
 */
var Space={
	myScroll:null,
	spaceType:{// 场地类型
		"0":"餐厅",
		"1":"酒楼公园",
		"2":"游乐园",
		"3":"艺术中心",
		"4":"画廊",
		"5":"会所",
		"6":"俱乐部",
		"7":"展览馆",
		"8":"秀场",
		"9":"会展",
		"10":"会议中心",
		"11":"学校",
		"12":"培训机构",
		"13":"度假村",
		"14":"农家乐",
		"15":"商圈",
		"16":"商场",
		"17":"剧院",
		"18":"礼堂",
		"19":"酒吧",
		"20":"KTV",
		"21":"咖啡厅",
		"22":"茶馆",
		"23":"体育场馆",
		"24":"小区社区",
		"25":"写字楼",
		"26":"特色场地",
		"27":"众创空间"
	}
};

/**
 * 场地轮播图
 */
Space.tapShow=function(position,mySwipe){
	var bullets = document.getElementById(position).getElementsByTagName('li');
	var banner = Swipe(document.getElementById(mySwipe), {
		auto: 2000,
		continuous: true,
		disableScroll:false,
		callback: function(pos) {
			var i = bullets.length;
			while (i--) {
				bullets[i].className = ' ';
			}
			bullets[pos].className = 'cur';
		}
	});
	return banner;
};

/**
 * 首页加载的精品场地
 */
Space.list=function(){
	var params={
		"spaceLevel":1,
	    "curPage":1,
	    "pageLimit":3,
	    "isUserAuth":false,
	    "isRand":1
	};
	ajaxSearch(BASE_PATH + "/space/getSpaceListByParam.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  活动查询设置为空
		$("#space_first_pop").empty();
		var spaceList=json.resultList;
		//把数据写到页面上
		if(null!=spaceList && spaceList.length>0){
			for(var i=0;i<spaceList.length;i++){
				var area = spaceList[i].area, cost = spaceList[i].cost;
				var costSpan = "<span>"+cost+"元/小时</span>";
				if(cost == 0){
					costSpan = "<span class='red' style='color:red;'>免费</span>";
				}
				area = null==area?"":area;
				
				$("#space_first_pop").append($("<a href='"+BASE_PATH+"/space/getSpaceInfoById.do?id="+spaceList[i].id+"'><li class='pd5'><img src='"+OSS_RES_URL+spaceList[i].show_images.split(",")[0]+"' width='100%' height='156'/><div class='classify-li-title'>"+(null==spaceList[i].name ? "无名称" : spaceList[i].name )+"</div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/place_2.png' width='10' height='10'/>&nbsp;<span>" + area + "</span>&nbsp;&nbsp;<span>"+spaceList[i].traffic+"</span>&nbsp;&nbsp;"+costSpan+"</div></li></a>"));
			}
		}else{
			$("#space_first_pop").append($("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>"));
		}
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
};

/**
 * 获取字符串长度
 */
Space.getLength=function(str){
	var cArr = str.match(/[^\x00-\xff]/ig);  
    return str.length + (cArr == null ? 0 : cArr.length); 
};

/**
 * 首页点击更多的精品场地
 */
Space.hightLevel=function(spaceLevel){
	window.location.href = BASE_PATH + "/" +"space/getSpaceListByLevel.do?spaceLevel="+spaceLevel+"&curPage=1&pageLimit=6&isUserAuth=false";
};

/**
 * 分享的取消按钮
 */
Space.cancel=function(){
	$("#space_share").addClass("hide");
};

/**
 * 下拉小三角
 */
Space.setSelect=function(){
	$("#search-box li").each(function(index,item){
        $(item).bind("click",function(){
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").removeClass("hide");
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").prevAll().addClass("hide");
        	$(this).parent().parent().find("div.search-ul:eq("+index+")").nextAll().addClass("hide");
        	$("#space_mc").css("height",window.screen.height);
        	$("#space_mc").removeClass("hide");
            $("#workfor-list").css("display","none");	
            $("#search-box").css("margin-top","0px");
        });
	});
};
/**
 * 场地适用活动选择
 */
Space.workForHerder=function(){
	/*适用活动下拉列表*/
	$(".morelist").click(function(){
       $("#workfor-list").slideToggle("fast",function(){
    	   $("#search-box").css("margin-top",$(this).is(':hidden')?"0px":"45px");
       });
    }); 
	$(".workforlist").width($("#scrolllist").width()-$(".slidedown").width());
	$("#scroller").width($("#scrolllist").width());
};

/**
 * 初始化区域搜索
 */
Space.initProvince = function(){
	var provinceList = dsy.Items['0_2_0'];//上海区域
	var length = provinceList.length;
	for (var i = 0; i < length; i++) {
		var province = provinceList[i];
		$("#search_province").append($('<li onclick="Space.address(' + "'" + province + "'" + ',this)">' + province + '</li>'));
	}
};

/**
 * 恢复原样式
 */
Space.resetStyle=function(curObj){
	//其他样式复原
	$(curObj).parent().parent().prevAll().find("ul li:eq(0)").addClass("active");
	$(curObj).parent().parent().prevAll().find("ul li:not(:eq(0))").removeClass("active");
	$(curObj).parent().parent().nextAll().find("ul li:eq(0)").addClass("active");
	$(curObj).parent().parent().nextAll().find("ul li:not(:eq(0))").removeClass("active");
};

/**
 * 场地费用
 */
Space.cost=function(costType, curObj){
	
	$('#curPage').val(1);
	$('#cost').val(costType);//costType的值为  0：全部  1：收费   2：免费
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$("#space_highlevel").empty();
	Space.search();
};

/*适用活动类型*/
Space.workFor=function (workFor, curObj){
	
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
//	if(index>1){
//		$(".workforlist").scrollLeft(75*index);
//	}else{
//		$(".workforlist").scrollLeft(0);
//	}
	
	$('#curPage').val(1);
	$('#workFor').val(workFor);
	$("#space_highlevel").empty();
	Space.search();
};

/**
 * 类型
 */
Space.type=function(spaceType, curObj){
	
	$('#curPage').val(1);
	$('#spaceType').val(spaceType);//spaceType的值为  0：全部  1：众创空间  2：咖啡厅  3：公司会议室   4：社区场地   5：商业广场
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$("#space_highlevel").empty();
	Space.search();
};
/**
 * 地址
 */
Space.address=function(area, curObj){
	
	$('#curPage').val(1);
	$('#area').val(area);
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$("#space_highlevel").empty();
	Space.search();
};
/**
 * 场地容量
 */
Space.capacity=function(minCapacity,maxCapacity, curObj){
	
	$('#curPage').val(1);
	$('#minCapacity').val(minCapacity);
	$('#maxCapacity').val(maxCapacity);
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$("#space_highlevel").empty();
	Space.search();
};

Space.search=function(){
	
	var pageLimit = parseInt($("#pageLimit").val());
	
	var params = {
	    "curPage":$("#curPage").val(),
	    "pageLimit":pageLimit,
	    'isUserAuth':false,
		'cost':$('#cost').val(),
		'spaceType':$('#spaceType').val(),
		'area':$('#area').val(),
		'minCapacity':$('#minCapacity').val(),
		'maxCapacity':$('#maxCapacity').val(),
		'spaceLevel':$('#spaceLevel').length==0?'':$('#spaceLevel').val(),
		'workFor':$('#workFor').val()
	};
	
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	
	ajaxSearch(BASE_PATH + "/space/getSpaceListByParam.do",params,function(json){
		var resultList=json.resultList;
		if(null != resultList && resultList.length > 0){
			for(var i=0;i<resultList.length;i++){
				var adress = resultList[i].adress, cost = resultList[i].cost;
				var costSpan = "<span class='co'>"+cost+"元/小时</span>";
				if(cost == 0){
					costSpan = "<span class='co red' style='color:red;'>免费</span>";
				};
				var name = resultList[i].name;
				var len=Space.getLength(adress);
				if(len > 10){
					adress=adress.substring(0,11) + "...";
				}
				if(Space.getLength(name) > 10){
					name=name.substring(0,10) + "...";
				}
				$("#space_highlevel").append("<li class='clearfix'><a href='"+BASE_PATH+"/space/getSpaceInfoById.do?id="+resultList[i].id+"'><div class='data-li-left'><img src='"+OSS_RES_URL+resultList[i].show_images.split(",")[0]+"' width='91' height='63'/></div><div class='data-li-right'><div class='dlr-title' id='space_type'>"+name+"</div><div class='dlr-address'>" + adress + "</div><div class='dlr-cost clearfix'><div class='fl'>费用："+costSpan+"</div><div class='fr'><span class='co'>"+resultList[i].capacity+"</span>人</div></div></div></a></li>");
			};
		}else{
			$("#space_highlevel").append("<div class='ground-no'><img src='"+REMOTE_RES_PATH+"/static/images/no_data.png' width='41' height='41'/><p>抱歉，没有找到合适的场地</p><p>请浏览其他场地吧</p></div>");
		}
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		var curPage = $("#curPage").val();
		if(curPage<pageSize){
			$("#loadMore_li").remove();
			$("#space_highlevel").parent().append("<div id='loadMore_li'><button id='loadMore' name='loadMore' class='btn btn-xs btn-light bigger loadBtn' onclick='Space.loadMore()'>加载更多...</button></div>");
		}else{
			$("#loadMore_li").remove();
			$("#curPage").val(1);
		}
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};
/**
 * 加载更多...按钮
 */
Space.loadMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	Space.search();
};

Space.scrolllist=function(){
	Space.myScroll = new IScroll('#scrolllist', { scrollX: true, scrollY: false, mouseWheel: true });
};

/*banner图片放大*/
Space.showMax=function(){
	$("#picMax").animate({left:"0"},300);
	Space.tapShow('position1','mySwipe1');
};
Space.hideMax=function(){
	$("#picMax").animate({left:window.screen.width},300);
};
