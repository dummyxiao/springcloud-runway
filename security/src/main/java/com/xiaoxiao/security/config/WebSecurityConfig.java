package com.xiaoxiao.security.config;

import com.xiaoxiao.security.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private RedisTemplate<String,OAuth2Authentication> redisTemplate;




    /**
     * 设置用户来源和密码加密器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    /**
     * 密码模式必须要有AuthenticationManagerBean，使用默认的也可以
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * AuthorizationCode存储code到redis
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServicesBean(){

        return new RandomValueAuthorizationCodeServices() {
            @Override
            protected void store(String code, OAuth2Authentication authentication) {
                redisTemplate.boundValueOps(code).set(authentication, 10, TimeUnit.MINUTES);
            }

            @Override
            protected OAuth2Authentication remove(String code) {
                OAuth2Authentication authentication = redisTemplate.boundValueOps(code).get();
                redisTemplate.delete(code);
                return authentication;
            }
        };
    }

    /** 配置具体的url需要的权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll();
    }
}
