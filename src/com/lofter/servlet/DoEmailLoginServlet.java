package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DoEmailLoginServlet extends CommonServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		// 判断op是什么，调用不同的方法做处理
		try {
			if (op != null && !"".equals(op)) {
				if ("emaillogin".equals(op)) {
					emailloginOP(request, response);
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("common/500.jsp");
		}
	}

	private void emailloginOP(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//判断用户邮箱是什么，跳到指定邮箱登陆界面
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");	//572480349@qq.com
		String pwd=(String) session.getAttribute("pwd");
		String addrstr=email.split("@")[1];	//qq.com
		
		if( "qq.com".equals(addrstr)){
			addrstr="https://mail.qq.com";
		}else if( "163.com".equals(addrstr)){
			addrstr="http://mail.163.com/";
		}else if( "126.com".equals(addrstr)){
			addrstr="http://www.126.com/";
		}else if( "sina.com".equals(addrstr)){
			addrstr="http://mail.sina.com.cn/";
		}else if( "hotmail.com".equals(addrstr)){
			addrstr="https://login.live.com";
		}
		
		response.sendRedirect("emailaction.jsp?&addrstr="+addrstr);
	}

}
