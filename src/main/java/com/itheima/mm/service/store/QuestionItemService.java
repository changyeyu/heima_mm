package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemService {
    
    void save(QuestionItem questionItem);
    
    void update(QuestionItem questionItem);
    
    void delete(String id);
    
    List<QuestionItem> findAll(String questionId);
    
    QuestionItem findById(String id);
 
    PageInfo findAll(String questionId, int page, int size);
}
