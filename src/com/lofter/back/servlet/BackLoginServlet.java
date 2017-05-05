package com.lofter.back.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lofter.servlet.CommonServlet;
import com.lofter.bean.JsonModel;
import com.lofter.dao.DBHelper;
import com.lofter.utils.Encrypt;

public class BackLoginServlet extends CommonServlet{
	private DBHelper db = new DBHelper();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminname = request.getParameter("adminname");
		String adminpwd = request.getParameter("adminpwd");
	
		HttpSession session = request.getSession();
		//将当前管理员的名字存到session中
		session.setAttribute("adminname", adminname);
		JsonModel jm = new JsonModel();
		
		String sql = "select count(*) from tb_admin where adminname=? and adminpwd=? and status=?";
		List<Object> params = new ArrayList<Object>();
		params.add(adminname);
		params.add(Encrypt.md5(adminpwd));
		params.add("on");
		try {
			double r=db.findDouble(sql, params);
			if( r>0){
				session.setAttribute("adminname", adminname);
				session.setAttribute("adminpwd", Encrypt.md5(adminpwd));
				session.setAttribute("role", "admin");   //  role
				jm.setCode(1);
				jm.setMsg("login success");
			}else{
				jm.setCode(0);
				jm.setMsg("login failure, username or password invalide");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg( e.getMessage() );
		} 
		
		super.outJson(response, jm);
	}
}
