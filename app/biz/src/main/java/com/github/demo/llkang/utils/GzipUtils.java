/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 数据压缩/解压工具类
 * @author xy23087
 * @version $Id: GzipUtils.java, v 0.1 2016年9月1日 下午2:46:57 xy23087 Exp $
 */
public class GzipUtils {
    /**
     * 通过GZIP对数据压缩
     * @param xml
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream compress(String xml, ByteArrayOutputStream out) throws IOException {
        // 请求参数的数据压缩
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        if (xml != null && !"".equals(xml)) {
            gzip.write(xml.getBytes("UTF-8"));
        }
        gzip.close();
        return out;
    }

    /**
     * 通过GZIP对返回流进行解压
     * @param postMethod
     * @return
     * @throws IOException
     */
    public static String deCompress(PostMethod postMethod, ByteArrayOutputStream out) throws IOException {
        // 返回结果的数据解压
        InputStream is = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), "UTF-8"));
        StringBuffer respStr = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null) {
            respStr.append(line);
        }
        out.flush();
        out.close();
        is.close();
        return respStr.toString();
    }

    /**
     * 对参数进行压缩，再转码
     * @param str
     * @return
     * @throws IOException
     */
    public static String compression(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return "";
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gunZip = new GZIPOutputStream(out);
        try {
            gunZip.write(str.getBytes());
        } finally {
            gunZip.close();
            out.close();
        }
        /*BASE64Decoder tBase64Decoder = new BASE64Decoder();
        byte[] t = tBase64Decoder.decodeBuffer(out.toString("UTF-8"));*/

        //编码
        String encodedText = Base64.getEncoder().encodeToString(out.toByteArray());//要用toByteArray()  这是byte流，和 String.getBytes()有区别
        return encodedText;
    }

    /**
     * 对参数先进行转码,再解压
     * @param str
     * @return
     * @throws IOException
     */
    public static String unZip(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return "";
        }
        byte[] t = Base64.getDecoder().decode(str);//转码
        ByteArrayInputStream bis = new ByteArrayInputStream(t);
        GZIPInputStream gzip = new GZIPInputStream(bis);//解压
        byte[] buf = new byte[2048];
        int num = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((num = gzip.read(buf, 0, buf.length)) != -1) {
            bos.write(buf, 0, num);
        }
        gzip.close();
        bis.close();
        bos.flush();
        bos.close();
        return bos.toString();
    }

    public static void main(String[] args) throws Exception{
        //System.out.print(compression("abcdefghigklmnopqrstuvwxyz0123456789"));
        System.out.print("------------------------");
        String res = unZip(
                "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");
        //System.out.print(res);
        System.out.print("------------------------");
        String resj = AESUtils.decrypt(res,"64e24362dd2424e1");
        System.out.print(resj);
    }
}
