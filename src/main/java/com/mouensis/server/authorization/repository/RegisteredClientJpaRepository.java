package com.mouensis.server.authorization.repository;

import com.mouensis.server.authorization.entity.RegisteredClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 客户端
 *
 * @author zhuyuan
 * @date 2020/12/19 20:04
 */
public interface RegisteredClientJpaRepository extends JpaRepository<RegisteredClientEntity, String> {
}
