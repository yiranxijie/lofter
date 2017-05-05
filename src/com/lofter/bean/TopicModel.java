package com.lofter.bean;

import java.io.Serializable;
import java.util.List;

public class TopicModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8885743064859046457L;
	
	private String topicid;
	private String userid;
	private String title;
	private String content;
	private String picid;
	private String posttime;
	private String topictype;
	private String replycount;
	private String praisecount;
	private String email;
	private String pwd;
	private String nickname;
	private String autograph;
	private String head;
	private String regtime;
	private String status;
	
	private String praiserecord;
	private String collectrecord;
	
	
	public String getPraiserecord() {
		return praiserecord;
	}
	public void setPraiserecord(String praiserecord) {
		this.praiserecord = praiserecord;
	}
	public String getCollectrecord() {
		return collectrecord;
	}
	public void setCollectrecord(String collectrecord) {
		this.collectrecord = collectrecord;
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicid() {
		return picid;
	}
	public void setPicid(String picid) {
		this.picid = picid;
	}
	public String getPosttime() {
		return posttime;
	}
	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}
	public String getTopictype() {
		return topictype;
	}
	public void setTopictype(String topictype) {
		this.topictype = topictype;
	}
	public String getReplycount() {
		return replycount;
	}
	public void setReplycount(String replycount) {
		this.replycount = replycount;
	}
	public String getPraisecount() {
		return praisecount;
	}
	public void setPraisecount(String praisecount) {
		this.praisecount = praisecount;
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
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
