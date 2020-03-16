package com.xiaoxiao.clientone.controller;

import com.xiaoxiao.clientone.service.UserService;
import com.xiaoxiao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById")
    public User getUserById(@RequestParam("id") int id){
        return userService.getUserById(id);
    }
}
