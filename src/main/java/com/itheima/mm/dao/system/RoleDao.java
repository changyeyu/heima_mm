package com.itheima.mm.dao.system;

import com.itheima.mm.domain.system.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    
    @Insert("insert into ss_role(role_id,name,remark,create_time) values(#{id},#{name},#{remark},#{createTime})")
    int save(Role role);
    
    @Delete("delete from ss_role where role_id=#{id}")
    int delete(String id);
    
    @Update("update ss_role set name=#{name},remark=#{remark} where role_id=#{id}")
    int update(Role role);
    
    @Select("select role_id, name, remark, create_time from ss_role where role_id=#{id}")
    @ResultMap("base_column_list")
    Role findById(String id);
    
    @Select("select role_id, name, remark, create_time from ss_role")
    @Results(id = "base_column_list",
            value = {@Result(column = "role_id", id = true, property = "id")
            })
    List<Role> findAll();
    
    @Delete("delete from ss_role_module where role_id=#{roleId}")
    void deleteRoleModule(String roleId);
    
    @Delete("delete from ss_role_user where user_id=#{userId}")
    void deleteRoleUSer(String userId);
    
    @Insert("insert into ss_role_module values(#{roleId}, #{moduleId})")
    void saveRoleModule(@Param("roleId") String roleId, @Param("moduleId") String moduleId);
    
    @Select("SELECT " +
            "role_id id, " +
            "NAME, " +
            "CASE " +
            "WHEN role_id IN (SELECT role_id FROM ss_role_user WHERE user_id = #{'userId'}) " +
            "THEN 'checked' " +
            "ELSE '' " +
            "END " +
            "AS remark " +
            "FROM " +
            "ss_role")
    List<Role> findAllRoleByUserId(String userId);
}
