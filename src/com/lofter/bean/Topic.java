package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {

	private static final long serialVersionUID = 8414227066893895489L;

	private String id;
	private String userid; // --用户id
	private String title; // --标题
	private String content; // --内容描述
	private String posttime; // --发布时间 数据形式 2015-05-19
	private String picid; // --图片id
	private String topictype; // --话题类型

	private String day; // 通过对数据库的查询然后截取
	private String month;
	private int like = 0;
	private Integer replyCount; // 某个topic下的回复数
	private Integer praiseCount; // 某个topic下的点赞数
	private Integer praiseRecord; // 某个topic下的回复数
	private Integer collectRecord; // 某个topic下的点赞数
	
	private String picname;//某topic下的图片名字

	
	public String getPicname() {
		return picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}

	public Integer getPraiseRecord() {
		return praiseRecord;
	}

	public void setPraiseRecord(Integer praiseRecord) {
		this.praiseRecord = praiseRecord;
	}

	public Integer getCollectRecord() {
		return collectRecord;
	}

	public void setCollectRecord(Integer collectRecord) {
		this.collectRecord = collectRecord;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getDay() {
		day = posttime.substring(2, 4);
		return day;
	}

	public String getMonth() {
		month = posttime.substring(0, 1);
		return month;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPosttime() {
		return posttime;
	}

	public void setPosttime(String posttime) {
		this.posttime = posttime;
	}

	public String getPicid() {
		return picid;
	}

	public void setPicid(String picid) {
		this.picid = picid;
	}

	public String getTopictype() {
		return topictype;
	}

	public void setTopictype(String topictype) {
		this.topictype = topictype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
