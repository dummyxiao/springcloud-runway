package com.xiaoxiao.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@RestController
public class UserController {

    @GetMapping("/getUser")
    public String getUser(){
        return "user";
    }
}
