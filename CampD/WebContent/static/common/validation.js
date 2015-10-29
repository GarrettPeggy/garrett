//////////////////////////表单验证/////////////////////////////
Validator = {};

/**
 * 表单元素验证
 * 
 * @param jqueryObj   需要验证的jquery对象(文本框或者文本域等)
 * @param needFocus
 *            验证不通过是否聚焦到验证的对象上 notnull:非空验证 maxlength:字符串最大长度 minlength:字符串最小长度
 *            datatype:数据类型包含有 number,integer,date,email,normal,notHtmlChar 此字段可以拓展
 * 验证性表单元素提示信息规则有如下两种:
 * 	1.同行显示,html事例:
 * 	<li>
		<div class="w_label" for="appName"><em>*</em>名称：</div>
		<div class="w1">
			<span class="i_txt">
				<input type="text" class="appName" id="appName" notnull="true" maxlength="7" datatype="normal" size="7" placeholder="" value="">
			</span>
		</div>
		<div class="w2"><span validation="appName" class="txtSmall" style="color: #ef6000;"></span></div>
	</li>
    必备条件:a.标签span必须存在属性for="验证文本框id";b.验证文本框后面必须存在span validation="验证文本框id";
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

	// 默认提示信息的标签(默认的提示信息和文本框同级别)
	var warningSpan = $('form span[validation="' + elmentId + '"]');
	// 验证前重置信息并隐藏
	warningSpan.empty().hide();
	
	//去掉默认信息
	if (jqueryObj.attr("placeholder") == jqueryObj.val()) {
		jqueryObj.val('');
	}
	
	// 校验为空的情况
	if (notnull == 'true') {
		if(inputType.toLowerCase != 'radio' && inputType.toLowerCase != 'checkbox'){
			if (!jqueryObj.val() || $.trim(jqueryObj.val()) == '') {
				warningSpan.html(label + '不能为空!').show();
				needFocus ? jqueryObj.focus() : $.noop();
				return false;
			}
		}else{
			var name = jqueryObj.attr('name');
			if($('input[name="'+name+'"]:checked').length<=0){
				warningSpan.html(label + '必须选择!').show();
				needFocus ? jqueryObj.focus() : $.noop();
				return false;
			}
		}
	}
	
	// 最小长度
	if (minlen && minlen > 0) {
		if (jqueryObj.val().length < minlen) {
			warningSpan.html(label + '长度不能小于' + minlen).show();
			needFocus ? jqueryObj.focus() : $.noop();
			return false;
		}
	}
	
	// 最大长度
	if (maxlen && maxlen > 0) {
		if (jqueryObj.val().length > maxlen) {
			warningSpan.html(label + '长度不能大于' + maxlen + '!').show();
			needFocus ? jqueryObj.focus() : $.noop();
			return false;
		}
	}
	
	//确认密码与新密码比较
	if (pwdCompare && $('#'+pwdCompare).val() != jqueryObj.val()) {
		warningSpan.html('<b></b>两次确认密码不一致！').show();
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
			// 数字
			if (dataType == 'number') {
				if (!isNumber(jqueryObj.val())) {
					warningSpan.html(label + '必须为数字!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 整数
			}else if (dataType == 'integer') {
				if (!isInteger(jqueryObj.val())) {
					warningSpan.html(label + '必须为整数!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 日期
			} else if (dataType == 'date') {
				if (!isDate(jqueryObj.val())) {
					warningSpan.html(label + '日期格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// EMAIL	
			} else if (dataType == 'email') {
				if (!isEmail(jqueryObj.val())) {
					warningSpan.html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 普通字符	
			} else if (dataType == 'normal') {
				if (!isNormal(jqueryObj.val())) {
					warningSpan.html(label + '不能含非法字符!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 手机号	
			} else if (dataType == 'phone') {
				if (!isPhone(jqueryObj.val())) {
					warningSpan.html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
			} else if (dataType == 'idCard') {
				if (!isIdentityCard(jqueryObj.val())) {
					warningSpan.html(label + '格式不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 身份证
			} else if (dataType == 'notHtmlChar') {
				if (isHtmlChar(jqueryObj.val())) {
					warningSpan.html(label + '不能含非法字符!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
				// 手机号	
			}  else if (dataType == 'tel') {
				
				if (!isTel(jqueryObj.val())) {
					formGroup.addClass('has-error');
					icon.attr('class','ace-icon fa fa-times-circle').show();
					tip.empty().html(label + '不正确!').show();
					needFocus ? jqueryObj.focus() : $.noop();
					return false;
				}
			}
			// 有需要验证其他的可以在此添加datatype
		}
	}
	return true;
};

/**
 * 验证整个表单 表单属性说明: notnull:非空验证 maxlength:字符串最大长度 minlength:字符串最小长度
 * datatype:数据类型包含有 number,integer,date,email,normal 此字段可以拓展(多种数据类型可以使用,分割)
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
	var ob = $('form div[for="' + id + '"]');
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
	jqueryObj.find("input:visible,textarea:visible,select:visible").bind('blur.validator', function() {
		Validator.validElement($(this), false);
	});
};



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
	if (!isNumber(year) || year > 2100 || year < 1900) {
		return false;
	}
	if (!isNumber(month) || month > 12 || month < 01) {
		return false;
	}
	if (day > getMaxDay(year, month) || day < 01) {
		return false;
	}
	return true;
}

/**
 * 检查输入字符串是否符合正整数格式
 * 
 */
function isNumber(s) {
	if (s == '') {
		return true;
	}
	
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (s.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入字符串是否是带小数的数字格式,可以是负数
 * 
 */
function isDecimal(str) {
	
	if (isInteger(str)){
		return true;
	}
		
	var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
	if (re.test(str)) {
		
		if (RegExp.$1 == 0 && RegExp.$2 == 0){
			return false;
		}
			
		return true;
		
	} else {
		return false;
	}
}

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
 * 校验字符串是否为整型
 * 
 */
function isInteger(str) {
	if (str == '') {
		return true;
	}

	if (/^(\-?)(\d+)$/.test(str)) {
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
	var lt_regexp=/^(86|1)(3[0-2]|5[56]|8[56]|4[5]|7[6])\d{8}$|^1709\d{7}$/;

	var dx_regexp=/^(86|1)(3[3]|5[3]|7[7]|8[01]|8[9])\d{8}$|^1700\d{7}$/;

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
	var reg = '~!@#$%^&*()+{}|:\'<>?`=[]-/\\ 。；;，,';
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

function isIdentityCard(code) { 
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var pass= true;
    
    if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
        pass = false;
    }
    
   else if(!city[code.substr(0,2)]){
        pass = false;
    }
    else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                pass =false;
            }
        }
    }
    return pass;
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

/**
 * 验证固定电话号码
 * 0\d{2,3}   代表区号   
	*[0\+]\d{2,3}   代表国际区号
	*\d{7,8} 代表7－8位数字(表示电话号码)
	*正确格式：区号-电话号码-分机号(全写|只写电话号码)
	*另外还要加上400号码段
 */
function isTel(str){ 
  
  // 普通固话号码验证
  var reg=/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;  
  // 400固话号码验证
  var reg400=/^400-([0-9]){1}(-{1}[0-9]{5}|[0-9]{1}-{1}[0-9]{4}|[0-9]{2}-{1}[0-9]{3}|[0-9]{3}-{1}[0-9]{2}|[0-9]{4}-{1}[0-9]{1}|[0-9]{5}-{1})([0-9]){1}$/;
  
  if (str == ""){
	  return true;
  }
		
  if(!reg.test(str) && !reg400.test(str) ){
     
      return false; 
  } 
  
  return true;
}