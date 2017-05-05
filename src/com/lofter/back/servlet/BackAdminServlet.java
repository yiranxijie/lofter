package com.lofter.back.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.bean.Admin;
import com.lofter.bean.Topic;
import com.lofter.biz.impl.BackAdminBizImpl;
import com.lofter.servlet.CommonServlet;
import com.lofter.utils.RequestUtil;

public class BackAdminServlet extends CommonServlet {
	
	private static final long serialVersionUID = 458166868165542763L;
	private BackAdminBizImpl bubi=new BackAdminBizImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		System.out.println(op);
		try {
			// 判断op是什么，调用不同的方法做处理
			if (op != null && !"".equals(op)) {
				if ("list".equals(op)) {
					listOp(request, response);
				}else if( "add".equals(op)){
					addOp(request, response);
				}else if( "delete".equals(op)){
					deleteOp(request, response);
				}else if("update".equals(op)){
					updateOp(request,response);
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//update操作 当statu发生改变时也会自动跳到这里，并且完成数据库更新
	private void updateOp(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, SQLException, NamingException {
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		RequestUtil ru=new RequestUtil();
		Admin admin=ru.parseRequest(request, Admin.class);
		Admin r=bubi.update(admin);
		if(r!=null){
			super.outJson(response, r);
		}else{
			Map<String,String> map=new HashMap<String,String>();
			map.put("success", "false");
			super.outJson(response, map);
		}
		
	}
	private void addOp(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException,  NamingException {
		RequestUtil ru=new RequestUtil();
		Admin user=ru.parseRequest(request, Admin.class);
		try{
			user=bubi.add(user);
			super.outJson(response, user);
		}catch( Exception ex){
			Map<String,String> jsonMap=new HashMap<String,String>();
			jsonMap.put("isError", "true");
			jsonMap.put("msg", ex.getMessage());
			super.outJson(response, jsonMap);
		}	
	}
	/**
	 * 删除但超级管理员a是否能删除的
	 * @param request
	 * @param response
	 * @throws NamingException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	private void deleteOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		String id=request.getParameter("id");
		Map<String,String> jsonMap=new HashMap<String,String>();
		if("1".equals(id)){
			jsonMap.put("isError", "true");
			super.outJson(response, jsonMap);
			return;
		}
		int result=bubi.delete(id);
		if(  result>0){
			jsonMap.put("success", "true");
		}else{
			jsonMap.put("isError", "true");
		}
		super.outJson(response, jsonMap);
		
	}

	private void listOp(HttpServletRequest request, HttpServletResponse response) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException, IOException, NamingException {
		int rows=30;
		int page=1;
		if(   request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(   request.getParameter("rows")!=null){
			rows=Integer.parseInt(request.getParameter("rows"));
		}
		String order="desc";
		if(   request.getParameter("order")!=null){
			order=request.getParameter("order");
		}
		String sort="adminname";
		if(   request.getParameter("sort")!=null){
			sort=request.getParameter("sort");
		}		
		
		int total=bubi.findUserCount();
		List<Admin> list=bubi.findByPage(page, rows, order, sort);	
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list	);
		super.outJson(response, jsonMap);
	}

}
