package com.newheart.tensquare.dao;

import com.newheart.tensquare.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author newHeart
 * @Create 2020/3/1 9:31
 */

@Repository
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
