/**
 * LY.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import com.ly.sof.utils.security.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 签名规则工具类
 * @author jt39175
 * @version $Id: SignRuleUtil.java, v 0.1 2017年7月10日 下午1:57:40 jt39175 Exp $
 */
public class SignRuleUtil {
    

    public static String getSignRule(String requestParam, String secret, String url, String customerSign, String requestId) throws UnsupportedEncodingException {
        // URL
        String urlCode = URLEncoder.encode(url,"utf-8").toLowerCase();
        String requestUrl = replaceBlank(urlCode);
        // TimeStamp
        Long TimeStamp = new Date().getTime();
        // hmac
        StringBuilder sBuilder = new StringBuilder();
        String string = sBuilder.append(customerSign).append(requestParam).append(requestId).append(TimeStamp.toString()).append(requestUrl).append(secret).toString();
        // 对 { CustomerSign}{RequestParam}{RequestId}{TimeStamp}{RequestUrl}{Secret} 字符串加密
        String hmac = MD5Utils.encrypt(string,"utf-8");
        StringBuilder signBuilder = new StringBuilder();
        // Authorization : hmac {customerSign}:{hmac}:{requestId}:{requestTimeStamp}
        String sign = signBuilder.append("hmac").append(" ").append(customerSign).append(":").append(hmac).append(":").append(requestId).append(":").append(TimeStamp.toString()).toString();
        return sign;
    }
    
    /**
     * java去除字符串中的空格、回车、换行符、制表符
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
