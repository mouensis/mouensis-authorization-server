package com.mouensis.server.authorization.security;

import com.mouensis.server.authorization.repository.RegisteredClientJpaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 注册客户端
 *
 * @author zhuyuan
 * @date 2020/12/19 20:10
 */
public class DaoRegisteredClientRepository implements org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository {
    private RegisteredClientJpaRepository registeredClientJpaRepository;

    @Autowired
    public void setRegisteredClientRepository(RegisteredClientJpaRepository registeredClientJpaRepository) {
        this.registeredClientJpaRepository = registeredClientJpaRepository;
    }

    /**
     * @param id the registration identifier
     * @return
     * @see #findByClientId(String)
     */
    @Override
    public RegisteredClient findById(String id) {
        return this.findByClientId(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return registeredClientJpaRepository.findById(clientId)
                .map(registeredClient -> RegisteredClient
                        .withId(registeredClient.getClientId())
                        .clientId(registeredClient.getClientId())
                        .clientSecret(registeredClient.getClientSecret())
                        .authorizationGrantTypes(authorizationGrantTypes -> {
                            if (StringUtils.isNotBlank(registeredClient.getGrantTypes())) {
                                authorizationGrantTypes.addAll(
                                        Arrays.asList(registeredClient.getGrantTypes().split(","))
                                                .stream()
                                                .map(t -> new AuthorizationGrantType(t))
                                                .collect(Collectors.toList())
                                );
                            }
                        })
                        .tokenSettings(tokenSettings -> {
                            tokenSettings.accessTokenTimeToLive(Duration.ofSeconds(registeredClient.getAccessTokenValidity()));
                            tokenSettings.refreshTokenTimeToLive(Duration.ofSeconds(registeredClient.getRefreshTokenValidity()));
                        })
                        .clientSettings(clientSettings -> {
                            clientSettings.requireUserConsent(registeredClient.getAutoApprove());
                            clientSettings.requireProofKey(registeredClient.getAutoApprove());
                        })
                        .scopes(scopes -> scopes.addAll(Arrays.asList(registeredClient.getScope().split(","))))
                        .redirectUri(registeredClient.getRedirectUri())
                        .build())
                .orElse(null);
    }

}
