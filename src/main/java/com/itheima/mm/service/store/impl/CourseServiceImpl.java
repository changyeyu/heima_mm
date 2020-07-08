package com.itheima.mm.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.CourseDao;
import com.itheima.mm.domain.store.Course;
import com.itheima.mm.factory.MapperFactory;
import com.itheima.mm.service.store.CourseService;
import com.itheima.mm.util.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    
    private SqlSession sqlSession = null;
    private CourseDao mapper = null;
    
    @Override
    public void save(Course course) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(CourseDao.class);
            String id = UUID.randomUUID().toString();
            course.setId(id);
            course.setCreateTime(new Date());
            int res = mapper.save(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public void update(Course course) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(CourseDao.class);
            int res = mapper.update(course);
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
            mapper = sqlSession.getMapper(CourseDao.class);
            int res = mapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public List<Course> findAll() {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            List<Course> list = mapper.findAll();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public Course findById(String id) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
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
            mapper = MapperFactory.getMapper(sqlSession, CourseDao.class);
            PageHelper.startPage(page, size);
            List<Course> list = mapper.findAll();
            return new PageInfo(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
}
