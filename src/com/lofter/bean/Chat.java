package com.lofter.bean;

import java.io.Serializable;

public class Chat implements Serializable {
	private static final long serialVersionUID = -7920566891748301619L;
	
	private String id;
	private String email;
	private String chattopic;
	private String category;
	
	public Chat() {
		super();
	}

	public Chat(String id, String email, String chattopic, String category) {
		super();
		this.id = id;
		this.email = email;
		this.chattopic = chattopic;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChattopic() {
		return chattopic;
	}

	public void setChattopic(String chattopic) {
		this.chattopic = chattopic;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", email=" + email + ", chattopic="
				+ chattopic + ", category=" + category + "]";
	}
	
	
}
