package com.lofter.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.biz.PicturesBiz;
import com.lofter.dao.DBHelper;

public class PicturesBizImpl implements PicturesBiz {
	private DBHelper db=new DBHelper();
	public void addPics(String picname) throws SQLException, IOException, NamingException {
		List<Object> params=new ArrayList<Object>();
		String sql="";
		sql="select * from tb_pic where id=?";
		params.add(picname);
		int result=(int)db.findDouble(sql, params);
		if(result<=0){
			sql="insert into tb_pic(id,picname) values(?,?)";
			params.clear();
			params.add(picname);
			params.add(picname);
			db.doUpdate(sql, params);
		}else{
			return;
		}

	}

}
