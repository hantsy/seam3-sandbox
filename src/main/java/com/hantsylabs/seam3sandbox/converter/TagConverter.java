package com.hantsylabs.seam3sandbox.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.seam.faces.conversion.Converter;

import com.hantsylabs.seam3sandbox.domain.Tag;

@FacesConverter("tagConverter")
@RequestScoped
public class TagConverter extends Converter<Tag> {

	@Inject
	EntityManager em;

	@Override
	public Tag toObject(UIComponent comp, String value) {
		if (value == null || "".equals(value.trim())) {
			return null;
		}
		return em.find(Tag.class, Long.valueOf(value));
	}

	@Override
	public String toString(UIComponent comp, Tag value) {
		if (value == null) {
			return "";
		}
		return value.getId().toString();
	}

}
