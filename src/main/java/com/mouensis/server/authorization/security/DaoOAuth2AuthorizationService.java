package com.mouensis.server.authorization.security;

import com.mouensis.server.authorization.entity.AuthorizedTokenEntity;
import com.mouensis.server.authorization.repository.AuthorizedTokenRepository;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.key.StaticKeyGeneratingCryptoKeySource;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.jose.jws.NimbusJwsEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationAttributeNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2TokenIssuerUtil;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.OAuth2Tokens;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.function.Function;

/**
 * 收取服务自定义数据库实现
 *
 * @author zhuyuan
 * @date 2020/12/22 19:43
 */
public class DaoOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private AuthorizedTokenRepository authorizedTokenRepository;

    private OAuth2AuthorizedTokenEntityMapper auth2AuthorizedTokenEntityMapper;

    public DaoOAuth2AuthorizationService() {
        this.auth2AuthorizedTokenEntityMapper = new OAuth2AuthorizedTokenEntityMapper();
    }

    @Autowired
    public void setAuthorizedTokenRepository(AuthorizedTokenRepository authorizedTokenRepository) {
        this.authorizedTokenRepository = authorizedTokenRepository;
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        AuthorizedTokenEntity authorizedTokenEntity = this.auth2AuthorizedTokenEntityMapper.apply(authorization);
        this.authorizedTokenRepository.save(authorizedTokenEntity);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        Assert.notNull(authorization, "authorization cannot be null");
        AuthorizedTokenEntity authorizedTokenEntity = this.auth2AuthorizedTokenEntityMapper.apply(authorization);
        this.authorizedTokenRepository.delete(authorizedTokenEntity);
    }

    @Override
    public OAuth2Authorization findByToken(String token, TokenType tokenType) {
        Assert.notNull(token, "token cannot be null");
        Assert.notNull(tokenType, "tokenType cannot be null");
        if (TokenType.REFRESH_TOKEN.equals(tokenType)) {
            AuthorizedTokenEntity authorizedTokenEntity = authorizedTokenRepository.findByRefreshTokenValue(token);
            if (authorizedTokenEntity == null || authorizedTokenEntity.getAuthorizationObject() == null) {
                return null;
            }
            OAuth2Authorization authorization = SerializationUtils.deserialize(authorizedTokenEntity.getAuthorizationObject());
            return authorization;
        } else {
            AuthorizedTokenEntity authorizedTokenEntity = authorizedTokenRepository.findByAccessTokenValue(token);
            if (authorizedTokenEntity == null || authorizedTokenEntity.getAuthorizationObject() == null) {
                return null;
            }
            OAuth2Authorization authorization = SerializationUtils.deserialize(authorizedTokenEntity.getAuthorizationObject());
            return authorization;
        }
    }


    /**
     * The default {@code Function} that maps {@link OAuth2Authorization} to a
     * {@code List} of {@link AuthorizedTokenEntity}.
     */
    public static class OAuth2AuthorizedTokenEntityMapper
            implements Function<OAuth2Authorization, AuthorizedTokenEntity> {

        @Override
        public AuthorizedTokenEntity apply(OAuth2Authorization authorization) {
            AuthorizedTokenEntity authorizedTokenEntity = new AuthorizedTokenEntity();
            authorizedTokenEntity.setAuthorizedClientId(authorization.getRegisteredClientId());
            String principalName = authorization.getPrincipalName();
            if (!StringUtils.hasText(principalName)) {
                principalName = authorization.getRegisteredClientId();
            }
            authorizedTokenEntity.setPrincipalName(principalName);
            authorizedTokenEntity.setAccessTokenValue(authorization.getTokens().getAccessToken().getTokenValue());
            OAuth2RefreshToken refreshToken = authorization.getTokens().getRefreshToken();
            if (refreshToken != null) {
                authorizedTokenEntity.setRefreshTokenValue(refreshToken.getTokenValue());
            }
            authorizedTokenEntity.setAuthorizationObject(SerializationUtils.serialize(authorization));
            return authorizedTokenEntity;
        }

    }
}
