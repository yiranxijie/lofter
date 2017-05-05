package com.lofter.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.biz.PraiseBiz;
import com.lofter.dao.DBHelper;

public class PraiseBizImpl implements PraiseBiz {

	private DBHelper db=new DBHelper();
	public int doPraise(String userid, String topicid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_praise_rel where userid=? and topicid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(topicid);
		int r=(int) db.findDouble(sql, params);
		if(r>0){
			sql="delete from tb_praise_rel where userid=? and topicid=?";
			db.doUpdate(sql, params);
			r=-1;
		}else{
			String id=UUID.randomUUID().toString();
			sql="insert into tb_praise_rel(id,userid,topicid) values(?,?,?)";
			params.clear();
			params.add(id);
			params.add(userid);
			params.add(topicid);
			db.doUpdate(sql, params);
			r=1;
		}
		return r;
	}
	
	//查询热度
	public int findPraiseCount(String topicid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_praise_rel where topicid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(topicid);
		int praiseCount=(int) db.findDouble(sql, params);
		return praiseCount;
	}

}
