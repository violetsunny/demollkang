/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

/**
 * 
 * @author gy22422
 * @version $Id: Cache.java, v 0.1 2016年7月22日 上午10:48:22 gy22422 Exp $
 */
public class Cache {

    private String value;   //值

    private long   dateTime;//缓存的时间

    public Cache(String value, long time) {
        this.value = value;
        this.dateTime = time;
    }

    /**
     * 
     */
    public Cache() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

}
