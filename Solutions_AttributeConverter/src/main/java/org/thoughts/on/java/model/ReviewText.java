package org.thoughts.on.java.model;



public class ReviewText {

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
