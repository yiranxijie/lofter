package com.lofter.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.lofter.bean.Reply;
import com.lofter.bean.ReplyLiModel;

public interface ReplyBiz {

	public List<Reply> findReplyById( String topicid ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;

	public ReplyLiModel addReply(String topicid,String userid,String content,String parentid ) throws SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception;

	public Integer findReplyCountById(String topicid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;
}
