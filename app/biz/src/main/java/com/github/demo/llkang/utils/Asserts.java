/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.ly.sof.api.error.LYError;
import com.github.demo.llkang.exception.DemollkangException;

/**
 * 
 * @author hy45524
 * @version $Id: Asserts.java, v 0.1 2017年1月24日 上午11:27:11 hy45524 Exp $
 */
public class Asserts {

    /**
     * 断言boolean表达式
     * 
     * @param expression
     * @param error
     * @throws AuditException
     */
    public static void isTrue(boolean expression, LYError error) throws DemollkangException {
        if (!expression) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言boolean表达式
     * 
     * @param expression
     * @param error
     * @throws AuditException
     */
    public static void expressionIsTrue(boolean expression, LYError error) throws DemollkangException {
        if (!expression) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言对象非空
     * 
     * @param object
     * @param error
     * @throws AuditException
     */
    public static void notNull(Object object, LYError error) throws DemollkangException {
        if (object == null) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言对象非空
     * 
     * @param object
     * @param error
     * @throws AuditException
     */
    public static void objectNotNull(Object object, LYError error) throws DemollkangException {
        if (object == null) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言字符串非空
     * 
     * @param string
     * @param error
     * @throws AuditException
     */
    public static void notEmpty(String string, LYError error) throws DemollkangException {
        if (StringUtils.isBlank(string)) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言字符串非空
     * 
     * @param string
     * @param error
     * @throws AuditException
     */
    public static void strNotEmpty(String string, LYError error) throws DemollkangException {
        if (StringUtils.isBlank(string)) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言
     * 
     * @param string
     * @param error
     * @throws AuditException
     */
    public static void notNull(Long l, LYError error) throws DemollkangException {
        if (l == null || l == 0) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言
     * 
     * @param string
     * @param error
     * @throws AuditException
     */
    public static void notNull(Integer l, LYError error) throws DemollkangException {
        if (l == null || l == 0) {
            throw new DemollkangException(error);
        }
    }

    /**
     * 断言
     * 
     * @param string
     * @param error
     * @throws AuditException
     */
    public static void notNullList(@SuppressWarnings("rawtypes") List list, LYError error) throws DemollkangException {
        if (CollectionUtils.isEmpty(list)) {
            throw new DemollkangException(error);
        }
    }

}
