<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>验证失败 | LOFTER（乐乎）</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8"/>
<meta http-equiv="x-ua-compatible" content="ie=emulateie7"/>
<meta name="author" content="netease"/>
<meta name="version" content="1.0"/>
<meta name="keywords" content="LOFTER,乐乎"/>
<meta name="description" content=""/>
  <script type="text/javascript">
   (function(window,document){
        
        document.uniqueID!=document.uniqueID&&!!location.hash&&(location.hash=location.hash);
        window.focus();
    })(this,document);
  </script>
<link href="http://l.bst.126.net/s/pt_lib_macro.css?41dce043bbeea1705b9e09ea50d4b0c8" type="text/css" rel="stylesheet"/>
<style>
.m-emv{padding:18px 0 0 140px;background:url(rsc/img/emailvbg.png) 22px 15px no-repeat;line-height:25px;}
.m-emv .title{font-size:20px;font-weight:normal;}
.m-emv .btn{padding:24px 0 10px;}
.m-emv .cont{padding:0 0 20px;margin:0 0 20px;border-bottom:1px solid #f2f2f2;}
.m-emv ul{margin:4px 0 0;}
.m-emv li{list-style:inside decimal;}
.m-emv-1{background:none;}
.m-emv-1 .title{margin-left:-50px;padding-left:50px;background:url(rsc/img/icon2-8.png) -54px -234px no-repeat;}
</style>
  </head>
  <body>
<div class="g-hd g-hd-tip f-dn" id="showDldTipsInIpad">
	<div class="g-hdc">
		<div class="m-hdtip">
			<div class="logo"></div>
			<p class="slogan">LOFTER for ipad —— 记录生活，发现同好</p>
			<a href="http://www.lofter.com/app/QRCodedownload?act=qbipaddl_20141014_01" class="btn" target="_blank">点击下载</a>
			<a href="" class="w-close2">关闭</a>
		</div>
	</div>
</div>
<a style="display:none;" rel="nofollow" href="http://www.lofter.com/feedback.do" class="w-fank">意见反馈</a>
<div class="g-hd">
<div id="lofterheadbar" class="g-hdc">
    <h1 class="m-logo"><a href="http://www.lofter.com" >LOFTER-网易轻博</a></h1>
    
</div>

	<% 
		String email=(String) session.getAttribute("email");
        String pwd=(String) session.getAttribute("pwd");
	%>

</div>
		<div class="g-bd5">
			<div class="g-bdc">
		        <div class="g-mn">
		            <div class="g-box3">
		            	<h2 class="w-fttl">验证失败</h2>
		            </div>
		            <div class="g-box8">
		                <div class="m-emv m-emv-1">
		                	<div class="cont">
		                        <h3 class="title">邮箱地址验证失败！</h3>
		                        <p class="s-fc1">激活时间或已到期</p>
		                    </div>
		                    <p class="f-fs1">你现在可以：</p>
		                    <ul class="s-fc1">
		                        <li><a href="user.action?op=reg&email=<%=email %>&pwd=<%=pwd %>"  style="color:blue;">再次发送验证邮件</a></li>
		                        <li>如果重发注册验证邮件仍然没有收到，请<a href="mylogin.jsp" class="s-fc3">更换另一个邮件地址</a></li>
		                    </ul>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
<div class="g-ft">
	<span class="m-ft2"><a target="_blank" rel="nofollow" href="http://www.lofter.com/contact">联系我们</a><span>|
	<span class="m-ft1">lofter团队所有　&copy;2015-</span>
</div>
  <div style="display:none;"  class="fa-init">		  <textarea rows="1" cols="1" name="js" >this.p={email:'2049828003@qq.com'}</textarea>

</div>
<script type="text/javascript">

location.dwr='http://www.lofter.com/dwr';
location.hosturl = 'http://www.lofter.com';
location.dirhost = 'http://l.bst.126.net';
location.cloudMusicFlashUrl = 'http://s1.music.126.net/style/swf/LofterMusicPlayer.swf?005';

location.f110 = 'http://l.bst.126.net/rsc/img/ava/110.png';


Date.servTime = '05/07/2015 12:49:53';

window.CF = {
debugMode:false,
testDBMode:false,
devSourceMode:false,
visitor:{
userId:488242144,
email:'3#e784ee7baa218759332a22179859889f#1426742973233',
mainBlogId:488242144,
isFromMobile:'',
isForbidUser:false
}
};
window.CF.visitor.visitorEmail = '3#e784ee7baa218759332a22179859889f#1426742973233';

CF.blogs = [];
CF.blogs.push({blogId:488242144,
role:10,
blogName:'yiranxijie',
blogNickName:'西界',
bigAvaImg: 'http://imglf0.ph.126.net/TrgFT-M-Ue5xmO5g7wdI4w==/6608723490399787200.jpg',
newNoticeCount: 0,
newRecommendNoticeCount: 0,
newActivityTagNoticeCount: 0,
newArtNoticeCount: 0
});
window.CF.visitor.blogName = 'yiranxijie';
window.CF.visitor.blogNickName = '西界';
window.CF.visitor.bigAvaImg = 'http://imglf0.ph.126.net/TrgFT-M-Ue5xmO5g7wdI4w==/6608723490399787200.jpg';


var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-31007899-1'],['_setLocalGifPath', '/UA-31007899-1/__utm.gif'],['_setLocalRemoteServerMode']);
_gaq.push(['_setDomainName', 'lofter.com']);
_gaq.push(['_trackPageview']);

(function() {
var loadgajs = function(){
var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
ga.src = 'http://wr.da.netease.com/ga.js';

var _handler = function(){
if(!_handler.isExeced){
_handler.isExeced = true;
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
}
};

if (!!document.addEventListener) {
document.body.addEventListener('load',_handler, false);
} else{
document.body.attachEvent('onload',_handler);
}

setTimeout(_handler,8000);
};

setTimeout(loadgajs,500);

})();
</script><script src="http://l.bst.126.net/s/core.js?9cb4efc76d907a3c64c84cebb67e8150" type="text/javascript"></script><script src="http://l.bst.126.net/s/pt_page_urs_ursFail.js?1fd793aa2418a4d7bd8c1e45404f540a" type="text/javascript"></script>
  </body>
</html>