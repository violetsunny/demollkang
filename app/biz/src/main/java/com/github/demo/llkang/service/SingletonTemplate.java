/**
 * LY.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.github.demo.llkang.service;

/**
 * @author kll49556
 * @version $Id: SingletonTemplate, v 0.1 2018/7/28 14:23 kll49556 Exp $
 *
 * 推荐写法 单例
 */
public class SingletonTemplate {

    private SingletonTemplate(){}

    public static SingletonTemplate getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }

    private enum SingletonEnum{
        INSTANCE;

        private SingletonTemplate singletonTemplate;

        //JVM保证这个构造器只会执行一次
        SingletonEnum(){
            singletonTemplate = new SingletonTemplate();
        }

        public SingletonTemplate getInstance(){
            return singletonTemplate;
        }
    }
}
