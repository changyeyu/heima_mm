package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.Module;

import java.util.List;
import java.util.Map;

public interface ModuleDao {
    
    int save(Module module);

    int delete(String id);

    int update(Module module);

    Module findById(String id);

    List<Module> findAll();

    List<Map> findAuthorDataByRoleId(String roleId);
    
    List<Module> findModulesByUserId(String id);
}
