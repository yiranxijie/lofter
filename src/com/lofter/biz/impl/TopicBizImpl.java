package com.lofter.biz.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.bean.Picture;
import com.lofter.bean.Topic;
import com.lofter.bean.TopicModel;
import com.lofter.bean.User;
import com.lofter.biz.TopicBiz;
import com.lofter.dao.DBHelper;

public class TopicBizImpl implements TopicBiz {
	private DBHelper db = new DBHelper();

	public List<TopicModel> findTopicModelByUserid(String userid,int size,int page)
			throws Exception {
		// 根据用户id查询出用户的所有发布的topic和所被关注用户的id
		int max=size* page;
		int min=size*(page-1)+1;
		List<TopicModel> listTMs = new ArrayList<TopicModel>();
		TopicModel tm = null;
		// 1、根据传进的userid并根据该userid查询出其关注的用户
		String sql = "select * from (select a.*, rownum as rn from(	select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from ( select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from( select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from ( select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic where tb_topic.userid in( select id from tb_user where id in(select focuseduserid from tb_focus_rel where focususerid=?) or id=?))  a left join tb_reply on a.id=tb_reply.topicid group by a.id,a.userid,title,a.content,picid,posttime,topictype) b left join tb_praise_rel on b.id=tb_praise_rel.topicid group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount ) c left join tb_user on c.userid=tb_user.id  order by posttime desc) a where rownum<=?) where rn>=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(userid);
		params.add(max);
		params.add(min);
		listTMs =db.findMutiObject(TopicModel.class, sql, params);
		return listTMs;
	}
	
	public List<TopicModel> findPersonalTopicModelByUserid(String userid)
			throws Exception {
		// 根据用户id查询出用户的所有发布的topic和所被关注用户的id

		List<TopicModel> listTMs = new ArrayList<TopicModel>();
		TopicModel tm = null;
		// 1、根据传进的userid并根据该userid查询出其关注的用户
		String sql = "select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from ( select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from( select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from ( select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic where tb_topic.userid=?)  a left join tb_reply on a.id=tb_reply.topicid group by a.id,a.userid,title,a.content,picid,posttime,topictype) b left join tb_praise_rel on b.id=tb_praise_rel.topicid group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount ) c left join tb_user on c.userid=tb_user.id  order by posttime desc";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		listTMs =db.findMutiObject(TopicModel.class, sql, params);
		return listTMs;
	}

	public int addTopic(Topic topic) throws SQLException, IOException, NamingException {
		String sql="insert into tb_topic(id,userid,title,content,picid,posttime,topictype) values(?,?,?,?,?,sysdate,?)";
		List<Object> params=new ArrayList<Object>();
		String id=UUID.randomUUID().toString();
		params.add(id);
		params.add(topic.getUserid());
		params.add(topic.getTitle());		
		params.add(topic.getContent());
		if(topic.getPicid()==null){
			params.add(" ");
		}else{
			params.add(topic.getPicid());
		}
		params.add(topic.getTopictype());
		
		int result=(int)db.doUpdate(sql, params);
		return result;
	}
	/* 查找当前主页下的用户的所有topic */
	public List<Topic> findTopicByHostUserid(String userid2)
			throws IllegalArgumentException, SQLException, IOException,
			NamingException, InstantiationException, IllegalAccessException,
			InvocationTargetException {
		List<Topic> list = new ArrayList<Topic>();
		List<Object> params = new ArrayList<Object>();
		String sql = "select * from tb_topic where userid=?";
		params.add(userid2);
		list = db.findMutiObject(Topic.class, sql, params);
		return list;
	}

	public int findLikeByTopicId(String id) throws SQLException, IOException,
			NamingException {
		int count = 0;
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(*) from tb_praise_rel where topicid=?";
		params.add(id);
		return (int) db.findDouble(sql, params);
	}

	public List<TopicModel> findTopicModel() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, IOException,
			NamingException {
		List<TopicModel> listTMs = new ArrayList<TopicModel>();
		TopicModel tm = null;
		String sql="select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from (select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from(select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from (select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic where tb_topic.userid in(select id from tb_user where id in(select focuseduserid from tb_focus_rel where focususerid=(select max(focuseduserid) from tb_focus_rel) ) or id=(select max(focuseduserid) from tb_focus_rel)) )  a left join tb_reply on a.id=tb_reply.topicid group by a.id,a.userid,title,a.content,picid,posttime,topictype ) b left join tb_praise_rel on b.id=tb_praise_rel.topicid group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount ) c left join tb_user  on c.userid=tb_user.id order by posttime desc"; 
		listTMs =db.findMutiObject(TopicModel.class, sql, null);
		return listTMs;
	}

	// 查找是否有点赞记录
	public int findPraiseRecord(String loginuserid, String curtopicid)
			throws SQLException, IOException, NamingException {
		String sql = "select count(*) from tb_praise_rel where userid=? and topicid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(loginuserid);
		params.add(curtopicid);
		int result = (int) db.findDouble(sql, params);
		return result;
	}

	// 查找是否有收藏记录
	public int findCollectRecord(String loginuserid, String curtopicid)
			throws SQLException, IOException, NamingException {
		String sql = "select count(*) from tb_collect where userid=? and topicid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(loginuserid);
		params.add(curtopicid);
		int result = (int) db.findDouble(sql, params);
		return result;
	}

	// 通过任何一个id标识查找对象的方法
	public Topic findT(String topicid) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, Exception {
		Topic topic = null;
		String sql = "select * from tb_topic where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(topicid);
		topic = db.findSingleObject(Topic.class, sql, params);
		return topic;
	}

	public int findTopicCount(String userid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_topic where userid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		int r=(int) db.findDouble(sql, params);
		return r;
	}
	
	//删除指定ID的topic
		public int deletetopic(String topicid) throws SQLException, IOException, NamingException {
			List<Object> params=new ArrayList<Object>();
			String sql="delete from tb_topic where id=?";
			params.add(topicid);
			return db.doUpdate(sql, params);
		}
		//查询登入用户所要展示的topic总数
		public int findTopicCountByUserid(String userid) throws IllegalArgumentException, SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, InvocationTargetException {
			List<TopicModel> listTMs = new ArrayList<TopicModel>();
			TopicModel tm = null;
			String sql = "select c.id topicid,c.userid,c.title,c.content,c.picid,c.posttime,c.topictype,c.replycount,c.praisecount,email,pwd,nickname,autograph,head,regtime,status from ( select b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount , count( tb_praise_rel.id) as praisecount from( select  a.id,a.userid,title,a.content,picid,posttime,topictype , count(tb_reply.id)as replycount from ( select tb_topic.id as id,tb_topic.userid as userid,title,tb_topic.content as content,picid,posttime,topictype from tb_topic where tb_topic.userid in( select id from tb_user where id in(select focuseduserid from tb_focus_rel where focususerid=?) or id=?))  a left join tb_reply on a.id=tb_reply.topicid group by a.id,a.userid,title,a.content,picid,posttime,topictype) b left join tb_praise_rel on b.id=tb_praise_rel.topicid group by b.id,b.userid,b.title,b.content,b.picid,b.posttime,b.topictype,b.replycount ) c left join tb_user on c.userid=tb_user.id   ";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			params.add(userid);
			listTMs =db.findMutiObject(TopicModel.class, sql, params);
			return listTMs.size();
			
		}

		//查询收藏topic
		public List<Topic> findCollectInfoByUserId(String userid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, IOException, NamingException {
			List<Topic> topiclist=null;
			String sql="select * from tb_topic where id in(select topicid from tb_collect where userid=?)";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			topiclist=db.findMutiObject(Topic.class, sql, params);
			return topiclist;
		}

		public Picture findPicById(String picid) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, Exception{
			Picture p=null;
	//		picid="http://localhost:8080/lofter/images/2cfd6576-6ba0-41d9-88f2-833451878754.jpg";
			String sql="select * from tb_pic where id=?";
			List<Object> params=new ArrayList<Object> ();
			params.add(picid.trim());
			p=db.findSingleObject(Picture.class, sql, params);
			return p;
		}

		public Topic update(Topic topic) throws SQLException, IOException, NamingException {
			String sql="update tb_topic set userid=?,title=?,content=?,picid=? where id=?";		
			List<Object> params=new ArrayList<Object>();		
			params.add(topic.getUserid());
			params.add(topic.getTitle());
			params.add(topic.getContent());
			params.add(topic.getPicid());
			params.add(topic.getId());
			
			int result=db.doUpdate(sql, params);
			if(result>0){
				return  topic;
			}else{
				return null;
			}
		}

		/*查出所有topic的总数数量*/
		public int findTopicCount() throws SQLException, IOException, NamingException {
			String sql="select count(id) from tb_topic";
			double count=db.findDouble(sql, null);
			return (int)count;
		}

		public List<Topic> findByPage(int page, int rows, String order,
				String sort) throws IllegalArgumentException, SQLException, IOException, NamingException, InstantiationException, IllegalAccessException, InvocationTargetException {
			List<Topic> list=null;
			int max=page*rows;
			int min=(page-1)*rows+1;
			String sql="select * from (select a.*,rownum as rn from(select * from tb_topic order by "+sort+" "+order+")a where rownum<=?) where rn>=?";
			List<Object> params=new ArrayList<Object>();
			//params.add(sort);
			//params.add(order);
			params.add(max+"");
			params.add(min+"");
			
			list=db.findMutiObject(Topic.class,sql,params	);
			
			return list;
		}

		public int shieldPic(String topicid) throws SQLException, IOException, NamingException {
			String sql="update tb_topic set picid='images/888.jpg' where id=?";
			List<Object> params=new ArrayList<Object>();
			params.add(topicid);
			return db.doUpdate(sql, params);
		}

}
