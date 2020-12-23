package com.mouensis.server.authorization.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * adaf
 *
 * @author zhuyuan
 * @date 2020/12/13 22:43
 */

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(components())
                .info(info());
    }

    private Components components() {
        return new Components()
                .addSecuritySchemes("security-bearer",
                        new SecurityScheme()
                                .description("Oauth2 认证Token")
                                .name("123")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }

    private Info info() {
        return new Info()
                .version("v1.0")
                .title("用户认证管理系统API")
                .description("用户统一认证中心，实现4A（Account，Authentication，Authorization，Audit）管理的API")
                .contact(new Contact().name("zhuyuan").email("zhuyuan2020.aliyun.com"))
                .license(new License().name("BSD 3-Clause").url("https://opensource.org/licenses/BSD-3-Clause"));
    }
}
