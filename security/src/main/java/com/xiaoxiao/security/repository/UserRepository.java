package com.xiaoxiao.security.repository;

import com.xiaoxiao.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);
}
