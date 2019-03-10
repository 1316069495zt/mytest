package com.network.company.controller;

import com.network.common.entity.PageResult;
import com.network.common.entity.Result;
import com.network.common.entity.ResultCode;
import com.network.company.service.CompanyService;
import com.network.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    /**
     * 添加企业
     * @param company
     * @return
     */
    @PostMapping("")
    public Result add(@RequestBody Company company){
        companyService.add(company);
        return new Result(ResultCode.SUCCESS);

    }

    /**
     * 修改企业用户
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@PathVariable String id ,@RequestBody Company company){

        company.setId(id);
        companyService.modify(company);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id删除企业
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id){
            companyService.removeById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询全部企业
     * @return
     */
    @GetMapping("")
    public Result query(){
        List<Company> query = companyService.query();
        return new Result(ResultCode.SUCCESS, query);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable String id){
        Company company = companyService.queryById(id);
        return new Result(ResultCode.SUCCESS,company);
    }
}
