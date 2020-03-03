package com.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @Author newHeart
 * @Create 2020/3/2 16:31
 */
@SpringBootApplication
@EnableCaching
public class GatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatherApplication.class,args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(4,4);
    }
}
