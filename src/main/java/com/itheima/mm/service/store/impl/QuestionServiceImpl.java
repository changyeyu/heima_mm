package com.itheima.mm.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.QuestionDao;
import com.itheima.mm.domain.store.Question;
import com.itheima.mm.service.store.QuestionService;
import com.itheima.mm.util.DaoInstanceUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    
    private QuestionDao mapper = DaoInstanceUtil.getMapper(QuestionDao.class);
    
    @Override
    public void save(Question question) {
        String id = UUID.randomUUID().toString();
        question.setId(id);
        question.setCreateTime(new Date());
        mapper.save(question);
    }
    
    @Override
    public void update(Question question) {
        mapper.update(question);
    }
    
    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
    
    @Override
    public List<Question> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public Question findById(String id) {
        return mapper.findById(id);
    }
    
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Question> list = mapper.findAll();
        return new PageInfo(list);
    }
}
