function zoomMe(obj) {
  var picSrc;
  if ($(obj)[0].tagName == 'IMG') {
    if ($(obj).attr('src') == null) {
      return false;
    }
    picSrc = $(obj).attr('src');
  } else {
    if ($(obj).attr('href') == null) {
      return false;
    }
    picSrc = $(obj).attr('href');
  }
  var picDom = "\n  <div class=\"zoomPic\">\n    <style type=\"text/css\">\n        .zoomPic{\n            display: block;\n            display: flex;\n            width: 100%;\n            height: 100%;\n            z-index: 666;\n            justify-content: center;\n            align-items: center;\n            position: fixed;\n            top: 0;\n            left: 0;\n            text-align: center;\n            background: rgba(0,0,0,0.7);\n        }\n        .zoomPic img{\n            display: inline-block;\n            max-height: 90%;\n            max-width: 90%;\n        }\n        .zoomPic i{\n            position: absolute;\n            font-size: 60px;\n            line-height: 60px;\n            color: #ffffff;\n            right: 20px;\n            top: 0px;\n            cursor: pointer;\n            text-shadow: 2px 2px 5px #aaa;\n        }\n    </style>\n        <i onclick=\"$('.zoomPic').remove()\">&times;</i>\n        <img src=/Other/shanxi/index.html"".concat(picSrc, "\" alt=\"loading...\">\n</div>");
  $(picDom).appendTo('body');
}

