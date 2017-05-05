package com.lofter.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public interface PicturesBiz {
	public void addPics(String picname) throws SQLException, IOException, NamingException;

}
