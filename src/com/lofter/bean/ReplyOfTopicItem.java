package com.lofter.bean;

import java.io.Serializable;

public class ReplyOfTopicItem<T> implements Serializable {

	private static final long serialVersionUID = -6977691424037846300L;
	private T t;
	private int count;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
