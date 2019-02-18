package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping()
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @GetMapping("/{key}/{page}/{size}")
    public Result searchKey(@PathVariable String key ,@PathVariable int page ,@PathVariable int size){
       Page<Article> articles = articleService.searchKey(key,page,size);

        return new Result(true,StatusCode.OK,"搜索成功",new PageResult<Article>(articles.getTotalElements(),articles.getContent()));
    }
}
