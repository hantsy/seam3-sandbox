package com.hantsylabs.seam3sandbox.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Tag extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2084952453451946917L;

	@Column
	private String name;

	@ManyToMany
	Set<Post> posts = new HashSet<Post>();

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tag [name=" + name + "]";
	}
}
