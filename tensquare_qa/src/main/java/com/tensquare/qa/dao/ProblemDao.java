package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
    //    最新问答列表
    @Query(value = "select * from tb_pl pl,tb_problem pb where pl.problemid = pb.id and pl.labelid = ? ORDER BY replytime DESC" ,nativeQuery = true)
    Page<Problem> newList(String labelid,Pageable pageable);

    //    最热问答列表
    @Query(value = "select * from tb_pl pl,tb_problem pb where pl.problemid = pb.id and pl.labelid = ? ORDER BY reply DESC" ,nativeQuery = true)
    Page<Problem> hotList(String labelid,Pageable pageable);


    //    等待回答列表
    @Query(value = "select * from tb_pl pl,tb_problem pb where pl.problemid = pb.id and pl.labelid = ? and reply = 0 ORDER BY createtime DESC" ,nativeQuery = true)
    Page<Problem> waitList(String labelid,Pageable pageable);



}
