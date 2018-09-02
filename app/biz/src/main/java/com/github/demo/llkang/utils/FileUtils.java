/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import com.github.demo.llkang.vo.FileVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    public static void removeFile(String fileName) {
        if (fileName == null) {
            return;
        }
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 下载文件
     * 
     * @param request
     * @param response
     * @param fileVo
     * @throws Exception
     */
    public static void fileDownLoad(HttpServletRequest request, HttpServletResponse response, FileVO fileVo) throws Exception {
        //下载文件名
        String downLoadName = fileVo.getFileName();
        //下载文件目录
        String downLoadPath = fileVo.getFilePath();
        //文件名称，文件路径为空返回
        if (StringUtils.isEmpty(downLoadName) || StringUtils.isEmpty(downLoadPath)) {
            return;
        }
        //获取项目目录
        //String ctxPath = request.getServletContext().getRealPath(downLoadPath);
        //获取文件
        File file = new File(downLoadPath + File.separator + downLoadName);
        if (file == null || file.length() == 0) {
            throw new FileNotFoundException("下载的文件不存在");
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            String fileName = file.getName();
            String agent = request.getHeader("USER-AGENT");
            //IE
            if (agent.contains("MSIE") || agent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                //Firefox
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            }
            //设置文件输出类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            //设置输出长度
            response.setHeader("Content-Length", String.valueOf(file.length()));
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(file));
            //输出流
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //关闭流
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

}
