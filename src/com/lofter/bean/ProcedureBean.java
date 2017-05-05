package com.lofter.bean;

import java.io.Serializable;

public class ProcedureBean implements Serializable {

	private static final long serialVersionUID = 809894604693791308L;
	private String userid;
	private String topicid;
	private String record;

	public ProcedureBean() {
		super();
	}

	public ProcedureBean(String userid, String topicid, String record) {
		super();
		this.userid = userid;
		this.topicid = topicid;
		this.record = record;
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

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "ProcedureBean [userid=" + userid + ", topicid=" + topicid
				+ ", record=" + record + "]";
	}

}
