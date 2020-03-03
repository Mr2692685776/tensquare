package com.tensquare.gathering.dao;

import com.tensquare.gathering.pojo.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author newHeart
 * @Create 2020/3/2 16:38
 */
@Repository
public interface GatheringDao extends JpaRepository<Gathering,String> {
}
