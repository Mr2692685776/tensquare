package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author newHeart
 * @Create 2020/3/5 0:03
 */
@Component
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @RabbitListener(queues = "sms")
    public void receviceEmial(Map<String, String> map){
        String mobile = map.get("mobile");
        String checkCode = map.get("checkCode");
        System.out.println("receive message is:"+checkCode);
        try {
            smsUtil.sendSms(mobile,"SMS_158440564", "666", "{\"code\": \"" + checkCode + "\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
