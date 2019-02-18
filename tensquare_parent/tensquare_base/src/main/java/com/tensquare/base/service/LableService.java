package com.tensquare.base.service;

import com.tensquare.base.dao.LableDao;
import com.tensquare.base.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Lable> findBySearch(Lable lable) {
        return lableDao.findAll(new Specification<Lable>() {
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname()) ) {
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(labelname);
                }

                if (lable.getState() != null && !"".equals(lable.getState()) ) {
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), lable.getState());
                    list.add(state);
                }
                Predicate[] pred = new Predicate[list.size()];
                list.toArray(pred);
                return criteriaBuilder.and(pred);
            }
        });
    }

    public Page<Lable> pageQuery(Lable lable, int page, int size) {
        Pageable pageable = new PageRequest(page-1,size);
        return lableDao.findAll(new Specification<Lable>() {
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (lable.getLabelname() != null && !"".equals(lable.getLabelname()) ) {
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + lable.getLabelname() + "%");
                    list.add(labelname);
                }

                if (lable.getState() != null && !"".equals(lable.getState()) ) {
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), lable.getState());
                    list.add(state);
                }
                Predicate[] pred = new Predicate[list.size()];
                list.toArray(pred);
                return criteriaBuilder.and(pred);
            }
        },pageable);
    }
}
