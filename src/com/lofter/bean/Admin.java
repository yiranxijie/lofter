package com.lofter.bean;

import java.io.Serializable;

public class Admin implements Serializable  {

	private static final long serialVersionUID = 1671882672170285489L;
	
	private String id;
	private String adminname ;
 	private String adminpwd;
 	private String status;
 	
 	
 	
 	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpwd() {
		return adminpwd;
	}
	public void setAdminpwd(String adminpwd) {
		this.adminpwd = adminpwd;
	}
 	
}
