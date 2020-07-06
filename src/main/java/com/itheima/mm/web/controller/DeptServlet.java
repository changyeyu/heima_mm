package com.itheima.mm.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Dept;
import com.itheima.mm.service.system.DeptService;
import com.itheima.mm.service.system.impl.DeptServiceImpl;
import com.itheima.mm.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/system/dept")
public class DeptServlet extends BaseServlet {
    
    private DeptService service = null;
    
    private void list(HttpServletRequest request, HttpServletResponse response) {
        
        service = new DeptServiceImpl();
        int page = 1;
        int size = 6;
        String pageStr = request.getParameter("page");
        String sizeStr = request.getParameter("size");
        if (StringUtils.isNotBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            size = Integer.parseInt(sizeStr);
        }
        PageInfo pageInfo = ((DeptServiceImpl) service).findAll(page, size);
        request.setAttribute("page", pageInfo);
        try {
            request.getRequestDispatcher("").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) {
        
        service = new DeptServiceImpl();
        Dept dept = BeanUtil.fillBean(request, Dept.class);
        dept.setId(UUID.randomUUID().toString());
        service.save(dept);
        
        try {
            request.getRequestDispatcher("").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
