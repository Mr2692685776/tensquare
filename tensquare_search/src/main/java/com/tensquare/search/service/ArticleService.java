package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utils.IdWorker;

/**
 * @Author newHeart
 * @Create 2020/3/4 15:20
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Article article) {
        article.setId(String.valueOf(idWorker.nextId()));
        articleDao.save(article);
    }

    public Page<Article> findByKeyWord(String keyWord, int currentPage, int pageSize) {
      return articleDao.findByTitleLikeOrContentLike(keyWord,keyWord, PageRequest.of(currentPage-1,pageSize));
    }
}
