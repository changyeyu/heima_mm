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
    public String save(Question question, boolean flag) {
        String pictureName = null;
        String id = UUID.randomUUID().toString();
        question.setId(id);
        question.setReviewStatus("0");
        question.setCreateTime(new Date());
        
        if (flag){
            pictureName = id + new Date().getTime();
            question.setPicture(pictureName);
        }
        mapper.save(question);
        return pictureName;
    }
    
    @Override
    public String update(Question question, boolean flag) {
        String pictureName = null;
        if (flag){
            pictureName = question.getId() + new Date().getTime();
            question.setPicture(pictureName);
        }
        mapper.update(question);
        return pictureName;
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
