<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
 %>
<div><span>用户已经登陆，若不是本人操作，请及时修改密码！</span></div>

<script type="text/javascript">  
    var time = 5;  
  
    function returnUrlByTime() {  
  
        window.setTimeout('returnUrlByTime()', 1000);  
  
        time = time - 1;  
  
        document.getElementById("layer").innerHTML = time;  
    }  
</script>  
  
</head>  
  
<body  onload="returnUrlByTime()">  
      
    <b><span id="layer">4</span>秒后，转入输入界面。</b>  
      
    <%  
        //转向语句  
        response.setHeader("Refresh", "4;URL=mylogin.jsp");  
    %>  
  
</body>