package com.xiaoxiao.article.feign;

import com.xiaoxiao.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@FeignClient(value = "user")
public interface UserClient {

    @GetMapping("/getUserById")
    User getUserById(@RequestParam("id") int id);
}
