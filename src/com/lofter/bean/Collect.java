package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class Collect implements Serializable {

	private static final long serialVersionUID = 5721744472644992464L;

	private String id;
	private String topicid; // --收藏的话题id
	private Date collecttime; // --收藏时间

	public Collect() {
		super();
	}

	public Collect(String id, String topicid, Date collecttime) {
		super();
		this.id = id;
		this.topicid = topicid;
		this.collecttime = collecttime;
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

	public Date getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(Date collecttime) {
		this.collecttime = collecttime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Collect [id=" + id + ", topicid=" + topicid + ", collecttime="
				+ collecttime + "]";
	}

}
