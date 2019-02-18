package com.tensquare.base.controller;

import com.tensquare.base.pojo.Lable;
import com.tensquare.base.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LableController {
    @Autowired
    private LableService lableService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @PostMapping()
    public Result save(@RequestBody Lable lable){
        lableService.save(lable);
        return new Result(true, StatusCode.OK,"添加成功");

    }

    @GetMapping()
    public Result findAll (){
        List<Lable> all = lableService.findAll();
        String authorization = httpServletRequest.getHeader("Authorization");
        System.out.println(authorization);
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

    @PostMapping("/search")
    public Result findBySearch(@RequestBody Lable lable){
        List<Lable> lables = lableService.findBySearch(lable);
        return new Result(true,StatusCode.OK,"查询成功",lables);
    }


    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Lable lable ,@PathVariable int page ,@PathVariable int size){
        Page<Lable> page1 = lableService.pageQuery(lable,page,size);
        return new Result(true,StatusCode.OK,"查询成功", new PageResult<Lable>(page1.getTotalElements(),page1.getContent()));
    }
}
