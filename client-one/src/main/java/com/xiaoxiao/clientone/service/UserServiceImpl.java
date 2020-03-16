package com.xiaoxiao.clientone.service;

import com.xiaoxiao.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getUserById(int id) {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        return user;
    }
}
