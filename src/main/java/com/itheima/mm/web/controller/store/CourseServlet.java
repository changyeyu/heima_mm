package com.itheima.mm.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Course;
import com.itheima.mm.service.store.CourseService;
import com.itheima.mm.service.store.impl.CourseServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/course")
public class CourseServlet extends BaseServlet {
    
    private CourseService service = new CourseServiceImpl();
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = request.getParameter("page");
        String size = request.getParameter("size");
        if (StringUtils.isBlank(page)) {
            page = "1";
        }
        if (size == null) {
            size = "5";
        }
        PageInfo pageInfo = service.findAll(Integer.parseInt(page), Integer.parseInt(size));
        request.setAttribute("page", pageInfo);
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/course/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/course/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = BeanUtil.fillBean(request, Course.class, "yyyy-MM-dd");
        service.save(course);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Course course = service.findById(id);
        request.setAttribute("course", course);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/course/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Course course = BeanUtil.fillBean(request, Course.class, "yyyy-MM-dd");
        service.update(course);
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
