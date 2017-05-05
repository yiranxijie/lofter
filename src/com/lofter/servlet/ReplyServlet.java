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

import com.lofter.bean.Reply;
import com.lofter.bean.ReplyLiModel;
import com.lofter.biz.ReplyBiz;
import com.lofter.biz.impl.ReplyBizImpl;

public class ReplyServlet extends CommonServlet {

	private ReplyBiz rui=new ReplyBizImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");

		//2、如果op的值不为空，对op的值进行任务的分发
		try {
			if( op!=null && !"".equals( op ) ){
				if( op.equals( "checkReplyCount")){
					checkReplyCountOp( request,response);
				}else if( op.equals( "addreply")){
					addreplyOp( request,response);
				}
			}else{

			}
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}

	//添加回复
	private void addreplyOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String topicid=request.getParameter("topicid");
		String userid=request.getParameter("userid");
		String content=request.getParameter("content");
		content=URLDecoder.decode(content,"utf-8");
		String parentid=request.getParameter("parentid");
		//插入数据并返回一个ReplyLiModel
		ReplyLiModel replyLiModel=rui.addReply(topicid, userid, content, parentid);
		int replyCount=rui.findReplyCountById(topicid);
		
		HttpSession session=request.getSession();
		session.setAttribute("replyLiModel", replyLiModel);
		session.setAttribute("replyCount", replyCount);
		request.getRequestDispatcher("addReply.jsp").forward(request, response);
	}

	//查询当前topic下的回复数
	private void checkReplyCountOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException, ServletException {
		String topicid=request.getParameter("topicid");
		List<Reply> replylist=rui.findReplyById(topicid);
//		int replycount=replylist.size();
		HttpSession session=request.getSession();
		session.setAttribute("replylist", replylist);
		session.setAttribute("topicid", topicid);
//		session.setAttribute("replycount", replycount);
		request.getRequestDispatcher("showReply.jsp").forward(request, response);
		
	}

}
