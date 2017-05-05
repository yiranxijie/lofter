<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>chat_index</title>
<link href="http://l.bst.126.net/s/pt_lib_macro.css?1c393a50ad7192b3668a9dc6a28a33bc" type="text/css" rel="stylesheet"/>
<link href="http://l.bst.126.net/s/pt_page_reg.css?18aafcbdeffdf390b9f323018b6041ec" type="text/css" rel="stylesheet"/>

</head>

<body>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a, img{border:0;}
body{font:12px/180% "微软雅黑", Arial, Helvetica, sans-serif;}

/* film_focus */
.film_focus{width:857px;height:340px;overflow:hidden;position:relative;margin:20px auto;margin-top:70px;}
.film_focus .film_focus_imgs_wrap{background:url(images/load.gif) no-repeat center center;}
.film_focus ul.film_focus_imgs{height:340px;height:9999em;position:absolute;right:0;top:0;overflow:hidden;}
.film_focus ul.film_focus_imgs li{height:340px;overflow:hidden;}
.film_focus ul.film_focus_imgs li img{height:340px;width:626px;}
.film_focus .film_focus_desc h3{height:45px;line-height:45px;overflow:hidden;position:absolute;left:232px;bottom:0;background:rgba(0, 0, 0, .5);color:#fff;width:100%;padding-left:20px;z-index:99;font-size:16px;filter:progid:DXImageTransform.Microsoft.gradient(enabled='true', startColorstr='#7F000000', endColorstr='#7F000000');}
.film_focus ul.film_focus_nav{width:232px;height:340px;position:absolute;left:0;top:0;z-index:100;}
.film_focus ul.film_focus_nav li{height:47px;background:#d7d7dc;margin:0px 0px 5px 0;padding:0px 18px 0 19px;position:relative;width:190px;cursor: pointer;cursor: hand;color:#333;font-weight:bold;font-size:14px;overflow:hidden;line-height:47px;}
.film_focus ul.film_focus_nav li.cur{background:url(images/hd_on.png) no-repeat;width:190px;height:132px;left:0px;padding:0px 33px 0 25px;word-break:break-all;color:#fff;font-weight:bold;font-size:22px;overflow:hidden;line-height:30px;}
.film_focus ul.film_focus_nav li b{display:none}
.film_focus ul.film_focus_nav li b span{display:block}
.film_focus ul.film_focus_nav li.cur b{display:block;vertical-align: middle;display: table-cell;height:132px;}
.film_focus ul.film_focus_nav li.cur span{display:none}

.nav2{
	float:right;
	position:relative;
	top:-40px;
	left:-243px;
	width:184px;
	height:26px;
	margin-left:26px;
	padding:2px 10px 2px 32px;
	background:url(img/icon4-24.png?016) 0px -40px no-repeat;
	z-index:65;
}

.nav2 .btn{
	display:block;
	position:absolute;
	top:6px;
	left:10px;
	width:18px;
	height:18px;
	background:url(img/icon4-24.png?016) 0px -81px no-repeat;	
}

.nav2 input{
	position:relative;
	width:144px;
	height:18px;
	padding:4px 45px 2px 0;
	margin:0;
	border:none;
	background-color:transparent;
	outline:none;
	line-height:18px;
	color:#fff;
	z-index:3;
}

.left_arrow{
	width:28px;
	height:48px;
	margin-left:20px;
	margin-top:120px;
	position:absolute;
	z-index:3;
	display:none;
	background:url(img/tong.jpg) -122px -170px no-repeat;
}

.right_arrow{
	width:28px;
	height:48px;
	margin-left:210px;
	margin-top:120px;
	position:absolute;
	z-index:3;
	display:none;
	background:url(img/tong.jpg) -169px -172px no-repeat;
}

.left_area{
	width:250px;
	height:340px;
	margin-left:230px;
	position:absolute;
	z-index:6;
}

.right_area{
	width:250px;
	height:340px;
	margin-left:605px;
	position:absolute;
	z-index:6;
}

.suggest{
	position: absolute;
	width:226px;
	overflow:hidden;
	left:-2px;
}

.suggest a{
	display:block;
	height:22px;
	padding:9px 16px 9px 42px;
	line-height:22px;
	background-color:#333;
}

.suggest a:hover{
	background-color:#AAA;
}

.sugbox{
	width:656px;
	padding-top:11px;
	background:url(images/icon4-24.png?016) -9999px -9999px no-repeat;
	background-position:105px -12px;
	visibility:hidden;
}

.sugitm{
	margin:-1px 0 1px;
	border-top:1px solid #202020;
	border-bottom:1px solid #0C0C0C;
	background-color:#191919;
	cursor:pointer;
}
.sugitm span{
	font-weight:normal;
	color:#fff;
	float:left;
	width:168px;
	overflow:hidden;
	font-size:12px;
	white-space:nowrap;
}
</style>

<body class="keBody p-newreg">
<div class="g-hdreg">
		<div class="head">
		    <h1 class="logo"><a rel="nofollow" href="http://www.lofter.com/">LOFTER-网易轻博</a></h1>
		</div>
		
		<div class="nav2" id="schtagbox">
            <div class="ssch tbtag">
                  <form class="xtag">
                    <a href="#" class="btn xtag" onclick="searchroom()"></a>
                    <label class="xtag" style="display:none;">搜索房间号或房间名</label>
                      <input type="text" placeholder="搜索房间号或房间名" class="xtag" onfocus="this.isfocus = true;"/>
                      <div class="xtag a-show-mask f-op0">
                        <div class="new" style="display:none;" id="nav2flnew"></div>
                      </div>
                  </form>
              </div>
              
	              <div class="suggest">
	              	<div class="sugbox">
	              		<div class="sugitm">
	              			<a href="#"><span></span></a>
	              		</div>
	              	</div>
	              </div>
        </div>
</div>
<div class="film_focus">
	<div class="film_focus_desc">
		<h3>文化</h3>
		<ul class="film_focus_nav">
			<li class="cur"><b>文化</b><span>文化</span></li>
			<li><b>体育</b><span>体育</span> </li>
			<li><b>生活</b><span>生活</span> </li>
			<li><b>美食</b><span>美食</span> </li>
			<li><b>科技</b><span>科技</span> </li>
		</ul>
	</div>
	<div class="film_focus_imgs_wrap">
		<div class="left_area"><div class="left_arrow"></div></div>
		<div class="right_area"><div class="right_arrow"></div></div>
		<ul class="film_focus_imgs">
			<li><a target="_blank" href="http://www.17sucai.com/"><img src="images/a4.jpg" width="626" height="340" alt="文化" /></a></li>
			<li><a target="_blank" href="http://www.17sucai.com/"><img src="images/a2.jpg" width="626" height="340" alt="体育" /></a></li>
			<li><a target="_blank" href="http://www.17sucai.com/"><img src="images/a3.jpg" width="626" height="340" alt="生活" /></a></li>
			<li><a target="_blank" href="http://www.17sucai.com/"><img src="images/a1.jpg" width="626" height="340" alt="美食" /></a></li>
			<li><a target="_blank" href="http://www.17sucai.com/"><img src="images/a5.jpg" width="626" height="340" alt="科技" /></a></li>
		</ul>
	</div>
</div>

<script type="text/javascript" src="js/chatindex.js"></script> 
<!-- 按房间名或房间号搜索房间 -->
<script type="text/javascript">
	function searchroom(){
		var chattopic=$(".ssch input").val();
		$.ajax({
			type : "POST",
			url : "chat.action?op=searchroom",
			data : "chattopic="+chattopic,
			success : function(msg) {
				$(".sugitm").html(msg);
				$(".sugbox").css("visibility","visible");
				$(".sugitm a").click(function(){
					$.ajax({
						type : "POST",
						url : "chat.action?op=enter",
						data : "chattopic="+chattopic,
						success : function(msg) {
							location.href="chatroom.jsp";
						}
					});
				});
			}
		});
	}
</script>

<script type="text/javascript">
	$(".xtag").blur(function(){
		$(".sugbox").css("visibility","hidden");
	});
</script> 

<script type="text/javascript">
(function(A){
	A.fn.th_video_focus=function(E){
		
		var G={
			actClass:"cur",
			navContainerClass:".focus_pic_preview",
			focusContainerClass:".focus_pic",
			animTime:600,
			delayTime:9000000
		};
		
		if(E){
			A.extend(G,E)
		}
		
		var C=G.actClass, D=G.navContainerClass, B=G.focusContainerClass, F=G.animTime, H=G.delayTime, I=null;
		
		return this.each(function(){
			
			var O=A(this), M=A(D+" li",O), P=A(B+" li",O), L=M.length, K=O.height();
			
			function N(R){
				var V=K*R*-1;
				var U=A(B+" li",O), W=null, T=null;
				for(var S=0;S<=R;S++){
					W=U.eq(S);
					T=W.find('script[type="text/templ"]');
					if(T.length>0){
						W.html(T.html())
					}
				}
				A(B,O).stop().animate({top:V},F,function(){
					var Y=O.find("h3"), X=Y.height();
					Y.height(0).html(A(B+" li").eq(R).find("img").attr("alt")).animate({height:X},600)
				});
				A(D+" li").eq(R).addClass(C).siblings().removeClass(C)
			}
			
			function Q(){
				if(I){
					clearInterval(I)
				}
				I=setInterval(function(){
					var R=A(D+" li."+C).index();
					N((R+1)%L)
				},H)
			}
			
			O.hover(function(){
				if(I){
					clearInterval(I)
				}
			},function(){
				Q()
			});
		
			var J=null;
			
			M.hover(function(){
				
				var R=A(this).index();
				
				if(I){
					clearInterval(I)
				}
				J=setTimeout(function(){
					N(R)
				},300)
			},function(){
				if(J){
					clearTimeout(J)
				}
				Q()
			}).click(function(T){
				var R=A(this).index(), S=P.eq(R).find("a");
				if(document.uniqueID||window.opera){
					S[0].click();
					T.stopPropagation();
					T.preventDefault()
				}
			});
			
			Q()
			
		})
	}
	
})(jQuery);

$(function(){
	
	
	$(".film_focus").th_video_focus({
		navContainerClass:".film_focus_nav",
		focusContainerClass:".film_focus_imgs",
		delayTime:30000000
	});
	
});	
</script>

</body>
</html>

<script type="text/javascript">
	$(".left_area").mouseover( function(){
		$(".left_arrow").show();
	}).mouseout(function(){
		$(".left_arrow").hide();
	});
	
	$(".right_area").mouseover( function(){
		$(".right_arrow").show();
	}).mouseout(function(){
		$(".right_arrow").hide();
	});
</script>