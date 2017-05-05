package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.biz.CollectBiz;
import com.lofter.biz.impl.CollectBizImpl;

public class CollectServlet extends HttpServlet {

	private CollectBiz cb=new CollectBizImpl();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");

		//2、如果op的值不为空，对op的值进行任务的分发
		try {
			if( op!=null && !"".equals( op ) ){
				if( op.equals( "collect")){
					collectOp( request,response);
				}
			}else{

			}
		} catch (Exception e) {

			e.printStackTrace();
		} 
	}

	//收藏或取消收藏
	private void collectOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException, NamingException {
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String topicid=request.getParameter("topicid");
		
		int r=cb.doCollect(userid, topicid);
		out.print(topicid+" "+r);
		out.flush();
		out.close();
	}

}
