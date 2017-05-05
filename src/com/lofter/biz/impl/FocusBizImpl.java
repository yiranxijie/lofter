package com.lofter.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.bean.Topic;
import com.lofter.bean.TopicModel;
import com.lofter.bean.User;
import com.lofter.biz.FocusBiz;
import com.lofter.dao.DBHelper;

public class FocusBizImpl implements FocusBiz {
	DBHelper db=new DBHelper();
	
	public void delete(String userid1, String userid2) throws SQLException, IOException, NamingException {
		List<Object> params=new ArrayList<Object>();
		String sql="delete from tb_focus_rel where focususerid=? and focuseduserid=?";
		params.add(userid1);
		params.add(userid2);
		db.doUpdate(sql, params);
	}

	public void add(String userid1, String userid2) throws SQLException, IOException, NamingException {
		List<Object> params=new ArrayList<Object>();
		String sql="insert into tb_focus_rel (focususerid,focuseduserid,focustime) values(?,?,sysdate) ";
		
		params.add(userid1);
		params.add(userid2);
		db.doUpdate(sql, params);
	}

	//关注我的人的总数
	public int findFocusedTopicCount(String userid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_focus_rel where focuseduserid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		int r=(int) db.findDouble(sql, params);
		return r;
	}

	//我关注的人的总数
	public int findFocusTopicCount(String focususerid) throws SQLException, IOException, NamingException {
		String sql="select count(*) from tb_focus_rel where focususerid=?"; 
		List<Object> params = new ArrayList<Object>();
		params.add(focususerid);
		int result=(int) db.findDouble(sql, params);
		return result;
	}

}
