package com.xiaoxiao.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@EntityScan("com.xiaoxiao.domain.**")
//@EnableResourceServer
@SpringBootApplication
public class SecurityApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class);
    }

}
