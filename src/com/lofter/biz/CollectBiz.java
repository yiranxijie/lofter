package com.lofter.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface CollectBiz {

	public int doCollect(String userid, String topicid) throws SQLException, IOException, NamingException;
}
