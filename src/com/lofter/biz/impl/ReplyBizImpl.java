package com.lofter.biz.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.bean.Reply;
import com.lofter.bean.ReplyLiModel;
import com.lofter.biz.ReplyBiz;
import com.lofter.dao.DBHelper;

public class ReplyBizImpl implements ReplyBiz {

	private DBHelper db=new DBHelper();
	public List<Reply> findReplyById(String topicid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException {
		List<Reply> replylist=null;
		String sql="select * from tb_reply left join tb_user on tb_reply.userid=tb_user.id where topicid='"+topicid+"' order by to_char(replytime,'yyyy-mm-dd hh24:mi:ss') desc";
		replylist=db.findMutiObject(Reply.class, sql, null);
		return replylist;
	}
	
	public Integer findReplyCountById(String topicid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException {
		String sql="select count(*) from tb_reply where topicid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(topicid);
		int replyCount=(int) db.findDouble(sql, params);
		return replyCount;
	}
	
	//添加回复
	public ReplyLiModel addReply(String topicid, String userid, String content,
			String parentid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
		String id=UUID.randomUUID().toString();
		String sql="insert into tb_reply(id,topicid,userid,content,parentid,replytime) values(?,?,?,?,?,sysdate)";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		params.add(topicid);
		params.add(userid);
		params.add(content);
		params.add(parentid);
		db.doUpdate(sql, params);
		
		sql="select userid,head,nickname,content,replytime,topicid from tb_reply left join tb_user on tb_reply.userid=tb_user.id where tb_reply.id='"+id+"'";
		ReplyLiModel rim=db.findSingleObject(ReplyLiModel.class, sql, null);
		return rim;
	}

	public int deleteReplyById(String id) throws SQLException, IOException, NamingException {
		String sql="delete from tb_reply where id=?";
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		int i=db.doUpdate(sql, params);
		return i;
	}

}
