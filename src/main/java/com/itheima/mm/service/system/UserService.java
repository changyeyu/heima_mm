package com.itheima.mm.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Module;
import com.itheima.mm.domain.system.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int save(User user);
    
    int update(User user);
    
    int delete(String id);
    
    List<User> findAll();
    
    User findById(String id);
    
    PageInfo findAll(int page, int size);
    
    void updateRole(String userId, String roleId);
    
    User login(String email, String password);
    
    List<Module> findModulesById(String id);
}
