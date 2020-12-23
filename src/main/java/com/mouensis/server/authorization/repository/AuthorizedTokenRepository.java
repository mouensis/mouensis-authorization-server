package com.mouensis.server.authorization.repository;

import com.mouensis.server.authorization.entity.AuthorizedTokenEntity;
import com.mouensis.server.authorization.entity.pk.AuthorizedTokenEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 授权Token Repository
 *
 * @author zhuyuan
 * @date 2020/12/22 21:24
 */
public interface AuthorizedTokenRepository extends JpaRepository<AuthorizedTokenEntity, AuthorizedTokenEntityPk> {
    /**
     * 根据刷新Token查询授权信息
     *
     * @param refreshTokenValue
     * @return
     */
    AuthorizedTokenEntity findByRefreshTokenValue(String refreshTokenValue);

    /**
     * 根据访问Token查询授权信息
     *
     * @param accessTokenValue
     * @return
     */
    AuthorizedTokenEntity findByAccessTokenValue(String accessTokenValue);
}
