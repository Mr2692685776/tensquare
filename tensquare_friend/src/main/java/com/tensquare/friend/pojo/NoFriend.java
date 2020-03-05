package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)  // 表示是联合主键
public class NoFriend implements Serializable {
    @Id
    @Column(name ="userid")
    private String userId;

    @Id
    @Column(name ="friendid")
    private String friendId;
}
