package com.network.test;

import com.network.company.CompanyApplication;
import com.network.company.dao.CompanyDao;
import com.network.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest(classes = CompanyApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class test1 {

    @Autowired
    private CompanyDao companyDao;
    @Test
    public void test(){

        List<Company> all = companyDao.findAll();
        System.out.println(all);

    }
}
