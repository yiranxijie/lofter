package com.lofter.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.lofter.biz.PicturesBiz;
import com.lofter.biz.impl.PicturesBizImpl;
import com.lofter.utils.FileUploadUtil;

public class FileUploadServlet extends CommonServlet {

	private String uploadFilePath = "images";
	private PicturesBiz pb=new PicturesBizImpl();
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (request.getSession().getServletContext().getInitParameter(
				"uploadFilePath") != null) {
			uploadFilePath = request.getSession().getServletContext()
					.getInitParameter("uploadFilePath");
		}
		JspFactory fac = JspFactory.getDefaultFactory();
		PageContext pageContext = fac.getPageContext(this, request, response,
				null, false, 1024, true);

		FileUploadUtil fuu = new FileUploadUtil();

		try {
			
			Map<String, List<String>> pvalues = fuu.fileUpload(pageContext,
					uploadFilePath);
			
			
	//		session.removeAttribute("fileName");
			// 请注意: 这个参数不是放在post 实体中，而是放在参数信息中传递的.
			String callback = request.getParameter("CKEditorFuncNum");
			// 取出文件名
			String fileName = pvalues.get("fileList").get(1);
			
			// 取出基底网站
			String basePath="";
			if( request.getSession().getServletContext()
					.getAttribute("basePath")!=null){
				basePath = (String) request.getSession().getServletContext()
				.getAttribute("basePath");
			}
			
			
			// 浏览图片的路径
			fileName = basePath + uploadFilePath + "/" + pvalues.get("fileList").get(1);
			//将图片保存到数据库
			pb.addPics(fileName);
			out.println(fileName);
			out.flush();
			out.close();
			
			
			
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<font color=\"red\"size=\"2\">" + e.getMessage()
					+ "</font>");

		} 

		
	}

}
