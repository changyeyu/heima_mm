package com.itheima.mm.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Catalog;
import com.itheima.mm.domain.store.Course;
import com.itheima.mm.service.store.CatalogService;
import com.itheima.mm.service.store.impl.CatalogServiceImpl;
import com.itheima.mm.service.store.impl.CourseServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/store/catalog")
public class CatalogServlet extends BaseServlet {
    
    private CatalogService service = new CatalogServiceImpl();
    
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
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/catalog/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Course> list = new CourseServiceImpl().findAll();
        request.setAttribute("courseList", list);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/catalog/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Catalog catalog = BeanUtil.fillBean(request, Catalog.class, "yyyy-MM-dd");
        service.save(catalog);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Catalog catalog = service.findById(id);
        List<Course> list = new CourseServiceImpl().findAll();
        request.setAttribute("courseList", list);
        request.setAttribute("catalog", catalog);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/catalog/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Catalog catalog = BeanUtil.fillBean(request, Catalog.class, "yyyy-MM-dd");
        service.update(catalog);
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
