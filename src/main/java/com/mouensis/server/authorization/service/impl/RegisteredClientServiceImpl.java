package com.mouensis.server.authorization.service.impl;

import com.mouensis.server.authorization.repository.RegisteredClientJpaRepository;
import com.mouensis.server.authorization.service.RegisteredClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户端服务接口实现
 *
 * @author zhuyuan
 * @date 2020/12/9 15:02
 */
@Service
@Slf4j
public class RegisteredClientServiceImpl implements RegisteredClientService {

    private RegisteredClientJpaRepository registeredClientJpaRepository;

    @Autowired
    public void setRegisteredClientRepository(RegisteredClientJpaRepository registeredClientJpaRepository) {
        this.registeredClientJpaRepository = registeredClientJpaRepository;
    }

    @Override
    public RegisteredClientJpaRepository getJpaRepository() {
        return this.registeredClientJpaRepository;
    }
}
