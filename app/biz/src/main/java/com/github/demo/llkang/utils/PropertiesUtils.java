/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * propertie 工具类
 * @author gy22422
 * @version $Id: PropertiesUtils.java, v 0.1 2016年11月28日 上午10:24:13 gy22422 Exp $
 */
public class PropertiesUtils {

    /**
     * 获得dubbo参数信息
     * 
     * @param key
     * @return
     */
    public static String getDubboPropertiesParam(String key) {
        Properties p = new Properties();
        try {
            p.load(PropertiesUtils.class.getResourceAsStream("/application.properties"));
            String value = p.getProperty(key).trim();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
