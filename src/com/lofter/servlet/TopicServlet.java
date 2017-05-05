package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lofter.bean.FocusProcedureBean;
import com.lofter.bean.ProcedureBean;
import com.lofter.bean.Reply;
import com.lofter.bean.ReplyOfTopicItem;
import com.lofter.bean.Topic;
import com.lofter.bean.TopicModel;
import com.lofter.bean.User;
import com.lofter.biz.FocusBiz;
import com.lofter.biz.ReplyBiz;
import com.lofter.biz.TopicBiz;
import com.lofter.biz.UserBiz;
import com.lofter.biz.impl.FocusBizImpl;
import com.lofter.biz.impl.ReplyBizImpl;
import com.lofter.biz.impl.TopicBizImpl;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.utils.Encrypt;
import com.lofter.utils.ProcedureUtil;
import com.lofter.utils.RequestUtil;

public class TopicServlet extends CommonServlet {

	private TopicBiz tb = new TopicBizImpl();
	private UserBiz ubi = new UserBizImpl();
	private ReplyBiz rui = new ReplyBizImpl();
	private FocusBiz fbi = new FocusBizImpl();
	private ProcedureUtil pu = new ProcedureUtil();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("op");

		// 2、如果op的值不为空，对op的值进行任务的分发
		try {
			if (op != null && !"".equals(op)) {
				if (op.equals("show")) {
					showOp(request, response);
				} else if (op.equals("publish")) {
					publishOp(request, response);
				} else if (op.equals("takedata")) {
					takedataOp(request, response);
				} else if (op.equals("personaltopicshow")) {
					personaltopicshowOp(request, response);
				} else if (op.equals("loadmore")) {
					loadmoreOp(request, response);
				} else if (op.equals("deleteTopic")) {
					deleteTopicOp(request, response);
				} else if (op.equals("showcollect")) {
					showcollectOp(request, response);
				}
			} else {

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// 显示收藏文章
	private void showcollectOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, IOException,
			NamingException, ServletException {
		String userid = request.getParameter("userid");
		List<Topic> topiclist = tb.findCollectInfoByUserId(userid);
		HttpSession session = request.getSession();
		session.setAttribute("topiclist", topiclist);
		request.getRequestDispatcher("collect.jsp").forward(request, response);
	}

	// 删除topic
	private void deleteTopicOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException,
			NamingException {
		PrintWriter out = response.getWriter();
		String topicid = request.getParameter("topicid");
		int result = tb.deletetopic(topicid);
		HttpSession session = request.getSession();
		session.setAttribute("deletetopicid", topicid);
		out.print(result + " " + topicid);
		out.flush();
		out.close();
	}

	private void loadmoreOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String userid = request.getParameter("userid");

		int size = Integer.parseInt(request.getParameter("size"));

		int page = Integer.parseInt(request.getParameter("page"));
	//	System.out.println(size + "," + page);

		List<TopicModel> loadmorelistTopicModel = tb.findTopicModelByUserid(
				userid, size, page);
		User user = ubi.findUserByID(userid);

		if (loadmorelistTopicModel.size() != 0) {
			// 调用存储过程
			// 查询点赞记录
			List<ProcedureBean> listbean = new ArrayList<ProcedureBean>();
			ProcedureBean pb = null;
			for (TopicModel tm : loadmorelistTopicModel) {
				pb = new ProcedureBean(user.getId(), tm.getTopicid(), "0");
				listbean.add(pb);
			}
			ResultSet rs = pu.doProByTypeOfTable(listbean);

			while (rs.next()) {
				for (TopicModel tm : loadmorelistTopicModel) {
					if (tm.getTopicid().trim().equals(rs.getString(2).trim())) {
						tm.setPraiserecord(rs.getString(3));
					}
				}
			}

			// 查询收藏记录

			rs = pu.doProFindCollect(listbean);

			while (rs.next()) {
				for (TopicModel tm : loadmorelistTopicModel) {
					if (tm.getTopicid().trim().equals(rs.getString(2).trim())) {
						tm.setCollectrecord(rs.getString(3));
					}
				}
			}
			while (rs.next()) {
				System.out.println("result : \t" + rs.getString(1) + "\t"
						+ rs.getString(2) + "\t" + rs.getString(3));
			}
		}

		// 1.3、将topics存入session当中
		session.setAttribute("loadmorelistTopicModel", loadmorelistTopicModel);

		request.getRequestDispatcher("loadmore.jsp").forward(request, response);
		// response.sendRedirect( "show.jsp" );

	}

	// 查询个人文章
	private void personaltopicshowOp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userid = request.getParameter("userid");
		List<TopicModel> listtopic = tb.findPersonalTopicModelByUserid(userid);
		HttpSession session = request.getSession();
		session.setAttribute("personalTopic", listtopic);
		request.getRequestDispatcher("personaltopic.jsp").forward(request,
				response);
	}

	// 取数据
	private void takedataOp(HttpServletRequest request,
			HttpServletResponse response) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, Exception {
		PrintWriter out = response.getWriter();
		String loginuserid = request.getParameter("loginuserid");
		String curtopicid = request.getParameter("curtopicid");

		int praiseRecord = tb.findPraiseRecord(loginuserid, curtopicid);
		int collectRecord = tb.findCollectRecord(loginuserid, curtopicid);
		HttpSession session = request.getSession();
		session.setAttribute("praiseRecord", praiseRecord);
		session.setAttribute("collectRecord", collectRecord);
		out.print(collectRecord + " " + praiseRecord);
		out.flush();
	}

	/**
	 * 发布消息
	 * 
	 * @param request
	 * @param response
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NumberFormatException
	 * @throws NamingException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void publishOp(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException, SQLException,
			IOException, NamingException {
		RequestUtil ru = new RequestUtil();
		Topic topic = ru.parseRequest(request, Topic.class);
		int result = tb.addTopic(topic);
		HttpSession session = request.getSession();
		session.setAttribute("topic", topic);
	}

	/**
	 * 用户展示
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void showOp(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String login_flag = request.getParameter("login_flag");
		if ("".equals(email) || "".equals(pwd) || email == null || pwd == null) {
			if ("".equals(login_flag) || login_flag == null) {// 说明不是从登录页面过来的，那就按规则展示内容
				// 用户直接访问show.jsp，按我们推送的内容显示
				List<TopicModel> listTopicModel = tb.findTopicModel();

				// 1.3、将topics存入session当中
				session.setAttribute("listTopicModel", listTopicModel);
				session.removeAttribute("error");
				// TODO 跳转到展示页面
				request.getRequestDispatcher("show.jsp").forward(request,
						response);
				// response.sendRedirect( "show.jsp" );
			} else {
				session.setAttribute("error", "error");
				response.sendRedirect("mylogin.jsp");
				return;
			}
		} else {
			UserBiz ubi = new UserBizImpl();
			int r = ubi.checkLoginUserAndPwd(email, pwd);
			if (r <= 0) {// 账号或密码错误
				session.setAttribute("error", "error");
				// request.getRequestDispatcher("dologin.jsp?r="+r).forward(request,
				// response);
				response.sendRedirect("mylogin.jsp");
			} else {
				User user = ubi.findUserByEmail(email);
				int islogined=user.getIslogined();
				//检查是否非法登录
				if( islogined>0 ){//表示已经有人登陆
					request.getRequestDispatcher("illegalLogin.jsp").forward(request,response);
					return;
				}
				//合法登录，修改 islogined为1
				int res=ubi.modifyIslogined(email+":login");
				if( res>0 ){//修改成功
					user.setIslogined(1);
				}else{
					System.out.println( "修改islogined失败！" );
				}
				String userid = user.getId();
				int size = 5;
				int page = 1;

				List<TopicModel> listTopicModel = tb.findTopicModelByUserid(
						userid, size, page);
				int count = tb.findTopicCountByUserid(userid);
				int topicCount = tb.findTopicCount(userid);
				int focusedCount = fbi.findFocusedTopicCount(userid); // 关注我的人的总数
																		// 粉丝
				int showfocusCount = fbi.findFocusTopicCount(userid); // 我关注的人的总数

				// 1.3、将topics存入session当中
				session.setAttribute("user", user);
				session.setAttribute("pwd", pwd);
				session.setAttribute("topicCount", topicCount);
				session.setAttribute("focusedCount", focusedCount);
				session.setAttribute("showfocusCount", showfocusCount);
				session.setAttribute("size", size);
				session.setAttribute("page", page);
				session.setAttribute("count", count);
				session.removeAttribute("error");

				if (listTopicModel.size() != 0) {
					// 调用存储过程
					// 查询点赞记录
					List<ProcedureBean> listbean = new ArrayList<ProcedureBean>();
					ProcedureBean pb = null;
					for (TopicModel tm : listTopicModel) {
						pb = new ProcedureBean(user.getId(), tm.getTopicid(),
								"0");
						listbean.add(pb);
					}
					ResultSet rs = pu.doProByTypeOfTable(listbean);

					while (rs.next()) {
						for (TopicModel tm : listTopicModel) {
							if (tm.getTopicid().trim()
									.equals(rs.getString(2).trim())) {
								tm.setPraiserecord(rs.getString(3));
							}
						}
					}

					// 查询收藏记录

					rs  = pu.doProFindCollect(listbean);

					while (rs.next()) {
						for (TopicModel tm : listTopicModel) {
							if (tm.getTopicid().trim()
									.equals(rs.getString(2).trim())) {
								tm.setCollectrecord(rs.getString(3));
							}
						}
					}

					while (rs.next()) {
						System.out.println("result : \t" + rs.getString(1)
								+ "\t" + rs.getString(2) + "\t"
								+ rs.getString(3));
					}
				}

				session.setAttribute("listTopicModel", listTopicModel);
				request.getRequestDispatcher("show.jsp").forward(request,
						response);
			}
		}
	}

}
