package com.hantsylabs.seam3sandbox.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post extends BaseEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String title;

	@Column
	private String content;



	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@ManyToMany(cascade = { CascadeType.REFRESH }, mappedBy = "posts")
	List<Tag> tags = new ArrayList<Tag>();


	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Post [title=" + title + ", content=" + content + ", createdOn="
				+ createdOn + "]";
	}
}
