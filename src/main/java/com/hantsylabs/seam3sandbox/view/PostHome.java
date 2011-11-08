package com.hantsylabs.seam3sandbox.view;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hantsylabs.seam3sandbox.domain.Post;

@Stateful
@Model
public class PostHome {
	private static final Logger log = LoggerFactory.getLogger(PostHome.class);

	@Inject
	EntityManager em;

	private List<Post> dataModel;

	public List<Post> getDataModel() {
		if (log.isDebugEnabled()) {
			log.debug("call getDataModel...");
		}

		if (dataModel == null) {
			this.dataModel = em.createQuery(
					"from Post p order by p.createdOn desc", Post.class).getResultList();
			if (log.isDebugEnabled()) {
				log.debug("posts size@" + this.dataModel.size());
			}
		}
		return this.dataModel;
	}
}
