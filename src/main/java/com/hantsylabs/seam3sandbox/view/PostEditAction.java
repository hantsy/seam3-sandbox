package com.hantsylabs.seam3sandbox.view;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.faces.event.ValueChangeEvent;
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
public class PostEditAction implements Serializable {
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

    private Set<Tag> tags = new HashSet<Tag>();

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Post getCurrentPost() {
		return currentPost;
	}

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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
            this.tags = new HashSet<Tag>(this.currentPost.getTags());
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

		em.flush();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void save4() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentPost.getId() == null) {
			em.persist(this.currentPost);
		} else {
//			this.currentPost = em.merge(this.currentPost);
		}

		em.flush();

		postSavedEvent.fire(this.currentPost);
		if (!conversation.isTransient()) {
			conversation.end();
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

    public void changeTags(ValueChangeEvent vce) {
        for(Tag t : (Collection<Tag>) vce.getNewValue()) {
            this.currentPost.addTag(t);
        }

        this.tags = new HashSet<Tag>(this.currentPost.getTags());
    }

	public void onSaved(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) Post post) {
		messages.info(new BundleKey("messages", "post.saved_successfully"))
				.defaults("Post saved successfully").build();
	}

}
