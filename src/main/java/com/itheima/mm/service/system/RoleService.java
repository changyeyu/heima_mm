package com.itheima.mm.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    
    void save(Role role);
    
    void update(Role role);
    
    void delete(String id);
    
    List<Role> findAll();
    
    Role findById(String id);
 
    PageInfo findAll(int page, int size);
    
    List<Role> findAllRoleByUserId(String userId);
    
    void deleteRoleModule(String roleId);
    
    void deleteRoleUSer(String userId);
    
    void saveRoleModule(String roleId, String moduleId);
    
}
