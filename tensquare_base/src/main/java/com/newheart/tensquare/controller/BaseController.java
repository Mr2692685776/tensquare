package com.newheart.tensquare.controller;

import com.newheart.tensquare.pojo.Label;
import com.newheart.tensquare.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author newHeart
 * @Create 2020/3/1 9:11
 */
@RestController
@RequestMapping("/label")
public class BaseController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        int i = 1/0;
        return new Result(StatusCode.OK,true,"查询成功",labelService.findAll());
    }


    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId")String labelId){
        return new Result(StatusCode.OK,true,"查询成功",labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(StatusCode.OK,true,"添加成功");
    }


    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(StatusCode.OK,true,"修改成功");
    }

    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(StatusCode.OK,true,"删除成功");
    }



    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return new Result(StatusCode.OK,true, "查询成功", list);
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Label> labelPage = labelService.findPage(label, page, size);
        return new Result(StatusCode.OK,true, "查询成功",
                new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }
}
