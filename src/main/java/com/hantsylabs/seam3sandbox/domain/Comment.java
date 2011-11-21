package com.hantsylabs.seam3sandbox.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Comment extends BaseEntity implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String content;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn=new Date();

	@ManyToOne
	@JoinColumn(name = "post_id")
	Post post;



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



	@Override
	public String toString() {
		return "Post [ content=" + content + ", createdOn="
				+ createdOn + "]";
	}
}
