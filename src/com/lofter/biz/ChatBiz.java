package com.lofter.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.lofter.bean.Chat;

public interface ChatBiz {

	public Integer createChat(String chattopic,String email) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;

	public List<Chat> searchRoom(String chattopic) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;
}
