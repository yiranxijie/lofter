package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lofter.bean.User;
import com.lofter.dao.DBHelper;

public class EmailActivateCheckServlet extends CommonServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取出op
				String op = request.getParameter("op");
				// 判断op是什么，调用不同的方法做处理
				try {
					if (op != null && !"".equals(op)) {
						if( "activate".equals(op)){
							activateOP(request,response);
						}
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("common/500.jsp");
				}
	}

	private void activateOP(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
		//获取参数token的值，即激活识别码。
		//将它与数据表中的用户信息进行查询对比，如果有相应的数据集，判断是否过期，如果在有效期内则将对应的用户表中字段status设置1，即已激活，这样就完成了激活功能。
		
		String id=request.getParameter("id");
		String token=request.getParameter("token");
		Calendar c = Calendar.getInstance();
		//现在的时间(单位：毫秒)
		long curtime = c.getTimeInMillis();
		
		String sql="select id,token_exptime,token,email,pwd from tb_user where status=0 and  token=?";
		List<Object> params=new ArrayList<Object>();
		params.add( token );
		
		DBHelper db=new DBHelper();
		User u=db.findSingleObject(User.class, sql, params);
		String email=u.getEmail();
		String pwd=u.getPwd();
		if( u!=null ){
			long token_exptime=Long.parseLong(u.getToken_exptime());
			if( curtime>token_exptime ){
				//激活码过期，先删除该用户记录，然后重新发送邮件
//				sql="delete from tb_user where id='"+u.getId()+"'";
//				db.doUpdate(sql, null);
				response.sendRedirect("actionfailer.jsp?email="+email+"&pwd="+pwd);
			//	 throw new DataExistAlreadyException("激活码已过期！");
				 return;
			}else{
				//验证激活码是否正确
				if( token.equals(u.getToken())){
					//激活成功， //并更新用户的激活状态，为已激活 
					sql="update tb_user set status=1 where id='"+u.getId()+"'";
					db.doUpdate(sql, null);
					response.sendRedirect("actionsuccess.jsp?email="+email+"&pwd="+pwd);
				}else{
					response.sendRedirect("actionfailer.jsp?email="+email+"&pwd="+pwd);
					return;
			//		 throw new DataExistAlreadyException("激活码不正确");  
				}
			}
		} else{
			response.sendRedirect("actionfailer.jsp");
		}
		
	}

}
