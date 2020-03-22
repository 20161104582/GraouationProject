// ��ҳ�����������ű�
var showTopBar = false; //��ʾ����
var pageBg = "/images/newbg.png"; //ҳ�汳��ͼƬ
var pageBgColor = ""; //ҳ�汳��ɫ
var pageBgRepeat = "no-repeat"//����ͼƬ�ظ�
var topBarWidth = 980;
var topBarHeight = 50; //�������߶�
var topBarBg = ""; //����������ͼƬ
var topBarImage = "/images/2011.jpg"; //������ͼƬ
var topBarLink = ""; //���ӵ�ַ
var topBarClose1 = "/images/btnclose.gif"; //�رհ�ťͼƬ
var topBarClose2 = "/images/btnclose2.gif"; //�رհ�ťͼƬƬ


//ҳ����ʼ
$(document).ready(function() {
    //����Ƿ����?
    if (showTopBar == true) {
        var htmlstr = "<style type='text/css'>\nbody{text-align:center;}\n#topBar{position:relative;height:0px;overflow:visible;margin-left:auto;margin-right:auto;text-align:center;}\n#topBarImg{position: relative;width:auto;height:auto;margin:0 auto;}\n#topBarClose{position:relative;top:-50px;left:950px;width:0;height:0;overflow: visible;}\n</style>\n";
        htmlstr += "<div id='topBar'><div id='topBarImg'></div><div id='topBarClose'><img src='" + topBarClose1 + "' /></div></div>";
        $("body").prepend(htmlstr);
        pageBgColor = $("body").css("background-color");
        //����ҳ�汳��
        $("body").css("background", pageBgColor + " url("/Other/tianjin/Scripts/pageBg/index.html") " + pageBgRepeat + " center top");
        //
        $("#topBar").width(topBarWidth).height(topBarHeight);
        //����ͼƬ
        if (topBarImage != "") {
            if (topBarLink == "") {
                $("#topBarImg").html("<img src='" + topBarImage + "' />");
            } else {
                $("#topBarImg").html("<a href='" + topBarLink + "' target='_blank'><img src='" + topBarImage + "' /></a>");
            }
        }
        //���ùرհ�ť
        $("#topBarClose")[0].onclick = function() {
            $("body").css("background", pageBgColor);
            $("#topBar").hide();
            $("#topBarTable").hide();
        }
        $("#topBarClose img").hover(function() { $(this).attr("src", topBarClose2); }, function() { $(this).attr("src", topBarClose1); });
    }
});
