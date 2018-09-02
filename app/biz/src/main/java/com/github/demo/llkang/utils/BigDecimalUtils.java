package com.github.demo.llkang.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    private BigDecimalUtils(){}

    /**
     * 加法
     * 
     * @param bd1
     * @param bd2
     * @return
     */
    public static BigDecimal add(BigDecimal bd1, BigDecimal bd2) {
        if (bd1 != null && bd2 != null) {
            return bd1.add(bd2);
        } else {
            return new BigDecimal("0");
        }
    }

    /**
     * 将String类型转换为BigDecimal, null时转换为0
     * @param val 需转换金额
     * @return 转换后金额
     */
    public static BigDecimal filter(String val) {
        return new BigDecimal(StringUtil.isEmpty(val) ? "0" : val);
    }

    /**
     * 将BigDecimal类型转换为BigDecimal, null时转换为0
     * @param val 需转换金额
     * @return 转换后金额
     */
    public static BigDecimal filter(BigDecimal val) {
        return null == val ? new BigDecimal(0) : val;
    }

    public static BigDecimal filter(Integer val) {
        return null == val ? new BigDecimal(0) : new BigDecimal(val);
    }

    public static BigDecimal filter(Double val) {
        return null == val ? new BigDecimal(0) : new BigDecimal(val);
    }

    public static BigDecimal filter(Float val) {
        return null == val ? new BigDecimal(0) : new BigDecimal(val);
    }

    public static BigDecimal filter(Long val) {
        return null == val ? new BigDecimal(0) : new BigDecimal(val);
    }

}
