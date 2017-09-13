package org.thoughts.on.java.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "BlogPost")
@DiscriminatorValue("Blog")
public class BlogPost extends Publication {

	@Column
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
