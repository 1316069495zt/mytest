package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LableService {

    @Autowired
    private LableDao lableDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 新增
     */
    public void save(Lable lable){
        lable.setId(idWorker.nextId()+"");
        lableDao.save(lable);
    }

    public List<Lable> findAll(){

        return lableDao.findAll();
    }

    public Lable findById(String id){

        return lableDao.findById(id).get();
    }

    public void updata(Lable lable){
        lableDao.save(lable);
    }

    public void delete(String id){

        lableDao.deleteById(id);
    }
}
