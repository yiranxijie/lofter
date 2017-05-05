<%@ page language="java" import="java.util.*,com.lofter.bean.*"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	application.setAttribute("basePath", basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>LOFTER(乐乎)</title>
<meta charset="utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link
	href="http://l.bst.126.net/s/pt_lib_macro.css?73169a1710a7901b058deb2988cd08c6"
	type="text/css" rel="stylesheet" />


<link
	href="http://l.bst.126.net/s/pt_page_newpublish_dashboardnew.css?7d5df08569b346fa51054fcc21d89855"
	type="text/css" rel="stylesheet" />

<link rel="stylesheet" type="text/css"
	href="http://l.bst.126.net/rsc/js/ueditor/themes/loftskin2/ueditor.css?v=20131115" />

<link
	href="http://l.bst.126.net/s/pt_page_newpublish_dashboard_common.css?cf43a1c9dfbd8842290462f15e8b7a14"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="control/css/zyUpload.css" type="text/css" />


<style type="text/css">
@charset "utf-8";
/* CSS Document */
.ztxt {
	color: #777;
	cursor: pointer;
	float: left;
	font-size: 16px;
	height: 35px;
	line-height: 35px;
	max-width: 191px;
	padding: 2px 0 15px;
}

.thied {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	word-break: keep-all;
}

.blogTittle {
	border-bottom: 1px solid #dfdfdf;
	height: 40px;
	line-height: 40px;
	margin-bottom: -1px;
	position: relative;
	font-size: 16px;
	color: #777;
}

.blogTittle .title {
	width: 70%;
	font-size: 14px;
}

.blogBtn {
	border: 1px solid #dfdfdf;
	height: 40px;
	line-height: 40px;
	position: relative;
}

.blogul {
	list-style: none;
}

.blogli {
	float: left;
	display: inline;
}

.previewBtn {
	float: right;
	background-position: 0 1px;
	width: 120px;
	border: medium none;
	color: #888;
	font-size: 16px;
	height: 50px;
	line-height: 50px;
	margin: 0;
	outline: medium none;
	padding: 0;
	text-shadow: -1px -1px 2px #f8f8f8;
}

.bloginp {
	width: 120px;
	height: 30px;
	line-height: 30px;
	background-color: #7CA600;
	color: #fff;
	text-shadow: -1px -1px 0 #779e00;
	width: 109px;
}

.show {
	display: block;
}

.hide {
	display: none;
}

.transparent_class {
	filter: alpha(opacity =     30);
	-moz-opacity: 0.3;
	-khtml-opacity: 0.3;
	opacity: 0.3;
}

.pulish_content {
	background-position: -1015px 0;
	background-repeat: repeat-y;
	padding: 3px 32px 28px 53px;
	background-color: #ffffff;
	border: 1px solid #cccccc;
	margin-top: -69px;
}

.title {
	font-size: 18px;
	font-weight: normal;
	margin: 0 0 16px;
	word-wrap: break-word;
}

.m-content {
	clear: both;
	content: ".";
	display: block;
	overflow: hidden;
}

.cnt {
	position: relative;
	width: 100%;
	text-align: center;
	color: #aa;
	height: auto;
}

.cnt .image {
	float: left;
	height: auto;
	width: 40%;
}

.cnt .image img {
	height: auto;
	width: 100%;
}

.cnt .text {
	float: left;
	padding-left: 20px;
}

.operation {
	clear: both;
	color: #aaa;
	line-height: 19px;
}

.ul {
	list-style: none;
	float: right;
}

.li {
	display: inline;
	padding-right: 20px;
}

#publishpic {
	border: 1px solid #cccccc;
	background-color: #ffffff;
	height: auto;
}

#publishpic .publishpic_nickname {
	border-bottom: 1px solid #cccccc;
	width: 90%;
	height: 40px;
	line-height: 40px;
	margin: 20px;
	font-size: 16px;
}

#publishpic .imgupload {
	border: 1px dashed #cccccc;
	width: 90%;
	margin: 20px;
	height: 400px;
}

#publishpic .imgupload .imguploadbtn {
	padding: 100px 200px;
}

#publishpic .imgupload .imguploadbtn .tip {
	padding-left: 55px;
	font-size: 16px;
}

#publishpic .editor {
	display: block;
}

.imgshow {
	height: 40%;
	width: 45%;
}
</style>

</script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.alerts.js"></script>
<link rel="stylesheet" href="css/jquery.alerts.css" type="text/css"></link>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>

<!-- 引用核心层插件 -->
<script type="text/javascript" src="core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="control/js/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript" src="js/demo.js"></script>
<body class="page-lofter" style="height: 100%;"
	onload="dwr.engine.setActiveReverseAjax(true);">

	<!--页头-->
	<div class="g-hd">
		<div id="lofterheadbar" class="g-hdc">
			<h1 class="m-logo">
				<a href="topic.action?op=show&email=${user.email }&pwd=${pwd}">LOFTER-网易轻博</a>
			</h1>
			<div class="m-nav" id="topbar">
				<ul class="nav1 tbtag">
					<li class="j-crt"><a rel="nofollow"
						href="topic.action?op=show&email=${user.email }&pwd=${pwd}">首页</a>
					</li>
					<li id="chat" class=""><a rel=""
						href="chat.action?op=chat&email=${user.email }">话题</a>
					</li>
					<li id="viewFirstClickStatus" class=""><a
						href="javascript:void(0)" onclick="scanAllUser()">浏览</a></li>
					<li id="exit" class=""><a href="user.action?op=exit&userid=${user.id }">退出</a>
					</li>
				</ul>
			</div>
		</div>
	</div>

	

	<div class="g-bd">
		<!--右边个人中心-->
		<div id="rside" class="g-sd"">
			<div class="g-box">
				<div class="m-menu">
					<div class="menut"></div>
					<div class="menum">
						<ul>
							<li class="first large" id="blogmanage"><a id="489581121"
								href="homepage.action?op=show&userid2=${user.id }"
								class="mi usname"><span class="txt"><span class="lg1">${user.nickname
											}</span> <input type="hidden" name="email" value="${user.email }" />
										<input type="hidden" name="userid" id="curloginuserid"
										value="${user.id }" /> <span class="lg2"
										style="display:none;">balagengaobaxiong.lofter.com</span> </span> </a>
								<div class="a-w-sel a-w-sel-6">
									<div class="w-sel w-sel-6 bmtag" style="margin-top:-9999px;">
										<div class="selc">
											<div id="bloglstp" class="selcc bmtag">
												<div class="seli add">
													<a class="user j-blogman" href="/newblog">创建新主页</a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</li>
							<li style="border-color:#b2b2b2;" class=""><div class="crtp"></div>
								<a href="javascript:personalTopicShow()" class="mi"><span
									class="icn icn-9"></span><span class="txt">文章</span><span
									class="inf inf-1" id="rside-postcount">${topicCount }</span> </a></li>
							<li class=""><a href="javascript:fans()" class="mi"><span
									class="icn icn-10"></span><span class="txt">粉丝</span><span
									class="inf inf-1">${focusedCount }</span> </a></li>
							<li><a class="mi" href="javascript:focus()"><span
									class="icn icn-1"></span><span class="txt">关注</span><span
									class="inf inf-1">${showfocusCount }</span> </a></li>

						</ul>
					</div>
					<div class="menub"></div>
				</div>
			</div>
		</div>

		<!--左边主体-->
		<div id="personaltopic">
			<div id="main" class="g-mn">
				<div id="publishBarArea" class="publishBarArea"
					style="display:block">
					<ul id="publishPostBar" class="m-nav2" style="">
						<li class="user"><a
							href="homepage.action?op=show&userid2=${user.id }"><img
								src="images/${user.head }"> </a></li>
						<li><a class="publishlink n21"
							href="javascript:showPublishtext()"
							bakhref="/blog/balagengaobaxiong/new/text/">文字</a>
						</li>
						<li><a class="publishlink n22"
							href="javascript:showPublishpic()"
							bakhref="/blog/balagengaobaxiong/new/photo/">图片</a>
						</li>
						<li><a class="publishlink n23" href="javascript:showPublishpic()"
							bakhref="/blog/balagengaobaxiong/new/music/">音乐</a>
						</li>
						<li><a class="publishlink publishlink-video n24"
							href="javascript:showPublishpic()"
							bakhref="/blog/balagengaobaxiong/new/video/">视频</a></li>
					</ul>

					<!-- 发布纯文字 -->
					<div id="publishtext" style="display:none">

						<div class="blogTittle">${user.nickname }</div>
						<form id="formtext">
							<input type="hidden" name="userid" value=${user.getId() }
								id="userid"> <input type="hidden" name="topictype"
								value="文字" id="topictype">

						</form>
						<div>


							<div class="blogTittle">
								<input type="text" name="title" id="title" class="title">
								<label>标题（可不填）</label>
							</div>
							<textarea rows="10" cols="50" name="content" id="content"></textarea>
							<script type="text/javascript">
								CKEDITOR.replace('content');
							</script>

							<div class="blogBtn">
								<ul class="blogul">
									<li class="blogli"><button class="bloginp"
											onclick="hidePublishtext()">取消</button></li>
									<li class="blogli previewBtn"><button class="bloginp">预览</button>
									</li>
									<li class="blogli previewBtn"><button class="bloginp"
											onclick="sendMessage()" id="btntext">发布</button></li>

								</ul>
							</div>

						</div>


					</div>
					<!-- 发布图文 -->
					<div id="publishpic" class="isaym" style="display:none">
						<div class="publishpic_nickname">
							<label>${user.nickname }</label>
						</div>

						<input type="hidden" name="userid" value=${user.id } id="userid">
						<input type="hidden" name="topictype" value="图文" id="topictype">
						<div class="imgupload">
							<div id="demo" class="demo"></div>
							<div
								style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">

							</div>

						</div>

						<div>
							<div class="editor" id="editorpic">
								<textarea rows="10" cols="50" name="content" id="contentpic"></textarea>
								<script type="text/javascript">
								CKEDITOR.replace('contentpic');
							</script>
							</div>

							<div class="blogBtn">
								<ul class="blogul">
									<li class="blogli"><button class="bloginp"
											onclick="hidePublishpic()">取消</button></li>
									<li class="blogli previewBtn"><button class="bloginp">预览</button>
									</li>
									<li class="blogli previewBtn"><button class="bloginp"
											onclick="sendMessage()" id="btnpic">发布</button></li>

								</ul>
							</div>

						</div>
					</div>
				</div>
				<!--                               -->




				<!--隐藏域，假如有没发布的文章，则会自动显示出来-->
				<div style="display:none" id="tempSaveTipArea"
					class="tempSaveTipArea">
					<div class="tempsavetipwrap">
						你还有一篇未发布的文章。<a class="gotemppost" href="#">查看</a><a
							class="closetip icon2-8" href="#" hidefocus="true">&nbsp;</a>
					</div>
				</div>

				<!--通知提示-->
				<div id="noticetip" class="a-slide a-slide-do"></div>

				<!--首次加载的动态效果-->
				<!-- <div class="w-load2" id="loadfirst" >
                <div class="load">加载中</div> 
            </div> -->

				<!-- 消息发布的隐藏域 -->
				<div id="publish" style="dispaly:none;">
					<div class="mlistimg js-673810"></div>

				</div>
				<!-- 图片显示路径掩藏域 -->

				<input type="hidden" id="pathpic"> <input type="hidden"
					id="size" value="${size }"> <input type="hidden" id="page"
					value="${page }"> <input type="hidden" id="count"
					value="${count }">
				<!--显示发布的版块 -->
				<div id="list"></div>
				<!--                               -->
				<!--图文版块-->



				<c:forEach items="${listTopicModel }" var="topicModel"
					varStatus="num">
					<div class="m-mlist" id="mlist ${topicModel.topicid }"
						style="display:block">
						<div class="mlistimg js-673810">

							<a href="homepage.action?op=show&userid2=${topicModel.userid }">
								<div class="w-img" style="z-index:1;">
									<img class="js-673810" src="images/${topicModel.head }">
								</div> </a>

						</div>
						<div class="mlistcnt">
							<div class="isay">
								<div class="isayt">
									<a class="isayc js-673810" target="_blank"
										title="查看全文 - 05/05 13:46" href="javascript:void(0)">打开新页</a>
								</div>

								<div class="isaym">
									<div class="w-who">
										<a class="js-673810" target="_blank"
											href="homepage.action?op=show&userid2=${topicModel.userid }">${topicModel.nickname
											}</a>
										<c:if test="${topicModel.userid=='0' }">
											<a
												class="js-673810 w-icn2 w-icn2-3 a-followicon icontag1889734 ${topicModel.userid}"
												href="javascript:void(0)" onclick="dofocus(this)"
												title="关注 ${topicModel.nickname
											} ${topicModel.userid}">关注
											</a>
										</c:if>
									</div>
									<div class="js-673810">
										<div class="m-icnt">
											<div class="cnt">
												<c:if test="${topicModel.topictype!='文字'  }">
													<div class="img js-800786"
														style="width: 164px; height: auto;">
														<div class="imgc">
															<img id="img" style="width: 164px;"
																src="${topicModel.picid }" onclick="imgclick(this)">
														</div>
													</div>
												</c:if>
												<div class="txt js-800786">${topicModel.content }</div>
											</div>
											<input type="hidden" name="collectRecord" value="" /> <input
												type="hidden" name="praiseRecord" value="" />
										</div>
									</div>

									<!-- 每循环一个Topic都去请求一次数据  -->
									<script>
									
									</script>

									<div class="w-opt">
										<div class="opta js-673810" style="width: 176px;"></div>
										<div class="optb">
											<span class="opti"> 热度(${topicModel.praisecount }) <span
												class="opticrt"></span> </span> <span class="opti opti-crt"
												id="opti opti-crt"> <a class="js-673810"
												hidefocus="true"
												href="javascript:showReply('${topicModel.topicid }')">评论(${topicModel.replycount
													})</a> <span class="opticrt" style="display:none;" id="opticrt"></span>
											</span>
											<c:choose>
												<c:when test="${topicModel.collectrecord=='1' }">
													<span class="opti opti-shareTo"> <a
														class="js-673810 collect" hidefocus="true"
														href="javascript:void(0)" onclick="collect(this)">已收藏</a>
														<span class="opticrt"></span> </span>
												</c:when>

												<c:otherwise>
													<span class="opti opti-shareTo"> <a
														class="js-673810 collect" hidefocus="true"
														href="javascript:void(0)" onclick="collect(this)">收藏</a> <span
														class="opticrt"></span> </span>
												</c:otherwise>
											</c:choose>

											<c:choose>
												<c:when test="${topicModel.praiserecord=='1' }">
													<span class="opti"> <a
														class="w-icn w-icn-0b js-673810 w-icn-0b-do"
														href="javascript:void(0)" hidefocus="true"
														title="喜欢${topicModel.praiserecord }" onclick=praise(this)>
															喜欢 <span></span> <span></span> </a> </span>
												</c:when>

												<c:otherwise>
													<span class="opti"> <a
														class="w-icn w-icn-0b js-673810 "
														href="javascript:void(0)" hidefocus="true"
														title="取消喜欢${topicModel.praiserecord }"
														onclick=praise(this)> 取消喜欢 <span></span> <span></span>
													</a> </span>
												</c:otherwise>
											</c:choose>

										</div>
									</div>
								</div>

								<%
									session.removeAttribute("collectRecord");
										session.removeAttribute("praiseRecord");
								%>

								<!-- 评论板块 -->
								<div class="a-isaym2" id="isaym2">
									<div class="isaym2 isaym2-open js-673810"
										style="margin-top:-81px;"></div>
								</div>


								<div class="a-isaym2 a-isaym2-do" style="display:none;"
									id="${topicModel.topicid }">

									<div id="showreplyinfo">
										<div class="isaym2 isaym2-open js-673810"
											style="margin-top: 0px;">
											<div>
												<div class="shadow"></div>

												<div class="isaymin">
													<div class="isayi add">
														<textarea name="textarea" id="textarea"
															style='overflow-y: hidden;width: 390px;padding:9px;min-height:40px;height:40px;line-height:20px;float:left;'
															onpropertychange="this.style.height = this.scrollHeight + 'px';"
															oninput="this.style.height = this.scrollHeight + 'px';"></textarea>
														<button class="w-bbtn xtag w-bbtn-0"
															onClick="publishReply()">发 布</button>
													</div>

													<!-- 回复的内容 -->
													<div class="m-cmt">
														<div class="a-show a-show-cmtul a-show-do"
															style="height:this.scrollHeight + 'px'">
															<ul class="xtag">
																<li class="a-slide a-slide-do">
																	<div class="cmti slide xtag" style="margin-top: 0px;">
																		<div class="w-img2 w-img2-2 noshortkey">
																			<a target="_blank" href="#"
																				title="余文森✿Vincent - 1小时前"><img class="xtag"
																				src="http://imgsize.ph.126.net/?imgurl=http://img0.ph.126.net/LFqLUHo7O1XBdABY5Y3LiA==/1329687789999207597.jpg_20x20x0.jpg">
																			</a>
																		</div>
																		<div class="cmtcnt">
																			<div class="cmthot">
																				<span class="cmtusr"> <a class="s-fc4 xtag"
																					target="_blank" href="http://beeeeeeeee.lofter.com"
																					title="余文森✿Vincent - 1小时前">余文森✿Vincent</a> </span> <span
																					class="xtag">维港海傍~</span>
																			</div>
																			<div class="cmtopt">
																				<a class="cmtj s-fc4 xtag" href="#">加黑</a> <a
																					class="xtag cmtj s-fc4" href="#"
																					style="display: none;">删除</a> <a class="s-fc4 xtag"
																					href="replybtn()">回复</a>
																			</div>
																		</div>
																	</div></li>
															</ul>
														</div>
													</div>
													<!-- 回复的内容 -->

													<div class="isayi">
														<a class="w-more2 xtag" href="#" style="display: none;">
															<span>查看更多</span> </a>
													</div>
													<div class="more" style="">
														<div class="isayi">
															<a class="w-more w-more-close xtag"
																href="javascript:hideReply(${topicModel.topicid })">收起</a>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>


								<div class="a-isaym2 a-isaym2-shareTo">
									<div class="isaym2 isaym2-open js-673810"
										style="margin-top:-125px;"></div>
								</div>
								<div class="isayb"></div>
							</div>

						</div>

					</div>

				</c:forEach>

				<c:if test="${topicCount!=0 }">
					<div id="Loading">
						<img src="images/20080320132841979.gif"></img>
					</div>
				</c:if>
			</div>
		</div>
	</div>

	<!--指在头像上的悬浮窗-->

</body>

</html>
<script type="text/javascript">	var count = 1;
	function showPublish() {
		$("#publish").toggleClass("hide", count++ % 2 == 1);

	}
</script>

<!-- 给导航栏的每一个li绑定点击事件 -->
<script type="text/javascript">	
var lis = $('.nav1 li');//根据自己实际情况获取所有li   
lis.click(function(){  
    lis.removeClass("j-crt");  
    $(this).addClass("j-crt");  
});  
</script>

<!-- 浏览，展示所有用户 -->
	<script>
	function scanAllUser(){
		$.ajax({
			type : "POST",
			url : "user.action?op=showAllUsers",
			success : function(msg) {
				$(".g-mn").html(msg);
		//		$("#viewFirstClickStatus").addClass("j-crt");
			}
		});
	}

	</script>

	<!-- 每循环一个Topic都去请求一次数据  -->
	<script>
	var loginuserid='${user.id }';
//	alert( loginuserid );
		$.ajax({
			type : "POST",
			url : "topic.action?op=ajaxRightMenum",
			data : "userid="+loginuserid,
			success : function(msg) {
			//	alert("Data Saved: " + msg);
			//	$(".menum").html(msg);
			}
		});
	</script>

	<!-- 粉丝 -->
	<script>
	function fans(){
		var userid='${user.id}';
		$.ajax({
			type : "POST",
			url : "user.action?op=showfans",
			data : "userid="+userid,
			success : function(msg) {
				$(".g-mn").html(msg);
			}
		});
	}

	</script>

	<!-- 打开关注jsp -->
	<script>
	function focus(){
		var userid='${user.id}';
		$.ajax({
			type : "POST",
			url : "user.action?op=showfocus",
			data : "userid="+userid,
			success : function(msg) {
				$(".g-mn").html(msg);
			}
		});
	}
	</script>

	<!-- 取消关注 -->
	<script>
	function cancelfocus(thisobj){
	
	jConfirm('你确定这么做吗?', '确定对话框', function(r) {
			if( r ){
				var userid='${user.id}';	//当前用户
				var willcanceluserid=$(thisobj).attr("class").split(" ")[3];//待取消关注用户
				$.ajax({
					type : "POST",
					url : "user.action?op=cancelfocus",
					data : "userid="+userid+"&willcanceluserid="+willcanceluserid,
					success : function(msg) {
						var result=msg.split(" ")[0];
						var willcanceluserid=msg.split(" ")[1];
						$("."+willcanceluserid).parents("li").css("display","none");
					}
				});
			}else{
			
			}
		});
	}

	</script>

	<!-- 添加关注 -->
	<script>
	function addfocus(thisobj){
		var userid='${user.id}';	//当前用户
		var willfocususerid=$(thisobj).attr("class").split(" ")[3];//待关注用户
		$.ajax({
			type : "POST",
			url : "user.action?op=addfocus",
			data : "userid="+userid+"&willfocususerid="+willfocususerid,
			success : function(msg) {
				var result=msg.split(" ")[0];
				var willfocususerid=msg.split(" ")[1];
				$("."+willfocususerid).css("display","none");
			}
		});
	}

	</script>

	<!-- 移除粉丝 -->
	<script>
	function removefocus(thisobj){
		jConfirm('你确定这么做吗?', '确定对话框', function(r) {
			if( r ){
				var userid='${user.id}';	//当前用户
				var willremoveuserid=$(thisobj).attr("class").split(" ")[3];//待移除用户
				$.ajax({
					type : "POST",
					url : "user.action?op=removefocus",
					data : "userid="+userid+"&willremoveuserid="+willremoveuserid,
					success : function(msg) {
						alert(msg);
						var result=msg.split(" ")[0];
						var willremoveuserid=msg.split(" ")[1];
						$("."+willremoveuserid).parents("li").css("display","none");
					}
				});
			}else{
			
			}
		});
	}

	</script>

<!-- 关注用户 -->
<script>
	function dofocus(thisobj){
	    var focuseduserid=$(thisobj).attr("title").split(" ")[2];
	    var userid='${user.id}';
	    alert( focuseduserid+"、"+userid );
		$.ajax({
   			type: "POST",
  			url: "user.action?op=dofocus",
   			data: "focuseduserid="+focuseduserid+"&userid="+userid,
   			success: function(msg){
    		alert(  msg );
    		var result=msg.split(" ")[0];
    		var focuseduserid=msg.split(" ")[1];
    		if( result>0 ){
    			alert( $("."+focuseduserid).attr("class"));
    			$("."+focuseduserid).removeClass("w-icn2").removeClass("w-icn2-3").text("");
    		}else{
    			$("."+focuseduserid).addClass("w-icn2").addClass("w-icn2-3");
    		}
   }
});
	}
/*	
	$(".a-followicon").hover(function(){
		$(this).text("取消关注");
	});
	
	$(".a-followicon").mouseout(function(){
		$(this).text("已关注");
	});
	*/
</script>

<!-- 删除个人文章 -->
<script>
	function deletetopic(thisobj){
		jConfirm('你确定这么做吗?', '确定对话框', function(r) {
			if( r ){
				var topicid=$(thisobj).parents(".m-mlist").attr("id").split(" ")[1];
				$.ajax({
		   			type: "POST",
		  			url: "topic.action?op=deleteTopic",
		   			data: "topicid="+topicid,
		   			success: function(msg){
			    		//alert(  msg );
			            var result=msg.split(" ")[0];
			    		var topicid=msg.split(" ")[1];
			    		if( result>0 ){
			    			$("#"+topicid).parents(".m-mlist").css("display","none");
			    		}
		    		}
		    	});
			}else{
				alert("bb");
			}
});
	}
</script>

<!-- 个人文章 -->
<script>
	function personalTopicShow(){
	    var userid='${user.id}';
	//    alert(userid);
		$.ajax({
   			type: "POST",
  			url: "topic.action?op=personaltopicshow",
   			data: "userid="+userid,
   			success: function(msg){
    //		alert( "Data Saved: " + msg );
    //		$(".m-mlist").css("display","none");
    //		$("#main").append(msg);
    		$("#personaltopic").html(msg);
   }
});
	}
</script>

<!--收藏点击事件-->
<script type="text/javascript">
	//需要传参数：userid、topicid
	//查询是否有记录，有就删除，没有就添加
	function collect(thisobj) {
		var userid = $("#curloginuserid").val();
		var topicid = $(thisobj).parents(".m-mlist").attr("id").split(" ")[1];
	//	alert( userid+"、"+topicid);
				
		$.ajax({
			type : "POST",
			url : "docollect.action?op=collect",
			data : "userid="+userid+"&topicid="+topicid,
			success : handlecollect
		});
	}
	function handlecollect(msg) {
				var r = msg;
				var topicid = r.split(" ")[0];
				var result = r.split(" ")[1];
		//		alert("topicid:"+topicid+"、"+"result:"+result);
				if (result > 0) { //显示已收藏
					$("#" + topicid).parents(".m-mlist").find("a").filter(
							".collect").text("已收藏");
				} else {//显示收藏
					$("#" + topicid).parents(".m-mlist").find("a").filter(
							".collect").text("收藏");
				}
	}
</script>

<!--点赞事件-->
<script type="text/javascript">
	//需要传参数：userid、topicid
	//查询是否有记录，有就删除，没有就添加
	function praise(thisobj) {
		var userid = $("#curloginuserid").val();
		var topicid = $(thisobj).parents(".m-mlist").attr("id").split(" ")[1];
		startRequest('dopraise.action?op=praise&topicid=' + topicid
				+ '&userid=' + userid, handlepraise);
	}
	function handlepraise() {
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
			if (xmlHttp.status == 200) { // 如果伺服端回應OK
				var r = xmlHttp.responseText;
				var topicid = r.split(" ")[0];
				var result = r.split(" ")[1];
				var praiseCount = r.split(" ")[2];
				//	alert("topicid:"+topicid+"、"+"result:"+result);
				if (result > 0) { //赞
					$("#" + topicid).parents(".m-mlist").find("a").filter(
							".w-icn-0b").removeClass("w-icn-0b-anim").addClass(
							"w-icn-0b-do-anim").addClass("w-icn-0b-do");
					$("#" + topicid).parents(".isay").find("div").filter(
							".optb").children().first().text(
							" 热度(" + praiseCount + ")");
				} else {//取消赞
					$("#" + topicid).parents(".m-mlist").find("a").filter(
							".w-icn-0b").removeClass("w-icn-0b-do")
							.removeClass("w-icn-0b-do-anim").addClass(
									"w-icn-0b-anim");
					$("#" + topicid).parents(".isay").find("div").filter(
							".optb").children().first().text(
							" 热度(" + praiseCount + ")");
				}
			}
		}
	}
</script>


<script type="text/javascript">
	//点击评论
	function showReply(topicid) {
		if ($("#" + topicid).css("display") == "none") {
			//	$("#"+topicid).css("display","block");
			startRequest(
					'doreply.action?op=checkReplyCount&topicid=' + topicid,
					handleStateChange);
		} else {
			$("#" + topicid).slideToggle(1000);
		}
	}

	//点击评论回调函数
	function handleStateChange() {
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
			if (xmlHttp.status == 200) { // 如果伺服端回應OK
				var r = xmlHttp.responseText;
				//	alert(r);
				//	alert(r.split("id")[1].split("\"")[1]);
				var topicid = r.split("id")[1].split("\"")[1];
				//	alert(topicid);
				$("#" + topicid).children("#showreplyinfo").html(r);
				if ($("#" + topicid).has("li").html() == undefined) { //没有回复的情况
					$("#" + topicid).find("ul").parent().css("height", "0");
					$(".more").css("display", "none");
				} else {
					//	alert($("#"+topicid).find("ul").parent()[0].scrollHeight);
					$(".more").css("display", "block");
				}
				$(".isay #isaym2").next().slideUp(600);
				$("#" + topicid).slideDown(600);
				$("#" + topicid).find("textarea").focus();
			}
		}
	}

	//点击收起评论
	function hidereply(thisobj) {
		//	alert(  $(thisobj).parents(".a-isaym2-do").attr("id") );		
		$(thisobj).parents(".a-isaym2-do").slideUp(1000);
	}

	//回复文本的获取焦点事件
	function textareafocus(thisobj) {
		if ($(thisobj).css("color") == "rgb(255, 0, 0)") {//红色
			$(thisobj).val("");
			$(thisobj).css("color", "#444");
		}
	}

	//回复文本的按键事件
	function userkeydown(event, thisobj) {
		if (event.keyCode == 8) {
			$(thisobj).css("height", "20px");
		}
	}
	//回复文本的按键事件
	function userkeyup(event, thisobj) {
		if (event.keyCode == 8) {
			if ($(thisobj).val().split(" ")[0].trim() != "回复") {
				$(thisobj).next().next().attr("value", "");
			}
		}
	}

	//点击发布触发的事件
	function publishReply(thisobj) {
		var curtextarea = $(thisobj).prev();
		if (curtextarea.val().trim() == "") { //文本域为空的情形
			curtextarea.val("请输入评论内容!");
			curtextarea.css("color", "red");
		} else {//不为空，1.有父id；2.没有父id
			var parentid = curtextarea.next().next().attr("value");
			if (parentid != "") {
				var content = curtextarea.val().split("：")[1].trim();
			} else {
				var content = curtextarea.val().trim();
			}
			if (content == "") {
				curtextarea.val("");
			} else {
				var topicid = curtextarea.parents(".isaym2-open").attr("id");
				var userid = $("#blogmanage").find("input").eq(1).attr("value");
				var parentid = curtextarea.next().attr("value");
				//	alert(topicid+"、"+loginUserEmail+"、"+parentid+"、"+content);
				startRequest("doreply.action?op=addreply&topicid=" + topicid
						+ "&userid=" + userid + "&content="
						+ encodeURI(encodeURI(content)) + "&parentid="
						+ parentid, handleStateChange2);

			}

		}
	}

	//发布的回调函数
	function handleStateChange2() {
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
			if (xmlHttp.status == 200) { // 如果伺服端回應OK
				var r = xmlHttp.responseText;
				//	alert(r);
				var topicid = r.split("value")[2].split("\"")[1];
				var replyCount = r.split("value")[3].split("\"")[1];
				//		alert(replyCount);
				//		alert($("#"+topicid).find("ul").children().first().attr("class"));
				//	$("#" + topicid).find("ul").children().first().before(r);
				$("#" + topicid).find("ul").prepend(r);
				//	$("#" + topicid).find("ul").children().first().slideDown(1000);
				$("#" + topicid).find("ul").children().first().animate({
					left : '0px'
				}, 500);
				$("#" + topicid).find("textarea").val("");
				//	$("#" + topicid).has("a").hasClass("w-more").css("display", "block");
				//	$("#" + topicid).prev().prev().find("span").hasClass("opti-crt").find("a").val("评论（"+replyCount+"）");
				//	alert( $("#" + topicid).parents(".isay").find("span").filter(".opti-crt").find("a").text() );
				$("#" + topicid).parents(".isay").find("span").filter(
						".opti-crt").find("a").text("评论(" + replyCount + ")");
			}
		}
	}

	//点击回复
	function replybtn(thisobj) {
		//	alert($(thisobj).parents(".m-cmt").prev().find("textarea").attr("name"));
		var curtextarea = $(thisobj).parents(".m-cmt").prev().find("textarea");
		var nickname = $(thisobj).parents(".cmtopt").prev().find("a").html();
		var parentid = $(thisobj).parents(".cmtcnt").prev().find("input").attr(
				"value");
		curtextarea.val("回复 " + nickname + "：");
		//	curtextarea.prev().html("回复 "+nickname+"：");	//注意中英文的空格
		//	curtextarea.prev().css("display","block");
		curtextarea.next().next().attr("value", parentid);
		curtextarea.css("color", "#444");
	}

	//点击图片放大
	function imgclick(thisobj) {
		var imgwidth = $(thisobj).width();
		var imgheight = $(thisobj).height();
		if( $(thisobj).parents(".js-800786").css("width")=='500px' ){
			$(thisobj).css("width", "164px");
			$(thisobj).parents(".js-800786").css("width","164px");
		}else{
			$(thisobj).css("width", "500px");
			$(thisobj).parents(".js-800786").css("width","auto");
		}
	}
</script>

<!-- 发布块的切换-->
<script type="text/javascript" charset="utf-8">	
function showPublishtext() {
		//$("#publishpic").slideUp("slow");
		$("#publishpic").hide();
		$("#publishtext").slideDown("slow");
		$("#topictype").val("文字");

	}
	function hidePublishtext(){
		
		$("#publishtext").slideUp("slow");
	}
	function showPublishpic() {
		
		
		
		//$("#publishtext").slideUp("slow");
		$("#publishtext").hide();
		$("#publishpic").slideDown("slow");
		$("#topictype").val("图片");
		
		

	}
	function hidePublishpic(){
		
		$("#publishpic").slideUp("slow");
	}
	
	function editorTopic(divid,title,content){
		$("#"+divid).hide();
		$("#publishtext").slideDown("slow");
		$("#title").val(title);
		CKEDITOR.instances.content.setData(content);
		
		
		
	}
</script>

<!-- websocket测试 -->
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript">
      var websocket = null;
       
      //判断当前浏览器是否支持WebSocket
      //TODO:ie浏览器不兼容 
      if('WebSocket' in window){
    	  //alert('${pageContext.request.contextPath}');
    	  var target="ws://"+ window.location.host+"/${pageContext.request.contextPath}/websocket";
          websocket = new WebSocket(target);
      }else if ('MozWebSocket' in window) {  
    	  websocket = new MozWebSocket(target);  
      }else{
          alert('WebSocket is not supported by this browser.');
      }
      
      // 点击发布事件  
      function sendMessage()  
      { 
      	var userid=document.getElementById("userid");
      	var topictype=document.getElementById("topictype");
      	var title=document.getElementById("title");
      
      
      
      	var message="";
      	message=userid.value+"xiao,"+topictype.value+"xiao,"+title.value+"xiao,"+CKEDITOR.instances.content.getData()+CKEDITOR.instances.contentpic.getData()+"xiao,"+$("#pathpic").val();
       
      	send(message);
      	
     	$("#pathpic").val('');
     	$("#publishtext").slideUp("slow");
     	$("#publishpic").slideUp("slow");
     	title.value="";
     	CKEDITOR.instances.content.setData('');
   		CKEDITOR.instances.contentpic.setData('');
   	
      } 
      
      //显示服务器传回来的消息 
      function receiveMessages(messages)  
   {  
     var chatlog = "";  
     var arr=new Array();
     arr=messages.toString().split("xiao,");
     
     var title=arr[0];
     var content=arr[1];
     var userid=arr[2];
     var pathpic=arr[3];
     var head=arr[4];
     
   	 var contentid=$("#userid").val();
     var divid=Math.ceil(Math.random()*10000).toString();
     chatlog="<div class='pulish_content'> <div class='m-content'> <div class='title' >"+title+"</div> <div class='cnt'> ";
   	if( pathpic=="null"){
   		
     	chatlog+="<div class='text' style='display:block'>"+content+"</div> </div> </div> ";
     	
    }else{
    	chatlog+="<div class='image' style='display:block'><img src=\""+pathpic+"\"/> </div>";
    	chatlog+="<div class='text' style='display:block'>"+content+"</div> </div> </div> ";
    	
    }
     	
    
    if(contentid==userid){
    	chatlog+="<div class='operation'></div>";
    }else{
    	chatlog+="</div>";
    }
     $("#list").after("<div><div class='w-img' style='z-index:1;'> <img class='js-673810' src='images/"+head+"'> </div><div id="+divid+" style='width:564px;margin-left:110px;margin-bottom:10px;'></div></div>" );
     dwr.util.setValue(divid, chatlog, { escapeHtml:false });  
   }
       
      //连接发生错误的回调方法
      websocket.onerror = function(){
          setMessageInnerHTML("error");
      };
       
      //连接成功建立的回调方法
      websocket.onopen = function(event){
       //   setMessageInnerHTML("open");
       		send("login:${user.id }");
      }
       
      //接收到消息的回调方法
      websocket.onmessage = function(message){
          receiveMessages(message.data);
      }
       
      //连接关闭的回调方法
      websocket.onclose = function(){
      //    setMessageInnerHTML("close");
      }
       
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
          websocket.close();
      }
       
      //将消息显示在网页上
      function setMessageInnerHTML(innerHTML){
      		alert(innerHTML);	
      }
       
      //关闭连接
      function closeWebSocket(){
          websocket.close();
      }
       
      //发送消息
      function send(message){
          websocket.send(message);
      }
  </script>

<!--页面加载更多 -->
<script type="text/javascript">	var size=Number($("#size").val());
	var page=Number($("#page").val());
	var count=Number($("#count").val());
	var countpage=count%size==0?count/size:count/size+1;
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		
		
		var contentid=$("#userid").val()
		if (scrollTop + windowHeight == scrollHeight) {
			page+=1;
			if(page<=countpage){
					$.post(
				"topic.action?op=loadmore",
				{ size:size,page:page,userid:contentid},
				function(data){
					 $("#Loading").before(data); 
				}
				
				);
			}else{
				 $("#Loading").html("<P style='color:#ccc;font-size:18px;text-align: center;text-shadow: -1px -1px 0 #779e00;margin-left:100px;'>已经加载完了，么么哒</>");
			}
			
			
		}
	})

</script>





