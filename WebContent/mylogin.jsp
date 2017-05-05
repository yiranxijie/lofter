<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>LOFTER（乐乎）</title>
<meta name="keywords" content="LOFT,LOFTER,乐乎,网易轻博,轻博客,博客,社区,摄影" />
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=emulateie7" />
<meta name="baidu-site-verification" content="MoJbU3Q9MoAvorDC" />
<meta name="author" content="netease" />
<meta name="version" content="1.0" />
<meta name="description"
	content="LOFTER(乐乎)是种随性的记录方式，可方便地分享照片、文字、音乐和视频。同时作为国内最优质的兴趣社区，汇聚超过千万的各类兴趣玩家，通过标签发现志趣相投的人。" />

<link rel="stylesheet" type="text/css" href="css/tab.css">
<link rel="stylesheet" type="text/css" href="css/DB_tab29.css">
<link rel="stylesheet" type="text/css" href="css/mylogin.css">
<script src="js/common.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="js/jquery.DB_rotateRollingBanner.min.js"></script>

</head>
<style>
</style>

<body>
	<div id="warninfo"
		style="position: absolute;padding:5px;top:295px;z-index:2;display:none;left:580px;font-size:14px;height:40px;line-height:40px;float:left;border:1px solid #cfc;color:#fff;background: red;">This
		is warninfo!</div>
	<div class="block1">

		<div class="logo">
			<h1 class="f1">plaLOFTER</h1>
			<input type="hidden" value="${error }">
		</div>


		<div class="wrap">
			<div class="tabs">
				<a href="#" hidefocus="true" class="active">登陆</a> <a href="#"
					hidefocus="true">注册</a>
			</div>
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<div class="content-slide">

							<form method="POST" name="loginForm" id="loginForm"
								action="topic.action?op=show">
								<input type="hidden" name="op" value="login" />
								<table width="100%" border="0">
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									<tr>
										<td valign="top" align="center" colspan="2">
											<div class="login_email"
												style="width:300px;height:40px;line-height:40px;border:1px solid #d7d7d7;position:relative;line-height:22px;">
												<label
													style="color:#999;top:8px;z-index:1;position:absolute;right:16px;">常用邮箱/网易邮箱</label>
												<input type="text" onblur="checkloginuser(this.value)"
													name="email" id="login_email" size="20" class="input"
													style="border:0 none;color:#999;outline: medium none;font-size:14px;left:16px;top:8px;position:absolute;">
												<div id="email_correct"
													style="display:none;position:absolute;width:30px;height:30px;left:320px;top:10px;background: url(images/btn.png);background-position:-352px -830px;"></div>
											</div>
											<div class="login_emailinfo"></div></td>
									</tr>

									<tr>
										<td valign="top" align="center" colspan="2"><div
												class="login_pwd"
												style="width:300px;height:40px;line-height:40px;border:1px solid #d7d7d7;position:relative;line-height:22px;">
												<label
													style="color:#999;top:8px;z-index:1;position:absolute;right:16px;">密码</label>
												<input type="password" name="pwd" size="20" class="input"
													id="login_pwd"
													style="border:0 none;color:#999;outline: medium none;font-size:14px;left:16px;top:8px;position:absolute;">
											</div></td>
									</tr>

									<tr>
										<td colspan="2" align="center">
											<div class="submit">
												<button type="submit" name="Submit" value="" id="myloginbtn"
													onclick="loginbtn()"
													style="background: url(images/btn.png);background-position:-1px -461px;width:300px;height:40px;line-height:40px;border:0 none;">
													<span style="font-size:18px;color:#fff;letter-spacing:3px;">登录</span>
												</button>
												<input type="hidden" name="login_flag" value="fromlogin">
											</div>
										</td>
									</tr>
									<tr>
										<td height="33">&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</form>

						</div>
					</div>
					<div class="swiper-slide">
						<div class="content-slide">

							<!-- 注册 -->
							<form method="POST" name="RegForm" action="user.action?op=reg">
								<input type="hidden" name="op" value="login" />
								<table width="100%" border="0">
									<tr>
										<td width="30%">&nbsp;</td>
										<td width="70%">&nbsp;</td>
									</tr>
									<tr>
										<td valign="top" align="center" colspan="2">
											<div class="email"
												style="width:300px;height:40px;line-height:40px;border:1px solid #d7d7d7;position:relative;line-height:22px;">
												<label
													style="color:#999;top:8px;z-index:1;position:absolute;right:16px;">常用邮箱/网易邮箱</label>
												<input type="text" name="email" id="email_input" size="20"
													class="input" onblur="checkuser(this.value)"
													style="border:0 none;color:#999;outline: medium none;font-size:14px;left:16px;top:8px;position:absolute;">
											</div>
											<div class="emailinfo"></div></td>
									</tr>

									<tr>
										<td valign="top" align="center" colspan="2">
											<div class="pwd"
												style="width:300px;height:40px;line-height:40px;border:1px solid #d7d7d7;position:relative;line-height:22px;">
												<label
													style="color:#999;top:8px;z-index:1;position:absolute;right:16px;">设置密码</label>
												<input type="password" name="pwd" id="pwd_input" size="20"
													class="input"
													style="border:0 none;color:#999;outline: medium none;font-size:14px;left:16px;top:8px;position:absolute;">
											</div></td>
									</tr>

									<tr>
										<td colspan="2" align="center">
											<div class="submit">
												<button type="submit" name="Submit" value="注册" id="submit"
													style="background: url(images/btn.png);background-position:-1px -461px;width:300px;height:40px;line-height:40px;border:0 none;">
													<span style="font-size:18px;color:#fff;letter-spacing:3px;">注册</span>
												</button>
											</div>
										</td>
									</tr>
									<tr>
										<td height="33">&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
								</table>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="block2">
		<h2>彰显个性的个人主页</h2>
		<p class="sub">
			打造专属自己的个人主页，<br>让你的个人主页变得与众不同，让你天马行空的想法肆意飞行。
		</p>

		<div id="d_tab29">

			<ul class="d_img">


				<li class="d_pos1"><img src="images/blog-1.jpg" alt="" /></li>

				<li class="d_pos2"><img src="images/blog-2.jpg" alt="" /></li>

				<li class="d_pos3"><img src="images/blog-3.jpg" alt="" /></li>

				<li class="d_pos4"><img src="images/blog-4.jpg" alt="" /></li>

				<li class="d_pos5"><img src="images/blog-5.jpg" alt="" /></li>

				<li><img src="images/blog-1.jpg"></li>

				<li><img src="images/blog-2.jpg"></li>

				<li><img src="images/blog-3.jpg"></li>

				<li><img src="images/blog-4.jpg"></li>

				<li><img src="images/blog-5.jpg"></li>

			</ul>

			<ul class="d_menu">
			</ul>

			<p class="d_prev">
				<img src="images/prev.png" alt="">
			</p>
			<p class="d_next">
				<img src="images/next.png" alt="">
			</p>

		</div>



		<!--<div class="cont">
			<div class="m-slider">
				<ul id="templateslide" class="f-cb" style="left: -3451px;">
                    <li>
                    <img width="720px" height="430px" src="images/blog-1.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-2.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-3.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-4.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-5.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-1.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-2.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-3.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-4.jpg">
                    </li>
                    <li>
                    <img width="720px" height="430px" src="images/blog-5.jpg">
                    </li>
				</ul>
			</div>
		</div>-->
	</div>

	<script>
		$('#d_tab29').DB_rotateRollingBanner({
			key : "c37080",
			moveSpeed : 200,
			autoRollingTime : 5000
		})
	</script>

</body>
</html>


<script src="http://www.lanrenzhijia.com/ajaxjs/jquery-1.10.1.min.js"></script>
<script
	src="http://www.lanrenzhijia.com/ajaxjs/idangerous.swiper.min.js"></script>
<script>
	var tabsSwiper = new Swiper('.swiper-container', {
		speed : 500,
		onSlideChangeStart : function() {
			$(".tabs .active").removeClass('active');
			$(".tabs a").eq(tabsSwiper.activeIndex).addClass('active');
		}
	});

	$(".tabs a").on('touchstart mousedown', function(e) {
		e.preventDefault()
		$(".tabs .active").removeClass('active');
		$(this).addClass('active');
		tabsSwiper.swipeTo($(this).index());
	});

	$(".tabs a").click(function(e) {
		e.preventDefault();
	});
</script>
<
<script type="text/javascript">
	var emailinfo = $(".emailinfo");
	var login_emailinfo = $(".login_emailinfo");
	var regbtn = $("#submit");
	//注册用户验证
	function checkuser(email) {
		if (email == null || email == '') {
	//		emailinfo.html("邮箱不能为空");
	//		emailinfo.addClass("error");
			$("#warninfo").text("邮箱不能为空,请重新输入！");
			$("#warninfo").fadeIn("slow").delay(1000).fadeOut("slow");
			regbtn.prop({
				disabled : true
			});
			return;
		} else {
			startRequest('user.action?op=checkuser&email=' + email,
					handleStateChange);
		}
	}

	//登陆用户验证
	function checkloginuser(email) {
		if (email == null || email == '') {
	//		login_emailinfo.html("邮箱不能为空");
	//		login_emailinfo.addClass("error");
			$("#warninfo").text("邮箱不能为空,请重新输入！");
	//		$("#warninfo").animate({left:'0'}).delay(2000).animate({left:'-205px'});
			$("#warninfo").fadeIn("slow").delay(1000).fadeOut("slow");
			return;
		} else {
			startRequest('user.action?op=checkloginuser&email=' + email,
					handleStateChange2);
		}
	}
	
	function handleStateChange2() {
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
			if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
				if (xmlHttp.responseText > 0  ) {  
					login_emailinfo.removeClass();
					login_emailinfo.addClass("emailinfo");
					$("#email_correct").css("left","320px");
					$("#email_correct").css("display","block");
					$("#myloginbtn").attr("disabled",false);
				} else {
				//	login_emailinfo.html("该用户不存在，请先注册");
				//	login_emailinfo.addClass("error");
					$("#warninfo").text("该用户不存在，请先注册！");
					$("#warninfo").fadeIn("slow").delay(1000).fadeOut("slow");
					$("#myloginbtn").attr("disabled",true);
				}
			}
		}
	}

	//注册回调函数
	function handleStateChange() {
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
			if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
				if (xmlHttp.responseText > 0) {
				//	emailinfo.html("该邮箱已被注册");
				//	emailinfo.addClass("error");
					$("#warninfo").text("该邮箱已被注册！");
					$("#warninfo").fadeIn("slow").delay(1000).fadeOut("slow");
					regbtn.prop({
						disabled : true
					});
				} else {
				//	emailinfo.html("该邮箱可用");
				//	emailinfo.addClass("correct");
				//	$("#email_correct").css("left","920px");
				//	$("#email_correct").css("display","block");
					regbtn.prop({
						disabled : false
					});
				}
			}
		}
	}

	$(".input").click(function() {
		emailinfo.removeClass();
		emailinfo.addClass("emailinfo");
	});
	//注册页面
	$("#email_input").click(function() {
		$(".email label").css("display", "none");
		$(".login_email label").css("display", "block");
		$("#login_email").val("");
		$("#email_correct").css("display","none");
		$("#login_pwd").val("");
		$(".login_pwd label").css("display", "block");
	});
	$("#pwd_input").click(function() {
		$(".pwd label").css("display", "none");
	});
	//登录页面
	$("#login_email").click(function() {
		$(".login_email label").css("display", "none");
		$(".email label").css("display", "block");
		$("#email_input").val("");
		$("#email_correct").css("display","none");
		$("#pwd_input").val("");
		$(".pwd label").css("display", "block");
	});
	$("#login_pwd").click(function() {
		$(".login_pwd label").css("display", "none");
	});

	$(function() {
		//	$("#email_input").focus();
		//	$("#login_email").focus();

		$(".login_email").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#login_email").val().length == 0) {
					$(".login_email label").css("display", "block");
				}
			}
		})

		$(".login_pwd").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#login_pwd").val().length == 0) {
					$(".login_pwd label").css("display", "block");
				}
			}
		})

		$(".email").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#email_input").val().length == 0) {
					$(".email label").css("display", "block");
				}
			}
		})

		$(".pwd").keyup(function(event) {
			if (event.keyCode == 8) {
				if ($("#pwd_input").val().length == 0) {
					$(".pwd label").css("display", "block");
				}
			}
		})
	});
	
	$(function(){
		if( $(".logo").find("input").val()=="error" ){
		//	alert($(".logo").find("input").val());
			$("#warninfo").text("账号或密码错误！");
			$("#warninfo").fadeIn("slow").delay(1000).fadeOut("slow");
		}
	});

</script>



