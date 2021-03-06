package com.xiaoxiao.clientone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ClientoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientoneApplication.class);
    }
}
