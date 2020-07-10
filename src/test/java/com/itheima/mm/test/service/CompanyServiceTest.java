package com.itheima.mm.test.service;

import com.itheima.mm.domain.store.Company;
import com.itheima.mm.service.store.CompanyService;
import com.itheima.mm.service.store.impl.CompanyServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CompanyServiceTest {
    
    private static CompanyService mapper = null;
    
    @BeforeClass
    public static void init() {
        mapper = new CompanyServiceImpl();
    }
    
    
    @Test
    public void testFindAll() {
        List<Company> list = mapper.findAll();
        System.out.println(list);
    }
    
    @Test
    public void testFindById(){
        mapper.findById("");
    }
    
//    @Test
//    public void testSave(){
//        Company company = new Company();
//        mapper.save(company);
//    }
//
//    @Test
//    public void testUpdate(){
//        Company company = new Company();
//        mapper.update(company);
//    }
    
    @Test
    public void testDelete(){
        mapper.delete("");
    }
    
    @Test
    public void testFindByPage(){
        System.out.println(mapper.findAll(1, 5));
    }
    
    @AfterClass
    public static void destroy() {
        mapper = null;
    }
    
}
