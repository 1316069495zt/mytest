package com.network.company.service;

import com.network.common.util.IdWorker;
import com.network.company.dao.CompanyDao;
import com.network.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 增加企业
     * @param company
     */
    public void add(Company company){

        String id = idWorker.nextId()+"";
        company.setId(id);
        company.setAuditState("0");
        company.setState(1);
        companyDao.save(company);


    }

    /**
     * 根据id删除用户
     * @param id
     */
    public void removeById(String id){

        companyDao.deleteById(id);
    }

    /**
     * 修改用户信息
     * @param company
     */
    public void modify(Company company){
        companyDao.save(company);

    }

    /**
     * 查询全部
     * @return
     */
    public List<Company> query(){

         return companyDao.findAll();
    }

    /**
     * 查询单个
     * @param id
     * @return
     */
    public Company queryById(String id){

        return companyDao.findById(id).get();
    }

}
