package com.challenge2.api.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {
    private final UserPrincipal principal;
    public UserPrincipalAuthenticationToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        setAuthenticated(true);
        this.principal = principal;

    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }
}
