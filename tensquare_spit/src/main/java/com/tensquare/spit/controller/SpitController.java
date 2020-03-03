package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author newHeart
 * @Create 2020/3/3 10:07
 */
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService service;

    @GetMapping()
    public Result findAll() {
        return new Result(StatusCode.OK, true, "查询成功", service.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result( StatusCode.OK,true, "查询成功", service.findById(spitId));
    }

    @PostMapping()
    public Result save(@RequestBody Spit spit) {
        service.save(spit);
        return new Result(StatusCode.OK,true,"保存成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        service.update(spit);
        return new Result(StatusCode.OK,true,"修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        service.deleteById(spitId);
        return new Result( StatusCode.OK, true,"删除成功");
    }
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable("parentid") String parentid,
                                 @PathVariable("page")int page,
                                 @PathVariable("size")int size){
        Page<Spit> spitPage = service.findByParentId(parentid, page, size);
        return new Result( StatusCode.OK,true, "查询成功",
                new PageResult<>(spitPage.getTotalElements(),spitPage.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId) {
        return  service.thumbup(spitId);
    }
}
