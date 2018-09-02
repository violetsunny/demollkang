package com.github.demo.llkang;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TT {

    @Test
    public void t1(){
        System.out.println(JSON.toJSONString(null));
    }
    
    @Test
    public void t2(){
        System.out.println("开始时间"+DateFormat.date2Str(Long.valueOf("1490025600000"), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("结束时间"+DateFormat.date2Str(Long.valueOf("1490198400000"), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("创建时间"+DateFormat.date2Str(Long.valueOf("1490009862237"), "yyyy-MM-dd HH:mm:ss"));
    }
}
