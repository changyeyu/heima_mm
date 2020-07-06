package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.Dept;

import java.util.List;

public interface DeptDao {
    
    int save(Dept dept);
    
    int update(Dept dept);
    
    int delete(String id);
    
    List<Dept> findAll();
    
    Dept findById(String id);
    
}
