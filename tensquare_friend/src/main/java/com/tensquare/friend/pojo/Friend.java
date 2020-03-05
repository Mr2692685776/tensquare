package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)  // 表示是联合主键
public class Friend implements Serializable {
    @Id
    @Column(name ="userid" )
    private String userId;

    @Id
    @Column(name ="friendid")
    private String friendId;

    private String islike;
}
