package com.itheima.mm.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.itheima.mm.domain.store.Catalog;
import com.itheima.mm.domain.store.Company;
import com.itheima.mm.domain.store.Question;
import com.itheima.mm.domain.store.QuestionItem;
import com.itheima.mm.service.store.QuestionService;
import com.itheima.mm.service.store.impl.CatalogServiceImpl;
import com.itheima.mm.service.store.impl.CompanyServiceImpl;
import com.itheima.mm.service.store.impl.QuestionItemServiceImpl;
import com.itheima.mm.service.store.impl.QuestionServiceImpl;
import com.itheima.mm.util.BeanUtil;
import com.itheima.mm.web.controller.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
    
    private QuestionService service = new QuestionServiceImpl();
    
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
        
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/question/list.jsp").forward(request, response);
    }
    
    
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Company> companyList = new CompanyServiceImpl().findAll();
        request.setAttribute("companyList", companyList);
        
        List<Catalog> catalogList = new CatalogServiceImpl().findAll();
        request.setAttribute("catalogList", catalogList);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/question/add.jsp").forward(request, response);
    }
    
    private void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //是否是文件上传
        if (ServletFileUpload.isMultipartContent(request)) {
            //创建核心对象
            FileUpload fileUpload = new FileUpload(new DiskFileItemFactory());
            //获取表单中所有字段
            List<FileItem> list = fileUpload.parseRequest(request);
            boolean flag = false;
            for (FileItem item : list) {
                if (StringUtils.isNotBlank(item.getName())) {
                    flag = true;
                    break;
                }
            }
            //封装，此时文件项未封装到属性
            Question question = BeanUtil.fillBean(list, Question.class);
            //调用service层方法返回数据库存放的图片名
            String pictureName = service.save(question, flag);
            if (flag) {
                for (FileItem fileItem : list) {
                    if (!fileItem.isFormField()) {
                        String realPath = request.getServletContext().getRealPath("/upload");
                        fileItem.write(new File(realPath + "/" + pictureName));
                    }
                }
            }
            list(request, response);
        }
        
    }
    
    
    private void toEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String page = request.getParameter("page");
        Question question = service.findById(id);
        request.setAttribute("question", question);
        
        List<Company> companyList = new CompanyServiceImpl().findAll();
        request.setAttribute("companyList", companyList);
        
        List<Catalog> catalogList = new CatalogServiceImpl().findAll();
        request.setAttribute("catalogList", catalogList);
        
        request.setAttribute("page", page);
        request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/store/question/update.jsp").forward(request, response);
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
        DiskFileItemFactory factory = new DiskFileItemFactory();
        FileUpload fileUpload = new FileUpload(factory);
        //获取表单中所有字段
        List<FileItem> list = fileUpload.parseRequest(request);
        boolean flag = false;
        for (FileItem item : list) {
            if (StringUtils.isNotBlank(item.getName())) {
                flag = true;
                break;
            }
        }
        //有文件上传
        Question question = BeanUtil.fillBean(list, Question.class);
        
        String pictureName = service.update(question, flag);
        if (flag) {
            for (FileItem fileItem : list) {
                if (!fileItem.isFormField()) {
                    String realPath = request.getServletContext().getRealPath("/upload");
                    fileItem.write(new File(realPath + "/" + pictureName));
                }
            }
        }
        list(request, response);
    }
    
    
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        String[] arr = idStr.split("_");
        QuestionItemServiceImpl questionItemService = new QuestionItemServiceImpl();
        for (String id : arr) {
            questionItemService.deleteByQuestionId(id);
            service.delete(id);
        }
        list(request, response);
    }
    
    private void toExport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        String filename = new String("试题.xlsx".getBytes(), "iso8859-1");
        response.setHeader("Content-Disposition","attachment;filename=" + filename);
        ByteArrayOutputStream out = service.getReport();
        ServletOutputStream responseOutputStream = response.getOutputStream();
        out.writeTo(responseOutputStream);
        responseOutputStream.flush();
        out.close();
        responseOutputStream.close();
    }
}
