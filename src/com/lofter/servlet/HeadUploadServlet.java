package com.lofter.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.alibaba.fastjson.JSON;
import com.lofter.bean.Result;

public class HeadUploadServlet extends CommonServlet {

	private static final long serialVersionUID = 7763790802943496107L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String contentType = request.getContentType();

		if ( contentType.indexOf("multipart/form-data") >= 0 )
		{
			Result result = new Result();
			result.avatarUrls = new ArrayList<Object>();
			result.success = false;
			result.msg = "Failure!";


			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			FileItemIterator fileItems=null;
			try {
				fileItems = upload.getItemIterator(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			} 
			
			//存了几张图片，我们要取出avatar1
			
			//定义上传的图片存储时的名字
			
			//定义一个变量用以储存当前头像的序号
			int avatarNumber = 1;
			//取服务器时间+8位随机码作为部分文件名，确保文件名无重复。
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS"); 
			String fileName = simpleDateFormat.format(new Date());
			Random random = new Random();
			String randomCode = "";
			for ( int i = 0; i < 8; i++ )
			{
				randomCode += Integer.toString(random.nextInt(36), 36);
			}
			fileName = fileName + randomCode;//最终的文件名 
			
			
			//基于原图的初始化参数
			String initParams = "";
			BufferedInputStream	inputStream;
			BufferedOutputStream outputStream;
			//遍历表单域
			try {
				while( fileItems.hasNext() )
				{
					FileItemStream fileItem = fileItems.next();//取出request传过来的图片集中的第一个 item
					String fieldName = fileItem.getFieldName();//图片的名字 
					//是否是原始图片 file 域的名称（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）
					Boolean isSourcePic = fieldName.equals("__source"); //如果是原图为ture,不是为false
					
					//当前头像基于原图的初始化参数（只有上传原图时才会发送该数据，且发送的方式为POST），用于修改头像时保证界面的视图跟保存头像时一致，提升用户体验度。
					
					
					
					//修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
					if ( fieldName.equals("__initParams") )
					{
						inputStream = new BufferedInputStream(fileItem.openStream());
						byte[] bytes = new byte [inputStream.available()];
						inputStream.read(bytes); 
						initParams = new String(bytes, "UTF-8");
						inputStream.close();
					}
					//如果是原始图片 file 域的名称或者以默认的头像域名称的部分“__avatar”打头(默认的头像域名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names)
					else if ( isSourcePic || fieldName.startsWith("__avatar1") )
					{
						String virtualPath = "/images/" + fileName + ".jpg";//要上传的图片 所在的地址 
						//原始图片（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）。
						if( isSourcePic )
						{
							//文件名，如果是本地或网络图片为原始文件名、如果是摄像头拍照则为 *FromWebcam.jpg
							String sourceFileName = fileItem.getName();	
							//原始文件的扩展名(不包含“.”)
							String sourceExtendName = sourceFileName.substring(sourceFileName.lastIndexOf('.') + 1);
							result.sourceUrl = virtualPath = String.format("/images/%s.%s", fileName, sourceExtendName);
						}
						//头像图片（默认的 file 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names）。
						else
						{
							result.avatarUrls.add(virtualPath);
							avatarNumber++;
						}
						
						//用输入流从当前的item中取出图片数据 ,通过输出流写出到指定文件夹中 
						inputStream = new BufferedInputStream(fileItem.openStream());
						System.out.println(fileName+".jpg"); //这个就是存到文件夹中的文件名，下边要将这个名字存入到数据库中 
						System.out.println(request.getSession().getServletContext().getRealPath("") + virtualPath.replace("/", "\\") );
						outputStream = new BufferedOutputStream(new FileOutputStream(request.getSession().getServletContext().getRealPath("") + virtualPath.replace("/", "\\")) ); 
						Streams.copy(inputStream, outputStream, true);	
						inputStream.close();
				        outputStream.flush();
				        outputStream.close();
						
					}
					else
					{
						//注释① upload_url中传递的查询参数，如果定义的method为post请使用下面的代码，否则请删除或注释下面的代码块并使用注释②的代码
						inputStream = new BufferedInputStream(fileItem.openStream());
						byte[] bytes = new byte [inputStream.available()];
						inputStream.read(bytes); 
						inputStream.close();
						if (fieldName.equals("userid"))
						{
							result.userid = new String(bytes, "UTF-8");
						}
						else if (fieldName.equals("username"))
						{
							result.username = new String(bytes, "UTF-8");
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			
				
			if ( result.sourceUrl != null )
			{
				result.sourceUrl += initParams;
			}
			result.success = true;
			result.msg = "Success!";
			
			System.out.println(result);
			//
			
			/*
				To Do...可在此处处理储存事项
			*/
			
			//返回图片的保存结果（返回内容为json字符串，可自行构造，该处使用fastjson构造）
			PrintWriter out = response.getWriter();
			out.println(JSON.toJSONString(result));
			out.flush();
			out.close();
		} 
		
		
	}

}
