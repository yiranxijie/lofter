<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="admindg"></table>
<script>
	var datagrid;
	
	var rowEditor=undefined; //这是待会要进行编辑的一个行
	//$(页面加载完成后要进行的操作)() 这种写法是当页面完成加载后自动执行的操作
	$(function(){
		datagrid=$('#admindg').edatagrid({
			url: 'back/backadmin.action?op=list', //要查询的地址
            saveUrl: 'back/backadmin.action?op=add',//保存的地址
            updateUrl: 'back/backadmin.action?op=update',//修改的地址
            destroyUrl: 'back/backadmin.action?op=delete',//删除地址
                
			pagination:true,//显示分页
			pageSize:30,//默认的分页条数
			pageList:[5,10,20,30,40,50,60,70], //显示出来的可选分页条数
			fitColumns:true, //自适应列
			title:"用户管理",
			idField:"id", //标识，会记录我们选中的一行的ID
			rownumbers:"true",//显示行号
			nowrap:true,//不换行
			sortName:"adminname",//根据这个列进行排序，这个参数会传到后台的servlet上，要有对应的后台的接收
			sortOrder:"desc",//排序方式
			
			
			
			
			columns:[[
		        {field:'id',title:'管理员编号',width:100,align:'center'},
		        {field:'adminname',title:'用户名',width:100,align:'center',
		        	editor:{
		        		type:'validatebox',//可编辑类型
		        		option:{//编辑器的属性
		        			required:true
		        		}
		        	}
		        },
		        {field:'adminpwd',title:'密码',width:100,align:'center',
		        	formatter: function(value,row,index){ //value:这个单元格的真实值，row表示这一行，index表示这一行的索引
		        					return "*******";
		        	},
		        	editor:{
		        		type:'validatebox',
		        		option:{//编辑器的属性
		        			required:true
		        		}
		        	}
		        },
		        {field:'status',title:'状态(正常/冻结)',width:60,align:'center',editor:{type:'checkbox',options:{on:'on',off:'off'}}
		        
		        }
		    ]],
		    
		    //定义按钮
		    toolbar: [{
			    	text:"增加",
					iconCls: 'icon-add',
					handler: function(){
						$('#admindg').edatagrid('addRow')
					}
				},'-',{
					text:"删除",
					iconCls: 'icon-remove',
					handler: function(){
						$('#admindg').edatagrid('destroyRow')
						$('#admindg').edatagrid('load')
					}
				},'-',{
					text:"保存",
					iconCls: 'icon-save',
					handler: function(){
						$('#admindg').edatagrid('saveRow')
					}
				},'-',{
					text:"取消编辑",
					iconCls: 'icon-undo',
					handler: function(){
						$('#admindg').edatagrid('cancelRow')
					}
				}
			]
		});
	});
</script>

