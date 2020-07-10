package com.itheima.mm.service.store.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.QuestionItemDao;
import com.itheima.mm.domain.store.QuestionItem;
import com.itheima.mm.service.store.QuestionItemService;
import com.itheima.mm.util.DaoInstanceUtil;

import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    
    private QuestionItemDao mapper = DaoInstanceUtil.getMapper(QuestionItemDao.class);
    
    @Override
    public void save(QuestionItem questionItem) {
        String id = UUID.randomUUID().toString();
        questionItem.setId(id);
        mapper.save(questionItem);
    }
    
    @Override
    public void update(QuestionItem questionItem) {
        mapper.update(questionItem);
    }
    
    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
    
    @Override
    public List<QuestionItem> findAll(String id) {
        return mapper.findAll(id);
    }
    
    @Override
    public QuestionItem findById(String id) {
        return mapper.findById(id);
    }
    
    @Override
    public PageInfo findAll(String questionId, int page, int size) {
        PageHelper.startPage(page, size);
        List<QuestionItem> list = mapper.findAll(questionId);
        return new PageInfo(list);
    }
    
    @Override
    public void deleteByQuestionId(String id) {
        List<QuestionItem> all = mapper.findAll(id);
        for (QuestionItem questionItem : all) {
            String questionItemId = questionItem.getId();
            mapper.delete(questionItemId);
        }
    }
}
