var _tiken = "token";
var _role = "role"
var _account = "account"
var token = getCookie(_tiken);
var role = getCookie(_role);
var account = getCookie(_account);
//var apiUrl = "http://192.168.31.109:8999/api/";  //也可以改成你的 服务接口 ip地址
var apiUrl = "http://localhost:8999/api/";

$(function () {
   $(function () {
        if (role == "教练员") {
            $(".teacher").show(); 
            $("#account").html(account);
            $("#roule").html(role);
        } else if (role == "球员") {
            $(".player").show();   
            $("#account").html(account);
            $("#roule").html(role);
        }
        else if (role == "队医") {
            $(".doctor").show(); 
            $("#account").html(account);
            $("#roule").html(role);
        }
        else if (role == "管理员") {
            $(".admin").show();    
            $("#account").html(account);
            $("#roule").html(role);
        } else {
            $("#basc").hide();
            $("#out").html("去登陆");
            $(".tourist").show();
            $("#account").html("xxxx");
            $("#roule").html("游客");
        }       
    })
   
});

function setCookie(name, value) {
    var Days = 7;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}//读取cookies·
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}//删除cooke
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function removeAllSpace(str) {
    return str.replace(/\s+/g, "");
}

function GetQueryString(parameter) {
    var reg = new RegExp("(^|&)" + parameter + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]); //解决中文乱码
    }
}
function isPhone(phone) {
    var re = /^1\d{10}$/;
    return re.test(phone);
}
function FormatPlannedDate (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份 
        "d+": this.getDate(),                    //日 
        "h+": this.getHours(),                   //小时 
        "m+": this.getMinutes(),                 //分 
        "s+": this.getSeconds(),                 //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds()             //毫秒 
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}       


//inputTime 参数是毫秒级时间戳
function formatDate(inputTime) {
    if (inputTime == "" || inputTime == null) {
        return "";
    }
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}
