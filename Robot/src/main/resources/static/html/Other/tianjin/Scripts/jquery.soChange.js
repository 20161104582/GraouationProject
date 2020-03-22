;(function($){
	$.fn.extend({
		"soChange": function(o){

		o= $.extend({
			thumbObj:null,
			botPrev:null,
			botNext:null,
			thumbNowClass:'now',
			thumbOverEvent:true,
			slideTime:1000,
			autoChange:true,
			clickFalse:true,
			overStop:true,
			changeTime:5000,
			delayTime:300
		}, o || {});

		var _self = $(this);
		var thumbObj;
		var size = _self.size();
		var nowIndex =0; 
		var index;
		var startRun;
		var delayRun;

	function fadeAB () {
		if (nowIndex != index) {
			if (o.thumbObj!=null) {
			$(o.thumbObj).removeClass(o.thumbNowClass).eq(index).addClass(o.thumbNowClass);}
			if (o.slideTime <= 0) {
				_self.eq(nowIndex).hide();
				_self.eq(index).show();	
			}else{
				_self.eq(nowIndex).fadeOut(o.slideTime);
				_self.eq(index).fadeIn(o.slideTime);
			}
			nowIndex = index;
			if (o.autoChange==true) {
			clearInterval(startRun);
			startRun = setInterval(runNext,o.changeTime);}
			}
	}


	function runNext() {
		index =  (nowIndex+1)%size;
		fadeAB();
	}

			_self.hide().eq(0).show();

		if (o.thumbObj!=null) {
		thumbObj = $(o.thumbObj);

			thumbObj.removeClass(o.thumbNowClass).eq(0).addClass(o.thumbNowClass);
			thumbObj.click(function () {
				index = thumbObj.index($(this));
				fadeAB();
				if (o.clickFalse == true) {
					return false;
				}
			});
			if (o.thumbOverEvent == true) {
			thumbObj.mouseenter(function () {
				index = thumbObj.index($(this));
				delayRun = setTimeout(fadeAB,o.delayTime);
			});
			thumbObj.mouseleave(function () {
				clearTimeout(delayRun);
			});
			}
		}

		if (o.botNext!=null) {
			$(o.botNext).click(function () {
				if(_self.queue().length<1){
				runNext();}
				return false;
			});
		}

		if (o.botPrev!=null) {
			$(o.botPrev).click(function () {
				if(_self.queue().length<1){
				index = (nowIndex+size-1)%size;
				fadeAB();}
				return false;
		});
		}

		if (o.autoChange==true) {
		startRun = setInterval(runNext,o.changeTime);
		if (o.overStop == true) {
			_self.mouseenter(function () {
				clearInterval(startRun);//�����Զ��л�����
				
			});
			_self.mouseleave(function () {
				startRun = setInterval(runNext,o.changeTime);
			});
			}
		}

	}

	})

})(jQuery);
