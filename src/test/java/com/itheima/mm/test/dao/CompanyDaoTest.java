package com.itheima.mm.test.dao;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.CompanyDao;
import com.itheima.mm.domain.store.Company;
import com.itheima.mm.factory.MapperFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CompanyDaoTest {
    
    private static CompanyDao mapper = null;
    
    @BeforeClass
    public static void init() {
        mapper = MapperFactory.getMapper(MapperFactory.getSqlSession(true), CompanyDao.class);
    }
    
    @Test
    public void testPage(){
//        Page<Company> page = PageHelper.startPage(100, 5);
        List<Company> page = mapper.findAll();
        System.out.println(new PageInfo<>(page).getList());
    }
    
    @Test
    public void testFindAll() {
        List<Company> list = mapper.findAll();
        System.out.println(list);
    }
    
    @Test
    public void testFindById(){
        mapper.findById("ss");
    }
    
//    @Test
//    public void testSave(){
//        Company company = new Company();
//        company.setId("sasa");
//        mapper.save(company);
//    }
    
//    @Test
//    public void testUpdate(){
//        Company company = new Company();
//        company.setId("sasa");
//        mapper.update(company);
//    }
    
    @Test
    public void testDelete(){
        mapper.delete("ss");
    }
    
    
    @AfterClass
    public static void destroy() {
        mapper = null;
    }
    
}
