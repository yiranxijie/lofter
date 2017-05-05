<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="topicdg"></table>
<script>
	var datagrid;
	
	var rowEditor=undefined; //这是待会要进行编辑的一个行
	//$(页面加载完成后要进行的操作)() 这种写法是当页面完成加载后自动执行的操作
	$(function(){
		datagrid=$('#topicdg').edatagrid({
			url: 'back/backtopic.action?op=list', //要查询的地址
            saveUrl: 'back/backtopic.action?op=add',//保存的地址
            updateUrl: 'back/backtopic.action?op=update',//修改的地址
            destroyUrl: 'back/backtopic.action?op=delete',//删除地址
                
			pagination:true,//显示分页
			pageSize:10,//默认的分页条数
			pageList:[5,10,20,30,40,50,60,70], //显示出来的可选分页条数
			fitColumns:true, //自适应列
			title:"用户管理",
			idField:"id", //标识，会记录我们选中的一行的ID
			rownumbers:"true",//显示行号
			nowrap:true,//不换行
			sortName:"id",//根据这个列进行排序，这个参数会传到后台的servlet上，要有对应的后台的接收
			sortOrder:"desc",//排序方式
			

			columns:[[
		        {field:'id',title:'帖子编号',width:30,align:'center'},
		        {field:'userid',title:'发帖用户',width:30,align:'center'},
		        {field:'title',title:'标题',width:40,align:'center',
			        editor:{
			        		type:'validatebox',//可编辑类型
			        		option:{//编辑器的属性
			        			required:true
			        		}
			        	}
		        },
		        {field:'content',title:'内容',width:100,align:'center',
		        	editor:{
		        		type:'validatebox',//可编辑类型
		        		option:{//编辑器的属性
		        			required:true
		        		}
		        	}
		        },
		        {field:'posttime',title:'发表时间',width:40,align:'center'},
		      
		    ]],
		    
		    //定义按钮
		    toolbar: [{
					text:"删除",
					iconCls: 'icon-remove',
					handler: //start_flow()
					function(){
					//这里两个调用其实是前者进入判断对话框后就调用后者，这时仍然没有完成删除从左，就先进行了刷新操作。这样是不符合逻辑的，在页面上刷新完成之后还是没删除之前的效果，用定时器，让后边的方法延迟4秒执行
						$('#topicdg').edatagrid('destroyRow');
						setTimeout(" $('#topicdg').edatagrid('load') ",2500);

					}
				},'-',{
					text:"保存",
					iconCls: 'icon-save',
					handler: function(){
						$('#topicdg').edatagrid('saveRow');
					}
				},'-',{
					text:"取消编辑",
					iconCls: 'icon-undo',
					handler: function(){
						$('#topicdg').edatagrid('cancelRow');
					}
				}
			],
			
			
			view: detailview,
			singleSelect:true,
			detailFormatter:function(index,row){
				return '<img  src="${basePath}images/'+ row.picname +  '"  style="height:300px;width:250px;float:left;"/> <input type="button" value="屏蔽图片" onClick="shield('+  row.id  +');"/> <div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
			},
			onExpandRow: function(index,row){
				$('#ddv-'+index).edatagrid({
	            url:'back/backtopic.action?op=detail&topicid='+row.id+'&picid='+row.picid,
	            destroyUrl: 'back/backtopic.action?op=deletereply',//删除地址
	            title:"评论列表",
	            pagination:true,//显示分页
				pageSize:10,//默认的分页条数
				pageList:[5,10,15,20], //显示出来的可选分页条数
				idField:"id", //标识,这个是评论的ID,查询回来之后存放在隐藏域中
	            fitColumns:true,
	            singleSelect:true,
	            rownumbers:false,
	            loadMsg:'',
	            
	            height:'auto',
	            columns:[[
	              {field:'id',title:'评论ID',width:100},
	              {field:'userid',title:'评论者ID',width:100},
	              {field:'content',title:'评论内容',width:100,align:'left'},
	              {field:'replytime',title:'评论时间',width:100,align:'right'}
	            ]],
	            //定义按钮
		    toolbar: [{
					text:"删除",
					iconCls: 'icon-remove',
					handler: function(){
						$('#ddv-'+index).edatagrid('destroyRow');
						$('#ddv-'+index).edatagrid('load');
					}
				}
			],
	            onResize:function(){
	              $('#topicdg').datagrid('fixDetailRowHeight',index);
	            },
	            onLoadSuccess:function(){
	              setTimeout(function(){
	                $('#topicdg').datagrid('fixDetailRowHeight',index);
	              },0);
	            }
	          });
	          $('#topicdg').datagrid('fixDetailRowHeight',index);
			
				/*var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
				ddv.panel({
					border:false,
					cache:false,
					href:'back/backtopic.action?op=detail&topicid='+row.id+'&picid='+row.picid,//row.id取出来的
					onLoad:function(){
						$('#topicdg').datagrid('fixDetailRowHeight',index);
					}
				});
				$('#topicdg').datagrid('fixDetailRowHeight',index);*/
			}
			
			
			
		});
		
		
		
			
			
		
	});
	
	//解决异步的callback方法
	function destroy(callback ){
	   $('#topicdg').edatagrid('destroyRow'); 
	   if(typeof callback != "undefined")
        callback();// 执行调用函数   
	}
	function load( ){
	  $('#topicdg').edatagrid('load');     
	}
	function start_flow(){
	    destroy(load );
	}
	
	
	
	function shield( topicid){
		startRequest('back/backtopic.action?op=shield&topicid='+topicid,handleStateChange);
	}
	
	
	
	function handleStateChange(){
		if (xmlHttp.readyState == 4) { // 測試狀態是否請求完成
				if (xmlHttp.status == 200) { // 如果伺服端回應OK
				//	alert("服务器端的响应" + xmlHttp.responseText); // 這邊只取得回應文字
				//alert(xmlHttp.responseText);
					if( xmlHttp.responseText>0 ){
						 alert("操作成功！");
					}else{
						 alert("操作失败！");
					}
				}
		}
	}
	
	
	
</script>


