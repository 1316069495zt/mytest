package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{

    User findByMobile(String mobile);

    @Modifying
    @Query(value = "UPDATE tb_user SET followcount = followcount+? where id =?",nativeQuery = true)
    void addFollowcount(int x , String id);

    @Modifying
    @Query(value = "UPDATE tb_user SET fanscount = fanscount+? where id =?",nativeQuery = true)
    void addFanscount(int x ,String friendid);
}
