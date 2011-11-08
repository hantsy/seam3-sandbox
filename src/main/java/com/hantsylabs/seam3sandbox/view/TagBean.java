package com.hantsylabs.seam3sandbox.view;

import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.hantsylabs.seam3sandbox.domain.Tag;
import org.jboss.seam.forge.persistence.PersistenceUtil;
import org.jboss.seam.transaction.Transactional;

@Transactional
@Named
@Stateful
@RequestScoped
public class TagBean extends PersistenceUtil {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1400571900271385797L;
	private List<Tag> list = null;
	private Tag tag = new Tag();
	private long id = 0;

	public void load() {
		tag = findById(Tag.class, id);
	}

	public String create() {
		create(tag);
		return "view?faces-redirect=true&id=" + tag.getId();
	}

	public String delete() {
		delete(tag);
		return "list?faces-redirect=true";
	}

	public String save() {
		save(tag);
		return "view?faces-redirect=true&id=" + tag.getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
		load();
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public List<Tag> getList() {
		if (list == null) {
			list = findAll(Tag.class);
		}
		return list;
	}

	public void setList(List<Tag> list) {
		this.list = list;
	}
}
