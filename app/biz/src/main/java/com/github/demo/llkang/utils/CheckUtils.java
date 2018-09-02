package com.github.demo.llkang.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {

    /**
     *  判断是否是数字
     * @param time
     * @return
     */
    public static boolean isnumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     *  判断是否是几位数字
     * @param str
     * @param num
     * @return
     */
    public static boolean isnumber(String str, int num) {
        Pattern pattern = Pattern.compile("[0-9]{" + num + "}$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    /**
     * 判断是否是金额类型（小数点最多几位小数）
     */
    public static boolean isAmount(String amount, int num) {
        Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)|(([1-9]+[0-9]*|0)(\\.[\\d]{0," + num + "}))");
        Matcher match = pattern.matcher(amount);
        return match.matches();
    }

    public static void main(String[] args) {
        boolean result = isAmount("0.01", 2);
        System.out.println(result);
    }
}
