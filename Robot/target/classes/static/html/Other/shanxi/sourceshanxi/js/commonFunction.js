var aryMainImages = new Array();
$(document).ready(function () {


});




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


function closeWindow() {
    window.close()
}

function printWindow() {
    window.print()
}

function specialPanelChange(a) {
    $("#specialPanel .specialList li").removeClass("current");
    a.parent().addClass("current")
}

