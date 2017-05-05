<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Lofter后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta name="author" content="DH">
	<link rel="stylesheet" type="text/css" href="${basePath }jslib/themes/bootstrap/easyui.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="${basePath }jslib/themes/icon.css" media="screen"/>
	
  </head>
  <style>
  	#north{
  		background:#333;
  		color:#fff;
  		font-family:"微软雅黑";
		font-size:50px;
  	}
  	#north a{
  		margin-left:20px;
  	}
  </style>
  
	 <body class="easyui-layout">
	    <div id="north" data-options="region:'north',split:false" style="height:80px;">
	    	<a>LOFTER-BMS</a>
	    </div>
	    <div data-options="region:'south',split:false,collapsible:false" style="height:70px;">
	    	当前时间：<a id="t"></a>&nbsp;&nbsp;&nbsp;&nbsp;操作管理员：<%=session.getAttribute("adminname") %>&nbsp;&nbsp;&nbsp;状态：正常
	    </div>
	   
	    <div data-options="region:'west',title:'菜单列表',split:false,collapsible:false" style="width:200px;">
	    
	    	<div id="aa" class="easyui-accordion" data-options="fit:true">
			    <div title="系统管理" style="overflow:auto;padding:10px;">
					<div id="tt1" class="easyui-tree" data-options="animate:true,dnd:true">
					</div>
			    </div>
			    <div title="用户管理" style="padding:10px;"data-options="selected:true">
					<div id="tt2" class="easyui-tree" data-options="animate:true,dnd:true">
					</div>
			    </div>
			    <div title="发帖管理"  >
					<div id="tt3" class="easyui-tree" data-options="animate:true,dnd:true">
					</div>
			    </div> 
			    <div title="数据统计"  >
					<div id="tt4" class="easyui-tree" data-options="animate:true,dnd:true">
					</div>
			    </div> 
			</div>
			
	    </div>
	    
	    <div data-options="region:'center',title:'主操作界面' " style="padding:5px;background:#eee;">
	    
	    	<div id="tabs" class="easyui-tabs" data-option="fit:true,border:false">
			    <div title="首页" style="padding:20px;display:block;">
					欢迎您，管理员&nbsp;<%=session.getAttribute("adminname") %>!
			    </div>
			   
			</div>
			
			
			
	    </div>
	    
	    <!--关闭的上下文菜单 ，默认的是隐藏的，需要绑定-->
	    <div id="mm" class="easyui-menu" style="width:120px;">
		    <div name="close">关闭</div>
		    <div name="closeOther">关闭其他</div>
		    <div name="closeAll">关闭全部</div>
		</div>
		
		
	</body>
</html>
	<script src="${basePath}js/common.js"></script>
	<script type="text/javascript" src="${basePath }jslib/jquery.min.js"></script>
	<script type="text/javascript" src="${basePath }jslib/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${basePath }jslib/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${basePath}jslib/jquery.edatagrid.js"></script>
	<script type="text/javascript" src="${basePath}jslib/datagrid-detailview.js"></script>
	<script src="js/Highcharts-4.1.5/js/highcharts.js" type="text/javascript"></script>
	<script src="js/Highcharts-4.1.5/js/modules/exporting.js" type="text/javascript"></script>
	
	<script>
	$(document).ready(function(){
		//当页面加载完成时，准备树节点的数据
		var treeData1=[{
			text:"管理员管理",
			attributes:{
				url:"back/sys/admin.jsp"
			}
		}];
		
		var treeData2=[{
			text:"用户管理",
			attributes:{
				url:"back/users/user.jsp"
			}	
		}];
		
		var treeData3=[{
			text:"帖子信息",
			attributes:{
				url:"back/topics/topic.jsp"
			}	
		}];
		
		var treeData4=[{
			text:"数据统计",
			children : [{ 
                    text : "发帖时间分布", 
                    attributes : { 
                        	url : "back/dataanalysis/topicpublish.jsp" 
                    	} 
                	}, { 
                    text : "发帖数统计", 
                    attributes : { 
                        	url : "back/dataanalysis/topicnumber.jsp" 
                    } 
            }]
		}];
		
		
		
		
		$('#tt1').tree({
			data: treeData1
		});
		$('#tt2').tree({
			data: treeData2
		});
		$('#tt3').tree({
			data: treeData3
		});
		$('#tt4').tree({
			data: treeData4
		});
		
		
		
		//树控件的操作
		$(".easyui-tree").tree({
			onContextMenu:function(e,title,index){
				e.preventDefault();
			    $("#mm").menu("show",{
					left: e.pageX,
					top:e.pageY
				}).data("tabTitle",title.text); //通过data方法来增加一个属性tabTitle存到上下文菜单对象menu中
			},
			onClick: function(node){
				// 打开新的tab
				open( node.text, node.attributes.url);
			}
		});
		//打开面板并加载页面
		 function open(text,url){
		 	//判断TAB中是否已经打开了一个节点
		 	if( $("#tabs").tabs("exists",text)  ){
		 		//如果有，则选定这块面板
		 		$("#tabs").tabs("select",text);
		 	}else{
		 		//如果没有，就打开一个新面板
		 		$("#tabs").tabs('add',{
					title: text,
					selected: true,
					closable:true,
					href:url,
					tools:[{
						iconCls:'icon-reload',
						handler:function(){
							//shuaxin 
						}
					}]
				});
		 	}
		 }
		 
		 //在整块tabs上绑定上下文菜单
		 $("#tabs").tabs({
		 	onContextMenu:function(e,title,index){
		 		e.preventDefault();
				$("#mm").menu("show",{
					left: e.pageX,
					top:e.pageY
				}).data("tabTitle",title);  //通过data方法来增加一个属性tabTitle存到上下文菜单对象menu中
			}
		 });
		 
		 //给上下文菜单绑定点击事件
		 $("#mm").menu({
		 	onClick: function(item){
		 	//this指的就是当前选的上下文菜单，要把当前选的tab名字存起来
		 		closeTab(this,item.name);
		 	}
		 });
		 
		 function closeTab(menu,type){
			 //取出tablTitle名字
			 var tabTitle=$(menu).data("tabTitle");
			 var tabs=$("#tabs");//取出所有的tabs
			 if(type=="close"){
			 	tabs.tabs("close",tabTitle);
			 	return;
			 }
			 //取出所有的 tab集合
			 var allTabs=tabs.tabs("tabs");
			 
			 var closeTabsTitle=[];//定义一个数组，用于存要关闭的tab
			 //循环所有的标签来关闭
			 $.each(allTabs,function(){
			 	//1.可关闭的 2.要关闭的
			 	var opt=$(this).panel("options");
			 	if(opt.closable && type=='closeAll'){
			 		closeTabsTitle.push(opt.title);
			 	}else if(opt.closable && type=='closeOther' && opt.title!=tabTitle){
			 		closeTabsTitle.push(opt.title);
			 	}
			 });
			 for(var i=0;i<closeTabsTitle.length;i++){
			 	tabs.tabs("close",closeTabsTitle[i]);
			 }
		 }
		 
		 
	});
	</script>
	
	<SCRIPT LANGUAGE="JavaScript">

	function gettime(){
		var d = new Date();
			document.getElementById("t").innerHTML =d;
			window.setTimeout("gettime()",1000);
	}
	window.onload = gettime;
</SCRIPT>