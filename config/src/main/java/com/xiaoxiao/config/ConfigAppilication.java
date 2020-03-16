package com.xiaoxiao.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigAppilication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigAppilication.class);
    }

}
