package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopicAnalysisServlet extends CommonServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String op=request.getParameter("op");
		
		if(op.equals("getData")){
			getDataOp(request,response);
		}
		
	}
	private void getDataOp(HttpServletRequest request,
			HttpServletResponse response) {
		
		
	}

}
