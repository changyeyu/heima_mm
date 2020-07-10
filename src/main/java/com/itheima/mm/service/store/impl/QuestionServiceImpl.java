package com.itheima.mm.service.store.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mm.dao.store.QuestionDao;
import com.itheima.mm.domain.store.Question;
import com.itheima.mm.service.store.QuestionService;
import com.itheima.mm.util.DaoInstanceUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QuestionServiceImpl implements QuestionService {
    
    private QuestionDao mapper = DaoInstanceUtil.getMapper(QuestionDao.class);
    
    @Override
    public String save(Question question, boolean flag) {
        String pictureName = null;
        String id = UUID.randomUUID().toString();
        question.setId(id);
        question.setReviewStatus("0");
        question.setCreateTime(new Date());
        
        if (flag) {
            pictureName = id + new Date().getTime();
            question.setPicture(pictureName);
        }
        mapper.save(question);
        return pictureName;
    }
    
    @Override
    public String update(Question question, boolean flag) {
        
        String pictureName = null;
        
        if (flag) {
            pictureName = question.getId() + new Date().getTime();
            question.setPicture(pictureName);
        }
        mapper.update(question);
        return pictureName;
    }
    
    @Override
    public void delete(String id) {
        mapper.delete(id);
    }
    
    @Override
    public List<Question> findAll() {
        return mapper.findAll();
    }
    
    @Override
    public Question findById(String id) {
        return mapper.findById(id);
    }
    
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<Question> list = mapper.findAll();
        return new PageInfo(list);
    }
    
    @Override
    public ByteArrayOutputStream getReport() throws Exception {
    
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima_mm","root","root");
        PreparedStatement statement = connection.prepareStatement("select * from st_question");
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        String tableName = metaData.getTableName(1);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(tableName);
        //设置标题
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,count-1));
        CellStyle cs_title = wb.createCellStyle();
        cs_title.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
    
        int num = 0;
        Row row_0 = sheet.createRow(num++);
        Cell cell_0 = row_0.createCell(0);
        cell_0.setCellValue(tableName);
        cell_0.setCellStyle(cs_title);
        //设置列名
        Row row_1 = sheet.createRow(num++);
    
        for (int i = 1; i <= count; i++) {
            sheet.setColumnWidth(i-1,18*256);
        
            Cell cell_1 = row_1.createCell(i-1);
            String columnName = metaData.getColumnName(i);
            cell_1.setCellValue(columnName);
            CellStyle cs_column = wb.createCellStyle();
            cs_column.setAlignment(HorizontalAlignment.CENTER);
            cell_1.setCellStyle(cs_column);
        }
        //设置数据
        while (resultSet.next()) {
            Row row_temp = sheet.createRow(num++);
            for (int j = 1; j <= count; j++) {
                Cell cell_temp = row_temp.createCell(j-1);
                Object s = resultSet.getObject(j);
                if (s !=null){
                    cell_temp.setCellValue(s.toString());
                }
                CellStyle cs_filed = wb.createCellStyle();
                cs_filed.setAlignment(HorizontalAlignment.LEFT);
                cell_temp.setCellStyle(cs_filed);
            }
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        resultSet.close();
        statement.close();
        connection.close();
        wb.write(outputStream);
        wb.close();
        return outputStream;
    }
}
