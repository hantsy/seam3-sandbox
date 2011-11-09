package com.hantsylabs.seam3sandbox.view;

import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hantsylabs.seam3sandbox.domain.Tag;

@Stateful
@Model
public class TagHome {
	private static final Logger log = LoggerFactory.getLogger(TagHome.class);

	@Inject
	EntityManager em;

	private List<Tag> dataModel;

	public List<Tag> getDataModel() {
		if (log.isDebugEnabled()) {
			log.debug("call getDataModel...");
		}

		if (dataModel == null) {
			this.dataModel = em.createQuery(
					"from Tag p order by p.name desc", Tag.class).getResultList();
			if (log.isDebugEnabled()) {
				log.debug("tags size@" + this.dataModel.size());
			}
		}
		return this.dataModel;
	}
}
