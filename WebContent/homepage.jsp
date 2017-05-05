
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
                      "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh" lang="zh">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type">
	<title id="oldname1">主页｜${hostuser.nickname }</title>
	<meta name="keywords" content="LOFTER,乐乎" />

	<link rel="stylesheet" type="text/css"
		href="css/loftercss/pt_lib_macro.css" />
	<link rel="stylesheet" type="text/css" href="css/site.css" />
	<link rel="stylesheet" type="text/css" href="css/navbar.css" />
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script src="js/common.js"></script>
	<style>
body {
	background: #dfdfe1;
}

.infoblog {
	position: absolute;
	top: 40px;
	left: 10px;
	border: 1px solid #c0c0c0;
	width: 300px;
	height: 250px;
}

.info:hover {
	-moz-transition: background-color .5s ease-in;
	-webkit-transition: background-color .5s ease-in;
	-o-transition: background-color .5s ease-in;
	-ms-transition: background-color .5s ease-in;
	transition: background-color .5s ease-in;
	background-color: #666;
}

.control li {
	float: left;
	margin-left: 20px
}

.control {
	margin-left: 1200px;
}

.main {
	background: rgba(0, 0, 0, 0) url("images/line.png") repeat-x scroll
		center bottom;
}

.logo i {
	background: rgba(0, 0, 0, 0) url("images/hosthead.png") no-repeat scroll
		0 0;
}

.head {
	width: 500px;
}

.w-img2 {
	float: left;
	left: 20px;
}

.upfacecnt {
	float: left;
	margin-left: 80px;
	padding-top: -40px
}

.itmh {
	margin-left: 10px;
}

#name {
	margin-top: 70px;
}

#uploadhead {
	background: #777;
	color: #fff;
}

#info_menu {
	background: #fff;
}

#description {
	height: 100px;
}

#modifyhead:hover {
	background: #dfdfe1;
}
</style>
	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<c:if test="${set==true }">
		<div id="navbar" class="navbar clearfix">
			<div class="pleft tabbar ztag">
				<span class="info"><a onclick="doInfo()">主页信息</a>
				</span> <span class="info"><a href="topic.action?op=show&email=${user.email }&pwd=${pwd}">返回首页</a>
				</span>
				<span class="collectinfo"><a href="javascript:showcollect()">收藏文章</a>
				</span>
			</div>
			<!--  <div class="pright btnbar ztag">
                    <a class="w-sbtn w-sbtn-0" href="topic.action?op=show">返回首页</a>
                    <a class="w-sbtn w-sbtn-0" href="#">保　存</a>
                </div>  -->
		</div>



		<div class="infoblog" id="info_menu" style="display:none;">
			<div class="itmh">
				<label>头像</label>
			</div>
			<div class="head">
				<div class="w-img2">
					<img class="blogsettingztag" src="images/${hostuser.head }">
				</div>

				<div class="upfacecnt">
					<p>支持jpg格式</p>
					<div>
						<a class="w-sbtn w-sbtn-1 blogsettingztag" id="uploadhead"
							href="simpleDemo.jsp?userid=${hostuser.id}"> <span
							class="w-iuar" id="modifyhead">修改头像</span> </a>
					</div>
				</div>
			</div>
			<div class="itmh" id="name">
				<label>名称</label>
			</div>
			<div class="name">
				<input class="w-inputxt infoztag" value=${hostuser.nickname }
					onblur="updateBlogName(this.value);" name="title" maxlength="20"
					style="color:#737373;width:145px;">
			</div>
			<div class="itmh">
				<label>介绍</label>
			</div>
			<div class="introduce">
				<textarea class="w-inputxt infoztag" id="description"
					onblur="updateBlogDesc(this.value);" name="description">${hostuser.autograph }</textarea>
			</div>
		</div>
		</form>
	</c:if>


	<c:if test="${set==false }">
		<div id="control" class="control" style="top: 0px; opacity: 1;">
			<ul>

				<li>
					<form action="/control" method="post">
						<input type="hidden" name="blogId" value="2735159"> <input
							type="hidden" name="optype" value="unfollow"> <a
								class="boprt03" onclick="submitAction()" href="#"> <em
									id="focus">关注此用户</em> </a>
					</form></li>

				<li>
					<form>
						<a id="golofterhome" class="boprt01" target="_top"
							href="topic.action?op=show&email=${user.email }&pwd=${pwd}"> <em>返回首页</em> </a>
					</form></li>
			</ul>
		</div>

	</c:if>





	<div class="h95"></div>

	<div class="box wid700 ">
		<div class="selfinfo">
			<div class="logo">
				<a href="#"> <img src="images/${hostuser.head }"> <i></i>
				</a>
			</div>
			<h1>
				<a href="#" id="oldname">${hostuser.nickname }</a>
			</h1>
			<div class="text" id="olddesc">${hostuser.autograph }</div>
		</div>
		<input type="hidden" value="${hostuser.id }" id="userid">
	</div>

	<div class="postwrapper box wid700 ">
		<c:forEach items="${hostTopiclist}" var="topic" varStatus="num">
			<div class="block photo" id="topic${topic.id}">
				<div class="side">
					<div class="day">
						<a href="#">${topic.day }</a>
					</div>
					<div class="month">
						<a href="#">${topic.month }</a>
					</div>
				</div>
				<div class="main">
					<div class="content">
						<div class="img">
							<a href="#"> <img src="${topic.picid }">
							</a>
						</div>
						<div class="text">
							<p>${topic.content }</p>
						</div>
					</div>

					<div class="link">
						<a href="#">热度(${topic.like })</a> <a href="#">全文链接</a>

						<c:if test="${set==true }">
							<a onclick="doDelete('${topic.id }')">删除</a>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>

		<!--这是修改信息的面板-->
</body>
</html>

<script type="text/javascript">
	function showcollect(){
		$.ajax({
   			type: "POST",
  			url: "topic.action?op=showcollect",
   			data: "userid=${user.id }",
   			success: function(msg){
    	//		alert(  msg );
    			if( ""==msg.trim() || msg.trim()==null ){
    				$(".postwrapper").html(msg);
    				$(".postwrapper").text("您还没有收藏文章哦！可以到处去逛逛");
    			}else{
    				$(".postwrapper").html(msg);
    			}
 			}
		});
	}
	
	$(function(){
		var obj=$(".postwrapper").find("div");
		if( obj.size()==0 ){
			$(".postwrapper").text("主人很懒，什么都没留下..").css("font-size","18px");
		}
	});
</script>

<script type="text/javascript">

window.onload = function(){ 
		startRequest('homepage.action?op=showfocus&userid2='+$("#userid").val(),handleStateChange);
	};
	
	function handleStateChange(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
					if( xmlHttp.responseText>0 ){
						 $("#focus").html("取消关注");
					}else{
						$("#focus").html("关注此用户");
					}
				}
		}
	}
	
	
	function doDelete(topicid ){
		startRequest('homepage.action?op=deletetopic&topicid='+topicid,handleStateChange2);
	}
	
	function handleStateChange2(){//删除成功后，接受被删除的topic的ID，将该DIV设置为隐藏
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
				var topicid="#topic"+xmlHttp.responseText;
					if( Number(xmlHttp.responseText)!=0 ){
						 $(topicid).hide();
						
							var obj=$(".postwrapper").find("div");
							if( obj.size()==0 ){
								$(".postwrapper").text("主人很懒，什么都没留下..").css("font-size","18px");
							}
						
					}else{
						alert("删除失败，请稍后再试");
					}
				}
		}
	}
	
	function doInfo(){
		var getDisplay =$("#info_menu").css("display") ;
		if(getDisplay=="none"){
			$("#info_menu").show();
		}
		if(getDisplay=="block"){
			$("#info_menu").hide();
		}
			
	}
	
	function submitAction(){
		startRequest('homepage.action?op=focuschange&userid2='+$("#userid").val(),handleStateChange);
	}
	
	
	
	
	//上传栏的处理
	function updateBlogName(newname){
		if( $("#oldname").text()!=newname){
			startRequest('homepage.action?op=updatename&newname='+encodeURI(encodeURI(newname))+'&userid='+$("#userid").val(),namechange);
		}
		
	}
	function namechange(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
					if( xmlHttp.responseText!=0 ){
						 $("#oldname").html(xmlHttp.responseText);
						  $("#oldname1").html("主页｜"+xmlHttp.responseText); 
					}else{
						alert("修改失败，请稍后再试");
					}
				}
		}	
	}
	
	
	function updateBlogDesc(newdesc){
		if($("#olddesc").text()!=newdesc){
			startRequest('homepage.action?op=updatedesc&newdesc='+encodeURI(encodeURI(newdesc))+'&userid='+$("#userid").val(),descchange);
		}
	}
	function descchange(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
					if( xmlHttp.responseText!=0 ){
						$("#olddesc").html(xmlHttp.responseText);
					}else{
						alert("修改失败，请稍后再试");
					}
				}
		}	 
	}
	
	
	
	
	

</script>
