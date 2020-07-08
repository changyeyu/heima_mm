package com.itheima.mm.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Date;

public class TestDemo {
    
    @Test
    public void testURLDecoder() throws Exception {
        
        System.out.println(URLDecoder.decode("张三", "utf-8"));
        
    }
    
    @Test
    public void testDate() {
        System.out.println(new Date().toString());
        String time = new Date().toLocaleString();
        System.out.println(time);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);
    }
    
    @Test
    public void testPOIWrite() throws Exception {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("test01");
        for (int i = 0; i < 10; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("test");
            }
        }
        wb.write(new FileOutputStream(new File("test.xlsx")));
        wb.close();
    }
    
    @Test
    public void testReadRead() throws IOException {
        Workbook wb = new XSSFWorkbook("test.xlsx");
        Sheet sheet = wb.getSheet("test01");
        int num = 0;
        for (int i = 0; i < 10; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < 10; j++) {
                Cell cell = row.getCell(j);
                String s = cell.getStringCellValue();
                System.out.println(s + num++);
            }
        }
        wb.close();
    }
    
    @Test
    public void testCreateSheets() throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima_mm","root","root");
        PreparedStatement statement = connection.prepareStatement("select * from ss_dept");
        ResultSet resultSet = statement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("ss_dept");
    
        int num = 0;
        Row row = sheet.createRow(num++);
        //设置列名
        for (int i = 1; i <= count; i++) {
            Cell cell = row.createCell(i-1);
            String columnName = metaData.getColumnName(i);
            cell.setCellValue(columnName);
        }
        //获取数据
        while (resultSet.next()) {
            Row row1 = sheet.createRow(num++);
            for (int j = 1; j <= count; j++) {
                Cell cell = row1.createCell(j-1);
                String s = resultSet.getString(j);
                cell.setCellValue(s);
            }
        }
        wb.write(new FileOutputStream(new File("dept.xlsx")));
        resultSet.close();
        statement.close();
        connection.close();
        wb.close();
    }
    
}
