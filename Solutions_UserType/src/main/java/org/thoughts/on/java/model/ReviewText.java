package org.thoughts.on.java.model;

import java.io.Serializable;



public class ReviewText implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String text;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "ReviewText title=[" + title + "], text=[" + text + "]";
	}
	
	public static ReviewText fromString(String str) {
		ReviewText rt = new ReviewText();
		rt.setTitle(str.split("\\[")[1].split("\\]")[0]);
		rt.setText(str.split("\\[")[2].split("\\]")[0]);
		return rt;
	}
}
