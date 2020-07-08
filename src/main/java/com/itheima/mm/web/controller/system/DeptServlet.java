package com.itheima.mm.web.controller.system;


import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Dept;
import com.itheima.mm.service.system.DeptService;
import com.itheima.mm.service.system.impl.DeptServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
    
    private DeptService service = new DeptServiceImpl();
    
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
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/dept/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Dept> list = service.findAll();
        request.setAttribute("deptList", list);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/dept/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Dept dept = BeanUtil.fillBean(request, Dept.class, "yyyy-MM-dd");
        service.save(dept);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Dept dept = service.findById(id);
        List<Dept> list = service.findAll();
        request.setAttribute("deptList", list);
        request.setAttribute("dept", dept);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/dept/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Dept dept = BeanUtil.fillBean(request, Dept.class, "yyyy-MM-dd");
        service.update(dept);
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
}
