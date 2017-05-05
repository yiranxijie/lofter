package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.lofter.bean.JsonModel;

public class CommonServlet extends HttpServlet{
	

	private static final long serialVersionUID = 568170366995745983L;
	protected String filepath="";
	protected String basePath="";
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		filepath=request.getRealPath("/");
		HttpSession session=request.getSession();
		ServletContext application=session.getServletContext();
		if( application.getAttribute("basePath")!=null){
			basePath=(String) application.getAttribute("basePath");
		}
		super.service(request, response);
	}
	
	public void outJson(HttpServletResponse response,JsonModel jsonModel) throws IOException{
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		Gson g=new Gson();
		String s=g.toJson( jsonModel);
		out.println(s);
		out.flush();
		out.close();
		
	}
	
	

	
	public void outJson(HttpServletResponse response, Object jsonModel)
			throws IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson g = new Gson();
		String s = g.toJson(jsonModel);
		out.println(s);
		out.flush();
		out.close();
	}
	

}
