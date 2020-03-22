

//访问数量
function GetVisitCount(webSiteID) {

    var pvCount = "0";
    if (webSiteID != "") {
        $.ajax({
            type: "get",
            url: "/CommonHandler/GetVisitCount.ashx",
            dataType: "jsonp",
            jsonp: "CallBack",
            data: "webSiteID=" + webSiteID,
            async: false,
            success: function (data) {
                console.log("wwww" + data.pvCount);
                pvCount = data.pvCount;
            }
        });
        var length = Trim(pvCount.toString()).length;
        var num = [];
        var aa = 1;
        function hw(length) {
            if (aa) {
                aa = 0;
                for (var i = 1; i <= length; i++) {
                    num[length - i] = Math.floor(pvCount / Math.pow(10, length - i));
                }
            } else {
                for (var i = 1; i <= length; i++) {
                    num[length - i] = num[length - i] - num[length] * Math.pow(10, i);
                }
            }
            if (length - 1 > 0) {
                hw(length - 1);
            }
        }
        hw(length);

        for (; length > 0; length--) {
            $(".footer .visit-num").append("<li class='item'>" + num[length - 1] + "</li>")
        }
    }
}

//去掉字符串前后的空格
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
