package com.mouensis.server.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhuyuan
 */
@SpringBootApplication(scanBasePackages = "com.mouensis")
public class MouensisAuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MouensisAuthorizationServerApplication.class, args);
    }

}
