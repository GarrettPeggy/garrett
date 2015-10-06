//////////////////////////表单验证/////////////////////////////
Validator = {};

/**
 * 表单元素验证
 * 
 * @param jqueryObj   需要验证的jquery对象(文本框或者文本域等)
 * @param needFocus
 *            验证不通过是否聚焦到验证的对象上 notnull:非空验证 maxlength:字符串最大长度 minlength:字符串最小长度
 *            datatype:数据类型包含有 posNumWithZero,date,email,normal 此字段可以拓展
 * 验证性表单元素提示信息规则有如下:
 * 	<div class="form-group has-error">
		<label for="inputError" class="col-xs-12 col-sm-3 col-md-3 control-label no-padding-right">Input with error</label>
		<div class="col-xs-12 col-sm-5">
			<span class="block input-icon input-icon-right">
				<input type="text" id="inputError" class="width-100">
				<i class="ace-icon fa fa-times-circle"></i>
			</span>
		</div>
		<div class="help-block col-xs-12 col-sm-reset inline"> Error tip help! </div>
	</div>
 */
Validator.validElement = function(jqueryObj, needFocus) {

	// 获取验证的属性
	var notnull = jqueryObj.attr('notnull');
	var maxlen = jqueryObj.attr('maxlength');
	var minlen = jqueryObj.attr('minlength');
	var dataTypes = jqueryObj.attr('datatype');
	var pwdCompare = jqueryObj.attr('pwdCompare');
	var elmentId = jqueryObj.attr("id");//文本框的id
	var inputType = jqueryObj.attr("type")||'';//控件的type

	// 有需要添加的验证必须添加至此判断
	if (!notnull && !maxlen && !minlen && !dataTypes) {
		return true;
	}

	// 验证表单控件的label获取
	var label = Validator.getCaption(elmentId);

	//提示图标
	var icon = jqueryObj.parent().find(".ace-icon");
//	var formGroup = jqueryObj.parent('.from-group');
	var formGroup = jqueryObj.parent().parent().parent();
	var tip = formGroup.find('.help-block');
	
	if(icon.length==0){
		icon = $('<i class="ace-icon fa" style="display:none;"></i>');
		jqueryObj.parent().append(icon);
	}
	if(tip.length==0){
		tip = $('<div class="help-block col-xs-12 col-sm-reset inline" style="display:none;"></div>');
		formGroup.append(tip);
	}
	
	//清空提示信息
	tip.empty();
	
	//去掉默认信息
	if (jqueryObj.attr("placeholder") == jqueryObj.val()) {
		jqueryObj.val('');
	}
	formGroup.removeClass('has-warning has-error has-success has-info');
	// 校验为空的情况
	if (notnull == 'true') {
		if(inputType.toLowerCase != 'radio' && inputType.toLowerCase != 'checkbox'){
			if (!jqueryObj.val() || $.trim(jqueryObj.val()) == '') {
				formGroup.addClass('has-error');
				icon.attr('class','ace-icon fa fa-times-circle').show();
				tip.empty().html(label+'不能为空').show();
				needFocus ? jqueryObj.focus() : $.noop();
				return false;
			}
		}else{
			var name = jqueryObj.attr('name');
			if($('input[name="'+name+'"]:checked').length<=0){
				formGroup.addClass('has-error');
				icon.attr('class','ace-icon fa fa-times-circle').show();
				tip.empty().html(label+'必须选择!').show();
				needFocus ? jqueryObj.focus() : $.noop();
				return false;
			}
		}
	}
	
	// 最小长度
	if (minlen && minlen > 0) {
		if (jqueryObj.val().length < minlen) {
			formGroup.addClass('has-error');
			icon.attr('class','ace-icon fa fa-times-circle').show();
			tip.empty().html(label+'长度不能小于' + minlen).show();
			needFocus ? jqueryObj.focus() : $.noop();
			return false;
		}
	}
	
	// 最大长度
	if (maxlen && maxlen > 0) {
		if (jqueryObj.val().length > maxlen) {
			formGroup.addClass('has-error');
			icon.attr('class','ace-icon fa fa-times-circle').show();
			tip.empty().html(label+'长度不能大于' + maxlen).show();
			needFocus ? jqueryObj.focus() : $.noop();
			return false;
		}
	}
	
	//确认密码与新密码比较
	if (pwdCompare && $('#'+pwdCompare).val() != jqueryObj.val()) {
		formGroup.addClass('has-error');
		icon.attr('class','ace-icon fa fa-times-circle').show();
		tip.empty().html('两次确认密码不一致！').show();
		needFocus ? jqueryObj.focus() : $.noop();
		return false;
	}
	
	// 类型
	if (dataTypes) {
		var dataTypeArr = dataTypes.split(',');
		for(var i=0;i<dataTypeArr.length;i++){
			var dataType = dataTypeArr[i];
			if(!dataType){
				continue;
			}
				// 非负整数
			if (dataType == 'posNumWithZero') {
				if (!isPosNumWithZero(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '必须为非负整数!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 日期
			} else if (dataType == 'date') {
				if (!isDate(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '日期格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// EMAIL	
			} else if (dataType == 'email') {
				if (!isEmail(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 普通字符	
			} else if (dataType == 'normal') {
				if (!isNormal(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '不能含非法字符!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 手机号	
			} else if (dataType == 'phone') {
				if (!isPhone(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				//手机号or邮箱 
			} else if (dataType == 'phoneOrEmail') {
				if (!isPhone(jqueryObj.val()) && !isEmail(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				//只能包含字母数字
			} else if (dataType == 'onlyNumOrLetter') {
				
				if (!isNumOrLetter(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '只能包含字母数字!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				
			} else if (dataType == 'number') {// 证书类型
				
				if (!isNumber(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '只能是正整数!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				//固定电话
			} else if (dataType == 'tel') {
				
				if (!isTel(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				//Money
			}else if (dataType == 'money') {
				
				if (!isMoney(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
			}
		}
	}
	formGroup.removeClass('has-warning has-error has-info').addClass('has-success');
	icon.attr('class','ace-icon fa fa-check-circle').show();
	tip.hide();
	return true;
};

/**
 * 验证整个表单 表单属性说明: notnull:非空验证 maxlength:字符串最大长度 minlength:字符串最小长度
 * datatype:数据类型包含有 posNumWithZero,date,email,normal 此字段可以拓展(多种数据类型可以使用,分割)
 */
Validator.validForm = function(formId) {
	var isValid = true;
	$("form[id=" + formId + "]:last input:visible,textarea:visible,select:visible").each(function(i) {
		if (!Validator.validElement($(this), true)) {
			isValid = false;
			return false;
		}
	});
	return isValid;
};

// 根据输入框的id,来取得其名称
Validator.getCaption = function(id) {
	var str = '';
	var ob = $('form label[for="' + id + '"]');
	if(ob.length>1){
		ob = $(ob[0]);
	}
	if (ob) {
		str = ob.text().replace('：', '').replace(':', '').replace('\*', '');
	}
	if (!str) {
		return '';
	}
	return str;
};

/**
 * 表单绑定onblur验证
 * 
 * @param jqueryObj jquery对象
 *            
 */
Validator.onblur = function(jqueryObj) {
	jqueryObj.find("input:visible,textarea:visible,select:visible").unbind('blur.validator').bind('blur.validator', function() {
		Validator.validElement($(this), false);
	});
};


function isUrl(str){
	if (str == '') {
		return true;
	}
	var reg=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
	var objExp=new RegExp(reg);
	if(objExp.test(str)==true){
		return true;
	}else{
		return false;
	}
} 
/**
 * 校验字符串是否为email
 * 
 */
function isEmail(str) {
	if (str == '') {
		return true;
	}

	if (str.charAt(0) == "." || str.charAt(0) == "@" || str.indexOf('@', 0) == -1 || str.indexOf('.', 0) == -1
			|| str.lastIndexOf("@") == str.length - 1 || str.lastIndexOf(".") == str.length - 1) {
		return false;

	} else
		return true;
}

/**
 * 判断是否是日期
 */
function isDate(date, fmt) {
	if (fmt == null) {
		fmt = "yyyy-MM-dd";
	}
	var yIndex = fmt.indexOf("yyyy");
	if (yIndex == -1) {
		return false;
	}
	var year = date.substring(yIndex, yIndex + 4);
	var mIndex = fmt.indexOf("MM");
	if (mIndex == -1) {
		return false;
	}
	var month = date.substring(mIndex, mIndex + 2);
	var dIndex = fmt.indexOf("dd");
	if (dIndex == -1) {
		return false;
	}
	var day = date.substring(dIndex, dIndex + 2);
	if (!isPosNumWithOutZero(year) || year > 2100 || year < 1900) {
		return false;
	}
	if (!isPosNumWithOutZero(month) || month > 12 || month < 01) {
		return false;
	}
	if (day > getMaxDay(year, month) || day < 01) {
		return false;
	}
	return true;
}

//**********整数、正整数、负整数、非负整数 、 小数--begin***************************************************//



/*********************************************判断整数***************************************/

/*
*  判断是否是非负整数  （0,1,2......）
*/
function isPosNumWithZero(s){
	
	if (s == '') {
		return true;
	}
	
    var regu = "^(0|\\+?[1-9][0-9]*)$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是大于零的正整数 不包括0  从  1,2,.....
*/
function isPosNumWithOutZero(s){
	
	if (s == '') {
		return true;
	}
	
    var regu = "^\\+?[1-9][0-9]*$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是小于于等于零的负整数 0,-1,-2
*/
function isNegNumWithZero(s){
	
	if (s == '') {
		return true;
	}
	
    var regu = "^(0|-[1-9][0-9]*)$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是小于零的负整数 -1,-2....
*/
function isNegNumWithOutZero(s){
	
	if (s == '') {
		return true;
	}
	
    var regu = "^-[1-9][0-9]*$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是整数...-2,-1,0,1,2....
*/
function isNumber(s){
	
	if (s == '') {
		return true;
	}
	
    var regu = "^(0|[-+]?[1-9][0-9]*)$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*********************************************判断小数***************************************/

/*
*  判断是否是大于等于零的正小数
*/
function isPosDecWithZero(s,c){
	
	if (s == '') {
		return true;
	}
	
    var regu ;
    var regx ;
    if(c>1){
        regu = "^(0|\\+?[0-9]*\\.\\d{1,"+ c +"})$";
        regx = "^(\\+?0[0-9][0-9]*\\.\\d{1,"+ c +"})$";
    }
    else{
        regu = "^(0|\\+?[0-9]*\\.\\d{1})$";
        regx = "^(\\+?0[0-9][0-9]*\\.\\d{1})$";
    }    
	var re = new RegExp(regu);
    var rx = new RegExp(regx);
	if (re.test(s)&&!rx.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是大于零的正小数
*/
function isPosDecWithOutZero(s,c){
	
	if (s == '') {
		return true;
	}
	
    var regu ;
    var regx ;
    if(c>1){
        regu = "^(\\+?[0-9]*\\.\\d{1,"+ c +"})$";
        regx = "^(\\+?0[0-9][0-9]*\\.\\d{1,"+ c +"})$";
    }
    else{
        regu = "^(\\+?[0-9]*\\.\\d{1})$";
        regx = "^(\\+?0[0-9][0-9]*\\.\\d{1})$";
    }    
	var re = new RegExp(regu);
    var rx = new RegExp(regx);
	if (re.test(s)&&!rx.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是小于等于零的负小数
*/
function isNegDecWithZero(s,c){
	
	if (s == '') {
		return true;
	}
	
    var regu ;
    var regx ;
    if(c>1){
        regu = "^(0|-[0-9]*\\.\\d{1,"+ c +"})$";
        regx = "^(-0[0-9][0-9]*\\.\\d{1,"+ c +"})$";
    }
    else{
        regu = "^(0|-[0-9]*\\.\\d{1})$";
        regx = "^(-0[0-9][0-9]*\\.\\d{1})$";
    }    
	var re = new RegExp(regu);
    var rx = new RegExp(regx);
	if (re.test(s)&&!rx.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是小于零的负小数
*/
function isNegDecWithOutZero(s,c){
	
	if (s == '') {
		return true;
	}
	
    var regu ;
    var regx ;
    if(c>1){
        regu = "^(-[0-9]*\\.\\d{1,"+ c +"})$";
        regx = "^(-0[0-9][0-9]*\\.\\d{1,"+ c +"})$";
    }
    else{
        regu = "^(-[0-9]*\\.\\d{1})$";
        regx = "^(-0[0-9][0-9]*\\.\\d{1})$";
    }    
	var re = new RegExp(regu);
    var rx = new RegExp(regx);
	if (re.test(s)&&!rx.test(s)) {
		return true;
	} else {
		return false;
	}
}

/*
*  判断是否是小数
*/
function isDecimal(s,c){
	
	if (s == '') {
		return true;
	}
	
    var regu ;
    var regx ;
    if(c>1){
        regu = "^(0|[+-]?[0-9]*\\.\\d{1,"+ c +"})$";
        regx = "^([+-]?0[0-9][0-9]*\\.\\d{1,"+ c +"})$";
    }
    else{
        regu = "^(0|[+-]?[0-9]*\\.\\d{1})$";
        regx = "^([+-]?0[0-9][0-9]*\\.\\d{1})$";
    }    
	var re = new RegExp(regu);
    var rx = new RegExp(regx);
	if (re.test(s)&&!rx.test(s)) {
		return true;
	} else {
		return false;
	}
}

//**********************************整数、小数--end***************************************************//

/**
 * 检查是否是Money
 * 
 */
function isMoney(str) {
	if (str == '') {
		return true;
	}

	if (/^\d+($|\.\d+$)/.test(str)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 校验字符串是否为合法电话号码
 * 
 */
function isMobile(str) {
	if (str == '') {
		return true;
	}

	if (/^1\d{10}$/.test(str)) {
		return true;
	} else {
		return false;
	}
}
function getPhoneType(phone){
	var lt_regexp=/^(86|1)(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$|^1709\\d{7}$/;

	var dx_regexp=/^(86|1)(3[3]|5[3]|7[7]|8[01]|8[9])\d{8}$|^1700\\d{7}$/;

	var yd_regexp=/^(86|1)(3[4-9]|5[0-2]|5[4]|5[7-9]|7[8]|8[2|3|4|7|8]|4[7])\d{8}$|^1705\d{7}$/;

	var xl_regexp=/^0\d{2,3}\d{8}$/;
	if (lt_regexp.test(phone)) {
		return 1;
	}
	if (dx_regexp.test(phone)) {
		return 2;
	}
	if (yd_regexp.test(phone)) {
		return 3;
	}
	if (xl_regexp.test(phone)) {
		return 4;
	}
	return -1;
} 
/**
 * 校验字符串是否为合法手机号码
 * 
 */
function isPhone(str) {
	if (str == '') {
		return true;
	}
	if (getPhoneType(str)!=-1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入字符串必须含字母和数字 
 * 
 */
function isNumberOrLetter(s) {

	//中级密码-必须包含两个
	 var containTypeNum = containType(s);
	
	 if(containTypeNum == 2){
	    return true;
	  }else{
	   return false;
	  }
}

//判断密码包含几种类型
function containType(pwd) {

	//
	var reg1 = /[a-zA-Z]+/g;// 字母
	var reg2 = /[0-9]+/g;// 数字
	var reg3 = /[\W+]+/g;// 特殊字符

	var step = 0;
	if (reg1.test(pwd)) {
		step++;
	}
	if (reg2.test(pwd)) {
		step++;
	}
	if (reg3.test(pwd)) {
		step = 0;
	}

	return step;
};

/**
 * 检查输入字符串是否只由英文字母和数字和下划线组成
 * 
 */
function isNumberOr_Letter(s) {

	var regu = "^[0-9a-zA-Z\_]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入字符串是否为空或者全部都是空格
 */
function isEmpty(str) {
	if (str == "")
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}



/**
 * 必需选择校验
 */
function isSelected(S, T) {
	var Obj = eval('document.all[\'' + S + '\']');
	var desc = T + "必须选择!";
	if (typeof (Obj) == "undefined") {
		alert("目标不存在。");
		return true;
	}

	if (Obj.selectedIndex == 0) {
		if (typeof (T) != "undefined") {
			alert(desc);
		}
		Obj.focus();
		return false;
	}
	return true;
}

/**
 * 判断是否是非法字符，即不含特殊字符
 * 
 */
function isIllegal(S, T) {

	var Obj = eval('document.all[\'' + S + '\']');
	var desc = T + "必须为汉字、数字、英文、或者下划线!";
	if (typeof (Obj) == "undefined") {
		alert("目标不存在。");
		return true;
	}
	var str = Obj.value;
	if (str == '') {
		return true;
	}

	var reg = '~!@#$%^&*()+{}|:\'<>?`=[]-/\\';
	for (var i = 0; i < reg.length; i++) {
		if (str.indexOf(reg.charAt(i)) != -1) {
			if (typeof (T) != "undefined") {
				alert(desc);
			}
			Obj.focus();
			return true;
		}
	}
	return false;
}

/**
 * 判断是否是非法字符，即不含特殊字符
 * 
 */
function isNormal(str) {
	if (str == '') {
		return true;
	}
	var reg = '~!@#$%^&*()+{}|:\'<>?`=[]-/\\';
	for (var i = 0; i < reg.length; i++) {
		if (str.indexOf(reg.charAt(i)) != -1) {
			return false;
		}
	}
	return true;
}

/**
 * 判断是否含有html字符
 * 
 */
function isHtmlChar(str) {
	if (str == '') {
		return true;
	}
	var reg = '&\'<>/\\';
	for (var i = 0; i < reg.length; i++) {
		if (str.indexOf(reg.charAt(i)) != -1) {
			return true;
		}
	}
	return false;
}

/**
 * 判断是否是汉字
 * 
 */
function isChinese(str) {
	if (/[^\u4e00-\u9fa5]/.test(str)) {
		return false;
	} else {
		return true;
	}
}

/**
 * 获取月份中最大的一天
 * 
 */
function getMaxDay(year, month) {
	if (month == 4 || month == 6 || month == 9 || month == 11)
		return "30";
	if (month == 2)
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return "29";
		else
			return "28";
	return "31";
}

/**
 * 字符1是否以字符串2结束
 * 
 */
function isLastMatch(str1, str2) {
	var index = str1.lastIndexOf(str2);
	if (str1.length == index + str2.length)
		return true;
	return false;
}

/**
 * 字符1是否以字符串2开始
 * 
 */
function isFirstMatch(str1, str2) {
	var index = str1.indexOf(str2);
	if (index == 0)
		return true;
	return false;
}

/**
 * 字符1是包含字符串2
 * 
 */
function isMatch(str1, str2) {
	var index = str1.indexOf(str2);
	if (index == -1)
		return false;
	return true;
}


/**
 * 只由英文字母或数字组成
 * 
 */
function isNumOrLetter(s) {

	var regu = "^[0-9a-zA-Z]+$";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		return false;
	}
}

/**
 * 验证固定电话号码
 * 0\d{2,3}   代表区号   
	*[0\+]\d{2,3}   代表国际区号
	*\d{7,8} 代表7－8位数字(表示电话号码)
	*正确格式：区号-电话号码-分机号(全写|只写电话号码)
 */
function isTel(str){ 
	
  var reg=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;  
  
  if (str == ""){
	  return true;
  }
		
  if(!reg.test(str)){
     
      return false; 
  } 
  
  return true;
}