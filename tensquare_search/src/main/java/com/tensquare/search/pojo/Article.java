package com.tensquare.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @Author newHeart
 * @Create 2020/3/4 14:16
 */
@Data
@Document(indexName = "articleindex",refreshInterval="10s")
public class Article implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    private String state;   // 审核状态

}
