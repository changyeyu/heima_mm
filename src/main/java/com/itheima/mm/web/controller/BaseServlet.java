package com.itheima.mm.web.controller;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        
        String operation = req.getParameter("operation");
       
        Class<? extends Servlet> clazz = this.getClass();
        
        try {
            Method method = clazz.getDeclaredMethod(operation, HttpServletRequest.class, HttpServletResponse.class);
           
            method.setAccessible(true);
            
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
}
