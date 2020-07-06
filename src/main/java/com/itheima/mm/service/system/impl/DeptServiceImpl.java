package com.itheima.mm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.system.DeptDao;
import com.itheima.mm.domain.system.Dept;
import com.itheima.mm.service.system.DeptService;
import com.itheima.mm.util.DaoInstanceUtil;
import org.apache.ibatis.annotations.Delete;

import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    
    private DeptDao mapper = DaoInstanceUtil.getMapper(DeptDao.class);
    
    @Override
    public int save(Dept dept) {
        dept.setId(UUID.randomUUID().toString());
        return mapper.save(dept);
    }
    
    @Override
    public int update(Dept dept) {
        return mapper.update(dept);
    }
    
    @Override
    public int delete(String id) {
        return mapper.delete(id);
    }
    
    @Override
    public List<Dept> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public Dept findById(String id) {
        return mapper.findById(id);
    }
    
    @Override()
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Dept> list = mapper.findAll();
        return new PageInfo(list);
    }
}
