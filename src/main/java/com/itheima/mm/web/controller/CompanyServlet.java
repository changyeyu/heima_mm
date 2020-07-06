package com.itheima.mm.web.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Company;
import com.itheima.mm.service.store.CompanyService;
import com.itheima.mm.service.store.impl.CompanyServiceImpl;
import com.itheima.mm.util.BeanUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/company")
public class CompanyServlet extends BaseServlet {
    
    private CompanyService service = new CompanyServiceImpl();
    
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
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/company/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/company/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Company company = BeanUtil.fillBean(request, Company.class, "yyyy-MM-dd");
        service.save(company);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Company company = service.findById(id);
        request.setAttribute("company", company);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/company/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        Company company = BeanUtil.fillBean(request, Company.class, "yyyy-MM-dd");
        service.update(company);
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
