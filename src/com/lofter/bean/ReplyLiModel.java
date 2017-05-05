package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class ReplyLiModel implements Serializable {

	private static final long serialVersionUID = 1637865536824974139L;
	private String userid;
	private String head;
	private String nickname;
	private String content;
	private Date replytime;
	private String topicid;

	public ReplyLiModel() {
		super();
	}

	public ReplyLiModel(String userid, String head, String nickname,
			String content, Date replytime, String topicid) {
		super();
		this.userid = userid;
		this.head = head;
		this.nickname = nickname;
		this.content = content;
		this.replytime = replytime;
		this.topicid=topicid;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReplyLiModel [userid=" + userid + ", head=" + head
				+ ", nickname=" + nickname + ", replytime=" + replytime + "]";
	}

}
