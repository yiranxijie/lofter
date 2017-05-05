<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="userdg"></table>
<script>
	var datagrid;
	
	var rowEditor=undefined; //这是待会要进行编辑的一个行
	//$(页面加载完成后要进行的操作)() 这种写法是当页面完成加载后自动执行的操作
	$(function(){
		datagrid=$('#userdg').edatagrid({
			url: 'back/backuser.action?op=list', //要查询的地址
            saveUrl: 'back/backuser.action?op=add',//保存的地址
            updateUrl: 'back/backuser.action?op=update',//修改的地址
            destroyUrl: 'back/backuser.action?op=delete',//删除地址
                
			pagination:true,//显示分页
			pageSize:30,//默认的分页条数
			pageList:[5,10,20,30,40,50,60,70], //显示出来的可选分页条数
			fitColumns:true, //自适应列
			title:"用户管理",
			idField:"id", //标识，会记录我们选中的一行的ID
			rownumbers:"true",//显示行号
			nowrap:true,//不换行
			sortName:"id",//根据这个列进行排序，这个参数会传到后台的servlet上，要有对应的后台的接收
			sortOrder:"desc",//排序方式
			
			
			
			
			columns:[[
		        {field:'id',title:'用户ID',width:100,align:'center'},
		        {field:'nickname',title:'昵称',width:100,align:'center',
		        	editor:{
		        		type:'validatebox',//可编辑类型
		        		option:{//编辑器的属性
		        			required:true
		        		}
		        	}
		        },
		        {field:'email',title:'邮箱',width:100,align:'center'},
		        {field:'regtime',title:'注册时间',width:100,align:'center'}
		    ]],
		    
		    //定义按钮
		    toolbar: [{
					text:"删除",
					iconCls: 'icon-remove',
					handler: function(){
						$('#userdg').edatagrid('destroyRow')
						$('#userdg').edatagrid('load')
					}
				},'-',{
					text:"保存",
					iconCls: 'icon-save',
					handler: function(){
						$('#userdg').edatagrid('saveRow')
					}
				},'-',{
					text:"取消编辑",
					iconCls: 'icon-undo',
					handler: function(){
						$('#userdg').edatagrid('cancelRow')
					}
				}
			]
		});
	});
</script>
