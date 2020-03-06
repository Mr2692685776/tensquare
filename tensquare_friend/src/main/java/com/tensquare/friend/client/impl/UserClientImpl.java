package com.tensquare.friend.client.impl;

import com.tensquare.friend.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @Author newHeart
 * @Create 2020/3/6 12:58
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public void updateFansAndFollower(String userId, String friendId, int num) {
        System.out.println("lalallalalal");
    }
}
