package com.itheima.mm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.system.ModuleDao;
import com.itheima.mm.dao.system.UserDao;
import com.itheima.mm.domain.system.Module;
import com.itheima.mm.domain.system.User;
import com.itheima.mm.service.system.UserService;
import com.itheima.mm.util.DaoInstanceUtil;
import com.itheima.mm.util.MD5Util;

import java.lang.reflect.Modifier;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    
    private UserDao mapper = DaoInstanceUtil.getMapper(UserDao.class);
    
    @Override
    public int save(User user) {
        user.setId(UUID.randomUUID().toString());
        String password = user.getPassword();
        String md5 = MD5Util.md5(password);
        user.setPassword(md5);
        return mapper.save(user);
    }
    
    @Override
    public int update(User user) {
        return mapper.update(user);
    }
    
    @Override
    public int delete(String id) {
        return mapper.delete(id);
    }
    
    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public User findById(String id) {
        return mapper.findById(id);
    }
    
    @Override()
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<User> list = mapper.findAll();
        return new PageInfo(list);
    }
    
    @Override
    public void updateRole(String userId,String roleId) {
        mapper.updateRole(userId, roleId);
    }
    
    @Override
    public User login(String email, String password) {
        String pwd = MD5Util.md5(password);
        return mapper.findByEmailAndPwd(email, pwd);
    }
    
    @Override
    public List<Module> findModulesById(String id) {
        ModuleDao moduleDao = DaoInstanceUtil.getMapper(ModuleDao.class);
        return moduleDao.findModulesByUserId(id);
    }
}
