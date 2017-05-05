package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.biz.PraiseBiz;
import com.lofter.biz.impl.PraiseBizImpl;

public class DoPraiseServlet extends CommonServlet {

	private PraiseBiz pbi=new PraiseBizImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");

		//2、如果op的值不为空，对op的值进行任务的分发
		try {
			if( op!=null && !"".equals( op ) ){
				if( op.equals( "praise")){
					praiseOp( request,response);
				}
			}else{

			}
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}

	//点赞或者取消赞
	private void praiseOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String topicid=request.getParameter("topicid");
		int r=pbi.doPraise(userid, topicid);
		int praiseCount=pbi.findPraiseCount(topicid);
		out.print(topicid+" "+r+" "+praiseCount);
		out.flush();
		out.close();
	}

}
