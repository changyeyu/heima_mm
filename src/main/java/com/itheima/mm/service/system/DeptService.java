package com.itheima.mm.service.system;

import com.itheima.mm.domain.system.Dept;

import java.util.List;

public interface DeptService {
    
    int save(Dept dept);
    
    int update(Dept dept);
    
    int delete(Integer id);
    
    List<Dept> findAll();
    
    List<Dept> findById(Integer id);
    
}
