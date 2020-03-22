var aryMainImages = new Array();
$(document).ready(function () {

    if (isExit("#topActivityWrap")) {
        var a = 0;
        $("#topActivityWrap ul li").each(function (b) {
            if ($(this).hasClass("current")) {
                a = b
            }
        });
        $("#topActivityWrap ul").tabs("#topImage a", {
            tabs: "li",
            effect: "default",
            event: "mouseover",
            initialIndex: a
        })
    }
    if (isExit("#focusSlider")) {
        $("#focusSlider").nivoSlider()
    }
    if (isIE6()) {
        fixPng()
    }
});

function fixPng() {
    if (isExit("#indexMain")) {
        DD_belatedPNG.fix("#focusWrap");
        DD_belatedPNG.fix("#indexMain #boardPanel")
    }
    if (isExit("#leftNavMenuWrap")) {
        DD_belatedPNG.fix("#leftNavMenuWrap");
        DD_belatedPNG.fix("#leftNavMenuWrap ul#leftNavMenu");
        DD_belatedPNG.fix("#leftNavMenuWrap #leftNavMenuBottom")
    }
    if (isExit(".nivoSlider")) {
        DD_belatedPNG.fix(".nivo-directionNav a");
        DD_belatedPNG.fix(".nivo-controlNav")
    }
    if (isExit("ul.gallery")) {
        DD_belatedPNG.fix("ul.gallery li")
    }
    DD_belatedPNG.fix(".leftPanel div.title a");
    DD_belatedPNG.fix(".centerPanel div.title a");
    DD_belatedPNG.fix(".rightPanel div.title");
    DD_belatedPNG.fix("a.iconButton img")
}

function isExit(a) {
    return ($(a).size() > 0 ? true : false)
}

function isUndefined(a) {
    return (typeof a == "undefined" ? true : false)
}

function setDefaultValue(b, a) {
    if (isUndefined(b)) {
        return a
    } else {
        return b
    }
}

function isIE6() {
    if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
        return true
    } else {
        return false
    }
}

function addFav() {
    if (document.all) {
        window.external.addFavorite("http://www.xiaoxiaotong.org", "\u5168\u56fd\u9752\u5c11\u5e74\u79d1\u6280\u521b\u65b0\u6d3b\u52a8\u670d\u52a1\u5e73\u53f0")
    } else {
        if (window.sidebar) {
            window.sidebar.addPanel("\u5168\u56fd\u9752\u5c11\u5e74\u79d1\u6280\u521b\u65b0\u6d3b\u52a8\u670d\u52a1\u5e73\u53f0", "http://www.xiaoxiaotong.org", "")
        }
    }
}

function setHome(c) {
    if (document.all) {
        document.body.style.behavior = "url(#default#homepage)";
        document.body.setHomePage(c)
    } else {
        if (window.sidebar) {
            if (window.netscape) {
                try {
                    netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")
                } catch (b) {
                    alert("\u8be5\u64cd\u4f5c\u88ab\u6d4f\u89c8\u5668\u62d2\u7edd\uff0c\u8bf7\u60a8\u624b\u52a8\u8bbe\u7f6e\u672c\u7ad9\u4e3a\u9996\u9875\u3002")
                }
            }
            var a = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
            a.setCharPref("browser.startup.homepage", c)
        }
    }
}

function setFontSize(c) {
    var d = 16;
    var a = 14;
    var b = 12;
    switch (c) {
        case "big":
            $("#articleContent").css("font-size", d + "px");
            break;
        case "normal":
            $("#articleContent").css("font-size", a + "px");
            break;
        case "small":
            $("#articleContent").css("font-size", b + "px");
            break
    }
}

function scrollTop() {
    scroll(0, 0)
}

function closeWindow() {
    window.close()
}

function printWindow() {
    window.print()
}

function specialPanelChange(a) {
    $("#specialPanel .specialList li").removeClass("current");
    a.parent().addClass("current")
};
