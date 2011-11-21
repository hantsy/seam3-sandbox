package com.hantsylabs.seam3sandbox.view;

import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hantsylabs.seam3sandbox.domain.Post;

@Stateful
@Named("commentHome")
@ViewScoped
public class CommentHome {
	private static final Logger log = LoggerFactory
			.getLogger(CommentHome.class);

	@Inject
	EntityManager em;

	private List<Post> dataModel;

	public List<Post> getDataModel() {
		if (log.isDebugEnabled()) {
			log.debug("call getDataModel...");
		}

		if (dataModel == null) {
			this.dataModel = em.createQuery(
					"from Post p order by p.createdOn desc", Post.class)
					.getResultList();
			if (log.isDebugEnabled()) {
				log.debug("posts size@" + this.dataModel.size());
			}
		}
		return this.dataModel;
	}

	private Post currentPost;

	public Post getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}


}
