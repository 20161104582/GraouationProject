/**
 * Created by hj on 2016/1/25.
 */


//公告栏开始
$(function () {
    var $this = $(".c-notice");
    var scrollTimer;
    $this.hover(function () {
        clearInterval(scrollTimer);
    }, function () {
        scrollTimer = setInterval(function () {
            scrollNews($this);
        }, 4000);
    }).trigger("mouseleave");
});
function scrollNews(obj) {
    var $self = obj.find("ul:first");
    var lineWidth = $self.find("li:first").width(); //获取宽度
    $self.animate({"marginLeft": -lineWidth + "px"}, 700, function () {
        $self.css({marginLeft: 0}).find("li:first").appendTo($self); //appendTo能直接移动元素
    })
}
//公告栏结束

$(document).ready(function () {
    //顶部二维码
    $(".header-top i.fa-mobile-phone").parent().mouseenter(function(){
       $(this).find(".child").fadeIn();
        $(this).mouseleave(function(){
            $(this).find(".child").fadeOut();
        })
    });
    //datepicker
    if ($(".datePicker").length > 0) {
        $(".datePicker").datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: "-75:+0"
        });
    }
    //        通用tab
    $(".tab_group a[name*=tab]").click(function () {
        var active = $(this).attr("name");
        $(this).parents(".tab_group").find("a[name*=tab_]").removeClass("cur");
        $(this).addClass("cur");
        $(this).parents(".tab_group").find("[class*=tab_]").hide();
        $(this).parents(".tab_group").find("." + active).show();
    });
    $(".tab_group_hover a[name*=tab]").hover(function () {
        var active = $(this).attr("name");
        $(this).parents(".tab_group_hover").find("a[name*=tab_]").removeClass("cur");
        $(this).addClass("cur");
        $(this).parents(".tab_group_hover").find("[class*=tab_]").hide();
        $(this).parents(".tab_group_hover").find("." + active).show();
    });

    //视频分享
    $("#videoTools .share-btn").mouseenter(function () {
        var mailBox = $(this).find("#sharecon");
        mailBox.stop(true,true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(100);
        });
    });

    //头部工作邮箱
    $(".addfunction .icons-mail").parent().mouseenter(function () {
        var mailBox = $(this).find(".child");
        mailBox.stop(true, true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(500);
        });
    });
    $(".addfunction .icons-local").parent().mouseenter(function () {
        var mailBox = $(this).find(".child");
        mailBox.stop(true, true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(500);
        });
    });
    $(".addfunction .icons-app").parent().mouseenter(function () {
        var mailBox = $(this).find(".child");
        mailBox.stop(true, true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(100);
        });
    });
    $(".addfunction .icons-share").parent().mouseenter(function () {
        var mailBox = $(this).find(".child");
        mailBox.stop(true, true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(100);
        });
    });
    $("#videoTools .share-btn").mouseenter(function () {
        var mailBox = $(this).find("#sharecon");
        mailBox.stop(true, true).fadeIn(100);
        $(this).mouseleave(function () {
            mailBox.fadeOut(100);
        });
    });
});


//显示弹出层
function showJbox(strType, strTarget, strTitle, intWidth, intHeight, closeHandle) {
    var targetURL = strType + ":" + strTarget;
    $.jBox(targetURL, {
        title: strTitle,
        width: intWidth,
        height: intHeight,
        top: (document.documentElement.clientHeight - intHeight - 20) / 2,
        showClose: true,
        closed: closeHandle
    });
}

function closeJbox() {
    top.$.jBox.close();
}


//取消事件
function cancelEvent(e) {
    if (e && e.preventDefault) {
        e.preventDefault();
    } else {
        window.event.returnValue = false;
    }
}

//倒计时
function ShowCountDown(year, month, day, divname) {
    var now = new Date();
    var endDate = new Date(year, month - 1, day);
    var leftTime = endDate.getTime() - now.getTime();
    var leftsecond = parseInt(leftTime / 1000);
    var day1 = Math.floor(leftsecond / (60 * 60 * 24));
    var hour = Math.floor((leftsecond - day1 * 24 * 60 * 60) / 3600);
    var minute = Math.floor((leftsecond - day1 * 24 * 60 * 60 - hour * 3600) / 60);
    var second = Math.floor(leftsecond - day1 * 24 * 60 * 60 - hour * 3600 - minute * 60);
    var cc = document.getElementById(divname);
    cc.innerHTML = "距离申报截止：<i>" + day1 + "</i>天<i>" + hour + "</i>小时<i>" + minute + "</i>分<i>" + second + "</i>秒";
}
//左侧菜单状态
function leftMenuState(_state){
    if ($(".left-list a")[0]){
        $(".left-list a").each(function(){
            var state = $(this).attr("data-content");
            if ( state== _state){
                $(this).addClass("cur");
            }
        })
    }
}

//jbox成功
function alertSuccess(str){
    $.jBox.success(str, '提示');
}
//jbox提示/错误
function alertInfo(str){
    $.jBox.info(str, '提示');
}

//<!--添加首页和收藏js-->
function AddFavorite(sURL, sTitle) {
    try {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e) {
            alert("加入收藏失败，请使用Ctrl+D进行添加!");
        }
    }
}


//设为首页
function SetHome(obj, url) {
    try {
        obj.style.behavior = 'url(#default#homepage)';
        obj.setHomePage(url);
    } catch (e) {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("抱歉，此操作被浏览器拒绝！\n\n请在浏览器地址栏输入“about:config”并回车然后将[signed.applets.codebase_principal_support]设置为'true'");
            }
        } else {
            alert("抱歉，您所使用的浏览器无法完成此操作。\n\n您需要手动将【" + url + "】设置为首页。");
        }
    }
}

    //jboxPro

    if ($(".imgShow")[0]){
        imgShow($(".imgShow"));
    }
//    jboxpro弹出
    function imgShow(obj) {
        obj.jboxpro({
            'width': 600,
            'height': 'auto',
            'centerOnScroll': false,
            'transitmnionIn': 'fade',
            'transitionOut': 'fade',
            'title':'图片详情',
            'type': 'image'
        });
    }

    function closeDiv(div) {
        $(div).slideUp();
    }






