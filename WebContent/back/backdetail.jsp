<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



    <div id="img" style="float:left;">
    	<img  src="${basePath}images/${picname}"  style="height:300px;width:250px;"/>
    </div>
    <div id="info" style="float:left;height:300px;width:400px;">
    	<div id=tool>
	    	<ul type="none">
	    		<li style="float:left;">热度：${backpraise}</li>
	    		<li style="margin-left:10px;float:left;">评论数：${backreplycount}</li>
	    		<li style="margin-left:30px;float:left;"><input type="button" value="屏蔽图片" onclick="shield()"/></li>   	
	    	</ul>
    	</div>
    	<div id="reply" >
    	
    	
    		<table class="easyui-datagrid" style="width:600px;height:280px"  
			        data-options="url:'back/backtopic.action?op=reply&topicid='+${topicid},fitColumns:true,singleSelect:true">  
			    <thead>  
			        <tr>  
			        	<th data-options="field:'userid',width:100">评论者ID</th>  
			            <th data-options="field:'content',width:100">评论内容</th>  
			            <th data-options="field:'replytime',width:100">评论时间</th>  
			        </tr>  
			    </thead>  
			</table> 
    	</div>
    </div>


<script>
	$(function(){
		alert("aa");
	});
</script>




