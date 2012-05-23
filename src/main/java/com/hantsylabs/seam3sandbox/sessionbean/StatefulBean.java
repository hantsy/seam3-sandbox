package com.hantsylabs.seam3sandbox.sessionbean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@SessionScoped
@Named
public class StatefulBean {

	private static final Logger log = LoggerFactory
			.getLogger(StatefulBean.class);
	
	@PostConstruct
	public void init() {
		log.info("call PostConstruct");
	}
	
	public void sayHello(String name){
		log.info("call greeting");
		System.out.print(" Hello , "+name);
		
	}
}
