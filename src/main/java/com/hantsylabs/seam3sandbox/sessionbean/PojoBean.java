package com.hantsylabs.seam3sandbox.sessionbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named
public class PojoBean implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(PojoBean.class);

	@Inject
	StatefulBean sbean;

	@PostConstruct
	public void init() {
		log.info("call PostConstruct");
	}

	public void greeting() {
		sbean.sayHello("Hantsy");
	}
}
