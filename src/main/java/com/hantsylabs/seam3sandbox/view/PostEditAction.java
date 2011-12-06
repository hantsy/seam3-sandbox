package com.hantsylabs.seam3sandbox.view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.hantsylabs.seam3sandbox.domain.Post;
import com.hantsylabs.seam3sandbox.domain.Tag;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.seam.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@Named
@ConversationScoped
public class PostEditAction {
	private static final Logger log = LoggerFactory
			.getLogger(PostEditAction.class);

	@Inject
	Conversation conversation;

	@Inject
	EntityManager em;

	@Inject
	Messages messages;

	@Inject
	Event<Post> postSavedEvent;

	private Long postId;

	private Post currentPost;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

  // Short cut for the EL lookups
    @Produces
    @Named("currentPost")
    @ConversationScoped
	public Post getCurrentPost() {
		return currentPost;
	}

	public void setCurrentPost(Post currentPost) {
		this.currentPost = currentPost;
	}

	public void init() {
		if (log.isDebugEnabled()) {
			log.debug("call init...id@" + this.postId);
		}

		if (this.postId == null) {
			this.currentPost = new Post();
		} else {
			this.currentPost = em.find(Post.class, this.postId);
		}

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void save() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentPost.getId() == null) {
			em.persist(this.currentPost);
		} else {
			this.currentPost = em.merge(this.currentPost);
		}

    correctlyAddManyToMany();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	@Transactional
	public void save2() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentPost.getId() == null) {
			em.persist(this.currentPost);
		} else {
			this.currentPost = em.merge(this.currentPost);
		}

    correctlyAddManyToMany();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}



	public void save3() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentPost.getId() == null) {
			em.persist(this.currentPost);
		} else {
			this.currentPost = em.merge(this.currentPost);
		}

    correctlyAddManyToMany();

		em.flush();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	@Transactional
	public void save4() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}

		if (this.currentPost.getId() == null) {
			em.persist(this.currentPost);
		} else {
			this.currentPost = em.merge(this.currentPost);
		}

    correctlyAddManyToMany();

		em.flush();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

  private void correctlyAddManyToMany() {
    for (Tag t : this.currentPost.getTags()) {
        if (t.getPosts() == null) {
            t.setPosts(Arrays.asList(this.currentPost));
        } else {
            t.getPosts().add(this.currentPost);
        }
    }
  }

	public void cancel() {
		if (log.isDebugEnabled()) {
			log.debug("call cancel ");
		}
		em.clear();

		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void onSaved(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) Post post) {
		messages.info(new BundleKey("messages", "post.saved_successfully"))
				.defaults("Post saved successfully").build();
	}

}
