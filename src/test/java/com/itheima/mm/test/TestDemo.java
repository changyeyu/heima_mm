package com.itheima.mm.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Arrays;
import java.util.Date;

public class TestDemo {
    
    @Test
    public void testSplit(){
        String s = "1,2,";
        String[] split = s.split(",");
        System.out.println(split.length);
    }
//
//    @Test
//    public void testURLDecoder() throws Exception {
//
//        System.out.println(URLDecoder.decode("张三", "utf-8"));
//
//    }
//
//    @Test
//    public void testDate() {
//        System.out.println(new Date().toString());
//        String time = new Date().toLocaleString();
//        System.out.println(time);
//        Timestamp timestamp = new Timestamp(new Date().getTime());
//        System.out.println(timestamp);
//    }
//
//    @Test
//    public void testPOIWrite() throws Exception {
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("test01");
//        for (int i = 0; i < 10; i++) {
//            Row row = sheet.createRow(i);
//            for (int j = 0; j < 10; j++) {
//                Cell cell = row.createCell(j);
//                cell.setCellValue("test");
//            }
//        }
//        wb.write(new FileOutputStream(new File("test.xlsx")));
//        wb.close();
//    }
//
//   /* @Test
//    public void testPOIRead() throws IOException {
//        Workbook wb = new XSSFWorkbook("test.xlsx");
//        Sheet sheet = wb.getSheet("test01");
//        int num = 0;
//        for (int i = 0; i < 10; i++) {
//            Row row = sheet.getRow(i);
//            for (int j = 0; j < 10; j++) {
//                Cell cell = row.getCell(j);
//                String s = cell.getStringCellValue();
//                System.out.println(s + num++);
//            }
//        }
//        wb.close();
//    }*/
//
//    @Test
//    public void testCreateSheets() throws Exception{
//        Connection connection = DriverManager.getConnection("jdbc:mysql:///heima_mm","root","root");
//        PreparedStatement statement = connection.prepareStatement("select * from ss_dept");
//        ResultSet resultSet = statement.executeQuery();
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        int count = metaData.getColumnCount();
//        String tableName = metaData.getTableName(1);
//        Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet(tableName);
//        //设置标题
//        sheet.addMergedRegion(new CellRangeAddress(0,0,0,count-1));
//        CellStyle cs_title = wb.createCellStyle();
//        cs_title.setAlignment(HorizontalAlignment.CENTER);//水平居中
//        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
//
//        int num = 0;
//        Row row_0 = sheet.createRow(num++);
//        Cell cell_0 = row_0.createCell(0);
//        cell_0.setCellValue(tableName);
//        cell_0.setCellStyle(cs_title);
//        //设置列名
//        Row row_1 = sheet.createRow(num++);
//
//        for (int i = 1; i <= count; i++) {
//            sheet.setColumnWidth(i-1,18*256);
//
//            Cell cell_1 = row_1.createCell(i-1);
//            String columnName = metaData.getColumnName(i);
//            cell_1.setCellValue(columnName);
//            CellStyle cs_column = wb.createCellStyle();
//            cs_column.setAlignment(HorizontalAlignment.CENTER);
//            cell_1.setCellStyle(cs_column);
//        }
//        //设置数据
//        while (resultSet.next()) {
//            Row row_temp = sheet.createRow(num++);
//            for (int j = 1; j <= count; j++) {
//                Cell cell_temp = row_temp.createCell(j-1);
//                Object s = resultSet.getObject(j);
//                if (s !=null){
//                    cell_temp.setCellValue(s.toString());
//                }
//                CellStyle cs_filed = wb.createCellStyle();
//                cs_filed.setAlignment(HorizontalAlignment.LEFT);
//                cell_temp.setCellStyle(cs_filed);
//            }
//        }
//        wb.write(new FileOutputStream(new File("dept.xlsx")));
//        resultSet.close();
//        statement.close();
//        connection.close();
//        wb.close();
//    }
//
}
