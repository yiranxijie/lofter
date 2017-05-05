package com.lofter.bean;

import java.io.Serializable;

public class FocusProcedureBean implements Serializable {

	private static final long serialVersionUID = 6224228681298362397L;
	private String focususerid;
	private String focuseduserid;
	private String focusrecord;

	public FocusProcedureBean() {
		super();
	}

	public FocusProcedureBean(String focususerid, String focuseduserid,
			String focusrecord) {
		super();
		this.focususerid = focususerid;
		this.focuseduserid = focuseduserid;
		this.focusrecord = focusrecord;
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

	public String getFocusrecord() {
		return focusrecord;
	}

	public void setFocusrecord(String focusrecord) {
		this.focusrecord = focusrecord;
	}

}
