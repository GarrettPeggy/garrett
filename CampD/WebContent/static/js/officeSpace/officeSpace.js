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
				$("#logolist").append("<div class='fl logoimg'><a href='"+BASE_PATH+"/office/toList.do?belongTo="+officelogos[i].id+"&name="+officelogos[i].name+"'><img src='"+OSS_RES_URL+officelogos[i].logo+"' width='65' height='65'/></a></div>");
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
	window.location.href = "#" + rnd();
};
/**
 * 区域下拉选择
 */
OfficeSpace.moreArea=function(){
	/*区域下拉列表*/
	$(".morelist").click(function(){
       $("#workfor-list").slideToggle("fast",function(){
    	   /*$("#space_main").css("margin-top",$(this).is(':hidden')?"7px":"45px");*/
    	   OfficeSpace.setOuterHeight();
       });
    }); 
	$(".workforlist").width($("#scrolllist").width()-$(".slidedown").width());
	$("#scroller").width($("#scrolllist").width());
	
};

OfficeSpace.search=function(){
	
	var params = {
		"status":$("#status").val(),
	    "curPage":$("#curPage").val(),
	    "pageLimit":parseInt($("#pageLimit").val()),
		'belongTo':$('#belongTo').val(),
		'area':$('#area').val()
	};
	OfficeSpace.ajaxSearch(params);
	
};

OfficeSpace.searchSpace=function(){
	
	var params = {
		"status":$("#status").val(),
	    "curPage":$("#curPage").val(),
	    "pageLimit":$("#pageLimit").val(),
		'minCost':$('#minCost').val(),
		'maxCost':$('#maxCost').val(),
		'railways':$('#line').val(),
		'area':$('#area').val()
	};
	OfficeSpace.ajaxSearch(params);
	// 先判断加载更多是否初始化过，如果没有就初始化，否则空执行
	OfficeSpace.dropload?$.noop():OfficeSpace.droploadPage(OfficeSpace.searchSpace, true); 
};

OfficeSpace.ajaxSearch = function(params){
	
	var pageLimit = parseInt($("#pageLimit").val());
	ajaxSearch(BASE_PATH + "/office/list.do",params,function(json){
		var resultList=json.resultList;
		if(null != resultList && resultList.length > 0){
			for(var i=0;i<resultList.length;i++){
				var area = resultList[i].area;
				var name = resultList[i].name;
				$("#creatorSpaceList").append($("<li class='clearfix item'><a  href='"+BASE_PATH+"/office/getById.do?id="+resultList[i].id+"'><div class='data-li-left fl'><img src='"+OSS_RES_URL+resultList[i].show_images.split(",")[0]+"' width='100%'></div><div class='data-li-right fl'><div class='dlr-title'>"+name+"</div><div class='dlr-address padt15'>"+area+"</div></div></a></li>"));
			};
		}else{
			if($("#curPage").val() == 1){
				$("#creatorSpaceList").append($("<div class='ground-no'><img src='"+REMOTE_RES_PATH+"/static/images/no_data.png' width='41' height='41'/><p>抱歉，没有找到合适的众创空间</p><p>请浏览其他众创空间吧</p></div>"));
			}
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
OfficeSpace.loadMore=function(isLogoPage){
	var curPage = 1 + parseInt($("#curPage").val());
	$("#curPage").val(curPage);//更新当前页面
	isLogoPage?OfficeSpace.searchSpace():OfficeSpace.search();
};

/**
 *滑动加载
 */
OfficeSpace.droploadPage= function(searchFunction, isLogoPage){
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
	    	searchFunction();
	    },
	    loadDownFn : function(me){
	    	var curPage = Number($("#curPage").val());
	    	var pageSize = Number($("#pageSize").val());
			if(curPage<pageSize){
				OfficeSpace.loadMore(isLogoPage);
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

/**
 * 品牌 区域 价位 地铁选择
 */
OfficeSpace.setSelect=function(){
	$("#modelSelect li").each(function(index,item){
        $(item).bind("click",function(){
        	if(index>0){
        		var value=index-1;
        		$("div.search-ul:eq("+value+")").removeClass("hide");
    	    	$("div.search-ul:eq("+value+")").css("top","-6px");
    	    	$("#space_mc").css({"height":window.screen.height,"top":"96px"});
    	    	$("#space_mc").removeClass("hide");
    	    	$("#scrolllist,.slidedown,#workfor-list").hide();
    	    	
    	    	$('#curPage').val(1);
    	    	$(".logolist,.qrcode").hide();
    	    	$("#creatorSpaceList").empty();
    	    	OfficeSpace.searchSpace();
        	}else{
        		$("#scrolllist,.slidedown").show();
        		$("#space_mc").addClass("hide");
        		$("#area").val("");
        		$(".logolist,.qrcode").show();
        	}
    	    	$(this).addClass('active');
                $(this).prevAll().removeClass("active");
                $(this).nextAll().removeClass("active");
        });
	});
};
/**
 * 初始化区域搜索
 */
OfficeSpace.initProvince = function(){
	var provinceList = dsy.Items['0_2_0'];//上海区域
	var length = provinceList.length;
	for (var i = 0; i < length; i++) {
		var province = provinceList[i];
		$("#search_province").append($('<li onclick="OfficeSpace.address(' + "'" + province + "'" + ',this)">' + province + '</li>'));
	}
};
/**
 * 区域选择
 */
OfficeSpace.address=function(area, curObj){
	
	$('#curPage').val(1);
	$('#area').val(area);
	$('#minCost').val("");
	$('#maxCost').val("");
	$('#line').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$('.search-ul').addClass("hide");
	$("#space_mc").addClass("hide");
	OfficeSpace.setOuterHeight();
	
	$(".logolist,.qrcode").hide();
	/*搜索场地*/
	$("#creatorSpaceList").empty();
	OfficeSpace.searchSpace();
};
/**
 * 价位选择
 */
OfficeSpace.cost=function(minCost,maxCost, curObj){
	
	$('#curPage').val(1);
	$('#minCost').val(minCost);
	$('#maxCost').val(maxCost);
	$('#area').val("");
	$('#line').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$('.search-ul').addClass("hide");
	$("#space_mc").addClass("hide");
	OfficeSpace.setOuterHeight();
	
	$(".logolist,.qrcode").hide();
	/*搜索场地*/
	$("#creatorSpaceList").empty();
	OfficeSpace.searchSpace();
};
/**
 * 地铁选择
 */
OfficeSpace.line=function(line, curObj){
	
	$('#curPage').val(1);
	$('#line').val(line);
	$('#area').val("");
	$('#minCost').val("");
	$('#maxCost').val("");
	
	$(curObj).addClass("active");
	$(curObj).prevAll().removeClass("active");
	$(curObj).nextAll().removeClass("active");
	
	$('.search-ul').addClass("hide");
	$("#space_mc").addClass("hide");
	OfficeSpace.setOuterHeight();
	
	$(".logolist,.qrcode").hide();
	/*搜索场地*/
	$("#creatorSpaceList").empty();
	OfficeSpace.searchSpace();
};
/**
 * 清空全局搜索框
 */
OfficeSpace.delSearchKey = function(){
	$("#searchKey").val("");
};
/**
 * 全局搜索
 */
OfficeSpace.searchKey = function(){
	
	$("#search_all").empty();
	$("#search_all").removeClass("hide");
	
	var searchKey = $('#searchKey').val();
	var params = {
	    "curPage":1,
	    "pageLimit":10000,
		'name':searchKey
	};
	
	if(isEmpty(searchKey)){
		$.dialog({title:'',
		    content: '您是否忘记输入关键词了？',
		    ok: function(){
		        return true;
		    },
		});
		return;
	}
	
	ajaxSearch(BASE_PATH + "/officeSpace/resourseList.do",params,function(json){
		
		var giftList = json.giftListMap.giftList;
		var spaceList = json.spaceListMap.resultList;
		var officeList = json.officeListMap.resultList;
		
		var giftLength = giftList.length;
		var spaceLength = spaceList.length;
		var officeLength = officeList.length;
		
		if(giftLength == 0 && spaceLength == 0 && officeLength == 0 ){
			$("#search_all").append($('<div class="searchRes nosearch">抱歉，暂时没有找到相关信息！</div>'));
			return;
		}
		
		if(null != officeList && officeLength > 0){
			for(var i=0; i<officeLength; i++){
				var id = officeList[i].id;
				var name = officeList[i].name;
				$("#search_all").append($('<div class="searchRes"><a href="'+BASE_PATH+'/office/getById.do?id='+id+'">'+name+'</a></div>'));
			};
		}
		
		if(null != giftList && giftLength > 0){
			for(var i=0; i<giftLength; i++){
				var id = giftList[i].id;
				var name = giftList[i].name;
				$("#search_all").append($('<div class="searchRes"><a href="'+BASE_PATH+'/gift/getById.do?id='+id+'">'+name+'</a></div>'));
			};
		}
		
		if(null != spaceList && spaceLength > 0){
			for(var i=0; i<spaceLength; i++){
				var id = spaceList[i].id;
				var name = spaceList[i].name;
				$("#search_all").append($('<div class="searchRes"><a href="'+BASE_PATH+'/space/getSpaceInfoById.do?id='+id+'">'+name+'</a></div>'));
			};
		}
		
	}, function(data) {
		systemLoaded();
		alert(data.returnMsg);
	});
};
