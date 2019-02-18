package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping()
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findOne(@PathVariable String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @PutMapping("/{spitId}")
    public Result updata(@PathVariable String spitId, @RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.updata(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.delete(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @PostMapping()
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"增加成功");
    }
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid,@PathVariable int page, @PathVariable int size){
        Page<Spit> spitPage = spitService.findByPId(parentid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));

    }
    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        String userid="111";
       if (redisTemplate.opsForValue().get("thumbup"+userid) != null){
           return new Result(false, StatusCode.REPERROR,"点赞失败");
       }
        spitService.thumbup(spitId);
       redisTemplate.opsForValue().set("thumbup"+userid,1);
        return new Result(true, StatusCode.OK,"点赞成功");


    }


}
