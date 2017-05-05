<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh">
  <head>
	<title>LOFTER（乐乎）</title>
	<meta name="keywords" content="LOFTER,乐乎"/>
    <meta http-equiv="x-ua-compatible" content="ie=emulateie7"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta http-equiv="content-style-type" content="text/css"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta name="author" content=""/>
    <meta name="keywords" content="LOFTER,乐乎"/>
    <meta name="description" content=""/>
  <script type="text/javascript">
   (function(window,document){
        
        document.uniqueID!=document.uniqueID&&!!location.hash&&(location.hash=location.hash);
        window.focus();
    })(this,document);
  </script>
   <script src="js/common.js"></script>
<link href="http://l.bst.126.net/s/pt_lib_macro.css?1c393a50ad7192b3668a9dc6a28a33bc" type="text/css" rel="stylesheet"/>
<link href="http://l.bst.126.net/s/pt_page_reg.css?18aafcbdeffdf390b9f323018b6041ec" type="text/css" rel="stylesheet"/>

<style>
#warninfo{
	position: absolute;
	padding:5px;
	top:110px;
	z-index:2;
	display:none;
	left:580px;
	font-size:14px;
	height:40px;
	line-height:40px;
	float:left;
	border:1px solid #cfc;
	color:#fff;
	background: #75a500;
}
</style>
  </head>
  <body class="p-newreg">
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
	
	<div id="warninfo">恭喜您，激活成功！</div>
		
	<div class="g-hdreg">
		<div class="head">
		    <h1 class="logo"><a rel="nofollow" href="http://www.lofter.com/">LOFTER-网易轻博</a></h1>
		</div>
	</div>
	
	 <%
            	String email=(String) session.getAttribute("email");
            	String pwd=(String) session.getAttribute("pwd");
            	String nickname=email.split("@")[0];
            	String error=request.getParameter("error");	//如果error不为空，说明有错误，不能登录
            %>
	
    <div class="g-bd6">
	    <div class="g-box7 ztag">
	    		<h2 class="w-fttl" id="title">欢迎开通LOFTER</h2>
            	<div id="infoemil" class="followinf f-dn">
	            	<p style="margin-bottom:0px;">当前帐号&nbsp;<%=nickname %></p>
	            </div>
            <p id="infotxt" class="info f-dn">创建我的博客，完善以下信息，可在”博客设置”中随时更改</p>
            
			<div class="m-nlogin">
				<div class="w-btxtbtn w-btxtbtn-1 conbox">
                    <div class="txt first ztag nickname">
                    	<label class="ztag" style="display:block;" >设置昵称</label>
                    	<input type="hidden" id="email" value=<%=email %>>
                    	<input type="text" id="nickname" class="name name-1 ztag" value="" onblur="checkuser(this.value,event)">
                    </div>
                    <span class="w-detect w-detect-2 firtstt ztag phide" id="unablenickname">请输入博客昵称</span>
                    <span class="w-detect w-detect-1 firtstt ztag phide" id="ablenickname">昵称可用</span>
                    <p class="tips2 s-fc2 phide ztag">域名长度不能小于5位，支持数字、字母和"-"(横线不可在首尾)</p>

					<div class="txt valcodearea ztag">
						<label class="ztag" id="codeLabel">验证码</label>
						<span class="w-detect w-detect-1 firtstt ztag phide" id="errorCode"><%=error %></span>
						<input class="name ztag" type="text" maxlength="5" id="acpro_inp2">
						<a href="javascript:void(0)" hidefocus="true" class="validt">
							<img class="ztag" width="85" height="38" title="点击更换验证码" src="code.jsp">
							<span class="icn ztag" onclick="showCodeAgain()"></span>
						</a>
						<input class="rCode" type="hidden" value="${rand }">
						<input class="pwd" type="hidden" value="<%=pwd %>">
					</div>
				</div>
				<div class="w-btxtbtn w-btxtbtn-1">
					<div class="act"><a class="w-bbtn w-bbtn-reg ztag" href="javascript:codeCheck()">开启lofter之旅</a></div>
				</div>

				<div class="other" style="padding-bottom:70px;">
					
				</div>

	    	</div>
	    </div>
	    <div class="bottombg"></div>
	</div>
  <div style="display:none;"  class="fa-init">        <textarea rows="1" cols="1" name="js" >this.p={flag:'',type:'reg',code:'',email:'taojunlxj@163.com'};</textarea>

</div>
<script type="text/javascript">

location.dwr='http://www.lofter.com/dwr';
location.hosturl = 'http://www.lofter.com';
location.dirhost = 'http://l.bst.126.net';
location.cloudMusicFlashUrl = 'http://s1.music.126.net/style/swf/LofterMusicPlayer.swf?005';

location.f110 = 'http://l.bst.126.net/rsc/img/ava/110.png';


Date.servTime = '05/07/2015 11:46:24';

window.CF = {
debugMode:false,
testDBMode:false,
devSourceMode:false,
visitor:{userId:0,mainBlogId:0}
};
window.CF.visitor.visitorEmail = 'taojunlxj@163.com';



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
</script>  	<div style="display:none;">
		<script type="text/javascript">
		/* <![CDATA[ */
		var google_conversion_id = 942798432;
		var google_conversion_language = "en";
		var google_conversion_format = "2";
		var google_conversion_color = "ffffff";
		var google_conversion_label = "9uPHCOjclAQQ4OzHwQM";
		var google_conversion_value = 0;
		/* ]]> */
		</script>
		<script type="text/javascript" src="http://www.googleadservices.com/pagead/conversion.js">
		</script>
		<noscript>
		<div style="display:inline;">
		<img height="1" width="1" style="border-style:none;" alt="" src="http://www.googleadservices.com/pagead/conversion/942798432/?value=0&amp;label=9uPHCOjclAQQ4OzHwQM&amp;guid=ON&amp;script=0"/>
		</div>
		</noscript>
		<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc5c55f9c94fbca8efd7d891afb3210e8' type='text/javascript'%3E%3C/script%3E"));
		</script>
	</div>
 </body>
 </html>
 <script src="http://www.lanrenzhijia.com/ajaxjs/jquery-1.10.1.min.js"></script>
 <script type="text/javascript">
	function showCodeAgain() {
			//获取到图片对象，并对图片对象src属性赋值
			var r = new Date();//提供一个变动的参数，让页面刷新
			var img = $(".ztag").prop({ src: "code.jsp?r=" + r});
		}
	
	var unablenickname=$("#unablenickname");
	var ablenickname=$("#ablenickname");
	
	function checkuser(nickname,e){
		if( nickname==null || nickname=='' ){
			unablenickname.removeClass("phide");
			ablenickname.addClass("phide");
			return;
		}else{
			startRequest('user.action?op=checkNickname&nickname='+nickname,handleStateChange);
		}
	}
	
	function handleStateChange(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
					if( xmlHttp.responseText>0 ){
						unablenickname.html("该昵称已被使用");
						unablenickname.removeClass("phide");
					}else{
						ablenickname.html("昵称可用");
						ablenickname.removeClass("phide");
					}
				}
			}
	}
	
	$("#nickname").keydown(function() {
 		 $("#unablenickname").addClass("phide");
	});
	
	function codeCheck(){
		var code=$("#acpro_inp2").val();
		var email=$("#email").val();
		var pwd=$(".pwd").val();
		startRequest('user.action?op=loginCodeCheck&code='+code,handleStateChange2);
	}
	function handleStateChange2(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
					if( xmlHttp.responseText==0 ){
						$("#errorCode").removeClass("phide");
						$("#errorCode").css("background-position","-79px -1809px").text("");
					}else{
						location.href="show.jsp";
					}
				}
			}
	}
	
	$(function (){
		$("#warninfo").slideDown('slow').delay(1000).slideToggle('slow');
	});
	
	$(".nickname").click(function() {
		$(".nickname label").css("display", "none");
	});
	$(".valcodearea").click(function() {
		$(".valcodearea label").css("display", "none");
	});
	
	$(".nickname").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#nickname").val().length == 0) {
					$(".nickname label").css("display", "block");
				}
			}
		})

		$(".valcodearea").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#acpro_inp2").val().length == 0) {
					$(".valcodearea label").css("display", "block");
				}
			}
		})
	
</script>
