/**
 * LY.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.github.demo.llkang.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lfy41980
 * @version $Id: PdfUtils, v 0.1 2017/04/18 15:52 jzx41637 Exp $
 */
public class PdfUtils {

    /**
     * 新建document对象
     */
    public static Document createDocument(String path, String fileName) {
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        //新建document对象
        Document document = new Document(rectPageSize, 50, 50, 10, 50);
        return document;
    }

    /**
     * 打开文件
     * 
     * @param document
     */
    public static void openDocument(Document document) {
        //打开文件
        document.open();
    }

    /**
     * 创建 PdfWriter 对象
     * @throws DocumentException 
     * @throws IOException 
     */
    public static PdfWriter createPdfWriter(Document document, String path, String fileName) throws DocumentException, IOException {
        //判断文保存pdf的文件夹是否存在，如果不存在则新建一个
        File tempFile = new File(path);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        //创建文件
        File file = new File(path + fileName);
        file.createNewFile();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        return writer;
    }

    /**
     * 创建字体
     * @throws IOException 
     * @throws DocumentException 
     */
    public static Font createBaseFont() throws DocumentException, IOException {
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.NORMAL);
        return font;
    }

    /**
     * 创建列表标题字体
     * @throws IOException 
     * @throws DocumentException 
     */
    public static Font createTitileFont() throws DocumentException, IOException {
        //字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfChinese, 7, Font.BOLD);
        return font;
    }

    public static void main(String[] args) {
        try {
            String path = "D:/apache-tomcat/webapps/tmcopplatform/down_pdf/";
            String fileName = System.currentTimeMillis() + ".pdf";
            String temp = path + fileName;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File pdf = new File(temp);
        } catch (Exception e) {

            System.out.println(e);
        }

    }

}
