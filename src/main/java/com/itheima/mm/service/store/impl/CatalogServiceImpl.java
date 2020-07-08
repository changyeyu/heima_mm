package com.itheima.mm.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.CatalogDao;
import com.itheima.mm.domain.store.Catalog;
import com.itheima.mm.service.store.CatalogService;
import com.itheima.mm.util.DaoInstanceUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    
    private CatalogDao mapper = DaoInstanceUtil.getMapper(CatalogDao.class);
    
    @Override
    public void save(Catalog catalog) {
        String id = UUID.randomUUID().toString();
        catalog.setId(id);
        catalog.setCreateTime(new Date());
        mapper.save(catalog);
    }
    
    @Override
    public void update(Catalog catalog) {
        mapper.update(catalog);
    }
    
    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
    
    @Override
    public List<Catalog> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public Catalog findById(String id) {
        return mapper.findById(id);
    }
    
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Catalog> list = mapper.findAll();
        return new PageInfo(list);
    }
}
