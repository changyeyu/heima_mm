package com.itheima.mm.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Module;

import java.util.List;
import java.util.Map;

public interface ModuleService {
    
    void save(Module module);
    
    void update(Module module);
    
    void delete(String id);
    
    List<Module> findAll();
    
    Module findById(String id);
 
    PageInfo findAll(int page, int size);
    
    List<Map> findAuthorDataByRoleId(String roleId);
}
