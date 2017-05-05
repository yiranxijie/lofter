package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class Reply implements Serializable {

	private static final long serialVersionUID = 455783099255596369L;

	private String id;
	private String topicid; // --回复的话题id
	private String userid; // --回复人id
	private String content; // --回复内容
	private String parentid; // --父回复id
	private Date replytime; // --回复时间

	private String head;
	private String nickname;

	public Reply() {
		super();
	}

	public Reply(String id, String topicid, String userid, String content,
			String parentid, Date replytime, String head, String nickname) {
		super();
		this.id = id;
		this.topicid = topicid;
		this.userid = userid;
		this.content = content;
		this.parentid = parentid;
		this.replytime = replytime;
		this.head = head;
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Override
	public String toString() {
		return "Reply [id=" + id + ", topicid=" + topicid + ", userid="
				+ userid + ", content=" + content + ", parentid=" + parentid
				+ ", replytime=" + replytime + "]";
	}

}
