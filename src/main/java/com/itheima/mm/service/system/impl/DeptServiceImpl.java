package com.itheima.mm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.system.DeptDao;
import com.itheima.mm.domain.system.Dept;
import com.itheima.mm.service.system.DeptService;
import com.itheima.mm.util.DaoInstanceUtil;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    
    private DeptDao mapper = DaoInstanceUtil.getMapper(DeptDao.class);
    
    @Override
    public int save(Dept dept) {
        return mapper.save(dept);
    }
    
    @Override
    public int update(Dept dept) {
        return mapper.update(dept);
    }
    
    @Override
    public int delete(Integer id) {
        return mapper.delete(id);
    }
    
    @Override
    public List<Dept> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public List<Dept> findById(Integer id) {
        return mapper.findById(id);
    }
    
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Dept> list = mapper.findAll();
        return new PageInfo(list);
    }
}
