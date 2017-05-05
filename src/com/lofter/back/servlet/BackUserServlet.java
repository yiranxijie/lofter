package com.lofter.back.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.bean.User;

import com.lofter.biz.impl.UserBizImpl;
import com.lofter.servlet.CommonServlet;
import com.lofter.utils.RequestUtil;

public class BackUserServlet extends CommonServlet {

	private static final long serialVersionUID = 1536095997583501744L;
	
	private UserBizImpl rfbi=new UserBizImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		System.out.println("user的"+op);
		try {
			// 判断op是什么，调用不同的方法做处理
			if (op != null && !"".equals(op)) {
				if ("list".equals(op)) {
					listOp(request, response);
				}else if( "add".equals(op)){
					addOp(request, response);
				}else if( "delete".equals(op)){
					deleteOp(request, response);
				}else if( "update".equals(op)){
					updateOp(request, response);
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	private void updateOp(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, IOException, NamingException {
		RequestUtil ru=new RequestUtil(); //如果在user.jsp中展示的数据中有日期类型的，则传过来就会无法进行注入
		User user=new User();
		String id=request.getParameter("id");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		user.setNickname(nickname);
		user.setId(id);
		user.setEmail(email);
		
		/*User user=ru.parseRequest(request, User.class); 不要这个来取，直接用request取出来*/
		User r=rfbi.update(user);
		if(r!=null){
			super.outJson(response, r);
		}else{
			Map<String,String> map=new HashMap<String,String>();
			map.put("success", "false");
			super.outJson(response, map);
		}
		
	}

	private void deleteOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		String id=request.getParameter("id");
		int result=rfbi.delete(id);
		Map<String,String> jsonMap=new HashMap<String,String>();
		if(  result>0){
			jsonMap.put("success", "true");
		}else{
			jsonMap.put("success", "false");
		}
		super.outJson(response, jsonMap);
	}

	private void addOp(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException {
	/*	
		RequestUtil ru=new RequestUtil();
		User user=ru.parseRequest(request, User.class);
		
		user=rfbi.add(user);
		
		super.outJson(response, user);*/
	}

	private void listOp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, NamingException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		int rows=30;
		int page=1;
		if(   request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		if(   request.getParameter("rows")!=null){
			rows=Integer.parseInt(request.getParameter("rows"));
		}
		
		int total=rfbi.findUserCount();
		List<User> list=rfbi.findByPage(page, rows);	
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list	);
		super.outJson(response, jsonMap);
	}

}
