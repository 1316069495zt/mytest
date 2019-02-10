package com.tensquare.base.controller;

import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LableController {
    @Autowired
    private LableService lableService;

    @PostMapping()
    public Result save(@RequestBody Lable lable){
        lableService.save(lable);
        return new Result(true, StatusCode.OK,"添加成功");

    }

    @GetMapping()
    public Result findAll (){
        List<Lable> all = lableService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",all);

    }

    @GetMapping("/{labelId}")
    public Result findById (@PathVariable String labelId){
        Lable byId = lableService.findById(labelId);
        return new Result(true,StatusCode.OK,"查询成功",byId);
    }

    @PutMapping("/{labelId}")
    public Result updata(@PathVariable String labelId ,@RequestBody Lable lable){
        lable.setId(labelId);
        lableService.updata(lable);
        return new Result(true, StatusCode.OK,"修改成功");

    }
    @DeleteMapping("/{labelId}")
    public Result delete(@PathVariable String labelId){
        lableService.delete(labelId);
        return new Result(true, StatusCode.OK,"删除成功");

    }

}
