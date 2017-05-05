package com.lofter.biz.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.bean.Chat;
import com.lofter.biz.ChatBiz;
import com.lofter.dao.DBHelper;

public class ChatBizImpl implements ChatBiz {

	private DBHelper db=new DBHelper();
	
	public Integer createChat(String chattopic,String email) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, IOException,
			NamingException {
		String id=UUID.randomUUID().toString();
		String sql="insert into tb_chat(id,email,chattopic,category) values(?,?,?,'')";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		params.add(email);
		params.add(chattopic);
		int r=db.doUpdate(sql, params);
		return r;
	}
	
	public List<Chat> searchRoom(String chattopic) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException{
		List<Chat> roomlist=null;
		String sql="select * from tb_chat where chattopic like '%"+chattopic+"%'";
		roomlist=db.findMutiObject(Chat.class, sql, null);
		return roomlist;
	}

}
