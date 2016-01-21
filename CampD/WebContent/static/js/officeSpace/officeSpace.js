/**
 * 办公空间js
 */
var OfficeSpace={dropload:null};

/**
 * 初始化区域，搜索众创空间（logo）
 */
OfficeSpace.initAreaLogo = function(){
	var areaList = dsy.Items['0_2_0'];//上海区域
	var length = areaList.length;
	for (var i = 0; i < length; i++) {
		var area = areaList[i];
		var $scrolllistUl = $("#scrolllist").find("ul");
		var $workforList = $("#workfor-list").find("ul");
		if(i<3){
			$scrolllistUl.append($('<li onclick="OfficeSpace.area(this)" value="'+(i+1)+'">' + area + '</li>'));
		}else{
			$workforList.append($('<li onclick="OfficeSpace.area(this)" value="'+(i+1)+'">' + area + '</li>'));
		}
	}
};

/**
 * 首页加载的众创空间logolist
 */
OfficeSpace.list=function(){
	var pageLimit = $("#pageLimit").val();
	var curPage = $("#curPage").val();
	var params={
		"status":$("#status").val(),
	    "curPage":$("#curPage").val(),
	    "pageLimit":pageLimit,
	    "area":$("#area").val()
	};
	ajaxSearch(BASE_PATH + "/officeSpace/list.do",params,function(json){
		systemLoaded();
		//数据查询成功
		//清空列表  活动查询设置为空
		var officelogos=json.resultList;
		var length=officelogos.length;
		//把数据写到页面上
		if(null!=officelogos && length>0){
			for(var i=0;i<length;i++){
				$("#logolist").append("<div class='fl logoimg'><a href='"+BASE_PATH+"/office/toList.do?belongTo="+officelogos[i].id+"&name="+officelogos[i].name+"'><img src='"+OSS_RES_URL+officelogos[i].logo+"' width='100%' height='60'/></a></div>");
			}
		}else{
			$("#logolist").append("<li class='pd5'>对不起，暂时没有你所要查询的数据</li>");
		}
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		$("#pageSize").val(pageSize);
		if(curPage<pageSize){
			$("#loadmorelogo").removeClass("hide");
		} else{
			$("#loadmorelogo").addClass("hide");
		}
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
};

/**
 * 根据区域找当前空间的场地
 */
OfficeSpace.searchByArea=function(){
	var params={
		"id": $("#belongTo").val()
	};
	ajaxSearch(BASE_PATH + "/officeSpace/getById.do",params,function(json){
		var areas = json.officeInfo.area;
		var areaArray = areas.split(',');
		var $scrolllistUl = $("#scrolllist").find("ul");
		var $logoareaList = $("#workfor-list").find("ul");
		for (var i = 0; i < areaArray.length; i++) {
			if(i<3){
				$scrolllistUl.append($('<li onclick="OfficeSpace.space(this)" value="'+(i+1)+'">' + areaArray[i] + '</li>'));
				$(".slidedown").addClass("hide");
			} else{
				$logoareaList.append($('<li onclick="OfficeSpace.space(this)" value="'+(i+1)+'">' + areaArray[i] + '</li>'));
				$(".slidedown").removeClass("hide");
			}
		}
		OfficeSpace.moreArea();
	},function(data){
		systemLoaded();
		alert(data.returnMsg);
	});
};
/*按区域筛选当前logo区域的场地*/
OfficeSpace.space = function(curObj){
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
	$("#curPage").val(1);
	index==0?$("#area").val(''):$("#area").val($(curObj).text());
	$("#creatorSpaceList").empty();
	OfficeSpace.search();
};

/*按区域筛选logo*/
OfficeSpace.area = function(curObj){
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
	$("#curPage").val(1);
	index==0?$("#area").val(''):$("#area").val($(curObj).text());
	$("#logolist").empty();
	OfficeSpace.list();
};
/**
 * 区域下拉选择
 */
OfficeSpace.moreArea=function(){
	/*区域下拉列表*/
	$(".morelist").click(function(){
       $("#workfor-list").slideToggle("fast",function(){
    	   $("#space_main").css("margin-top",$(this).is(':hidden')?"7px":"45px");
    	   OfficeSpace.setOuterHeight();
       });
    }); 
	$(".workforlist").width($("#scrolllist").width()-$(".slidedown").width());
	$("#scroller").width($("#scrolllist").width());
};

OfficeSpace.search=function(){
	
	var pageLimit = parseInt($("#pageLimit").val());
	
	var params = {
		"status":$("#status").val(),
	    "curPage":$("#curPage").val(),
	    "pageLimit":pageLimit,
		'belongTo':$('#belongTo').val(),
		'area':$('#area').val()
	};
	
	ajaxSearch(BASE_PATH + "/office/list.do",params,function(json){
		var resultList=json.resultList;
		if(null != resultList && resultList.length > 0){
			for(var i=0;i<resultList.length;i++){
				var area = resultList[i].area;
				var name = resultList[i].name;
				$("#creatorSpaceList").append($("<li class='clearfix item'><a  href='"+BASE_PATH+"/office/getById.do?id="+resultList[i].id+"'><div class='data-li-left fl'><img src='"+OSS_RES_URL+resultList[i].show_images.split(",")[0]+"' width='91' height='63'></div><div class='data-li-right fl'><div class='dlr-title'>"+name+"</div><div class='dlr-address padt15'>"+area+"</div></div></a></li>"));
			};
		}else{
			$("#creatorSpaceList").append($("<div class='ground-no'><img src='"+REMOTE_RES_PATH+"/static/images/no_data.png' width='41' height='41'/><p>抱歉，没有找到合适的众创空间</p><p>请浏览其他众创空间吧</p></div>"));
		}
		
		var dataCount = parseInt(json.dataCount);
		var pageSize = Math.floor(dataCount/pageLimit);
		pageSize = dataCount%pageLimit==0 ? pageSize: pageSize + 1;
		$("#pageSize").val(pageSize);
		OfficeSpace.dropload?OfficeSpace.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
	}, function(data) {
		systemLoaded();
		OfficeSpace.dropload?OfficeSpace.dropload.resetload():$.noop();// 滑动加载重置,一定要重置加载，即便是ajax请求失败也要在error中重新加载。
		alert(data.returnMsg);
	});
};
/**
 * 加载更多logo
 */
OfficeSpace.loadMoreLogo=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	OfficeSpace.list();
};

/**
 * 加载更多空间
 */
OfficeSpace.loadMore=function(){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	OfficeSpace.search();
};
/**
 *滑动加载
 */
OfficeSpace.droploadPage= function(){
	OfficeSpace.dropload= $('.inner').dropload({
	    domUp : {
	        domClass   : 'dropload-up',
	        domRefresh : '<div class="dropload-refresh">↓下拉刷新</div>',
	        domUpdate  : '<div class="dropload-update">↑释放更新</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    domDown : {
	        domClass   : 'dropload-down',
	        domRefresh : '<div class="dropload-refresh">↑上拉加载更多</div>',
	        domUpdate  : '<div class="dropload-update">↓释放加载</div>',
	        domLoad    : '<div class="dropload-load"><span class="loading"></span>加载中...</div>'
	    },
	    loadUpFn : function(me){
	    	$("#curPage").val(1);
	    	$("#creatorSpaceList").empty();
	    	OfficeSpace.search();
	    },
	    loadDownFn : function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				OfficeSpace.loadMore();
			}else{
				$(".dropload-load").empty();
				$(".dropload-load").append('<span class="loading"></span>没有更多了...');
				setTimeout(function(){
					OfficeSpace.dropload?OfficeSpace.dropload.resetload():$.noop();
				},1000);
			}
	    }
	});
};
/**
 * 给滑动加载最外层div设置高度
 */
OfficeSpace.setOuterHeight=function(){
	$(".outer").height(window.innerHeight-($(".header").height()+$(".search-box").height()+$("#scrolllist").height()+6));// 场地活动类型列表margin+场地列表的margi-top
};

