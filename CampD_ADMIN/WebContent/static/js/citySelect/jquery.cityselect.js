//eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('!4(a){a.n.P=4(b){4 c(b){6 c,d=a(b),e="Y",f=a("m",d);d.o(4(){a(5).Z(e),f.F(!0,!0).10()}),c=4(){d.Q(e),f.F(!0,!0).U()},a("T").o(4(d){a(d.S).R(b+" *")||c()})}6 d,e={C:["#V","#W","#X"],D:["请选择省份","请选择城市","请选择地区"],y:"i",K:\'11[O^="L"]\',A:!0},f=a.w(e,b),g=f.C,h=f.D,i=f.y,j=f.K,k=g.z;I(a.n.8=4(b){6 c={G:!1,7:0},d=a.w(c,b),e=a(5),f=a("m 3",e),g=(a("m 3:N",e),a("m 3:M(0)",e)),k=a(i,e),l=a(j,e);9 d.G?f.E():g.E(),k.q(h[d.7]),l.r(h[d.7]),5},a.n.t=4(b){6 c,d={p:"0"},e=a.w(d,b),f=a(5),g=a("m",f),h=18.1a[e.p],i="";I("1b"==19 h)9!1;J(c=0;c<h.z;c++)i+=\'<3><a 16="13:14(0)" s="\'+h[c]+\'">\'+h[c]+"</a></3>";g.12(i),i=""},a.n.H=4(){6 b,c,d;a("3",g[0]),a("3",g[1]),a("3",g[2]),a("3",g[0]).u("o",4(){b=a("3",g[0]).v(5)-1;6 c=a("a",5).x("s"),d=a(i,g[0]),e=a(j,g[0]);9 d.q(c),e.r(c),a(g[1]).8({7:1}),a(g[1]).t({p:"B"+b}),a(g[2]).8({7:2}),b}),a("3",g[1]).u("o",4(){c=a("3",g[1]).v(5)-1;6 d=a("a",5).x("s"),e=a(i,g[1]),f=a(j,g[1]);9 e.q(d),f.r(d),a(g[2]).8({7:2}),a(g[2]).t({p:"B"+b+"17"+c}),c}),a("3",g[2]).u("o",4(){d=a("3",g[2]).v(5);6 b=a("a",5).x("s"),c=a(i,g[2]),e=a(j,g[2]);9 c.q(b),e.r(b),d})},f.A)J(d=0;k>d;d++)a(g[d]).8({7:d}),c(g[d]);a(g[0]).t({p:"0"}),a.n.H()}}(15);',62,74,'|||li|function|this|var|thisindex|removelist|return|||||||||||||ul|fn|click|theindex|text|val|alt|appendlist|live|index|extend|attr|czemt|length|intva|0_|setId|stval|remove|stop|removeAll|liClick|if|for|inpvt|cho|gt|first|name|citySelect|removeClass|is|target|body|slideUp|Province|City|Area|active|toggleClass|slideToggle|input|append|javascript|void|jQuery|href|_|dsy|typeof|Items|undefined'.split('|'),0,{}))
(function($){
$.fn.citySelect = function(cityArray,defaultCityArray,options) {  
 	var defaults = {  
 		setId : cityArray,                //默认id
 		stval : defaultCityArray,     //默认值
 		czemt : 'i',                        					  //存值元素
 		inpvt : 'input[name^="cho"]',                     		  //存值文本框	
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
 			$('li' , _setId[0]).live('click' , function(){ //省点击事件
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
 				_inputB.val('请选择城市');
 				_inputC.val('请选择地区');
 				
 				return indA;
 			});
 			$('li' , _setId[1]).live('click' , function(){ //市点击事件
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
 				_inputC.val('请选择地区');		
 				return indB;
 			});
 			$('li' , _setId[2]).live('click', function(){ //区点击事件
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
 	
 	//TODO:根据默认值初始化市级信息
	$('a',_setId[0]).each(function(){
		if(defaultCityArray[0] == $(this).attr('alt')){
			indA = $('a' , _setId[0]).index(this) - 1;
			$(_setId[1]).removelist({thisindex : 1});
 			$(_setId[1]).appendlist({theindex:'0_'+indA});
 			$(_setId[2]).removelist({thisindex : 2});
			$.fn.liClick();
		}
		
	});
	//TODO:根据默认值初始化地区级信息
	$('a',_setId[1]).each(function(){
		if(defaultCityArray[1] == $(this).attr('alt')){
			indB = $('a' , _setId[1]).index(this) - 1;
			$(_setId[2]).removelist({thisindex : 2});
 			$(_setId[2]).appendlist({theindex:'0_'+indA+'_'+indB});			
			$.fn.liClick();
		}
	});
};  
})(jQuery);