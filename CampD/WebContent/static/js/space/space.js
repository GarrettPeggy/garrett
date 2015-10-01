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
	Space.list();
	Space.setSelect();
};

/**
 * 首页加载的精品场地
 */
Space.list=function(){
	var params={"flag":0};
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
				space_html.push("<li class='pd5'><img src='"+REMOTE_RES_PATH+"/static/images/example_img_big.png' width='100%' height='116'/><div class='classify-li-title'>"+(null==spaceList[i].name ? "无名称" : spaceList[i].name )+"</div><div class='classify-li-desc color94 fontSize14'><a href='"+BASE_PATH+"/space/getSpaceInfoById.do?id="+spaceList[i].id+"'>"+spaceList[i].description+"</a></div><div class='classify-li-date fontSize14'><img src='"+REMOTE_RES_PATH+"/static/images/date_icon.png' width='10' height='10'/>&nbsp;<span>"+spaceList[i].adress+"</span>&nbsp;&nbsp;<span>"+spaceList[i].traffic+"</span>&nbsp;&nbsp;<span>"+spaceList[i].cost+"元/小时</span></div></li>");
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
	window.location.href = BASE_PATH + "/" +"space/getSpaceListByLevel.do?spaceLevel="+spaceLevel+"";
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
        	$(this).addClass("active");
        	$(this).prevAll().removeClass("active");
        	$(this).nextAll().removeClass("active");
        });
	});
};
/**
 * 场地费用
 */
Space.cost=function(costType){
	//alert(costType);
	var params={"cost":costType};//costType的值为  0：全部  1：收费   2：免费
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	Space.search(params);
};
/**
 * 类型
 */
Space.type=function(spaceType){
	var params={"spaceType":spaceType}; //spaceType的值为  0：全部  1：众创空间  2：咖啡厅  3：公司会议室   4：社区场地   5：商业广场
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	Space.search(params);
};
/**
 * 地址
 */
Space.address=function(adress){
	var params={"adress":adress};
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	Space.search(params);
};
/**
 * 场地容量
 */
Space.capacity=function(minCapacity,maxCapacity){
	var params={"minCapacity":minCapacity,"maxCapacity":maxCapacity};
	$(".search-detail").addClass("hide");
	$("#space_mc").addClass("hide");
	Space.search(params);
};

Space.search=function(params){
	ajaxSearch(BASE_PATH + "/space/getSpaceListByParam.do",params,function(json){
		$("#space_main").hide();
		$("#space_main_ajax").removeClass("hide");
		var resultList=json.resultList;
		if(0==resultList.length){
			$("#space_no").removeClass("hide");
			$("#space_yes").hide();
		}else{
			$("#space_no").addClass("hide");
			$("#space_yes").show();
			$("#space_yes ul li").remove();
			for(var i=0;i<resultList.length;i++){
				$("#space_cost_ul").append("<li class='clearfix'><div class='data-li-left'><img src='"+REMOTE_RES_PATH+"/static/images/ground_ex.png' width='91' height='63'/></div><div class='data-li-right'><div class='dlr-title' id='space_type'>"+Space.spaceType[resultList[i].space_type]+"</div><div class='dlr-address'>"+resultList[i].adress+"</div><div class='dlr-cost clearfix'><div class='fl'>费用：<span class='co'>"+resultList[i].cost+"元/小时</span></div><div class='fr'><span class='co'>"+resultList[i].capacity+"</span>人</div></div></div></li>");
			}
		}
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};

/**
 * 跳转到场地首页
 */
Space.classify=function(){
	window.location.href = BASE_PATH + "/" +"space/getSpaceInfoList.do";
};

