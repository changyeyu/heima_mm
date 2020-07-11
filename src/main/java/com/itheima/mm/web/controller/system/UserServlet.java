package com.itheima.mm.web.controller.system;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.system.Dept;
import com.itheima.mm.domain.system.Module;
import com.itheima.mm.domain.system.Role;
import com.itheima.mm.domain.system.User;
import com.itheima.mm.service.system.DeptService;
import com.itheima.mm.service.system.UserService;
import com.itheima.mm.service.system.impl.DeptServiceImpl;
import com.itheima.mm.service.system.impl.RoleServiceImpl;
import com.itheima.mm.service.system.impl.UserServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/system/user")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();
    
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
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/user/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Dept> list = new DeptServiceImpl().findAll();
        request.setAttribute("deptList", list);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/user/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        service.save(user);
        list(request, response);
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        User user = service.findById(id);
        DeptService deptService = new DeptServiceImpl();
        List<Dept> list = deptService.findAll();
        
        request.setAttribute("user", user);
        request.setAttribute("deptList", list);
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/system/user/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = BeanUtil.fillBean(request, User.class, "yyyy-MM-dd");
        service.update(user);
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
    
    private void userRoleList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        User user = service.findById(id);
        request.setAttribute("user", user);
        
        RoleServiceImpl roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.findAllRoleByUserId(id);
        request.setAttribute("roleList", roleList);
        
        request.getRequestDispatcher("/WEB-INF/pages/system/user/role.jsp").forward(request, response);
    }
    
    private void updateRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("userId");
        new RoleServiceImpl().deleteRoleUSer(userId);
        String[] roleIds = request.getParameterValues("roleIds");
        if (roleIds != null) {
            for (String roleId : roleIds) {
                service.updateRole(userId, roleId);
            }
        }
        list(request, response);
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = service.login(email, password);
        if (user != null) {
            List<Module> moduleList = service.findModulesById(user.getId());
            StringBuilder moduleStr = new StringBuilder();
            for (Module module : moduleList) {
                moduleStr.append(module.getCurl());
                moduleStr.append(",");
            }
            request.getSession().setAttribute("loginUser", user);
            request.getSession().setAttribute("moduleStr", moduleStr);
            request.getSession().setAttribute("modules", moduleList);
            request.getRequestDispatcher("/WEB-INF/pages/home/main.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "登录失败，请检查用户名或密码");
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            
        }
    }
    
    private void islogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null)
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        else {
            request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
        }
    }
    
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("loginUser");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
    
    private void home(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pages/home/home.jsp").forward(request, response);
    }
}
