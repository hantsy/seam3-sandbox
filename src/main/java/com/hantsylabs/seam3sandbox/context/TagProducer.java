package com.hantsylabs.seam3sandbox.context;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.hantsylabs.seam3sandbox.domain.Tag;

@ApplicationScoped
public class TagProducer {

	@Inject EntityManager em;
	
	@Named("tags")
	@Produces
	public List<Tag> getTags(){
		return em.createQuery("from Tag t order by t.name desc", Tag.class).getResultList();
	}
}
