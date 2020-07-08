package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.User;

import java.util.List;

public interface UserDao {
    
    int save(User user);
    
    int update(User user);
    
    int delete(String id);
    
    List<User> findAll();
    
    User findById(String id);
}
