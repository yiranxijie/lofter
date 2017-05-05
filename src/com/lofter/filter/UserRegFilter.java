package com.lofter.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.dao.DBHelper;

public class UserRegFilter implements Filter {
	private DBHelper db=new DBHelper();

	public void destroy() {
		

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		PrintWriter out=response.getWriter();
		
		String username=request.getParameter("username");
		String userpwd=request.getParameter("userpwd");
		
		UserBiz ub=new UserBizImpl();
		try {
			boolean flag=ub.findUser(username, userpwd);
			if( flag ){
				request.getRequestDispatcher("user.action?op=login").forward(request, response);
			}else{
				out.println("<script>alert('你还未注册,请注册后在登入')</script>");
				//待用户点击确定弹出框时，跳转到用户注册页面
				request.getRequestDispatcher("user.action?op=reguser").forward(request, response);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		chain.doFilter(request, response);
			
			
		
		

	}

	public void init(FilterConfig arg0) throws ServletException {
	

	}

}
