package com.itheima.mm.web.controller.system;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Role;
import com.itheima.mm.service.system.RoleService;
import com.itheima.mm.service.system.impl.ModuleServiceImpl;
import com.itheima.mm.service.system.impl.RoleServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/system/role")
public class RoleServlet extends BaseServlet {
    
    private RoleService service = new RoleServiceImpl();
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        int page = 1;
        int size = 5;
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            size = Integer.parseInt(sizeStr);
        }
        PageInfo pageInfo = service.findAll(page, size);
        request.setAttribute("page", pageInfo);
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/role/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Role> list = service.findAll();
        request.setAttribute("roleList", list);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/role/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        service.save(role);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Role role = service.findById(id);
        List<Role> list = service.findAll();
        request.setAttribute("roleList", list);
        request.setAttribute("role", role);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/role/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role role = BeanUtil.fillBean(request, Role.class, "yyyy-MM-dd");
        service.update(role);
        list(request, response);
    }
    
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        String[] arr = idStr.split("_");
        for (String id : arr) {
            service.delete(id);
        }
        list(request, response);
    }
    
    
    private void author(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        Role role = service.findById(id);
        request.setAttribute("role", role);
        
        ModuleServiceImpl moduleService = new ModuleServiceImpl();
        List<Map> maps = moduleService.findAuthorDataByRoleId(id);
        ObjectMapper writer = new ObjectMapper();
        String roleModuleJson = writer.writeValueAsString(maps);
        request.setAttribute("roleModuleJson", roleModuleJson);
        request.getRequestDispatcher("/WEB-INF/pages/system/role/author.jsp").forward(request, response);
    }
    
    private void updateRoleModule(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String roleId = request.getParameter("roleId");
        service.deleteRoleModule(roleId);
        
        String moduleIdStr = request.getParameter("moduleIds");
        String[] moduleIds = moduleIdStr.split(",");
        for (String moduleId : moduleIds) {
            service.saveRoleModule(roleId, moduleId);
        }
        list(request, response);
    }
    
    
}
