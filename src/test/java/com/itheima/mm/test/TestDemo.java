package com.itheima.mm.test;

import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

public class TestDemo {
    
    @Test
    public void test() throws Exception {
        
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
    
}
