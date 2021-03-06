package com.itheima.mm.service.system;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Dept;

import java.util.List;

public interface DeptService {
    
    int save(Dept dept);
    
    int update(Dept dept);
    
    int delete(String id);
    
    List<Dept> findAll();
    
    Dept findById(String id);
    
    PageInfo findAll(int page, int size);
    
}
