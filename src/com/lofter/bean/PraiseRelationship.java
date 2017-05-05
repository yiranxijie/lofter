package com.lofter.bean;

import java.io.Serializable;

public class PraiseRelationship implements Serializable {

	private static final long serialVersionUID = -8438935120810004479L;

	private String id;
	private String userid; // --用户id
	private String topicid; // --话题id

	public PraiseRelationship() {
		super();
	}

	public PraiseRelationship(String id, String userid, String topicid) {
		super();
		this.id = id;
		this.userid = userid;
		this.topicid = topicid;
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

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PraiseRelationship [id=" + id + ", userid=" + userid
				+ ", topicid=" + topicid + "]";
	}

}
