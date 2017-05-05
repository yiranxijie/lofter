package com.lofter.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface FocusBiz {

	public void delete(String userid1, String userid2) throws SQLException, IOException, NamingException;

	public void add(String userid1, String userid2) throws SQLException, IOException, NamingException;

	public int findFocusedTopicCount(String userid) throws SQLException, IOException, NamingException;

	public int findFocusTopicCount(String userid) throws SQLException, IOException, NamingException;
	
}
