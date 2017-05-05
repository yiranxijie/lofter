package com.lofter.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.biz.CollectBiz;
import com.lofter.dao.DBHelper;

public class CollectBizImpl implements CollectBiz {

	private DBHelper db=new DBHelper();
	public int doCollect(String userid, String topicid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_collect where userid=? and topicid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		params.add(topicid);
		int r=(int) db.findDouble(sql, params);
		if(r>0){
			sql="delete from tb_collect where userid=? and topicid=?";
			db.doUpdate(sql, params);
			r=-1;
		}else{
			String id=UUID.randomUUID().toString();
			sql="insert into tb_collect(id,userid,topicid,collecttime) values(?,?,?,sysdate)";
			params.clear();
			params.add(id);
			params.add(userid);
			params.add(topicid);
			db.doUpdate(sql, params);
			r=1;
		}
		return r;
	}

}
