package com.mouensis.server.authorization.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.key.CryptoKeySource;
import org.springframework.security.crypto.key.StaticKeyGeneratingCryptoKeySource;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * 安全配置
 *
 * @author zhuyuan
 * @date 2020/12/13 22:43
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        return new DaoRegisteredClientRepository();
    }

    @Bean
    public CryptoKeySource cryptoKeySource() {
        return new StaticKeyGeneratingCryptoKeySource();
    }

    @Bean
    public OAuth2AuthorizationService auth2AuthorizationService() {
        return new DaoOAuth2AuthorizationService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(Customizer -> Customizer
                        .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/**")
                        .permitAll())
                .authorizeRequests(customizer -> customizer
                        .antMatchers("/**")
                        .authenticated())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new OAuth2AuthorizationServerConfigurer<>());
    }
}
