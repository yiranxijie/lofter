package com.websocket;  
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;  
import java.util.List;  

import javax.naming.NamingException;

//import org.directwebremoting.Browser;  
//import org.directwebremoting.ScriptSessions;  





import com.lofter.bean.Topic;
import com.lofter.bean.User;
import com.lofter.biz.TopicBiz;
import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.TopicBizImpl;
import com.lofter.biz.impl.UserBizImpl;

public class SendMsg {  
public static String addMessage(String message) throws Exception {  
//	System.out.println("this is sendMsg");
	String messages="";
	TopicBiz tb=new TopicBizImpl();
   Topic topic=new Topic();
   String userid=null;
   String topictype=null;
   String title=null;
   String content=null;
   String picid=null;
   User curuser=null;
   
   String[] sArray=message.split("xiao,");
   if(sArray!=null&& sArray.length==4){
	   userid=sArray[0];
	   topictype=sArray[1];
	   title=sArray[2];
	   content=sArray[3];
	  
//	   System.out.println(title+","+content);
	   topic.setUserid(userid);
	   topic.setTopictype(topictype);
	   topic.setTitle(title);
	   topic.setContent(content);
	   topic.setPicid(picid);
	   tb.addTopic(topic);
	   
   }else{
	   userid=sArray[0];
	   topictype=sArray[1];
	   title=sArray[2];
	   content=sArray[3];
	   picid=sArray[4];
//	   System.out.println(title+","+content);
	   topic.setUserid(userid);
	   topic.setTopictype(topictype);
	   topic.setTitle(title);
	   topic.setContent(content);
	   topic.setPicid(picid);
	   tb.addTopic(topic);
   }
   //messages.add(title+","+content);  
  // final String messages=title+"xiao,"+content+"xiao,"+userid+"xiao,"+picid;
   UserBiz ubi=new UserBizImpl();
   curuser=ubi.findUserByID(userid);
   messages=title+"xiao,"+content+"xiao,"+userid+"xiao,"+picid+"xiao,"+curuser.getHead();
   return messages;
   
   /* 
   Browser.withCurrentPage(new Runnable() {//启用监听客户端当前页线程  
            public void run() {//把数据添加到客户端调用的方法中  
                ScriptSessions.addFunctionCall("receiveMessages", messages);  
            }  
        });  */
}  
   
private List<String> messages = new LinkedList<String>();  
}


