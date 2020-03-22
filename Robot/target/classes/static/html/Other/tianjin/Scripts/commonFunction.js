$(document).ready(function(){
	if (isExit("#focusPhotoWrap")) {
		initImages();	
	}
	if (isExit("#tabPanel")) {
		$("div.tabs").tabs("div.panes > div.pane",{tabs:"div",event:'mouseover'});
	}
	
	if (isExit("#menuPanel")) {
		if (isIE6) {
			$("#menuPanel .content ul.menu li").mouseover(function(){
				$("#menuPanel .content ul.menu li ul.subMenu").hide();
				$(this).children("ul.subMenu").show();
			});
		}
	}
	
});

function initImages() {
	$(".imageDiv").soChange({ 
		thumbObj:"#thumbBox>ul>li",
		botPrev:null,
		botNext:null,
		thumbNowClass:'now', 
		thumbOverEvent:true,
		slideTime:1000, 
		autoChange:true, 
		clickFalse:true,
		overStop:true,
		changeTime:5000, 
		delayTime:200
	});	
}

function isIE6() {
	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
		return true;	
	} else {
		return false;	
	}
}

function isExit(strSelector) {
	return ($(strSelector).size()>0?true:false);
}

function isUndefined(value) {
	return (typeof value == "undefined"?true:false);
}

function historyBack() {
	window.history.back();	
	return false;	
}

function changeStyle(intStyle) {
	$("body").hide();
	$("#pageStyleLink").attr("type","")	
	switch(intStyle) {
		case 1:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/beijing.css")	
			break
		case 2:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/liaoning.css")	
			break
		case 3:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/tianjin.css")	
			break
		case 4:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/heilongjiang.css")	
			break
		case 5:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/neimenggu.css")	
			break
		case 6:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/shanghai.css")	
			break
		case 7:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/fujian.css")	
			break
		case 8:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/jiangsu.css")	
			break
		case 9:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/zhejiang.css")	
			break
		case 10:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/hainan.css")	
			break	
		case 11:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/xinjiang.css")	
			break	
		case 12:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/shaanxi.css")	
			break	
		case 13:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/hunan.css")	
			break	
		case 14:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/guangdong.css")	
			break
		case 15:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/jiangxi.css")	
			break
		case 16:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/shanxi.css")	
			break
		case 17:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/hubei.css")	
			break
		case 18:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/shandong.css")	
			break
		case 19:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/chongqing.css")	
			break
		case 20:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/yunnan.css")	
			break
		case 21:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/guizhou.css")	
			break
		case 22:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/henan.css")	
			break
		case 23:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/gansu.css")	
			break
		case 24:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/guangxi.css")	
			break
		case 25:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/sichuan.css")	
			break
		case 26:
			$("#pageStyleLink").attr("href","/Other/tianjin/Scripts/css/anhui.css")	
			break
	}	
	
	$("#pageStyleLink").attr("type","text/css")	
	$("body").show();
}
