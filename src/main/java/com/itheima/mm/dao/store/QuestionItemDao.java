package com.itheima.mm.dao.store;

import com.itheima.mm.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
    
    //insert
    int save(QuestionItem questionItem);
    
    //updatee
    int update(QuestionItem questionItem);
    
    //delete
    int delete(String id);
    
    //select
    List<QuestionItem> findAll(String questionId);
    
    QuestionItem findById(String id);
}
