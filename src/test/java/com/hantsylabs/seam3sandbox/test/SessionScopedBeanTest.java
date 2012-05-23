package com.hantsylabs.seam3sandbox.test;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.hantsylabs.seam3sandbox.sessionbean.PojoBean;
import com.hantsylabs.seam3sandbox.sessionbean.StatefulBean;

@RunWith(Arquillian.class)
public class SessionScopedBeanTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClass(StatefulBean.class).addClass(PojoBean.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	PojoBean pojo;

	
	@Test
	public void testGreet() {
		pojo.greeting();
	}

}
