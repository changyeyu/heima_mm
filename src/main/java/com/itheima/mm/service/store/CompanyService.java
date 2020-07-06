package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Company;

import java.util.List;

public interface CompanyService {
    
    void save(Company company);
    
    void update(Company company);
    
    void delete(String id);
    
    List<Company> findAll();
    
    Company findById(String id);
 
    PageInfo findAll(int page, int size);
}
