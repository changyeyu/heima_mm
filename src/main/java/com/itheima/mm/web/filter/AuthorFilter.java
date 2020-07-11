package com.itheima.mm.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class AuthorFilter implements Filter {
    
    private FilterConfig filterConfig;
    
    /**
     * 初始化方法，获取过滤器的配置对象
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) resp;
            String uri = request.getRequestURI();// system/user
            if ("/".equals(uri) || uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith("login.jsp")
                    || uri.endsWith("index.jsp") || uri.endsWith(".jpg") || uri.endsWith(".png")
                    || uri.endsWith("unauthorized.jsp") || uri.endsWith("home.jsp")
            ) {
                chain.doFilter(request, response);
                return;
            }
            
            
            uri = uri.substring(1);
            String queryString = request.getQueryString();
            if (queryString != null) {
                int index = queryString.indexOf('&');
                if (index != -1) {
                    queryString = queryString.substring(0, index);
                }
            }
            
            uri = uri + "?" + queryString;
            
            if (uri.endsWith("login") || uri.endsWith("logout") || uri.endsWith("home") ) {
                chain.doFilter(request, response);
                return;
            }
            Object moduleStr1 = request.getSession().getAttribute("moduleStr");
            String moduleStr = moduleStr1.toString();
            System.out.println(moduleStr);
            if (moduleStr.contains(uri)) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/unauthorized.jsp");
            e.printStackTrace();
        }
    }
    
    @Override
    public void destroy() {
        //可以做一些清理操作
    }
}
