package com.lofter.bean;

import java.io.Serializable;
import java.util.Date;

public class FocusAndFocused implements Serializable {

	private static final long serialVersionUID = -8577753838832342148L;

	private String id;
	private String focususerid; // --关注方id
	private String focuseduserid; // --被关注方id
	private Date focustime; // --关注时间

	public FocusAndFocused() {
		super();
	}

	public FocusAndFocused(String id, String focususerid, String focuseduserid,
			Date focustime) {
		super();
		this.id = id;
		this.focususerid = focususerid;
		this.focuseduserid = focuseduserid;
		this.focustime = focustime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFocususerid() {
		return focususerid;
	}

	public void setFocususerid(String focususerid) {
		this.focususerid = focususerid;
	}

	public String getFocuseduserid() {
		return focuseduserid;
	}

	public void setFocuseduserid(String focuseduserid) {
		this.focuseduserid = focuseduserid;
	}

	public Date getFocustime() {
		return focustime;
	}

	public void setFocustime(Date focustime) {
		this.focustime = focustime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FocusAndFocused [id=" + id + ", focususerid=" + focususerid
				+ ", focuseduserid=" + focuseduserid + ", focustime="
				+ focustime + "]";
	}

}
