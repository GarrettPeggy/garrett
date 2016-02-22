<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/common/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>${address}</title>
	<%@ include file="/page/common/meta.jsp" %>
	<script type="text/javascript" src="${locResPath}/static/common/jquery/jquery-1.11.3.min.js?_v=${vs}"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7IGTlD9BMR4jas0p1PZn92RB"></script>
	<style type="text/css">
	body, html,#container {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	.header { position: relative; background: #638ee0;color: #ffffff; width: 100%; height: 51px; line-height: 51px;font-size: 19px;}
	.header .head-left-icon { position: absolute; top: 0;left: 2px; display: block; width: 45px; text-align: center; padding-top: 4px;}
	.header .head-content {width: 100%;text-align: center;}
	</style>
</head>
<body>
	<!-- 头部 -->
	<div class="header">
    	<a class="head-left-icon">
        	<img src="${rmtResPath}/static/images/back.png" onclick="back()" width="13" height="22"/>
        </a>
        <div class="head-content">
           <c:if test="${fn:length(address) > 11}">
               <c:out value="${fn:substring(address, 0, 12)}..." /> 
           </c:if>
           <c:if test="${fn:length(address) <=11}">
               <c:out value="${address}" /> 
            </c:if>
        </div>
    </div>
	<!-- end -->
	<!-- 主体 -->
    <div class="main">
    <!-- 点击出现地图  需要显示时删除hide -->
       <div class="showMap" id="container"></div>
    <!-- end -->
    </div>  
</body>
<script type="text/javascript">
	$(function(){
		$(".showMap").css("height",window.innerHeight);
		$(".showMap").css("width",window.innerWidth);
		initBaiduMap("container", '${address}');
	});
		
	/**
	* 在地图中根据详细地址搜索
	* containerId:地图展示的容器，address:详细地址
	*/
	function initBaiduMap(containerId, address){
		// 百度地图API功能
		var map = new BMap.Map(containerId);    // 创建Map实例
		searchByStationName(address, map);
		map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
		map.enableScrollWheelZoom(true);       //开启鼠标滚轮缩放 ，默认禁用
		map.enableContinuousZoom(true);         //启用地图惯性拖拽，默认禁用
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
		//map.addControl(new BMap.ScaleControl()); // 添加默认比例尺控件
	};
	/**
	* 在地图中根据详细地址搜索
	* address:详细地址，map:初始化map对象
	*/
	function searchByStationName(address,map){
		//构建查询
		var localSearch = new BMap.LocalSearch(map);
	    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	    map.clearOverlays();//清空原来的标注
		localSearch.setSearchCompleteCallback(function(searchResult){
			var poi = searchResult.getPoi(0);   //获取搜索结果中的第一个坐标(经度，纬度)
			if(poi){
				map.centerAndZoom(poi.point,16);
				// 自定义标记
				var myIcon = new BMap.Icon("${rmtResPath}/static/images/marker.png",new BMap.Size(20,29),{
					offset:new BMap.Size(10,25)    //指定定位位置
				});
				var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat),{icon:myIcon});  // 创建标注，为要查询的地址对应的经纬度
			    var opts = {title : '<span style="font-size:14px;color:#0A8021;">${address}</span>'};
			    var infoWindow =new BMap.InfoWindow("<div style='line-height:1.8em;font-size:12px;'>联系电话：15601925235</div>", opts);  // 创建信息窗口对象，引号里可以书写任意的html语句。
			    marker.addEventListener("click", function(){
			    this.openInfoWindow(infoWindow);
			    });
			    map.addOverlay(marker);
			}else{
				map.centerAndZoom(new BMap.Point(121.48,31.22),13);
			}
		});
		localSearch.search(address);	
	};
	
	/**
	 * 页面上的返回按钮
	 */
	function back(oldUrl){
		if(!oldUrl){
			window.history.go(-1);// 返回历史
		} else{
			window.location.href = oldUrl;
		}
	};
	
</script>
</html>
