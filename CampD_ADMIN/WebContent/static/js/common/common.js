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
	if(url.indexOf("?")>0){
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
	if(url.indexOf("?")>0){
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
/**
 * ajax load资源到指定元素上
 * @param boxId
 * @param url
 * @param param
 * @param callBack
 */
function ajaxLoad(boxId,url,param,callBack){
	if($('#'+boxId).length<=0){
		return ;
	}
	$('#'+boxId).load(url,param,function(){
		//表单聚焦验证
		Validator.onblur($(document));
		//绑定全局的时间
		GlobalEvent.init();
		if(callBack){
			callBack();
		}
	});
}
function submitSearch(formId,boxId,url,callBack){
	systemLoading('#'+boxId,true);
	if(url.indexOf("?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}
	//没有form的时候
	if(!$('#'+formId).attr('id')){
		$('#'+boxId).load(url,function(){
			systemLoaded('#'+boxId);
			//表单聚焦验证
			Validator.onblur($(document));
			//绑定全局的时间
			GlobalEvent.init();
			if(callBack){
				callBack();
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
		target:'#'+boxId, //结果显示目标//
		url:url, //action目标
		type: 'POST',
		success:function(){
			systemLoaded('#'+boxId);
			//表单聚焦验证
			Validator.onblur($(document));
			//绑定全局的时间
			GlobalEvent.init();
			if(callBack){
				callBack();
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
	},
	maxZindex : 1040//时刻记录页面最大的z-index，然后保证最近打开总是在最上面
};

Dialog.alertErrorCodeMsg = function(returnCode){
	var msg = Dialog.systemCode[returnCode];
	if(msg){
		Dialog.alertError(msg);
	}
};

Dialog.alertInfo = function(ctt,autoClose){
	var $infoDialog = $('#sysInfoDialog');
	if($infoDialog.length<=0){
		$infoDialog = $('<div class="modal fade" id="sysInfoDialog" tabindex="-1" role="dialog" aria-labelledby="sysInfoLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title" id="sysInfoLabel">提示</h4></div><div class="modal-body"><i class="fa fa-info icon"></i><div class="e-text" id="sysInfoMsg">提示信息</div></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">确定</button></div></div></div></div>');
		$('body').append($infoDialog);
	}
	$infoDialog.find('#sysInfoMsg').empty().html(ctt?ctt:'');
	if(autoClose){
		$infoDialog.find('.modal-header').hide();
		setTimeout(function(){
			$infoDialog.modal('hide');
		},1000);
	}else{
		$infoDialog.find('.modal-header').show();
	}
	$infoDialog.modal('show');
};

Dialog.alertSuccess = function(ctt){
	var des = ctt?ctt:'操作成功';
	var $successDialog =$('#sysSuccDialog');
	if($successDialog.length<=0){
		$successDialog = $('<div class="modal fade" id="sysSuccDialog" tabindex="-1" role="dialog" aria-labelledby="sysSuccLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body"><i class="fa fa-check-circle icon green"></i><div class="e-text green" id="sysSuccMsg">您操作成功</div></div></div></div></div>');
		$('body').append($successDialog);
	}
	$successDialog.find('#sysSuccMsg').empty().html(des);
	$successDialog.modal('show');
	setTimeout(function(){
		$successDialog.modal('hide');
	},1000);
};

Dialog.alertError = function(ctt){
	var des = ctt?ctt:'操作失败';
	var $errorDialog =$('#sysErrorDialog');
	if($errorDialog.length<=0){
		var $errorDialog = $('<div class="modal fade" id="sysErrorDialog" tabindex="-1" role="dialog" aria-labelledby="sysErrorLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title" id="sysErrorLabel">错误</h4></div><div class="modal-body"><i class="fa fa-times icon red"></i><div class="e-text red" id="sysErrorMsg">操作失败</div></div><div class="modal-footer"><button type="button" class="btn btn-default" data-dismiss="modal">关闭</button></div></div></div></div>');
		$('body').append($errorDialog);
	}
	$errorDialog.find('#sysErrorMsg').empty().html(des);
	$errorDialog.modal('show');
};

/**
 * 确认
 * @param content
 * @param yes
 * @param no
 * @param parent
 */
Dialog.confirm = function(ctt,yes,no){
	var $comfirmDialog =$('#sysConfirmDialog');
	if($comfirmDialog.length<=0){
		var $comfirmDialog = $('<div class="modal fade" id="sysConfirmDialog" tabindex="-1" role="dialog" aria-labelledby="sysConfirmLabel" aria-hidden="false"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button><h4 class="modal-title" id="sysConfirmLabel">确认</h4></div><div class="modal-body"><i class="fa fa-question-circle icon orange2"></i><div class="e-text orange2" id="sysConfirmMsg">?</div></div><div class="modal-footer"><button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button><button type="button" class="btn btn-primary confirm">确定</button></div></div></div></div>');
		$('body').append($comfirmDialog);
	}
	$comfirmDialog.find('#sysConfirmMsg').empty().html(ctt?ctt:'');
	$comfirmDialog.modal('show');
	// 取消
	$comfirmDialog.find(".cancel").click(function(){
		no?no():$.noop();
		$comfirmDialog.modal('hide');
	});
	// 确认
	$comfirmDialog.find(".confirm").click(function(){
		yes?yes():$.noop();
		$comfirmDialog.modal('hide');
	});
};

// 先隐藏然后模态框，然后从body中remove掉没用的弹出框
Dialog.close = function(id){
	$('#'+id).remove();
};

/**
 * ajax异步请求打开弹出框
 * @param url 打开的url地址
 * @param params 参数
 * @param title 标题
 * @param modalId 弹出框的id
 * @param ajaxCallback ajax回调函数 callback(dialog,resData)
 * @param dialogOptions dialog插件的dialog传入参数对象{width:123,height:456...}
 */
Dialog.ajaxOpenDialog = function(url,params,modalId,ajaxCallback,dialogOptions){
	systemLoading(null,true);
	dialogOptions = dialogOptions ||{};
	
	var random = Math.floor(Math.random() * 10001);
  	var rd = (new Date().getTime()*random).toString();
	
	var $ajaxDialog = $('<div id="' + rd +'">');
	$('body').append($ajaxDialog);
	
	if(url.indexOf("?")>0){
		url = url+"&"+rnd();
	}else{
		url = url+"?"+rnd();
	}

	$ajaxDialog.load(url,params,function(){
		systemLoaded();
		Dialog.maxZindex += 2;
		$('#'+modalId).css("z-index",Dialog.maxZindex);
		$('#'+modalId).modal('show');
		if(ajaxCallback){
			ajaxCallback();
		};
		$('#'+modalId).on('hidden.bs.modal', function (e) {
			Dialog.close(rd);
		});
		$('#'+modalId).on('shown.bs.modal', function (e) {
			Validator.onblur($ajaxDialog);
		});
		
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
	return ;
	var isBodySel = selector || true;
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
		$(this).addClass('pos_rel');
		//loading层控制
		var dataLoadingDiv = $(selector+' > .sys_loading');
		if(dataLoadingDiv.length<=0){
			dataLoadingDiv = $('<div class="sys_loading"><img src="'+LOC_RES_BASE_PATH+'/web/meta/images/loading.gif"><span class="sys_loading_des">'+(desc?desc:'加载中...')+'</span></div>');
			$(this).append(dataLoadingDiv);
		}
		//修改样式位置
		var loadingWidth = $(selector+' > .sys_loading').width();
		var leftPencent = parseInt(((width/2.0-loadingWidth/2.0)/width)*100);
		
		var loadingHeight = $(selector+' > .sys_loading').height();
		var topPencent = isBodySel?parseInt(((height/2.0-loadingHeight/2.0)/height)*100):20;
		dataLoadingDiv.css({left:leftPencent+'%',top:topPencent+'%','z-index':ldgMaskIndex+8});
		
		//控制显示容器最小高度
		if(loadingHeight>height){
			$(this).addClass('sysLoadingMinHeight');
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
	return ;
	selector = selector || 'body';
	var container = $(selector);
	container.each(function(){
		$(this).removeClass('sysLoadingMinHeight').removeClass('pos_rel');
		$(selector+' > .sys_loading').hide();
		$(selector+' > .sys_masklock').hide();
	});
}

//////////////////////////ajax 初始化全局共用信息/////////////////////////////
$(function(){
	//表单聚焦验证
	Validator.onblur($(document));
	//绑定全局的时间
	GlobalEvent.init();
	//全局菜单设定
	GlobalMenu.init();
    $(document).ajaxSuccess(function(event, XMLHttpRequest, ajaxOptions){
        //此处有bug。 应该直接判断返回结果是否是json，而不是通过dataType来判断。有可能dataType会省略。
         if (ajaxOptions.dataType=='json'&&XMLHttpRequest!=null){
            var rs = eval('('+XMLHttpRequest.responseText+')');
            if(rs.returnType=='0'){
            	//如果returnCode=200，表示操作正常完成
                if (rs.returnCode=='200'){
                	rs.returnMsg=rs.returnMsg?rs.returnMsg:'操作成功';
                	Dialog.alertSuccess(rs.returnMsg);
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
GlobalMenu={};
GlobalMenu.init = function(){
	//需要初始化的菜单
	GlobalMenu.mainMenuInit();
	
};
/**
 * 主菜单初始化
 */
GlobalMenu.mainMenuInit = function(){
	//左边菜单选中
	if(typeof leftMenuSelectId!='undefined' && leftMenuSelectId){
		$('li[id^="leftMenu"]').removeClass('active open');
		var firstMenuId = leftMenuSelectId.substr(0,4)+'00';
		//当前左侧菜单是一级菜单
		if(leftMenuSelectId == firstMenuId){
			$('#leftMenu'+leftMenuSelectId).addClass('active');
		}
		//选中2级菜单
		var subMenuLi = $('li[name="subMenu'+firstMenuId+'"]');
		if(subMenuLi.length>0){
			$('#leftMenu'+firstMenuId).addClass('open');
			$('#leftMenu'+leftMenuSelectId).addClass('active');
		}else{
			//没有二级子菜单
			$('#leftMenu'+firstMenuId).find('b').removeClass('fa fa-angle-down');
			$('#leftMenu'+firstMenuId).find('.submenu').remove();
		}
	}
	//导航信息生成
	if(typeof navigationItems !='undefined' && navigationItems){
		var navItems = $.parseJSON(navigationItems);
		if(navItems && navItems.length>0){
			var html = '';
			for ( var index in navItems) {
				var href = navItems[index].href;
				var name = navItems[index].name;
				href = href?href:'javascript:void(0);';
				name = name?name:'';
				var tag = '<li>';
				if(name){
					tag += '<a href="'+href+'">'+name+'</a>';
				}
				tag+='</li>';
				html+=tag;
			}
			$("#sysBreadCrumb").append(html);
			$("#sysBreadCrumb").find('li:last').addClass('active');
		}
	}
};
/**
 * 折叠菜单
 */
GlobalMenu.foldMenu = function (secondMenuId){
	//当前二级菜单下所有三级菜单的div hide的css类名切换
	$('#subMenu'+secondMenuId).toggleClass('hide');
};
/**
 * 全局事件
 */
var GlobalEvent = {};
GlobalEvent.init = function(){
	//输入框文本初始化事件
	GlobalEvent.inputInit();
	
};

/**
 * 文本框默认文字及鼠标聚焦失去焦点事件绑定
 * ajax load需要手动绑定
 */
GlobalEvent.inputInit = function(){
	$('input[placeholder],textarea[placeholder]').each(function(){
		var type = $(this).attr('type');
		if(type && type.toLowerCase()=='password'){
			return;
		}
		if(!$(this).val()){
			$(this).val($(this).attr("placeholder"));
			$(this).addClass("placeholder");
		}
		
		
		$(this).unbind('focus.placeholder');
		$(this).unbind('blur.placeholder');
		$(this).bind('focus.placeholder',function(){
			if($(this).val()==$(this).attr("placeholder")){
				$(this).val('');
				$(this).removeClass("placeholder");
			}
		});
		$(this).bind('blur.placeholder',function(){
			if(!$(this).val()){
				$(this).val($(this).attr("placeholder"));
				$(this).addClass("placeholder");
			}
			if($(this).val()==$(this).attr("placeholder")){
				$(this).addClass("placeholder");
			}
		});
	});
};

var View = {};
View.viewMsg = function(id){
	Dialog.ajaxOpenDialog(BASE_PATH+'/production/common/view/'+id,null,null,null,{
		cancel:true,
		cencelVal:'取消'
	});
	
};

/////////////////////////////////////////////////////////////////

/********************************js时间工具*****************************************/
DateUtil = {};

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

/**
 * 初始化时间选择
 * 开始时间为
 *
 */
DateUtil.initDatePicker = function(){
	
	var currentDay = new Date();
	var startDate = DateUtil.getFirstDate();
	
	$('.input-daterange-startDate').datetimepicker({
		format: 'YYYY-MM-DD',// 如果需要添加时间，请使用：YYYY-MM-DD HH:MM:SS
		// defaultDate: startDate, // 默认显示的时间
		use24hours: true, // 是否启用24小时制
        useSeconds: true // 是否启用秒选择
       // maxDate: currentDay // 最大可选择时间
	});
	
	$('.input-daterange-endDate').datetimepicker({
		format: 'YYYY-MM-DD',// 如果需要添加时间，请使用：YYYY-MM-DD HH:MM:SS
		// defaultDate: currentDay, // 默认显示的时间
		use24hours: true, // 是否启用24小时制
        useSeconds: true // 是否启用秒选择
       // maxDate: currentDay // 最大可选择时间
	});
};

/**
 * 初始化时间选择 不显示默认时间
 * 开始时间为
 *
 */
DateUtil.initDateChoose = function(){
	
	$('.input-daterange-startDate').datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss',// 如果需要添加时间，请使用：YYYY-MM-DD HH:mm:ss
		use24hours: true, // 是否启用24小时制
        useSeconds: true // 是否启用秒选择
       // maxDate: currentDay // 最大可选择时间
	});
	
	$('.input-daterange-endDate').datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss',// 如果需要添加时间，请使用：YYYY-MM-DD HH:mm:ss
		use24hours: true, // 是否启用24小时制
        useSeconds: true // 是否启用秒选择
       // maxDate: currentDay // 最大可选择时间
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
	$('#'+formId).find('a[name="sysPageBtn"]').each(function(){
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
 * 下载文件
 * @param name 下载文件名 测试
 * @param path  文件路径  /test/test2/test.txt
 * 
 */
function downLoadFile(name,path){
	var params = 'fileName='+encodeURIComponent(name);
	params+='&filePath='+encodeURIComponent(path);
	window.open(BASE_PATH+'/common/downLoad!file.do?'+params);
}

/**
 * 查看上传进度信息
 * @param progressId 进度条元素ID
 * @param trackerId 上传跟踪ID
 * @param fileIndex 文件索引，第几个上传文件
 */
function getUploadProgress(trackerId,fileIndex,doProgress){
	if(fileIndex == null || undefined == fileIndex)
		fileIndex = 0;
	var params = {
		trackerId : trackerId	
	};
	var url = BASE_PATH + "/common/upload!getProgress.do";
	submitSave(url,params,function(data){
		var returnValue = data.returnValue;
		if(returnValue && returnValue.length > 0){
			var uploadInfo = returnValue[fileIndex];
			doProgress(uploadInfo);
		}
	});
}

/**
 * 阻止事件冒泡
 * @param e js事件对象
 */
function stopBubble(e){ 
	if (e && e.stopPropagation){
		e.stopPropagation();
	}
	else{
		window.event.cancelBubble=true 
	}
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

//换一张
function changeRandCode(code) {
	if(code !='' && code !=null){
		$("#randCode_" + code).attr("src", BASE_PATH + "/web/page/common/code.jsp?" + rnd()+"&codeKey=REPLY_CODE_"+code);
	}else{
		$("#randCode").attr("src", BASE_PATH + "/web/page/common/code.jsp?" + rnd());
	}
	
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
