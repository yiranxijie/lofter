package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lofter.bean.Topic;
import com.lofter.bean.User;
import com.lofter.biz.FocusBiz;
import com.lofter.biz.TopicBiz;
import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.FocusBizImpl;
import com.lofter.biz.impl.TopicBizImpl;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.utils.RequestUtil;

public class HomePageServlet extends CommonServlet {
	private String userid1;	//LoginUser
	private String userid2;	//TopicUser
	
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		userid1=user.getId();
		userid2=request.getParameter("userid2");
		//2、如果op的值不为空，对op的值进行任务的分发
		try {
			if( op!=null && !"".equals( op ) ){
				if( op.equals( "show")){
					showOp( request,response);
				}else if(op.equals("showfocus")){
					showfocusOp(request,response);
				}else if(op.equals("focuschange")){
					focuschangeOp(request,response);
				}else if(op.equals("deletetopic")){
					deletetopicOp(request,response);
				}else if(op.equals("updatename")){
					updatenameOp(request,response);
				}else if(op.equals("updatedesc")){
					updatedescOp(request,response);
				}else if(op.equals("upload")){
					uploadOp(request,response);
				}
			}else{

			}
		} catch (Exception e) {

			e.printStackTrace();
		} 
	
	}


	//修改头像,1 查出原来数据库中的旧头像名,如果不为空,则按照名字拼接路径并删除旧头像的图片.为空不操作  2插入新的图片名到数据库中
		private void uploadOp(HttpServletRequest request,
				HttpServletResponse response) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, Exception {
			HttpSession session=request.getSession();
			UserBiz ub=new UserBizImpl();
			String userid=request.getParameter("userid").trim();
			String headname=request.getParameter("headname").trim();
			String email=(String) session.getAttribute("email");
			String pwd=(String) session.getAttribute("pwd");
		
			int i=ub.updatehead(userid,headname);
			if(i>0){//成功修改
				//重新根据id查一遍user,重新存到session中
				User user=ub.findUserByID(userid);
				//将新的头像文件名传给session中
				request.getSession().setAttribute("user", user);
			}
			//跳转
		//	request.getRequestDispatcher("topic.action?op=show").forward(request, response);
			response.sendRedirect( "topic.action?op=show&email="+email+"&pwd="+pwd);
		}


	//修改签名
	private void updatedescOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		UserBiz ub=new UserBizImpl();
		String newdesc=(String)request.getParameter("newdesc");
		String userid=(String)request.getParameter("userid");
		PrintWriter out = response.getWriter();
		newdesc=URLDecoder.decode(newdesc,"utf-8");
		int i=ub.updateautograph(newdesc,userid);
		if(i>0){//更改成功
			out.print(newdesc);
		}else{
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	//修改昵称
	private void updatenameOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		UserBiz ub=new UserBizImpl();
		String newname=(String)request.getParameter("newname");
		String userid=(String)request.getParameter("userid");
		PrintWriter out = response.getWriter();
		newname=URLDecoder.decode(newname,"utf-8");
		int i=ub.updatenickname(newname,userid);
		if(i>0){//更改成功
			out.print(newname);
		}else{
			out.print("0");
		}
		out.flush();
		out.close();
	}

	//删除指定的TOPIC
	private void deletetopicOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException, NamingException {
		TopicBiz tb=new TopicBizImpl();
		String topicid=(String)request.getParameter("topicid");
		PrintWriter out = response.getWriter();
		//调用删除指定topic的业务
		int i=tb.deletetopic(topicid);
		System.out.println(topicid);
		
		if(i>0){
			//删除成功，返回当前删除的topic的id
			out.print(topicid);
		}else{
			//删除失败，返回0
			out.print(0);
		}
		out.flush();
		out.close();
	}

	private void focuschangeOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		//根据点击，查询关注关系并操作数据库，如果查出来有，则删除，没有则添加
		UserBiz ub=new UserBizImpl();
		FocusBiz fb=new FocusBizImpl();
		PrintWriter out = response.getWriter();
//		String userid1="2";//登陆的用户
//		String userid2="1";//主页的主人
		int focus=ub.judgeFocus(userid1,userid2);
		if(focus>0){//证明user1关注了user2,要删除,并且返回数据给回调函数
			fb.delete(userid1,userid2);
			out.print(0);
			
		}else{//user1没关注user2，要添加一条新纪录
			fb.add(userid1,userid2);
			out.print(1);
		}
		out.flush();
		out.close();
	}

	private void showfocusOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException, ServletException {
		UserBiz ub=new UserBizImpl();
		//取出登陆用户是否已经关注了当前这个主页的用户,1表示关注了，0表示没有关注
//		String userid1="2";//登陆的用户
//		String userid2="1";//主页的主人
		int focus=ub.judgeFocus(userid1,userid2);
		PrintWriter out = response.getWriter();
		//System.out.println(focus);
		out.print(focus);
		out.flush();
		out.close();
	}

	//进入个人主页，展示
	private void showOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO 假设现在是登陆的人和点击的头像是同一个用户都是 userid=1, email=572480349@qq.com 
				UserBiz ub=new UserBizImpl();
				RequestUtil ru=new RequestUtil();
				HttpSession session=request.getSession();
				
				//1.取出当前用户的User，得到user的userid1
		//		String userid1="2";//request.getParameter(" userid");//当前登录用户的id
				//User user=ub.findUserByEmail(email1);
				
				//2.根据当前点击的topic在相应的topic里取出userid2
				//Topic topic=ru.parseRequest(request, Topic.class);
		//		String userid2="1"; //request.getParameter(" email2");
				
				//3.两者比对,如果为false,则不显示toolbar,否则显示
				Boolean set=false;
				if(userid1.equals(userid2)){
					set=true;
				}/*else{
					//取出登陆用户是否已经关注了当前这个主页的用户,1表示关注了，0表示没有关注
					int focus=ub.judgeFocus(userid1,userid2);
					session.setAttribute("focus", focus);
				}*/
				session.setAttribute("set", set);
				
				
				//4.拼出这个页面用户（user2）的信息bean,存到session hostuser
			//	String email2="572480349@qq.com";//request.getParameter(" email2");
				User hostuser=ub.findUserByID(userid2);
				session.setAttribute("hostuser", hostuser);
				
				//5.拼出这个用户主页下的所有topic放到hostTopiclist中，存到session
				TopicBiz tb=new TopicBizImpl();
				List<Topic> hostTopiclist=tb.findTopicByHostUserid(userid2);//只能通过userid来查找topic，不能用email
				session.setAttribute("hostTopiclist", hostTopiclist);
				//6.每篇post的点赞数
				//TODO
				for(Topic tp:hostTopiclist){
					String id=tp.getId();
					int like=tb.findLikeByTopicId(id);
					tp.setLike(like);
				}
				
				//7.跳转
				request.getRequestDispatcher("homepage.jsp").forward(request, response);
		
	}

}
