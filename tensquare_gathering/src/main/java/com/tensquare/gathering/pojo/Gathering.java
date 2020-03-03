package com.tensquare.gathering.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author newHeart
 * @Create 2020/3/2 16:32
 */
@Entity
@Table(name = "tb_gathering")
@Data
public class Gathering implements Serializable {

    @Id
    private String id;

    //活动名称
    private String name;
    private String summary;//大会简介
    private String detail;//详细说明
    private String sponsor;//主办方
    private String image;//活动图片
    private Date starttime;//开始时间
    private Date endtime;//截止时间
    private String address;//举办地点
    private String enrolltime;//报名截止
    private String state;//	是否可见
    private String city;//城市

}
