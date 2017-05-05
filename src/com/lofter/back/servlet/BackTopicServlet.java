package com.lofter.back.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
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
import javax.servlet.http.HttpSession;

import com.lofter.biz.ReplyBiz;
import com.lofter.biz.impl.ReplyBizImpl;
import com.lofter.biz.impl.TopicBizImpl;
import com.lofter.biz.impl.UserBizImpl;
import com.lofter.servlet.CommonServlet;
import com.lofter.bean.Admin;
import com.lofter.bean.Picture;
import com.lofter.bean.Topic;
import com.lofter.bean.User;
import com.lofter.bean.Reply;
import com.lofter.utils.RequestUtil;

public class BackTopicServlet extends CommonServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TopicBizImpl rubi=new TopicBizImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op=request.getParameter("op");
		System.out.println(op);
		try {
			if(op!=null&&!"".equals(op)){
				//判断op
				if("list".equals(op)){
					listOp(request,response	);
				}else if("update".equals(op)){
					updateOp(request,response);
				}else if("delete".equals(op)){
					deleteOp(request,response);
				}else if("detail".equals(op)){
					detailOp(request,response);
				}else if("reply".equals(op)){
					replyOp(request,response);
				}else if("deletereply".equals(op)){
					deletereplyOp(request,response);
				}else if("shield".equals(op)){
					shieldOp(request,response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("../common/500.jsp");
		} 
	}
	
	//屏蔽图片（其实就是更新数据库）
	private void shieldOp(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException, NamingException {
		String topicid=request.getParameter("topicid");
		int i=rubi.shieldPic(topicid);
		PrintWriter out = response.getWriter();
		out.print(i);
		out.flush();
		out.close();
	}


	//删除回复
	private void deletereplyOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ReplyBizImpl rb=new ReplyBizImpl();
		String id=request.getParameter("id"); //这个是评论id
		Map<String, Boolean> map=new HashMap<String,Boolean>();
		int result=0;
		try {
			result=rb.deleteReplyById(id);
		} catch (Exception e) {
			result=-1;
			e.printStackTrace();
		}
		if(result>0){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		super.outJson(response, map);
		
	}



	private void replyOp(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException, IOException, NamingException {
		ReplyBizImpl rb=new ReplyBizImpl();
		
		int rows=30;
		int page=1;
		String topicid=request.getParameter("topicid");
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
		String sort="id";
		if(   request.getParameter("sort")!=null){
			sort=request.getParameter("sort");
		}		
		int total=rb.findReplyCountById(topicid);//评论总数
		List<Reply> list=rb.findReplyById(topicid);//评论列表
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", list	);
		super.outJson(response, jsonMap);
		
	}



	//扩展行
	private void detailOp(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, SQLException, Exception {
		
		int rows=30;
		int page=1;
		
		String topicid=request.getParameter("topicid");//取到ID
		String picid=request.getParameter("picid");//图片ID
		
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
		String sort="id";
		if(   request.getParameter("sort")!=null){
			sort=request.getParameter("sort");
		}	
		
		//通过ID查出当前ID的图片名称，热度，评论数
		Picture pic=rubi.findPicById(picid);
		if( pic!=null ){
			String picname=pic.getPicname();
		}
		String praise=Integer.toString( rubi.findLikeByTopicId(topicid) );// 点赞数（热度）
		ReplyBiz rb=new ReplyBizImpl();
		String backreplycount=Integer.toString( rb.findReplyCountById(topicid) );
		/*HttpSession ss=request.getSession();
		ss.setAttribute("picname", picname);
		ss.setAttribute("backpraise", praise);
		ss.setAttribute("backreplycount", backreplycount);
		ss.setAttribute("topicid", topicid);*/
		
		//使用MAP完成数据的封装
		int total=rb.findReplyCountById(topicid);
		List<Reply> list=rb.findReplyById(topicid);
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		
		jsonMap.put("total", total);//评论总数
		jsonMap.put("rows", list	);//评论列表
		super.outJson(response, jsonMap);
		//response.sendRedirect("backdetail.jsp");
	}




	//删除数据
	private void deleteOp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String id=request.getParameter("id");
		Map<String, Boolean> map=new HashMap<String,Boolean>();
		int result=0;
		try {
			result=rubi.deletetopic(id);
		} catch (Exception e) {
			result=-1;
			e.printStackTrace();
		}
		if(result>0){
			map.put("success", true);
		}else{
			map.put("success", false);
		}
		super.outJson(response, map);
	}
	
	
	//更新数据
	private void updateOp(HttpServletRequest request,
			HttpServletResponse response) throws NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException, IOException, NamingException {
		RequestUtil ru=new RequestUtil();
		Topic topic=ru.parseRequest(request, Topic.class);
		Topic r=rubi.update(topic);
		if(r!=null){
			super.outJson(response, r);
		}else{
			Map<String,String> map=new HashMap<String,String>();
			map.put("success", "false");
			super.outJson(response, map);
		}
		
	}

	

	//查找数据库，列出后台用户管理页面，在这个方法中就应该将图片的 名字回传给第一个分页表和热度
	private void listOp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page=1;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int rows=10;
		if(request.getParameter("rows")!=null){
			rows=Integer.parseInt(request.getParameter("rows"));
		}
		String order="desc";
		if(request.getParameter("order")!=null){
			order=request.getParameter("order");
		}
		String sort="id";
		if(request.getParameter("sort")!=null){
			sort=request.getParameter("sort");
		}
		
		//查出记录总数,已查到
		int total=rubi.findTopicCount();
		//再查出这一项记录
		List<Topic> list=rubi.findByPage(page, rows, order, sort);//id标示符无效
		for(Topic t:list){
			Picture pic=rubi.findPicById(t.getPicid());
			if( pic!=null ){
				t.setPicname(pic.getPicname());
			}
		}
		//使用MAP完成数据的封装
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",total);
		map.put("rows",list);
		
		super.outJson(response, map);
	}

}
