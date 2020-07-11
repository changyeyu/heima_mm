package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    
    int save(User user);
    
    int update(User user);
    
    int delete(String id);
    
    List<User> findAll();
    
    User findById(String id);
    
    void updateRole(@Param("userId") String userId, @Param("roleId") String roleId);
    
    User findByEmailAndPwd(@Param("email") String email,  @Param("password") String password);
}
