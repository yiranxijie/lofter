package com.lofter.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Result implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4251569511802854274L;
	/**
	* 表示图片是否已上传成功。
	*/
	public Boolean success;
	public String userid;
	public String username;
	/**
	* 自定义的附加消息。
	*/
	public String msg;
	/**
	* 表示原始图片的保存地址。
	*/
	public String sourceUrl;
	/**
	* 表示所有头像图片的保存地址，该变量为一个数组。
	*/
	public ArrayList<Object> avatarUrls;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		if(   username==null){
			return "";
		}else{
			return username;
		}
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSourceUrl() {
		if(  sourceUrl==null  ){
			return "";
		}else{
			return sourceUrl;
		}
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public ArrayList<Object> getAvatarUrls() {
		return avatarUrls;
	}
	public void setAvatarUrls(ArrayList<Object> avatarUrls) {
		this.avatarUrls = avatarUrls;
	}
	@Override
	public String toString() {
		return "Result [success=" + success + ", userid=" + userid
				+ ", username=" + username + ", msg=" + msg + ", sourceUrl="
				+ sourceUrl + ", avatarUrls=" + avatarUrls + "]";
	}
	
	
}
