package com.itheima.mm.dao.store;

import com.itheima.mm.domain.store.Company;

import java.util.List;

public interface CompanyDao {
    
    //insert
    int save(Company company);

    //update
    int update(Company company);
    
    //delete
    int delete(String id);
    
    //select
    List<Company> findAll();
    
    Company findById(String id);


}
