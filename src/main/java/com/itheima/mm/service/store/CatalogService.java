package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Catalog;

import java.util.List;

public interface CatalogService {
    
    void save(Catalog catalog);
    
    void update(Catalog catalog);
    
    void delete(String id);
    
    List<Catalog> findAll();
    
    Catalog findById(String id);
 
    PageInfo findAll(int page, int size);
}
