/////////////////////整体封装/////////////////////////

/**form ajax 提交*
 * @param formId
 * @param url 地址
 * @param successback 成功调用
 * @param failback 失败调用
 * */
function submitForm(formId,url,successback,failback){
	if (!formId){
	   alert('formId不能为空！');
	   return false;
	}
	//提交前默认值清空
	$('#'+formId).find("input[placeholder],textarea[placeholder]").each(function(){
		if($(this).val()==$(this).attr('placeholder')){
			$(this).val('');
		}
	});
	if(url.indexOf(".do?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}
	var options = { //ajaxform表单提交设置
		url:url, //action目标
		type: 'POST',
		dataType:"json",
		success:function(data){
			if(data && data.returnCode=='200'){
				if(successback){
					successback(data);
				}
				return true;
			}
			if(failback){
				failback(data);
			}
			return false;
		}
	};
	$('#'+formId).ajaxSubmit(options);
	return true;

}

/**
 * 非form ajax提交
 * @param url
 * @param params 提交参数，json对象
 * @param callback
 * @returns {Boolean}
 */
function submitSave(url,params,successback,failback){
	if(url.indexOf(".do?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}
	$.ajax({
		url:url,
		data:params,
		type: 'POST',
		dataType:'json',
		success:function(data){
			if(data && data.returnCode=='200'){
				if(successback){
					successback(data);
				}
				return;
			}
			if(failback){
				failback(data);
			}
		}
	
	});
	return true;
}

/**
 * ajax查询
 * @param url
 * @param params
 * @param successback
 * @param failback
 */
function ajaxSearch(url,params,successback,failback){
	submitSave(url,params,successback,failback);
}

function submitSearch(formId,boxId,url,callBack){
	
	$('#'+boxId+' table tr:gt(0)').hide();
	$('#'+boxId+' .pages_list').hide();
	
	systemLoading('#'+boxId);
	var randParam = rnd();
	var randNum = randParam.split("=")[1];
	$('#'+boxId).attr('loaddatarandnum',randNum);
	if(url.indexOf(".do?")>0){
		url = url+"&"+randParam;
	}else{
		url = url+"?"+randParam;
	}
	//没有form的时候
	if(!$('#'+formId).attr('id')){
		$.get(url,function(resData){
			var curRandNum = $('#'+boxId).attr('loaddatarandnum');
			if(randNum==curRandNum){
				$('#'+boxId).empty().html(resData);
				systemLoaded('#'+boxId);
				//表单聚焦验证
				Validator.onblur($(document));
				
				if(callBack){
					callBack();
				}
			}
		});
		return;
	}
	$('#'+formId).find("input[placeholder],textarea[placeholder]").each(function(){
		if($(this).val()==$(this).attr('placeholder')){
			$(this).val('');
		}
	});
	var options = { //ajaxform表单提交设置
		url:url, //action目标
		type: 'POST',
		success:function(resData){
			var curRandNum = $('#'+boxId).attr('loaddatarandnum');
			if(randNum==curRandNum){
				$('#'+boxId).empty().html(resData);
				systemLoaded('#'+boxId);
				//表单聚焦验证
				Validator.onblur($(document));
				
				if(callBack){
					callBack();
				}
			}
		},
		error:function(){
			systemLoaded('#'+boxId);
		}
	};
	$('#'+formId).ajaxSubmit(options);
	return false;
}


Dialog = {
	systemCode:{
		'499':'后台异常!',
		'500':'后台异常!',
		'502':'数据异常!',
		'503':'数据重复!',
		'508':'数据违反长度约束!',
		
		'900':'会话失效!',
		'901':'参数错误!',
		'902':'操作异常!',
		'903':'文件过大!',
		'904':'文件格式错误!'
	}
};

Dialog.alertErrorCodeMsg = function(returnCode){
	var msg = Dialog.systemCode[returnCode]||'操作失败';
	if(msg){
		Dialog.alertError(msg);
	}
};

Dialog.alertInfo = function(ctt,autoClose){
	var borderStartDiv = autoClose?'':'<div class="dashbor">';
	var borderEndDiv = autoClose?'':'</div>';
	var infoDialogOp = {
		id:'sysInfo',
		title:'提示',
		content: '<div class="wp_340"><div class="wp_con">'+borderStartDiv+'<span class="popinfor"></span><div class="infortext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div>'+borderEndDiv+'</div></div>',
		lock:true,
		okVal: '确定',
	    ok: true
	};
	if(autoClose){
		infoDialogOp.title = false;
		infoDialogOp.ok = false;
	}
	if(autoClose){
		infoDialogOp.time = 1;
	}
	var dialog = $.dialog(infoDialogOp);
};

Dialog.alertSuccess = function(ctt,autoClose){
	var borderStartDiv = autoClose?'':'<div class="dashbor">';
	var borderEndDiv = autoClose?'':'</div>';
	var succDialogOP = {
		id:'sysSucc',
		title:'提示',
		content: '<div class="wp_340"><div class="wp_con">'+borderStartDiv+'<span class="popsus"></span><div class="sustext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div>'+borderEndDiv+'</div></div>',
		lock:true,
		okVal: '关闭',
	    ok: true
	};
	if(autoClose){
		succDialogOP.title = false;
		succDialogOP.ok = false;
	}
	if(autoClose){
		succDialogOP.time=1;
	}
	var dialog = $.dialog(succDialogOP);
};

Dialog.alertSuccessWithOk = function(ctt,ok,autoClose){
	var borderStartDiv = autoClose?'':'<div class="dashbor">';
	var borderEndDiv = autoClose?'':'</div>';
	var succDialogOP = {
		id:'sysSucc',
		title:'提示',
		content: '<div class="wp_340"><div class="wp_con">'+borderStartDiv+'<span class="popsus"></span><div class="sustext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div>'+borderEndDiv+'</div></div>',
		lock:true,
		okVal: '关闭',
	    ok: ok
	};
	if(autoClose){
		succDialogOP.title = false;
		succDialogOP.ok = false;
	}
	if(autoClose){
		succDialogOP.time=1;
	}
	var dialog = $.dialog(succDialogOP);
};

Dialog.alertWarning = function(ctt){
	$.dialog({
		id:'sysWarning',
		title:'警告',
		content: '<div class="wp_602"><div class="wp_con"><div class="dashbor"><span class="popwarning"></span><div class="warntext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div></div></div></div>',
		lock:true,
		okVal: '关闭',
	    ok: true
	});
};

Dialog.alertError = function(ctt){
	$.dialog({
		id:'sysError',
		title:'错误',
		content: '<div class="wp_340"><div class="wp_con"><div class="dashbor"><span class="poperro"></span><div class="errotext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div></div></div></div>',
		lock:true,
		okVal: '关闭',
	    ok: true
	});
};

/**
 * 确认
 * @param content
 * @param yes
 * @param no
 * @param parent
 */
Dialog.confirm = function(ctt,yes,no){
	$.dialog({
		id:'sysConfirm',
		title:'确认',
		content: '<div class="wp_340"><div class="wp_con"><div class="dashbor"><span class="popwarning"></span><div class="warntext"><h3>'+(ctt?ctt:'')+'</h3></div><div class="clr"></div></div></div></div>',
		lock:true,
		okVal: '确定',
	    ok: function(){
	    	yes?yes():$.noop();
	    	return true;
	    },
	    cancelVal:'取消',
	    cancel:true
	});
};

/**
 * dialog插件自带的锁屏层显示
 */
Dialog.showLockMask = function(){
	$('#ldg_lockmask').show();
};

/**
 * ajax异步请求打开弹出框
 * @param url 打开的url地址
 * @param params 参数
 * @param title 标题
 * @param ajaxCallback ajax回调函数 callback(dialog,resData)
 * @param dialogOptions dialog插件的dialog传入参数对象{width:123,height:456...}
 */
Dialog.ajaxOpenDialog = function(url,params,title,ajaxCallback,dialogOptions){
	systemLoading(null,true);
	dialogOptions = dialogOptions ||{};
	dialogOptions.title = false;//title?title:"操作";
	dialogOptions.lock = true;
	if(url.indexOf("?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}
	$.ajax({
		url: url,
		data:params,
		type: "POST",
		success: function(data){
			dialogOptions.content = data;
			var dialog = $.dialog(dialogOptions);
			//表单聚焦验证
			Validator.onblur($(document));
			
			systemLoaded();
			if(ajaxCallback){
				ajaxCallback(dialog,data);
			}
		}
	});
};

Dialog.ajaxSubmitFormOpen = function(formId,url,title,ajaxCallback,dialogOptions){
	systemLoading(null,true);
	//提交前清空
	$('#'+formId).find("input[placeholder]").each(function(){
		if($(this).val()==$(this).attr('placeholder')){
			$(this).val('');
		}
	});
	dialogOptions = dialogOptions ||{};
	if(title){
		dialogOptions.title = title;
	}
	if(url.indexOf(".do?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}
	$("#"+formId).ajaxSubmit({
		url: url,
		type: "POST",
		success: function(data){
			dialogOptions.content = data;
			var dialog = $.dialog(dialogOptions);
			//表单聚焦验证
			Validator.onblur($(document));
			
			systemLoaded();
			if(ajaxCallback){
				ajaxCallback(dialog,data);
			}
		},
		error:function(){
			Dialog.alertError("操作失败!");
		}
	});
};

//随机码
function rnd(){
	var random = Math.floor(Math.random() * 10001);
  	var id = (new Date().getTime()*random).toString();
  	id = id.split('').reverse().join('');
  	return 'randomId='+id;
}

/**
 * 系统显示加载中
 * @param jquery选择符
 * @param isLock 是否锁屏
 * @param desc 加载的描述字符
 */
function systemLoading(selector,isLock,desc){
	selector = selector || 'body';
	var container = $(selector);
	//控制弹出层和loading的层的z-index
	var ldgMask = $('#ldg_lockmask');
	var ldgMaskIndex = 1976;
	if(ldgMask.length>0){
		ldgMaskIndex = parseInt($('#ldg_lockmask').css('z-index'));
	}
	var width = container.width();
	var height = container.height();
	
	container.each(function(){
		//loading层控制
		var dataLoadingDiv = $(selector+' > .loading');
		if(dataLoadingDiv.length<=0){
			dataLoadingDiv = $('<div class="loading" style="width:'+window.innerWidth+'px;height:'+window.innerHeight+'px;"><img src="'+REMOTE_RES_PATH+'/static/images/loading.gif" /></div>');
			$(this).append(dataLoadingDiv);
		}
		
		//锁屏层
		var dataMask = $(selector+' > .sys_masklock');
		if(isLock && dataMask.length<=0){
			dataMask = $('<div class="sys_masklock"></div>');
			$(this).append(dataMask);
		}
		dataMask.css({'z-index':ldgMaskIndex+7});
		dataMask.show();
		dataLoadingDiv.show();
	});
}

/**
 * 系统加载完成
 */
function systemLoaded(selector){
	selector = selector || 'body';
	var container = $(selector);
	container.each(function(){
		$(selector+' > .loading').hide();
		$(selector+' > .sys_masklock').hide();
	});
}

//////////////////////////ajax 初始化全局共用信息/////////////////////////////
$(function(){
	//对话框默认全局设置
	$.dialog.setting.lock = true;
	$.dialog.setting.min = false;
	$.dialog.setting.max = false;
	//表单聚焦验证
	Validator.onblur($(document));
	
    $(document).ajaxSuccess(function(event, XMLHttpRequest, ajaxOptions){
        //此处有bug。 应该直接判断返回结果是否是json，而不是通过dataType来判断。有可能dataType会省略。
         if (ajaxOptions.dataType=='json'&&XMLHttpRequest!=null){
            var rs = eval('('+XMLHttpRequest.responseText+')');
            if(rs.returnType=='0'){
            	//如果returnCode=200，表示操作正常完成
                if (rs.returnCode=='200'){
                	rs.returnMsg=rs.returnMsg?rs.returnMsg:'操作成功!';
                	Dialog.alertSuccess(rs.returnMsg, true);
                }else{ //如果非等于200，代表操作失败。则给出操作失败的原因。如：因为有关系数据不能正常删除等。
                	rs.returnMsg=rs.returnMsg?rs.returnMsg:'操作失败';
                	Dialog.alertError(rs.returnMsg);
                }
            }
         }
	});
    
	$(document).ajaxError(function(event,jqxhr,settings,exception) {
		systemLoaded();
		var resStatus = jqxhr.getResponseHeader('status');
		if(resStatus=='900'){
			window.location.href=BASE_PATH;
		}else if(resStatus=='905'){
			//表单重复提交不做任何处理
		}else{
			var readyState = jqxhr.readyState-0;
			var status = jqxhr.status;
			var statusText = jqxhr.statusText;
			typeof console != 'undefined'?console.info('readyState:'+readyState+',status:'+status+',statusText:'+statusText):$.noop();
			if(readyState<3 && readyState > 0){
				Dialog.alertError("网络异常!");
			}
			if(readyState==4){
				Dialog.alertError("后台异常!");
			}
		}
	});
	

});

/********************************js时间工具*****************************************/
DateUtil = {
	"chineseWeek":["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]
};

DateUtil.isLeapYear = function(date){
    return (0==date.getYear()%4&&((date.getYear()%100!=0)||(date.getYear()%400==0))); 
};

/**
 * 格式化日期
 */
DateUtil.fomatDate = function(date,fmt){
	var yyyy = date.getFullYear();
	var MM = date.getMonth();
	var dd = date.getDate();
	var HH = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();
	var hh = HH>12?HH-12:HH;
	var dateStr = fmt.replace('yyyy',yyyy).replace('MM',DateUtil.addZero(MM+1))
	.replace('dd',DateUtil.addZero(dd)).replace('HH',DateUtil.addZero(HH)).replace('mm',DateUtil.addZero(mm))
	.replace('ss',DateUtil.addZero(ss)).replace('hh',DateUtil.addZero(hh));
	return dateStr;
};

DateUtil.addZero = function(num){
	if(num<10)
		return '0'+num;
	return num;
};

/**
 * 将日期字符串转成日期
 * fmt：yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd
 */
DateUtil.parseDate = function(str,fmt){
	if(!str){
		return null;
	}
	var date;
	var year=0;
	var month=0;
	var day=0;
	var hour=0;
	var minute=0;
	var second=0;
	var tempStrs = str.split(' ');
	if(tempStrs[0]){
		var dateStrs = tempStrs[0].split("-");
		year = parseInt(dateStrs[0], 10);
		month = parseInt(dateStrs[1], 10) - 1;
		day = parseInt(dateStrs[2], 10);
	}
	if(tempStrs[1]){
		var timeStrs = tempStrs[1].split(":");
		hour = parseInt(timeStrs [0], 10);
		minute = parseInt(timeStrs[1], 10);
		second = parseInt(timeStrs[2], 10);
	}
	
	if(fmt=='yyyy-MM-dd'){
		date = new Date(year, month, day);
		return date;
	}
	else if(fmt=='yyyy-MM-dd HH:mm:ss'){
		date = new Date(year, month, day, hour, minute, second);
		return date;
	}
	return null;
};
/**
 * 获取指定日期最后一天日期
 */
DateUtil.getLastDate = function(date){
	date = arguments[0] || new Date();
	var newDate = new Date(date.getTime());
	newDate.setMonth(newDate.getMonth() + 1);
	newDate.setDate(1);
    var time = newDate.getTime() - 24 * 60 * 60 * 1000;
    newDate = new Date(time);
    return newDate;
};
/**
 * 获取指定日期第一天日期
 */
DateUtil.getFirstDate = function(date){
	date = arguments[0] || new Date();
	var newDate = new Date(date.getTime());
	newDate.setDate(1);
    return newDate;
};

/**
 * 获取指定日期属于星期几
 * date:js  Date对象
 */
DateUtil.getWeekDay = function(date){
	
    return DateUtil.chineseWeek[date.getDay()];
};

/**
 * 日期计算  
 * @param strInterval string  可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒  
 * @param num int
 * @param date Date 日期对象
 * @return Date 返回日期对象
 */
DateUtil.dateAdd = function(strInterval, num, date){
    date =  arguments[2] || new Date();
    switch (strInterval) {
        case 's' :return new Date(date.getTime() + (1000 * num));  
        case 'n' :return new Date(date.getTime() + (60000 * num));  
        case 'h' :return new Date(date.getTime() + (3600000 * num));  
        case 'd' :return new Date(date.getTime() + (86400000 * num));  
        case 'w' :return new Date(date.getTime() + ((86400000 * 7) * num));  
        case 'm' :return new Date(date.getFullYear(), (date.getMonth()) + num, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());  
        case 'y' :return new Date((date.getFullYear() + num), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());  
    }  
};

DateUtil.initDateTime = function (fmt,beginTimeId,endTimeId,monthDiff,showClear,hasDefaultVal){
	var beginTimeInput = $('#'+beginTimeId);
	var endTimeInput = $('#'+endTimeId);
	monthDiff = monthDiff||6;
	
	var checkDateTime = function(isBeginChanged){
		var beginTimeVal = beginTimeInput.val();
		var endTimeVal = endTimeInput.val();
		var beginDate = DateUtil.parseDate(beginTimeVal,fmt);
		var endDate = DateUtil.parseDate(endTimeVal,fmt);
		if(isBeginChanged){
			var tempDate = beginDate;
			tempDate.setMonth(beginDate.getMonth()+monthDiff);
			if(tempDate<endDate){
				endTimeInput.val(DateUtil.fomatDate(tempDate,fmt));
			}
		}else{
			var tempDate = endDate;
			tempDate.setMonth(endDate.getMonth()-monthDiff);
			if(tempDate>beginDate){
				beginTimeInput.val(DateUtil.fomatDate(tempDate,fmt));
			}
		}
	};

	var beginTimeChanged = function() {
		var beginTimeVal = beginTimeInput.val();
		if('' != beginTimeVal) {
			var beginTime = DateUtil.parseDate(beginTimeVal,fmt);
			var endTime = DateUtil.parseDate(endTimeInput.val(),fmt);
			
			if(endTime == null || beginTime > endTime) {
				endTime = beginTime;
				endTime.setMonth(beginTime.getMonth() + 1);
				endTimeInput.val(DateUtil.fomatDate(endTime, fmt));
			}
			checkDateTime(true);
		}
	};

	var endTimeChanged = function() {
		var endTimeVal = endTimeInput.val();
		if('' != endTimeVal) {
			var endTime = DateUtil.parseDate(endTimeVal,fmt);
			var beginTime = DateUtil.parseDate(beginTimeInput.val(),fmt);
			if(beginTime == null || beginTime > endTime){
				beginTime = endTime;
				beginTime.setMonth(endTime.getMonth() - 1);
				beginTimeInput.val(DateUtil.fomatDate(beginTime, fmt));
			}
			checkDateTime(false);
		}
	};

	showClear = typeof showClear==undefined?true:showClear;
	if(hasDefaultVal){
		var nowDate = new Date();
		var defalutStartTime = DateUtil.fomatDate(DateUtil.getFirstDate(nowDate),fmt);
		var defalutEndTime = DateUtil.fomatDate(DateUtil.getLastDate(nowDate),fmt);
		beginTimeInput.val(defalutStartTime);
		endTimeInput.val(defalutEndTime);
	}
	beginTimeInput.click(function(){
		WdatePicker({dateFmt:fmt,maxDate:'#F{$dp.$D(\''+endTimeId+'\')}',isShowClear:showClear,onpicked:beginTimeChanged});
	});
	endTimeInput.click(function(){
		WdatePicker({dateFmt:fmt,minDate:'#F{$dp.$D(\''+beginTimeId+'\')}',isShowClear:showClear,onpicked:endTimeChanged});
	});
};


/**
 * 处理分页方法
 * @param formId
 * @param callBack
 */
function pageInfo(formId,callBack){
	
	if($('#'+formId).length==0){
		return;
	}
	$('#'+formId).find('.pages').find('a').each(function(){
		$(this).click(function(){
			var pageNum = $(this).attr('value');
			//判断是否是数字,能否分页
			if(isNumber(pageNum)){
				var curPageInput = $('#'+formId).find('#curPage');
				//当前页和跳转页不相等时进行翻页操作
				if(curPageInput.val()!=pageNum){
					curPageInput.val(parseInt(pageNum));
					callBack?callBack():$.noop();
				}
			}
		});
	});
	
	$('#'+formId).find('.selectPage').find('select[name="pageLimit"]').change(function(){
		var pageNum = $(this).attr('value');
		//判断是否是数字,能否分页
		if(isNumber(pageNum)){
			var curPageInput = $('#'+formId).find('#curPage');
			//当前页和跳转页不相等时进行翻页操作
			if(curPageInput.val()!=pageNum){
				curPageInput.val(1);
				callBack?callBack():$.noop();
			}
		}
	
	});
	
}

/**
 * 全选
 */
function checkAll(self,ckName){
	if($(self).attr('checked')=='checked'){
		$('input[name="'+ckName+'"]').attr('checked','checked');
	}else{
		$('input[name="'+ckName+'"]').removeAttr('checked');
	}
	$('input[name="'+ckName+'"]').each(function(){
		$(this).unbind('click.ck').bind('click.ck',function(){
			var count = $('input[name="'+ckName+'"]').length;
			var ckCount = $('input[name="'+ckName+'"]:checked').length;
			if(ckCount<count){
				$(self).removeAttr('checked');
			}else{
				$(self).attr('checked','checked');
			}
		});
	});
	
}

/**
 * 批量删除操作
 * @param ckName checkbox的name
 * @param delUrl  删除的url
 * @param idsKey  提交到后台存放id字符串的key
 * @returns
 */
function deletes(ckName,delUrl,idsKey,succsssCallBack,faildCallBack){
	var idKey = idsKey?idsKey:'ids';
	var ids = [];
	$('input[name="'+ckName+'"]:checked').each(function(){
		if($(this).attr('checked')=='checked'){
			ids.push($(this).val());
		}
	});
	if(ids.length<=0){
		Dialog.alertInfo('请选择删除的对象');
		return;
	}
	var idStr = ids.join(',');
	submitSave(delUrl,idKey+'='+idStr,succsssCallBack,faildCallBack);
}
/**
 * 批量删除操作
 * @param ckName checkbox的name
 * @param delUrl  删除的url
 * @param idsKey  提交到后台存放id字符串的key
 * @returns
 */
function deleteByArr(ckName,delUrl,idsKey,succsssCallBack,faildCallBack){
	var idKey = idsKey?idsKey:'ids';
	var ids = [];
	$('input[name="'+ckName+'"]:checked').each(function(){
		if($(this).attr('checked')=='checked'){
			ids.push($(this).val());
		}
	});
	if(ids.length<=0){
		Dialog.alertInfo('请选择删除的对象');
		return;
	}
	var params = CommonUtil.converArrToParams(ids,idKey);
	submitSave(delUrl,params,succsssCallBack,faildCallBack);
}

/**
 * 关闭系统公告
 * @param
 * 
 */
function closeTopNotice(id){
	$("#topnotice").remove();
	CommonUtil.setCookie('topnotice',id);
}
/**
 * 页面上的返回按钮
 */
function back(oldUrl){
	if(!oldUrl){
		window.history.go(-1);// 返回历史
	} else{
		window.location.href = oldUrl;
	}
}

/**
 * 跳转到百度地图界面
 */
toBaiDuMap=function(address){
	window.location.href = BASE_PATH + "/common/toMap.do?address="+address;
};
/**
 * 清空查询条件
 * 注:根据id 清空form所有input内容 排除隐藏文本框
 * @return
 */
function formClear(formId){
	var form = $('#'+formId);
	if(form.length>0){
		var radioSet = {};
		form.find('input[type="text"],textarea').each(function(){
			var placeholder = $(this).attr('placeholder');
			if(placeholder){
				$(this).val(placeholder);
			}else{
				$(this).val('');
			}
		});
		form.find('select').each(function(){
			$(this).find('option').removeAttr('selected');
			$(this).find('option:eq(0)').attr('selected','selected');
		});
		form.find('input[type="radio"]').each(function(){
			var name = $(this).attr('name');
			if(name){
				radioSet[name]='';
			}else{
				$(this).removeAttr('checked');
			}
		});
		for(var p in radioSet){
			$('input[name="'+p+'"]').removeAttr('checked');
			$('input[name="'+p+'"]:eq(0)').attr('checked','checked');
		}
		form.find('input[type="checkbox"]').removeAttr('checked');
	}
}

/**
 * 判断对象是否为空
 * @param name obj
 * @param 
 * 
 */
function isEmptyObject(obj){
    for(var n in obj){
    	return false;
    	};
    return true; 
};

/**
 * 阻止事件冒泡
 * @param e js事件对象
 */
function stopBubble(e){ 
	if (e && e.stopPropagation){
		e.stopPropagation();
	}
	else{
		window.event.cancelBubble=true;
	}
}

//阻止浏览器的默认行为 
function stopDefault( e ) {
    //阻止默认浏览器动作(W3C)
    if (e && e.preventDefault ){
    	e.preventDefault();
    }
    //IE中阻止函数器默认动作的方式
    else{
        window.event.returnValue = false;
    }
    return false; 
}

/**
* 通知公告轮播 方法
* containerID:通知的链接容器， 
* intervalTop:每次滚动的距离
* 调用方法： initNotifyInfo("div1", 30);
*/
function initNotifyInfo(boxID, intervalTop){
	var box = document.getElementById(boxID), can = true;
	// 鼠标移上去的时候暂停
	box.onmouseover = function() { can = false; };
	box.onmouseout = function() { can = true; };
	new function() {
		var stop = box.scrollTop % intervalTop == 0 && !can;
		if (!stop){
			box.scrollTop == parseInt(box.scrollHeight / 2) ? box.scrollTop = 0 : box.scrollTop++;
		}
		setTimeout(arguments.callee, box.scrollTop % intervalTop ? 10 : 1500);
	};
}

var CommonUtil = {};

/**
 * 写cookie值
 * @param name
 * @param value
 * @param time 过期时间，单位ms
 */
CommonUtil.setCookie = function(name,value,time){
	var period = time;
	if(!time){
		period = 30*24*60*60*1000;
	}
    var exp = new Date();
    exp.setTime(exp.getTime() + period);
    document.cookie = name + "="+ escape (value) + ";Path=/;expires=" + exp.toGMTString();
};

/**
 * 取得cookie值
 * @param name
 * @returns
 */
CommonUtil.getCookie = function(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg)){
    	return unescape(arr[2]);
    }
    return null;
};
/**
 * 通用工具方法
 * 获取一个object数组中某个字段的数组或者请求参数的字符串
 * objArr:[{id:"123",name:""},{id:"456",name:""}]
 * ||
 * \/
 * [123,456]
 */
CommonUtil.getFieldArrFromObjArr = function(objArr,fieldName,isConvertToparam,paramName){
	var arr = [];
	var params = '';
	paramName = paramName||'id';
	if(objArr && objArr.push){
		for(var i=0;i<objArr.length;i++){
			var f = objArr[i][fieldName];
			if(f != undefined){
				arr.push(f);
				params+='&'+paramName+'='+f;
			}
		}
	}
	if(isConvertToparam){
		return params;
	}
	return arr;
};

/**
 * 把数组转换成提交的参数
 */
CommonUtil.converArrToParams = function(arr,paramName){
	var params = '';
	if(!arr || !arr.push){
		return params;
	}
	paramName=paramName||'id';
	for(var i=0;i<arr.length;i++){
		var f = arr[i];
		if(f!=undefined){
			params += '&'+paramName+'='+f;
		}
	}
	return params;
};

// 字符串添加自定义方法
String.prototype.endWith = function(str) {
	if (str == null || str == '' || this.length == 0
			|| str.length > this.length) {

		return false;
	}
	if (this.substring(this.length - str.length) == str) {

		return true;
	}
	return false;
};
String.prototype.startWith = function(str) {
	if (str == null || str == '' || this.length == 0
			|| str.length > this.length) {

		return false;
	}
	if (this.substr(0, str.length) == str) {
		return true;

	}
	return false;
};
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, '');
};
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/g, '');
};
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/g, '');
};
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo) && typeof reallyDo == 'string') {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
};
String.prototype.getBytesLength = function() {
	var totalLength = 0;
	var charCode;
	for (var i = 0; i < this.length; i++) {
		charCode = this.charCodeAt(i);
		if (charCode < 0x007f) {
			totalLength++;
		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
			totalLength += 2;
		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
			totalLength += 3;
		} else {
			totalLength += 4;
		}
	}
	return totalLength;
};
//------------------------------ie 的textArea 控制输入长度jquery插件------------------------------
(function($) {
	
	// 点击筛选框外面的时候，隐藏筛选框
	document.addEventListener('touchend', function(e) { 
		//浏览器兼容
		event = e||event;
		source = event.srcElement||event.target;
		
		// 首页头图右边图标初始化newfoot-left-icon
		if($(source).parents('.rightmenu').length<=0 && !$(source).hasClass("head-right-icon") && $(source).parents('.head-right-icon').length <= 0) { 
			$("#rightmenu-mc").addClass("hide");
		}else if($(source).parents('.head-right-icon').length > 0 || $(source).hasClass("head-right-icon")){
			$("#rightmenu-mc").removeClass("hide");
		};
		
		// 底部左下角图标初始化
		if($(source).parents('.newfoot-left-icon').length<=0 && !$(source).hasClass("newfoot-left-icon") && $(source).parents('#avtivity_nav').length<=0 && !$(source).hasClass("newindex-nav")) { 
			$("#avtivity_nav").addClass("hide");
		}
		
		// 场地列表下拉框初始化
		if($(source).parents('.search-parent-list').length <= 0 && $(source).parents('.search-detail').length <= 0) { 
			$(".search-detail").addClass("hide");
			$("#space_mc").addClass("hide");
		};
	});
	
	
	$.fn.textarealimit = function() {
		var maxLength = $(this).attr('maxlength');
		if(!maxLength){
			return;
		}
		if($.browser.msie){
			$(this).unbind('keydown', doKeydown).unbind('keypress', doKeypress).unbind('beforepaste', doBeforePaste).unbind('paste', doPaste);
			$(this).bind('keydown', doKeydown).bind('keypress', doKeypress).bind('beforepaste', doBeforePaste).bind('paste', doPaste);
		}
		function doKeypress() {
			var oTR = document.selection.createRange();
			if (oTR.text.length >= 1){
				event.returnValue = true;
			}
			else if (this.value.length > maxLength - 1){
				event.returnValue = false;
			}
		}
		function doKeydown() {
			var _obj = this;
			setTimeout(function() {
				if (_obj.value.length > maxLength - 1) {
					var oTR = window.document.selection.createRange();
					oTR.moveStart('character', -1 * (_obj.value.length - maxLength));
					oTR.text = '';
				}
			}, 1);
		}
		function doBeforePaste() {
			event.returnValue = false;
		}
		function doPaste() {
			event.returnValue = false;
			var oTR = document.selection.createRange();
			var iInsertLength = maxLength - this.value.length + oTR.text.length;
			var sData = window.clipboardData.getData('Text').substr(0,iInsertLength);
			oTR.text = sData;
		}
	};
})(jQuery);
