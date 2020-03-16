package com.xiaoxiao.article.controller;

import com.xiaoxiao.article.service.ArticleService;
import com.xiaoxiao.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobenneng@hotmail.com
 * @date 2020/3/14
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/addArticle")
    public int addArticle(){
        return articleService.addArticle(new Article());
    }

}
