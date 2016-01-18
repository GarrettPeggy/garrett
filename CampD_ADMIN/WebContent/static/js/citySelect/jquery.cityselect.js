(function($){
$.fn.citySelect = function(cityArray,defaultCityArray,options) {  
 	var defaults = {  
 		setId : cityArray,                //默认id
 		stval : defaultCityArray,     //默认值
 		czemt : 'i',                        					  //存值元素
 		inpvt : '.curValue',                     		  //存值文本框	
 		intva : true											  //初始化所有下拉默认确定
 	},
 		opts = $.extend(defaults, options),
 			_setId = opts.setId,
 			_stval = opts.stval,
 			_czemt = opts.czemt,
 			_inpnt = opts.inpvt,
 			len = _setId.length;
 	$.fn.removelist = function(options){  //清空
 			var removdefa = {  
 			    removeAll : false,
 			    thisindex : 0
 			},
 		optremove = $.extend(removdefa, options);  
 	var $_removebox = $(this),
 		$_listall = $('ul li' , $_removebox),
 		$_listfirst = $('ul li:first' , $_removebox),
 		$_listsib = $('ul li:gt(0)' , $_removebox),
 		$_vala = $(_czemt , $_removebox),
 		$_valb = $(_inpnt , $_removebox);
 		if(optremove.removeAll){
 			$_listall.remove();
 		}else{
 			$_listsib.remove();
 		}
 		$_vala.text(_stval[optremove.thisindex]);
 		$_valb.val(_stval[optremove.thisindex]);
 		return this;
 	};
 	$.fn.appendlist = function(options){  //添加
 			var appdefa = {  
 		  	    theindex : '0'
 			},
 		optapp = $.extend(appdefa, options);
 		var $_appendbox = $(this),
 			$_listbox = $('ul' , $_appendbox),
 			appendArray = dsy.Items[optapp.theindex],
 			appList = '';
 		if(typeof(appendArray) == "undefined") return false; //如果不存在当前对象返回false
 		for(var i = 0; i<appendArray.length;i++){
 			appList += '<li><a href="javascript:void(0)" alt="'+appendArray[i]+'">'+appendArray[i]+'</a></li>';
 		}
 		$_listbox.append(appList);
 		appList = '';
 	};
 	function slide(str){ //下拉事件
 		var oID = $(str),
 		    oClass = 'active',
 		    oList = $('ul' , oID),
 		    closed;
 		oID.click(function(){
 		    $(this).toggleClass(oClass);
 		    oList.stop(true,true).slideToggle();
 		});
 		closed = function(){
 		    oID.removeClass(oClass);
 		    oList.stop(true,true).slideUp();
 		}
 		$("body").click(function(e){
 		    if(!$(e.target).is(str+" *")){
 		        closed();
 		    }
 		});
 	};
 	
 	var indA,indB,indC;
 	$.fn.liClick = function(){
 			var $_liA = $('li' , _setId[0]),
 				$_liB = $('li' , _setId[1]),
 				$_liC = $('li' , _setId[2]);
 			$(_setId[0]).delegate('li' , 'click' , function(){ //省点击事件
 				indA = $('li' , _setId[0]).index(this) - 1;
 				var   _valA = $('a' , this).attr('alt'),
					_textA = $('a' , this).text(),
 					_emeltA = $(_czemt , _setId[0]),
 					_emeltB = $(_czemt , _setId[1]),
 					_emeltC = $(_czemt , _setId[2]),
 					_inputA = $(_inpnt , _setId[0]);
 					//TODO:当省份发生变化时，城市和地区要恢复默认值，以下两个变量是
 					_inputB = $(_inpnt , _setId[1]);
 					_inputC = $(_inpnt , _setId[2]);
 				_emeltA.text(_textA);
 				_inputA.val(_valA);
 				$(_setId[1]).removelist({thisindex : 1});
 				$(_setId[1]).appendlist({theindex:'0_'+indA});
 				$(_setId[2]).removelist({thisindex : 2});
 				//TODO:当省份发生变化时，城市和地区要恢复默认值
 				_emeltB.text('请选择城市');
 				_emeltC.text('请选择地区');
 				_inputB.val('');
 				_inputC.val('');
 				
 				return indA;
 			});
 			$(_setId[1]).delegate('li' , 'click' , function(){ //市点击事件
 				indB = $('li' , _setId[1]).index(this) - 1;
 				var   _valB = $('a' , this).attr('alt'), 
					_textB = $('a' , this).text(),
 					_emeltB = $(_czemt , _setId[1]),
 					_emeltC = $(_czemt , _setId[2]),
 					_inputB = $(_inpnt , _setId[1]);
 					//TODO:当城市发生变化时，地区要恢复默认值
 					_inputC = $(_inpnt , _setId[2]);
 				_emeltB.text(_textB);
 				_inputB.val(_valB);
 				$(_setId[2]).removelist({thisindex : 2});
 				$(_setId[2]).appendlist({theindex:'0_'+indA+'_'+indB});	
 				_emeltC.text('请选择地区');		
 				_inputC.val('');		
 				return indB;
 			});
 			$(_setId[2]).delegate('li' , 'click', function(){ //区点击事件
 				indC = $('li' , _setId[2]).index(this);
 				var   _valC = $('a' , this).attr('alt'), 
					_textC = $('a' , this).text(),
 					_emeltC = $(_czemt , _setId[2]),
 					_inputC = $(_inpnt , _setId[2]);
 				_emeltC.text(_textC);
 				//TODO:当城市发生变化时，地区要恢复默认值
 				_inputC.val(_valC);	
 				return indC;
 			});	
 	};
 	function show(obj){   //大按钮事件
 		$(obj).toggleClass('active').find('ul').slideToggle();
 	}
 	if(opts.intva){
 		for(var i = 0 ; i < len; i++) {  //初始化默认值所有值
 			$(_setId[i]).removelist({thisindex : i});
 			slide(_setId[i]);
 		};			
 	}
 	$(_setId[0]).appendlist({theindex:'0'}); //默认添加省
 	$.fn.liClick();
};  
})(jQuery);