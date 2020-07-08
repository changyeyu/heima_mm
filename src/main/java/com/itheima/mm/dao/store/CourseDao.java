package com.itheima.mm.dao.store;

import com.itheima.mm.domain.store.Course;

import java.util.List;

public interface CourseDao {
    
    //insert
    int save(Course course);

    //update
    int update(Course course);
    
    //delete
    int delete(String id);
    
    //select
    List<Course> findAll();
    
    Course findById(String id);


}
