package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lofter.bean.FocusProcedureBean;
import com.lofter.bean.ProcedureBean;
import com.lofter.bean.TopicModel;
import com.lofter.bean.User;
import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.dao.DBHelper;
import com.lofter.utils.Encrypt;
import com.lofter.utils.ProcedureUtil;
import com.lofter.utils.SendEmail;

public class UserServlet extends CommonServlet {
	private DBHelper db=new DBHelper();
	private UserBiz ubi=new UserBizImpl();
	private ProcedureUtil pu=new ProcedureUtil();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取出op
		String op = request.getParameter("op");
		// 判断op是什么，调用不同的方法做处理
		try {
			if (op != null && !"".equals(op)) {
				if ("reg".equals(op)) {
					regOP(request, response);
				}else if( "checkuser".equals(op)){
					checkuserOp( request,response);
				}else if( "checkNickname".equals(op)){
					checkNicknameOp( request,response);
				}else if( "loginCodeCheck".equals(op)){
					loginCodeCheckOp( request,response);
				}else if( "checkloginuser".equals(op)){
					checkloginuserOp( request,response);
				}else if( "showfans".equals(op)){
					showfansOp( request,response);
				}else if( "showfocus".equals(op)){
					showfocusOp( request,response);
				}else if( "dofocus".equals(op)){
					dofocusOp( request,response);
				}else if( "cancelfocus".equals(op)){
					cancelfocusOp( request,response);
				}else if( "addfocus".equals(op)){
					addfocusOp( request,response);
				}else if( "removefocus".equals(op)){
					removefocusOp( request,response);
				}else if( "showAllUsers".equals(op)){
					showAllUsersOp( request,response);
				}else if( "exit".equals(op)){
					exitOp( request,response);
				}
			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("common/500.jsp");
		}
	}

	private void exitOp(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, NamingException, ServletException {
		//退出登录，应将islogined置为0
		String userid=request.getParameter("exit");
        int r=ubi.modifyIslogined(userid+":exit");
        request.getRequestDispatcher("mylogin.jsp").forward(request, response);
	}

	//浏览，展示所有用户
	private void showAllUsersOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		String userid=user.getId();
		List<User> allusers=null;
		if( userid==null ){
			allusers=ubi.findAllUsers();
		}else{
			allusers=ubi.findAllUsers(userid);
		}
		//存储过程：查询我是否关注我的粉丝记录
		List<FocusProcedureBean> listbean = new ArrayList<FocusProcedureBean>();
		FocusProcedureBean pb=null;
		for( User u: allusers){
			pb=new FocusProcedureBean(userid,u.getId(),"0");
			listbean.add(pb);
		}
		ResultSet rs=pu.doProFindFocusRecord(listbean);
		
			while( rs.next() ){
				for( User u: allusers){
					if( u.getId().trim().equals(rs.getString(2).trim())){
						u.setFocusrecord(rs.getString(3));
					}
			}
		}
					
		session.setAttribute("allusers", allusers);
		int alluserscount=allusers.size();
		session.setAttribute("alluserscount", alluserscount);
		request.getRequestDispatcher("scanAllUser.jsp").forward(request, response);
	}

	//移除粉丝
	private void removefocusOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException, NamingException {
		PrintWriter out=response.getWriter();
		String curuserid=request.getParameter("userid");	//当前被关注用户id
		String willremoveuserid=request.getParameter("willremoveuserid");	//待移除用户
		int r=ubi.removeFocus(willremoveuserid, curuserid);
		out.print(r+" "+willremoveuserid);
		out.flush();
		out.close();
	}

	//添加关注
	private void addfocusOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException, NamingException {
		PrintWriter out=response.getWriter();
		String curuserid=request.getParameter("userid");
		String willfocususerid=request.getParameter("willfocususerid");
		int r=ubi.doFocus(willfocususerid, curuserid);
		out.print(r+" "+willfocususerid);
		out.flush();
		out.close();
	}

	//取消关注
	private void cancelfocusOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		PrintWriter out=response.getWriter();
		String curuserid=request.getParameter("userid");
		String willcanceluserid=request.getParameter("willcanceluserid");
		int r=ubi.doFocus(willcanceluserid, curuserid);
		out.print(r+" "+willcanceluserid);
		out.flush();
		out.close();
	}

	//关注用户
	private void dofocusOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		PrintWriter out=response.getWriter();
		String focuseduserid=request.getParameter("focuseduserid");	//被关注对象
		String userid=request.getParameter("userid");	
		int r=ubi.doFocus(focuseduserid,userid);
		out.print(r+" "+focuseduserid);
		out.flush();
		out.close();
	}

	//当前用户所关注的人
	private void showfocusOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException, ServletException {
		String userid=request.getParameter("userid");
		List<User> userlist=ubi.findFocusByUserid(userid);
		HttpSession session=request.getSession();
		session.setAttribute("focuslist", userlist);
		int fanscount=userlist.size();
		session.setAttribute("focuscount", fanscount);
		request.getRequestDispatcher("focus.jsp").forward(request, response);
	}

	//粉丝展示
	private void showfansOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException, ServletException {
		String userid=request.getParameter("userid");
		List<User> userlist=ubi.findFansByUserid(userid);	//我的粉丝
		HttpSession session=request.getSession();
		
		//存储过程：查询我是否关注我的粉丝记录
		List<FocusProcedureBean> listbean = new ArrayList<FocusProcedureBean>();
		FocusProcedureBean pb=null;
		for( User u: userlist){
			pb=new FocusProcedureBean(userid,u.getId(),"0");
			listbean.add(pb);
		}
		ResultSet rs=pu.doProFindFocusRecord(listbean);
		
			while( rs.next() ){
				for( User u: userlist){
					if( u.getId().trim().equals(rs.getString(2).trim())){
						u.setFocusrecord(rs.getString(3));
					}
			}
		}
		
		session.setAttribute("fanslist", userlist);
		int fanscount=userlist.size();
		session.setAttribute("fanscount", fanscount);
		request.getRequestDispatcher("fans.jsp").forward(request, response);
	}

	//登陆的时候检查用户是否存在
	private void checkloginuserOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException, ServletException {
		String email=request.getParameter("email");
		int r=ubi.checkLoginUser(email);
		request.getRequestDispatcher("doreg.jsp?r="+r).forward(request, response);
	}

	//校验验证码是否正确
	private void loginCodeCheckOp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		String pwd=(String) session.getAttribute("pwd");
		String code=request.getParameter("code");	//用户填写的验证码
		String rcode= (String) session.getAttribute("rand");	//标准验证码
		if( code.equals(rcode)){
			response.sendRedirect( "topic.action?op=show&email="+email+"&pwd="+pwd);
		//	request.getRequestDispatcher("dologin.jsp?r=1").forward(request, response);
		}else{
		//	response.sendRedirect( "actionsuccess.jsp?email="+email+"&error=error" );
			request.getRequestDispatcher("dologin.jsp?r=0").forward(request, response);
		}
	}

	//验证是否有该昵称用户
	private void checkNicknameOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException, ServletException {
		String nickname=request.getParameter("nickname");
		String sql="select count(*) from tb_user where nickname=?";
		List<Object> params=new ArrayList<Object>();
		params.add(nickname);
		int r=(int)db.findDouble(sql, params);
		request.getRequestDispatcher("dologin.jsp?r="+r).forward(request, response);
	}

	//验证用户是否已经存在
	private void checkuserOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException, ServletException {
		String email=request.getParameter("email");
		String sql="select count(*) from tb_user where email=? and status=1";
		List<Object> params=new ArrayList<Object>();
		params.add(email);
		int r=(int)db.findDouble(sql, params);
		request.getRequestDispatcher("doreg.jsp?r="+r).forward(request, response);
	}

	private void regOP(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, NamingException {
//		1.数据库加两个字，state字段（0:未激活，1：激活成功），ActiCode:(放激活码)
//		2.用户填写资料，插入数据成功，state字段默认是0,同时生成一个ActiCode也存入数据库
//		3.提示用户激活。。。发送邮件。。。邮件中带一个激活成功页的URL，URL里有两个参数（1，用户ID，2：激活码）
//		4.用户点击链接，回到激活成功页。。。激活成功页的Load事件，得到两个参数，以这两个参数为条件查询数据库里的数据，如果有，修改字段state为1,反之。。提示激活失败，重新激活。。 
		
		String email=request.getParameter("email"); 
		String pwd=request.getParameter("pwd");
		HttpSession session=request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("pwd", pwd);
		Calendar c = Calendar.getInstance();
		//现在的时间(单位：毫秒)
		//TODO:时间换算问题，如何处理int和long之间的关系
		long time = c.getTimeInMillis();

		//创建激活码
		String token=Encrypt.md5(email+pwd+time);
		//过期时间为24小时后
//		int token_exptime=(int)(time+1000*60*60*24);
		String token_exptime=(time+1000*20)+"";
		
		String id=UUID.randomUUID().toString();
		String sql="insert into tb_user(id,email,pwd,token,token_exptime,regtime,status) values (?,?,?,?,?,sysdate,0)";
		
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		params.add(email);
		params.add(Encrypt.md5(pwd));
		params.add(token);
		params.add(token_exptime);
		
		int r=db.doUpdate(sql, params);	//保存注册信息
		
		if( r>0 ){
			//发送邮件
			///邮件的内容
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String ip = request.getServerName();
	  //      System.out.println("最终的ip："+ip);
			
	        StringBuffer sb=new StringBuffer("<div style=\"width:660px;overflow:hidden;border-bottom:1px solid #bdbdbe;\"><div style=\"height:52px;overflow:hidden;border:1px solid #464c51;background:#353b3f url(http://www.lofter.com/rsc/img/email/hdbg.png);\"><a href=\"http://www.lofter.com?mail=qbclickbynoticemail_20120626_01\" target=\"_blank\" style=\"display:block;width:144px;height:34px;margin:10px 0 0 20px;overflow:hidden;text-indent:-2000px;background:url(http://www.lofter.com/rsc/img/email/logo.png) no-repeat;\">LOFTER</a></div>"+"<div style=\"padding:24px 20px;\">您好，"+email+"<br/><br/>LOFTER是一款\"专注兴趣、分享创作\"的轻博客产品，旨在为\"热爱记录生活、追求时尚品质、崇尚自由空间\"的你，打造一个全新而定展示平台！<br/><br/>请点击下面链接激活账号，24小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
	        sb.append("<a href=\"http://"+ip+":8080/lofter/emailcheck.action?op=activate&id=");
	        sb.append(id);
	        sb.append("&token=");
	        sb.append(token);
	        sb.append("\">http://"+ip+":8080/lofter/emailcheck.action?op=activate&id=");
	        sb.append(id);
	        sb.append("&token=");
	        sb.append(token);
	        sb.append("</a>"+"<br/>如果以上链接无法点击，请把上面网页地址复制到浏览器地址栏中打开<br/><br/><br/>LOFTER，专注兴趣，分享创作<br/>"+sdf.format(new Date())+ "</div></div>" );

	        //发送邮件
	        SendEmail.send(email, sb.toString());
		}
		
		response.sendRedirect("doEmail.action?op=emaillogin");
		
	}

}
