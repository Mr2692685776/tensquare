package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @Author newHeart
 * @Create 2020/3/3 10:06
 */
@Repository
public interface SpitDao extends MongoRepository<Spit,String> {
    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
