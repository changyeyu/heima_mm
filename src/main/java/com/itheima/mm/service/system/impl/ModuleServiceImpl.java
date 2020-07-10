package com.itheima.mm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.system.ModuleDao;
import com.itheima.mm.domain.system.Module;
import com.itheima.mm.service.system.ModuleService;
import com.itheima.mm.util.DaoInstanceUtil;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ModuleServiceImpl implements ModuleService {
    
    private ModuleDao mapper = DaoInstanceUtil.getMapper(ModuleDao.class);
    
    @Override
    public void save(Module module) {
        String id = UUID.randomUUID().toString();
        module.setId(id);
        mapper.save(module);
    }
    
    @Override
    public void update(Module module) {
        mapper.update(module);
    }
    
    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
    
    @Override
    public List<Module> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public Module findById(String id) {
        return mapper.findById(id);
    }
    
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Module> list = mapper.findAll();
        return new PageInfo(list);
    }
    
    @Override
    public List<Map> findAuthorDataByRoleId(String roleId) {
        return mapper.findAuthorDataByRoleId(roleId);
    }
}
