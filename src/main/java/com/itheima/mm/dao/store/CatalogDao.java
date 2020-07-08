package com.itheima.mm.dao.store;

import com.itheima.mm.domain.store.Catalog;

import java.util.List;

public interface CatalogDao {
    
    //insert
    int save(Catalog catalog);

    //update
    int update(Catalog catalog);
    
    //delete
    int delete(String id);
    
    //select
    List<Catalog> findAll();
    
    Catalog findById(String id);


}
