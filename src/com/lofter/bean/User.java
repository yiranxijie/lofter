package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -1989259749641485708L;

	private String id;
	private String email; // --账户
	private String pwd; // --密码
	private String nickname; // --名称
	private String autograph; // --个人签名
	private String head; // --头像
	private Date regtime; // --注册时间

	private String token; // --账号激活码
	private String token_exptime; // --激活码有效期
	private Integer status; // --激活状态 ，0-未激活，1-已激活
	private Integer islogined; // --登录状态 ，0-未激活，1-已激活

	private String focusrecord; // 关注记录

	public User() {
		super();
	}


	public User(String id, String email, String pwd, String nickname,
			String autograph, String head, Date regtime, String token,
			String token_exptime, Integer status, Integer islogined,
			String focusrecord) {
		super();
		this.id = id;
		this.email = email;
		this.pwd = pwd;
		this.nickname = nickname;
		this.autograph = autograph;
		this.head = head;
		this.regtime = regtime;
		this.token = token;
		this.token_exptime = token_exptime;
		this.status = status;
		this.islogined = islogined;
		this.focusrecord = focusrecord;
	}


	public Integer getIslogined() {
		return islogined;
	}


	public void setIslogined(Integer islogined) {
		this.islogined = islogined;
	}


	public String getFocusrecord() {
		return focusrecord;
	}

	public void setFocusrecord(String focusrecord) {
		this.focusrecord = focusrecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken_exptime() {
		return token_exptime;
	}

	public void setToken_exptime(String token_exptime) {
		this.token_exptime = token_exptime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pwd=" + pwd
				+ ", nickname=" + nickname + ", autograph=" + autograph
				+ ", head=" + head + ", regtime=" + regtime + ", token="
				+ token + ", token_exptime=" + token_exptime + ", status="
				+ status + ", islogined=" + islogined + ", focusrecord="
				+ focusrecord + "]";
	}

	

}
