package com.xiaoxiao.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * 配置一个统一的密码加密器，保持加密解密的一致
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderBean(){
        return NoOpPasswordEncoder.getInstance();
    }


}
