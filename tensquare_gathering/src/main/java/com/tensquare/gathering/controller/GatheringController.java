package com.tensquare.gathering.controller;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author newHeart
 * @Create 2020/3/2 16:39
 */
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;


    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id){
        return new Result(StatusCode.OK,true,"操作成功",gatheringService.findById(id));
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") String id, @RequestBody Gathering gathering){
        gathering.setId(id);
        gatheringService.update(gathering);
        return new Result(StatusCode.OK,true,"操作成功");
    }


    @DeleteMapping("/{id}")
    public Result delById(@PathVariable("id") String id){
        gatheringService.deleteById(id);
        return new Result(StatusCode.OK,true,"操作成功");
    }
}
