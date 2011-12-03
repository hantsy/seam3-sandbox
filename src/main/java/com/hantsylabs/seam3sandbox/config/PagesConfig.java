package com.hantsylabs.seam3sandbox.config;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.rewrite.UrlMapping;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

@ViewConfig
public interface PagesConfig {

	static enum Pages {

		@FacesRedirect
		@UrlMapping(pattern = "/")
		@ViewPattern("/posts.xhtml")
		HOME,

		@FacesRedirect
		@UrlMapping(pattern = "/posts")
		@ViewPattern("/posts.xhtml")
		POSTS,

		@FacesRedirect
		@UrlMapping(pattern = "/post/edit/#{id}")
		@ViewPattern("/post-form.xhtml")
		POST_EDIT,
		
//		@FacesRedirect
//		@UrlMapping(pattern = "/post/new")
//		@ViewPattern("/post-form.xhtml")
//		POST_NEW,
		
//		@FacesRedirect
//		@UrlMapping(pattern = "/tags")
//		@ViewPattern("/tags.xhtml")
//		TAGS,
//
//		@FacesRedirect
//		@UrlMapping(pattern = "/tag/edit/#{id}")
//		@ViewPattern("/tag-form.xhtml")
//		TAG_EDIT,
//		
//		@FacesRedirect
//		@UrlMapping(pattern = "/tag/new")
//		@ViewPattern("/tag-form.xhtml")
//		TAG_NEW,


		@FacesRedirect
		@ViewPattern("/*")
		@AccessDeniedView("/denied.xhtml")
		@LoginView("/login.xhtml")
		//@LoggedIn
		ALL;

	}

}
