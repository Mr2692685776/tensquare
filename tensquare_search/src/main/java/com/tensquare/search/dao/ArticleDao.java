package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author newHeart
 * @Create 2020/3/4 15:14
 */
@Repository
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleLikeOrContentLike(String title ,String content,Pageable pageable);
}
