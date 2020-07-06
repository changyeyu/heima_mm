package com.itheima.mm.test;

import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestDemo {
    
    @Test
    public void test() throws Exception{
       
        System.out.println(URLDecoder.decode("张三", "utf-8"));
    
    }
    
}
