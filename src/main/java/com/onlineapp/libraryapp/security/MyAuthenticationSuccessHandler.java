package com.onlineapp.libraryapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public MyAuthenticationSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {

        handle(request, response, authentication);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> authoritiesList = new ArrayList<>();

        authorities.forEach(auth -> authoritiesList.add(auth.getAuthority()));

        if (authoritiesList.contains("developers:read") && authoritiesList.contains("developers:write") && authoritiesList.contains("developers:update") && authoritiesList.contains("developers:delete")) {
            return "/addElements";
        } else if (authoritiesList.contains("developers:read")) {
            return "/listsOfElements";
        }

        throw new IllegalStateException();
    }

}
