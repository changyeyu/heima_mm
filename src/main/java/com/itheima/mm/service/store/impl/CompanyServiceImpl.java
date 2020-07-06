package com.itheima.mm.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.CompanyDao;
import com.itheima.mm.domain.store.Company;
import com.itheima.mm.factory.MapperFactory;
import com.itheima.mm.service.store.CompanyService;
import com.itheima.mm.util.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {
    
    private SqlSession sqlSession = null;
    private CompanyDao mapper = null;
    
    @Override
    public void save(Company company) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(CompanyDao.class);
            String id = UUID.randomUUID().toString();
            company.setId(id);
            int res = mapper.save(company);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public void update(Company company) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(CompanyDao.class);
            int res = mapper.update(company);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public void delete(String id) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(CompanyDao.class);
            int res = mapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public List<Company> findAll() {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            List<Company> list = mapper.findAll();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public Company findById(String id) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            return mapper.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public PageInfo findAll(int page, int size) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, CompanyDao.class);
            PageHelper.startPage(page, size);
            List<Company> list = mapper.findAll();
            return new PageInfo(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
}
