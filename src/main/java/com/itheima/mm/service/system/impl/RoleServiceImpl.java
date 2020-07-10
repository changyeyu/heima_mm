package com.itheima.mm.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.system.RoleDao;
import com.itheima.mm.domain.system.Role;
import com.itheima.mm.factory.MapperFactory;
import com.itheima.mm.service.system.RoleService;
import com.itheima.mm.util.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {
    
    private SqlSession sqlSession = null;
    private RoleDao mapper = null;
    
    @Override
    public void save(Role role) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(RoleDao.class);
            String id = UUID.randomUUID().toString();
            role.setId(id);
            role.setCreateTime(new Date());
            int res = mapper.save(role);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public void update(Role role) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = sqlSession.getMapper(RoleDao.class);
            int res = mapper.update(role);
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
            mapper = sqlSession.getMapper(RoleDao.class);
            int res = mapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public List<Role> findAll() {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            List<Role> list = mapper.findAll();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public Role findById(String id) {
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
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
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            PageHelper.startPage(page, size);
            List<Role> list = mapper.findAll();
            return new PageInfo(list);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public List<Role> findAllRoleByUserId(String userId) {
        List<Role> list = new ArrayList<>();
        try {
            sqlSession = MapperFactory.getSqlSession(true);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            list = mapper.findAllRoleByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
        return list;
    }
    
    @Override
    public void deleteRoleModule(String roleId) {
        try {
            sqlSession = MapperFactory.getSqlSession(false);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            mapper.deleteRoleModule(roleId);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
    
    @Override
    public void deleteRoleUSer(String userId) {
        try {
            sqlSession = MapperFactory.getSqlSession(false);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            mapper.deleteRoleUSer(userId);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
        
    }
    
    @Override
    public void saveRoleModule(String roleId, String moduleId) {
        try {
            sqlSession = MapperFactory.getSqlSession(false);
            mapper = MapperFactory.getMapper(sqlSession, RoleDao.class);
            mapper.saveRoleModule(roleId, moduleId);
            TransactionUtil.commit(sqlSession);
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
        } finally {
            TransactionUtil.close(sqlSession);
        }
    }
}
