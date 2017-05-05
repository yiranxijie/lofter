package com.lofter.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface PraiseBiz {

	public int doPraise(String userid,String topicid ) throws SQLException, IOException, NamingException;
	
	public int findPraiseCount(String topicid) throws SQLException, IOException, NamingException;
}
