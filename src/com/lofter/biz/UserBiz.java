package com.lofter.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.lofter.bean.Topic;
import com.lofter.bean.User;

public interface UserBiz { 
	public User findUserByEmail( String email) throws IllegalArgumentException, SQLException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException, Exception;
	
	public int checkLoginUser(String email) throws SQLException, IOException, NamingException;
	
	public boolean findUser( String username,String userpwd) throws SQLException, IOException, NamingException;
	
	public List<Topic> findAllTopicByUserid(String userid ) throws IllegalArgumentException, SQLException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException;
	
	public void regUser(User user) throws SQLException, IOException, NamingException;
	
	public int judgeFocus(String email1, String email2) throws SQLException, IOException, NamingException;
	
	public int checkLoginUserAndPwd(String email,String pwd) throws SQLException, IOException, NamingException; 

	public int modifyIslogined(String email) throws SQLException, IOException, NamingException;
	
	public User findUserByID(String id) throws Exception;
	
	public int updatenickname(String newname,String userid) throws SQLException, IOException, NamingException;
	public int updateautograph(String newdesc, String userid) throws SQLException, IOException, NamingException;

	public int updatehead(String userid, String headname) throws SQLException, IOException, NamingException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, Exception;

	public List<User> findFansByUserid(String userid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;

	public List<User> findFocusByUserid(String focususerid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;

	public int doFocus(String focuseduserid, String userid) throws SQLException, IOException, NamingException;

	public int removeFocus(String userid, String focuseduserid) throws SQLException, IOException, NamingException;
	
	public List<User> findAllUsers(String userid) throws Exception;

	public List<User> findAllUsers() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;
	
}
