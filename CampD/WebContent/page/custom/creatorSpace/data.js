var CreatorDataUtil = {};
/**
 * 众创空间场地数据结构
 */
// 每个区域下面有哪些重创空间
var areaLogoData={
	"浦东新区":[
        {"id":"0", "logo":"", "name":"", "mdn":""},
        {"id":"1", "logo":"", "name":"", "mdn":""}
	],
	"黄浦区":[
        {"id":"2", "logo":"", "name":"", "mdn":""},
        {"id":"3", "logo":"", "name":"", "mdn":""}
	]
};

// 每个重创空间下面有哪些场地
var logoSpaceData={
	"0":[
        {"id":"0", "capacity":"", "name":"", "cost":"", "showImage":""},
        {"id":"1", "capacity":"", "name":"", "cost":"", "showImage":""}
	],
	"1":[
        {"id":"2", "capacity":"", "name":"", "cost":"", "showImage":""},
        {"id":"3", "capacity":"", "name":"", "cost":"", "showImage":""}
	],
	"2":[
        {"id":"4", "capacity":"", "name":"", "cost":"", "showImage":""},
        {"id":"5", "capacity":"", "name":"", "cost":"", "showImage":""}
	],
	"3":[
        {"id":"6", "capacity":"", "name":"", "cost":"", "showImage":""},
        {"id":"7", "capacity":"", "name":"", "cost":"", "showImage":""}
	]
};

/**
 * 根据区域搜索场地
 */
CreatorDataUtil.getSpacesByArea = function(area){
	
	var logoArray = areaLogoData[area];
	var spaceArray = new Array();
	
	var logoId = null, curLogoSpaceArray = null;
	var length = logoArray.length;
	for (var i = 0; i < length; i++) {
		logoId = logoArray[i].id;
		curLogoSpaceArray = CreatorDataUtil.getSpacesByLogo(logoId);
		for (var j = 0; j < curLogoSpaceArray.length; j++) {
			spaceArray.push(curLogoSpaceArray[j]);
		}
	}
	
	return spaceArray;
};

/**
 * 根据众创空间（logo）搜索场地
 */
CreatorDataUtil.getSpacesByLogo = function(logoId){
	return logoSpaceData[logoId];
};