<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showTopic.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">  
   function sendMessage()  
   {  
   	var obj=document.getElementById("message");
    var message=obj.value;  
  	SendMsg.addMessage(message);  
   }  
   function receiveMessages(topic)  
   {  
     var chatlog = "";  
     for (var data in topic) {  
       chatlog = "<div>" + dwr.util.escapeHtml(messages[data]) + "</div>" + chatlog;  
     }  
     dwr.util.setValue("list", chatlog, { escapeHtml:false });  
   }  
</script>
  </head>
  
  <body>
   <!--图文版块-->
		

					
			
  </body>
</html>
