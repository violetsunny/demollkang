/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kll49556
 * @version $Id: NumberTest, v 0.1 2018/7/17 20:24 kll49556 Exp $
 */
public class NumberTest {

    public static void main(String[] args){
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println("--------");
        System.out.println(e == f);
        System.out.println("--------");
        System.out.println(c == (a + b));
        System.out.println("--------");
        System.out.println(c.equals(a + b));
        System.out.println("--------");
        System.out.println(g == (a + b));
        System.out.println("--------");
        System.out.println(g.equals(a + b));
    }


    public class ConcurrentNumber{

        private AtomicInteger count = new AtomicInteger(0);

        public void test1(){
            count.getAndIncrement();
        }
    }
}
