package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Question;

import java.util.List;

public interface QuestionService {
    
    void save(Question question);
    
    void update(Question question);
    
    void delete(String id);
    
    List<Question> findAll();
    
    Question findById(String id);
 
    PageInfo findAll(int page, int size);
}
