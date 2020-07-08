package com.itheima.mm.dao.store;


import com.itheima.mm.domain.store.Question;

import java.util.List;

public interface QuestionDao {
    
    //insert
    int save(Question question);

    //update
    int update(Question question);
    
    //delete
    int delete(String id);
    
    //select
    List<Question> findAll();
    
    Question findById(String id);


}
