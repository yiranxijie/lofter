package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lofter.bean.Chat;
import com.lofter.bean.FocusProcedureBean;
import com.lofter.bean.ProcedureBean;
import com.lofter.bean.TopicModel;
import com.lofter.bean.User;
import com.lofter.biz.ChatBiz;
import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.ChatBizImpl;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.dao.DBHelper;
import com.lofter.utils.Encrypt;
import com.lofter.utils.ProcedureUtil;
import com.lofter.utils.SendEmail;

public class ChatServlet extends CommonServlet {
	private DBHelper db=new DBHelper();
	private ChatBiz cb=new ChatBizImpl();
	private ProcedureUtil pu=new ProcedureUtil();
	private UserBiz ub=new UserBizImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取出op
		String op = request.getParameter("op");
		// 判断op是什么，调用不同的方法做处理
		try {
			if (op != null && !"".equals(op)) {
				if ("chat".equals(op)) {
					chatOP(request, response);
				}else if( "createchat".equals(op)){
					createchatOp( request,response);
				}else if( "searchroom".equals(op)){
					searchroomOp( request,response);
				}else if( "enter".equals(op)){
					enterOp( request,response);
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("common/500.jsp");
		}
	}

	private void enterOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String chattopic=request.getParameter("chattopic");
		List<Chat> roomlist=cb.searchRoom(chattopic);
		String email="";
		for(Chat room:roomlist){
			email=room.getEmail();
		}
		User chatuser=ub.findUserByEmail(email);
		HttpSession session=request.getSession();
		session.setAttribute("chatuser", chatuser);
		session.setAttribute("chattopic", chattopic);
	}

	//按房间名或房间号搜索房间 
	private void searchroomOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException {
		String chattopic=request.getParameter("chattopic");
		PrintWriter out=response.getWriter();
		List<Chat> roomlist=cb.searchRoom(chattopic);
		HttpSession session=request.getSession();
		session.setAttribute("roomlist", roomlist);
		String str="";
		for(Chat room:roomlist){
			str+="<a><span>"+room.getChattopic()+"</span></a>";
		}
		if(roomlist.size()<=0){
			str+="<a><span>对不起，您要找的房间不存在！</span></a>";
		}
		out.print(str);
		out.flush();
		out.close();
	}

	//创建聊天室
	private void createchatOp(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException, NamingException, Exception {
		String email=request.getParameter("email");
		String chattopic=request.getParameter("topic");
		HttpSession session=request.getSession();
		session.setAttribute("chattopic", chattopic);
		int r=cb.createChat(chattopic,email);
	//	request.getRequestDispatcher("chatroom.jsp").forward(request, response);
	}

	//createchat.jsp
	private void chatOP(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		HttpSession session=request.getSession();
		session.setAttribute("manager", email);
		request.getRequestDispatcher("createchat.jsp").forward(request, response);
	}

}
