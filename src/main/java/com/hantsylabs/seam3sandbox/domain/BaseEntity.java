package com.hantsylabs.seam3sandbox.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4276098173862779306L;

	@Id
	private @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;

	@Version
	private @Column(name = "version")
	Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
}
