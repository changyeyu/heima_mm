package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Question;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface QuestionService {
    
    String save(Question question, boolean flag);
    
    String update(Question question, boolean flag);
    
    void delete(String id);
    
    List<Question> findAll();
    
    Question findById(String id);
 
    PageInfo findAll(int page, int size);
    
    ByteArrayOutputStream getReport() throws Exception;
}
