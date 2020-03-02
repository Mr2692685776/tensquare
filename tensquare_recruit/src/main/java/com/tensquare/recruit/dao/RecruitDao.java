package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
//	List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);
//
//    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);

    /**
     * 最新
     * @return
     */
    List<Recruit> findTop6ByStateIsNotNullOrderByCreatetimeDesc();


    /**
     * 最热
     */
    List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);
}
