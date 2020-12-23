/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mouensis.server.authorization.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.Version;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationProvider;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An {@link Authentication} implementation used for the OAuth 2.0 Client Credentials Grant.
 *
 * @author Alexey Nesterov
 * @see AbstractAuthenticationToken
 * @see OAuth2ClientCredentialsAuthenticationProvider
 * @see OAuth2ClientAuthenticationToken
 * @since 0.0.1
 */
public class OAuth2PasswordAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = Version.SERIAL_VERSION_UID;
    private final Authentication clientPrincipal;
    private final Set<String> scopes;
    private final String username;

    /**
     * Constructs an {@code OAuth2PasswordAuthenticationToken} using the provided parameters.
     *
     * @param clientPrincipal the authenticated client principal
     * @param username
     */
    public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, String username) {
        this(clientPrincipal, username, Collections.emptySet());
    }

    /**
     * Constructs an {@code OAuth2PasswordAuthenticationToken} using the provided parameters.
     *
     * @param clientPrincipal the authenticated client principal
     * @param scopes          the requested scope(s)
     */
    public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, String username, Set<String> scopes) {
        super(Collections.emptyList());
        Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
        Assert.notNull(scopes, "scopes cannot be null");
        this.clientPrincipal = clientPrincipal;
        this.username = username;
        this.scopes = Collections.unmodifiableSet(new LinkedHashSet<>(scopes));
    }

    @Override
    public Object getPrincipal() {
        return this.clientPrincipal;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return "";
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    /**
     * Returns the requested scope(s).
     *
     * @return the requested scope(s), or an empty {@code Set} if not available
     */
    public Set<String> getScopes() {
        return this.scopes;
    }
}
