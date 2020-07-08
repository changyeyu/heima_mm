package com.itheima.mm.service.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Course;

import java.util.List;

public interface CourseService {
    
    void save(Course course);
    
    void update(Course course);
    
    void delete(String id);
    
    List<Course> findAll();
    
    Course findById(String id);
 
    PageInfo findAll(int page, int size);
    
}
