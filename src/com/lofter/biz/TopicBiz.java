package com.lofter.biz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.lofter.bean.Topic;
import com.lofter.bean.TopicModel;

public interface TopicBiz {
	public List<TopicModel> findTopicModelByUserid( String userid,int start,int end) throws IllegalArgumentException, SQLException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException, Exception;

	public List<Topic> findTopicByHostUserid(String email2) throws IllegalArgumentException, SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, InvocationTargetException;
	
	public int addTopic(Topic topic) throws SQLException, IOException, NamingException;

	public int findLikeByTopicId(String id) throws SQLException, IOException, NamingException;

	public List<TopicModel> findTopicModel() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;

	public int findPraiseRecord(String loginuserid,String curtopicid) throws SQLException, IOException, NamingException;
	
	public int findCollectRecord(String loginuserid,String curtopicid) throws SQLException, IOException, NamingException;

	public Topic findT(String topicid ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, Exception;

	public int findTopicCount(String userid) throws SQLException, IOException, NamingException;
	
	public int deletetopic(String topicid) throws SQLException, IOException, NamingException;
	
	public List<TopicModel> findPersonalTopicModelByUserid(String userid) throws Exception;
	public int findTopicCountByUserid(String userid) throws IllegalArgumentException, SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, InvocationTargetException;

	public List<Topic> findCollectInfoByUserId(String userid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException;
}
