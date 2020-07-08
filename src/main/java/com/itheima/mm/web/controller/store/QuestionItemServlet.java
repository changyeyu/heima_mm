package com.itheima.mm.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.QuestionItem;
import com.itheima.mm.service.store.QuestionItemService;
import com.itheima.mm.service.store.impl.QuestionItemServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    
    private QuestionItemService service = new QuestionItemServiceImpl();
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String questionId = request.getParameter("questionId");
        request.setAttribute("questionId", questionId);
        PageInfo pageInfo = service.findAll(questionId, 1, 20);
        request.setAttribute("page", pageInfo);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/questionItem/list.jsp").forward(request, response);
    }
    
    
    private void saveOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        QuestionItem questionItem = BeanUtil.fillBean(request, QuestionItem.class, "yyyy-MM-dd");
        if ("".equalsIgnoreCase(id)) {
            service.save(questionItem);
        } else {
            service.update(questionItem);
        }
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        QuestionItem questionItem = service.findById(id);
        request.setAttribute("questionItem", questionItem);
        list(request, response);
    }
    
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        service.delete(idStr);
        list(request, response);
    }
}
