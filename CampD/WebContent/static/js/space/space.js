/**
 * 场地js
 */
var Space={
	spaceType:{
		'0':'众创空间',
		'1':'咖啡厅',
		'2':'公司会议室',
		'3':'社区场地',
		'4':'商业广场'
	}
};

$(function(){
	Space.init();
});
/**
 * 场地初始化
 */
Space.init=function(){
	Space.setSelect();
};

/**
 * 首页加载的精品场地
 */
Space.list=function(){
	var params={
		"spaceLevel":1,
	    "curPage":1,
	    "pageLimit":3,
	    "isUserAuth":false
	};
	ajaxSearch(BASE_PATH + "/space/getSpaceListByParam.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  活动查询设置为空
		$("#space_first_pop").html("");
		var space_html=[];
		var spaceList=json.resultList;
		//把数据写到页面上
		if(null!=spaceList && spaceList.length>0){
			for(var i=0;i<spaceList.length;i++){
				space_html.push("<li class='pd5'><img src='"+OSS_RES_URL+spaceList[i].show_images.split(",")[0]+"' width='100%' height='116'/><div class='classify-li-title'>"+(null==spaceList[i].name ? "无名称" : spaceList[i].name )+"</div><div class='classify-li-desc color94 fontSize14'><a href='"+BASE_PATH+"/space/getSpaceInfoById.do?id="+spaceList[i].id+"'>"+spaceList[i].description+"</a></div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+spaceList[i].adress+"</span>&nbsp;&nbsp;<span>"+spaceList[i].traffic+"</span>&nbsp;&nbsp;<span>"+spaceList[i].cost+"元/小时</span></div></li>");
			}
		}else{
			space_html.push("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>");
		}
		$("#space_first_pop").html(space_html.join(""));
	},function(data){
		systemLoaded();
		alert("呵呵，错了吧-->"+data.returnMsg);
	});
};

/**
 * 首页点击更多的精品场地
 */
Space.hightLevel=function(spaceLevel){
	window.location.href = BASE_PATH + "/" +"space/getSpaceListByLevel.do?spaceLevel="+spaceLevel+"&curPage=1&pageLimit=3&isUserAuth=false";
};

/**
 * 场地分享
 */
Space.share=function(){
	$("#space_share").removeClass("hide");
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
	$(".search-parent-list li").each(function(index,item){
        $(item).bind("click",function(){
        	$(this).parent().parent().find("div.search-detail:eq("+index+")").removeClass("hide");
        	$(this).parent().parent().find("div.search-detail:eq("+index+")").prevAll().addClass("hide");
        	$(this).parent().parent().find("div.search-detail:eq("+index+")").nextAll().addClass("hide");
        	$("#space_mc").removeClass("hide");
        });
	});
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
	$('#spaceType').val("");
	$('#adress').val("");
	$('#minCapacity').val("");
	$('#maxCapacity').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	Space.resetStyle(curObj);
	
	$("#space_highlevel").empty();
	Space.search();
};
/**
 * 类型
 */
Space.type=function(spaceType, curObj){
	
	$('#curPage').val(1);
	$('#spaceType').val(spaceType);//spaceType的值为  0：全部  1：众创空间  2：咖啡厅  3：公司会议室   4：社区场地   5：商业广场
	$('#cost').val("");
	$('#adress').val("");
	$('#minCapacity').val("");
	$('#maxCapacity').val("");
	
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	Space.resetStyle(curObj);
	
	$("#space_highlevel").empty();
	Space.search();
};
/**
 * 地址
 */
Space.address=function(adress, curObj){
	
	$('#curPage').val(1);
	$('#adress').val(adress);
	$('#cost').val("");
	$('#spaceType').val("");
	$('#minCapacity').val("");
	$('#maxCapacity').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	Space.resetStyle(curObj);
	
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
	$('#cost').val("");
	$('#spaceType').val("");
	$('#adress').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	Space.resetStyle(curObj);
	
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
		'adress':$('#adress').val(),
		'minCapacity':$('#minCapacity').val(),
		'maxCapacity':$('#maxCapacity').val(),
		'spaceLevel':$('#spaceLevel').length==0?'':$('#spaceLevel').val()
	};
	
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	
	ajaxSearch(BASE_PATH + "/space/getSpaceListByParam.do",params,function(json){
		var resultList=json.resultList;
		for(var i=0;i<resultList.length;i++){
			$("#space_highlevel").append("<li class='clearfix'><div class='data-li-left'><img src='"+OSS_RES_URL+resultList[i].show_images.split(",")[0]+"' width='91' height='63'/></div><div class='data-li-right'><div class='dlr-title' id='space_type'>"+Space.spaceType[resultList[i].space_type]+"</div><div class='dlr-address'>"+resultList[i].adress+"</div><div class='dlr-cost clearfix'><div class='fl'>费用：<span class='co'>"+resultList[i].cost+"元/小时</span></div><div class='fr'><span class='co'>"+resultList[i].capacity+"</span>人</div></div></div></li>");
		};
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		//alert("pageSize=============="+pageSize);
		var curPage = $("#curPage").val();
		//alert("curPage###############"+curPage);
		if(curPage<pageSize){
			$("#loadMore_li").remove();
			$("#space_highlevel").parent().append("<div id='loadMore_li'><button id='loadMore' name='loadMore' class='btn btn-xs btn-light bigger loadBtn' onclick='Space.loadMore()'>加载更多</button></div>");
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
 * 加载更多按钮
 */
Space.loadMore=function(){
	
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	
	Space.search();
	
};

/**
 * 跳转到场地首页
 */
Space.classify=function(){
	window.location.href = BASE_PATH + "/" +"space/getSpaceInfoList.do?curPage=1&pageLimit=3&isUserAuth=false";
};

