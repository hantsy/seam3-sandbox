package com.hantsylabs.seam3sandbox.view;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleKey;
import org.jboss.seam.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hantsylabs.seam3sandbox.domain.Tag;

@Stateful
@Named
@ConversationScoped
public class TagEditAction {
	private static final Logger log = LoggerFactory
			.getLogger(TagEditAction.class);

	@Inject
	Conversation conversation;

	@Inject
	EntityManager em;

	@Inject
	Messages messages;

	@Inject
	FacesContext facesContext;

	@Inject
	Event<Tag> tagSavedEvent;

	private Long tagId;

	private Tag currentTag;

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Tag getCurrentTag() {
		return currentTag;
	}

	public void setCurrentTag(Tag currentTag) {
		this.currentTag = currentTag;
	}

	public void init() {
		if (log.isDebugEnabled()) {
			log.debug("call init...id@" + this.tagId);
		}

		if (this.tagId == null) {
			this.currentTag = new Tag();
		} else {
			this.currentTag = em.find(Tag.class, this.tagId);
		}

		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void save() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	@Transactional
	public void save2() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void save3() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		em.flush();

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	@Transactional
	public void save4() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}
		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		em.flush();

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void save5() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}

		if ("error".equals("error")) {
			messages.error("Show error message should prevent navigation.");
			return;
		}
		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		em.flush();

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void save6() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}

		if ("error".equals("error")) {
			throw new ValidatorException(new FacesMessage(
					"Exception threw in page directly"));
		}

		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		em.flush();

		tagSavedEvent.fire(this.currentTag);
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	public void save7() {
		if (log.isDebugEnabled()) {
			log.debug("call save ");
		}

		if ("error".equals("error")) {
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"addMessages  to faces contex", ""));
			return;
		}

		if (this.currentTag.getId() == null) {
			em.persist(this.currentTag);
		} else {
			this.currentTag = em.merge(this.currentTag);
		}

		em.flush();

		tagSavedEvent.fire(this.currentTag);
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

	public void onSaved(
			@Observes(during = TransactionPhase.AFTER_SUCCESS) Tag tag) {
		messages.info(new BundleKey("messages", "tag.saved_successfully"))
				.defaults("Tag saved successfully").build();
	}

}
