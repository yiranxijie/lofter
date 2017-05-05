package com.lofter.biz.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;

import com.lofter.bean.Admin;
import com.lofter.bean.Topic;
import com.lofter.bean.User;
import com.lofter.dao.DBHelper;
import com.lofter.utils.Encrypt;

public class BackAdminBizImpl {
	private DBHelper db = new DBHelper();
	
	public Admin add(  Admin user) throws SQLException, IOException, NamingException{
		String sql="insert into tb_admin(id,adminname, adminpwd) values( ?,?,? )";
		List<Object> params=new ArrayList<Object>();
		String id=UUID.randomUUID().toString();
		params.add( id);
		params.add(    user.getAdminname() );
		params.add(   Encrypt.md5(user.getAdminpwd())  );
		int result=db.doUpdate(sql, params);
		if(   result>0){
			return user;
		}else{
			return null;
		}
	}
	
	public int findUserCount() throws SQLException, IOException, NamingException {
		String sql = "select count(adminname) from tb_admin";
		double count = db.findDouble(sql, null);
		return (int) count;
	}
	
	public List<Admin> findByPage(    int currentpage, int pagesize, String sorttype, String ordercolumn) throws IllegalArgumentException, SQLException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NamingException{
		List<Admin> list=null;
		int max=currentpage*pagesize;
		int min=(currentpage-1)*pagesize+1;
		String sql="select * from (select a.*, rownum as rn from(	select * from tb_admin order by "+ordercolumn+" "+sorttype+") a where rownum<=?) where rn>=?";
		List<Object> params=new ArrayList<Object>();
		params.add(max);
		params.add(min);
		list=db.findMutiObject(Admin.class, sql, params);
		return list;
	}
	
	public int delete(  String id) throws SQLException, IOException, NamingException{
		String sql="delete from tb_admin where id=?";
		List<Object> params=new ArrayList<Object>();	
		params.add(  id );
		int result=db.doUpdate(sql, params);
		return result;
	}
	
	//更新操作
	public Admin update(Admin admin) throws SQLException, IOException, NamingException {
		String sql="update tb_admin set adminname=?,adminpwd=?,status=? where id=?";		
		List<Object> params=new ArrayList<Object>();		
		params.add(admin.getAdminname());
		params.add(admin.getAdminpwd());
		params.add(admin.getStatus());
		params.add(admin.getId());
		
		int result=db.doUpdate(sql, params);
		if(result>0){
			return  admin;
		}else{
			return null;
		}
	}
}
