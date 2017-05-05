package com.lofter.websocket;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArraySet;
 






import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.UserBizImpl;
import com.websocket.SendMsg;
 
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint(value ="/websocket")
public class MyWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
     
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
     
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String  userid;
    private UserBiz ubi = new UserBizImpl();
    
     
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 连接关闭调用的方法
     * @throws NamingException 
     * @throws IOException 
     * @throws SQLException 
     */
    @OnClose
    public void onClose() throws SQLException, IOException, NamingException{
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1  
        
        //退出登录，应将islogined置为0
        int r=ubi.modifyIslogined(userid+":exit");
        System.out.println(userid+"号用户退出，"+"有一连接关闭！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 收到客户端消息后调用的方法，向所有已链接的客户端广播消息
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     * @throws Exception 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        System.out.println("来自客户端的消息:" + message);
        String status[]=message.split(":");
        if( "login".equals(status[0]) ){
        	System.out.println( status[1]+"号用户上线了！");
        	userid=status[1];
        }else{
	        //更新数据库
	        String messages=SendMsg.addMessage(message);
	        //群发消息
	        for(MyWebSocket item: webSocketSet){             
	            try {
	                item.sendMessage(messages);
	            } catch (IOException e) {
	                e.printStackTrace();
	                continue;
	            }
	        }
        }
    }
     
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
     
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
       // System.out.println("message is :"+message);
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
