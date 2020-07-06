package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.Dept;

import java.util.List;

public interface DeptDao {
    
    int save(Dept dept);
    
    int update(Dept dept);
    
    int delete(Integer id);
    
    List<Dept> findAll();
    
    List<Dept> findById(Integer id);
    
}
