package com.lofter.biz.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.lofter.bean.Topic;
import com.lofter.bean.User;
import com.lofter.biz.UserBiz;
import com.lofter.dao.DBHelper;
import com.lofter.utils.Encrypt;

public class UserBizImpl implements UserBiz {
	private DBHelper db = new DBHelper();

	public User findUserByEmail(String email) throws Exception {
		String sql = "select * from tb_user where email=? and status=1";
		List<Object> params = new ArrayList<Object>();
		params.add(email);

		User user = db.findSingleObject(User.class, sql, params);
		return user;

	}

	public User findUserByID(String id) throws Exception {
		String sql = "select * from tb_user where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);

		User user = db.findSingleObject(User.class, sql, params);

		return user;

	}

	// 查询所有用户
	public List<User> findAllUsers(String userid) throws Exception {
		List<User> allusers = null;
		String sql = "select * from tb_user where id not in(?)  and status=1";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		allusers = db.findMutiObject(User.class, sql, params);
		return allusers;

	}

	// 检查登陆用户是否存在
	public int checkLoginUser(String email) throws SQLException, IOException,
			NamingException {
		String sql = "select count(*) from tb_user where email=? and status=1";
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		int r = (int) db.findDouble(sql, params);
		return r;
	}

	public int checkLoginUserAndPwd(String email, String pwd)
			throws SQLException, IOException, NamingException {
		String sql = "select count(*) from tb_user where email=? and pwd=?";
		List<Object> params = new ArrayList<Object>();
		params.add(email);
		params.add(Encrypt.md5(pwd));
		int r = (int) db.findDouble(sql, params);
		return r;
	}

	public boolean findUser(String username, String userpwd)
			throws SQLException, IOException, NamingException {
		boolean flag = false;
		// TODO 查询数据库中是否已经存在此用户
		String sql = "";
		List<Object> params = new ArrayList<Object>();
		params.add(username);
		params.add(userpwd);
		double result = db.findDouble(sql, params);
		if (result > 0) {
			flag = true;
		}
		return flag;
	}

	public List<Topic> findAllTopicByUserid(String userid)
			throws IllegalArgumentException, SQLException, IOException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NamingException {
		// TODO 通过用户id查询此用户下的所有的Topic
		String sql = "";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);

		List<Topic> list = db.findMutiObject(Topic.class, sql, params);
		return list;
	}

	public void regUser(User user) throws SQLException, IOException,
			NamingException {
		// 插入用户到数据库
		String sql = "";
		List<Object> params = new ArrayList<Object>();
		// 将用户信息添加到params
		// 通过db进行的数据库的操作
		db.doUpdate(sql, params);

	}

	// 查找两个用户之间是否关注
	public int judgeFocus(String userid1, String userid2) throws SQLException,
			IOException, NamingException {
		String sql = "select count(*) from tb_focus_rel where focususerid=? and focuseduserid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid1);
		params.add(userid2);

		return (int) db.findDouble(sql, params);
	}

	public int updatenickname(String newname, String userid)
			throws SQLException, IOException, NamingException {
		String sql = "update tb_user set nickname=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(newname);
		params.add(userid);
		int i = db.doUpdate(sql, params);
		return i;
	}

	public int updateautograph(String newdesc, String userid)
			throws SQLException, IOException, NamingException {
		String sql = "update tb_user set autograph=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(newdesc);
		params.add(userid);
		int i = db.doUpdate(sql, params);
		return i;
	}

	public int updatehead(String userid, String headname)
			throws IllegalArgumentException, InstantiationException,
			IllegalAccessException, InvocationTargetException, Exception {
		String sql = "select head from tb_user where id=?";
		List<Object> params = new ArrayList<Object>();
		// 想查出原来的旧文件名,并且定位到服务器下的images文件夹，将其删除
		params.add(userid);
		User user = db.findSingleObject(User.class, sql, params);
		if (user.getHead() != null && !"".equals(user.getHead())) {// 如果查出来不为空,则删除该文件
			String oldpath = findServerPath() + "lofter/images/"
					+ user.getHead();// 路径拼接完成
			System.out.println("我是测试" + oldpath);
			deleteFile(oldpath);
		}

		sql = "update tb_user set head=? where id=?";
		params.clear();
		params.add(headname);
		params.add(userid);
		return db.doUpdate(sql, params);
	}

	private String findServerPath() {
		String classPath = this.getClass().getClassLoader().getResource("/")
				.getPath();
		try {
			classPath = URLDecoder.decode(classPath, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] strPath = classPath.split("/");
		String path = "";
		for (int i = 0; i < strPath.length; i++) {
			if (i > 0 && i <= 3) {
				path = path + strPath[i] + "/";
			}
		}
		return path;
	}

	private boolean deleteFile(String sPath) {
		Boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	// 展示粉丝
	public List<User> findFansByUserid(String focuseduserid)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			IOException, NamingException {
		List<User> userlist = null;
		String sql = "select tb_user.* from tb_focus_rel left join tb_user on tb_focus_rel.focususerid=tb_user.id where focuseduserid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(focuseduserid);
		userlist = db.findMutiObject(User.class, sql, params);
		return userlist;
	}

	// 当前用户所关注的人
	public List<User> findFocusByUserid(String focususerid)
			throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			IOException, NamingException {
		List<User> userlist = null;
		String sql = "select tb_user.* from tb_focus_rel left join tb_user on tb_focus_rel.focuseduserid=tb_user.id where focususerid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(focususerid);
		userlist = db.findMutiObject(User.class, sql, params);
		return userlist;
	}

	// 添加/取消 关注
	public int doFocus(String focuseduserid, String userid)
			throws SQLException, IOException, NamingException {
		String sql = "select count(*) from tb_focus_rel where focususerid=? and focuseduserid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(focuseduserid);
		int r = (int) db.findDouble(sql, params);
		if (r > 0) {
			sql = "delete from tb_focus_rel where focususerid=? and focuseduserid=?";
			db.doUpdate(sql, params);
			r = -1;
		} else {
			sql = "insert into tb_focus_rel(focususerid,focuseduserid,focustime) values(?,?,sysdate)";
			r = db.doUpdate(sql, params);
		}

		return r;
	}

	// 移除粉丝
	public int removeFocus(String userid, String focuseduserid)
			throws SQLException, IOException, NamingException {
		String sql = "delete from tb_focus_rel where focususerid=? and focuseduserid=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(focuseduserid);
		int r = db.doUpdate(sql, params);
		return r;
	}

	public User update(User user) throws SQLException, IOException,
			NamingException {
		String sql = "update tb_user set nickname=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getNickname());
		params.add(user.getId());
		int result = db.doUpdate(sql, params);
		if (result > 0) {
			return user;
		} else {
			return null;
		}
	}

	public int delete(String id) throws SQLException, IOException,
			NamingException {
		String sql = "delete from tb_user where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		int result = db.doUpdate(sql, params);
		return result;
	}

	public int findUserCount() throws SQLException, IOException,
			NamingException {
		String sql = "select count(id) from tb_user";
		double count = db.findDouble(sql, null);
		return (int) count;
	}

	public List<User> findByPage(int page, int rows)
			throws IllegalArgumentException, SQLException, IOException,
			NamingException, InstantiationException, IllegalAccessException,
			InvocationTargetException {
		List<User> list = null;
		System.out.println("进入biz");
		int max = page * rows;
		int min = (page - 1) * rows + 1;
		String sql = "select * from (select a.*, rownum as rn from(	select * from tb_user) a where rownum<=?) where rn>=?";
		List<Object> params = new ArrayList<Object>();
		params.add(max);
		params.add(min);
		list = db.findMutiObject(User.class, sql, params);
		return list;
	}

	public List<User> findAllUsers() throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, IOException,
			NamingException {
		List<User> allusers = null;
		String sql = "select * from tb_user and status=1";
		allusers = db.findMutiObject(User.class, sql, null);
		return allusers;
	}

	// 修改登录状态为1
	public int modifyIslogined(String msg) throws SQLException, IOException,
			NamingException {
		String msgs[] = msg.split(":");
		int r=0;
		if ("login".equals(msgs[1])) {
			String email=msgs[0];
			String sql = "update tb_user set islogined=1 where email=?";
			List<Object> params = new ArrayList<Object>();
			params.add(email);
			r = db.doUpdate(sql, params);
		}else if( "exit".equals(msgs[1]) ){
			String userid=msgs[0];
			String sql = "update tb_user set islogined=0 where id=?";
			List<Object> params = new ArrayList<Object>();
			params.add(userid);
			r = db.doUpdate(sql, params);
		}
		return r;
	}

}
