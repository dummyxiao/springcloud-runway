package com.xiaoxiao.article.service;

import com.xiaoxiao.article.feign.UserClient;
import com.xiaoxiao.domain.Article;
import com.xiaoxiao.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private UserClient userClient;

    @Override
    public int addArticle(Article article) {
        User userById = userClient.getUserById(0);
        System.out.println(userById);
        return 0;
    }
}
