package com.github.demo.llkang.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.zefer.pd4ml.PD4ML;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.Map;

public class FreemarkerPdfUtils {
	
	private static final String TEMPLATE = "resource/templates/";//模板存储路径

	private static final String HTMLNAME = "pdfDemo";//html文件名
	
	private static final String FTLPATH = File.separator + "WEB-INF" + File.separator+"templates";
	
	protected static int topValue       = 10;
    protected static int leftValue      = 20;
    protected static int rightValue     = 10;
    protected static int bottomValue    = 10;
    protected static int userSpaceWidth = 700;
	
	public static void contractHandler(String path,String ftlPath,String templateName,String pdfName,Map<String, Object> paramMap,HttpServletResponse response) throws Exception{
		// 获取本地模板存储路径、合同文件存储路径
		String templatePath = ftlPath;
		String contractPath = path;
		// 组装html和pdf合同的全路径URL
		String localHtmlUrl = contractPath + HTMLNAME + ".html";
		String localPdfPath = contractPath + "/";
		// 判断本地路径是否存在如果不存在则创建
		File localFile = new File(localPdfPath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		String localPdfUrl = localFile + "/" + pdfName + ".pdf";
		templateName=templateName+".ftl";
		htmHandler(templatePath, templateName, localHtmlUrl, paramMap);// 生成html合同
//		pdfHandler(localHtmlUrl, localPdfUrl,ftlPath);// 根据html合同生成pdf合同
		doConversion(ftlPath,localHtmlUrl,localPdfUrl,response);
		deleteFile(localHtmlUrl);// 删除html格式合同
		
		System.out.println("PDF生成成功");
	}
	
	public static void doConversion(String ftlPath,String url, String outputPath,HttpServletResponse response) throws InvalidParameterException, IOException {
		File output = new File(outputPath);
		java.io.FileOutputStream fos = new java.io.FileOutputStream(output);
		PD4ML pd4ml = new PD4ML();
		pd4ml.setPageSize(new Dimension(650, 800));
		//        pd4ml.setHtmlWidth(userSpaceWidth); // set frame width of "virtual web browser"
		//        new Dimension().s
		//        pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A4.));pd4ml.setPageSize(new Dimensi);
		//        pd4ml.setPageInsetsMM(new Insets(topValue, leftValue, bottomValue, rightValue));
		//        pd4ml.addStyle("BODY {margin: 0}", true);
		String fontsPath = ftlPath.replace("templates", "fonts");
		try {
			pd4ml.useTTF(fontsPath.substring(0, fontsPath.length()-1), true);
			pd4ml.enableDebugInfo();
			pd4ml.overrideDocumentEncoding("UTF-8");//强制指定编码
			pd4ml.render(new URL("file:///"+url), fos); // actual document conversion from URL to file  字节流

			/*String content = org.apache.commons.io.FileUtils.readFileToString(new File(url),"utf-8");
			StringReader reader = new StringReader(content);//字符流
			pd4ml.render(reader,fos);*/

		} catch (Exception e) {

		}finally {
			fos.close();
		}

    }
	
	/**
	 * 生成html格式合同
	 */
	private static void htmHandler(String templatePath, String templateName,String htmUrl, Map<String, Object> paramMap) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File(templatePath));

		Template template = cfg.getTemplate(templateName);

		File outHtmFile = new File(htmUrl);

		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(outHtmFile),"UTF-8"));
		template.process(paramMap, out);

		out.close();
	}
	
	/**
	 * 生成pdf格式合同
	 */
//	private static void pdfHandler(String htmUrl, String pdfUrl,String ftlPath)
//			throws DocumentException, IOException {
//		File htmFile = new File(htmUrl);
//		File pdfFile = new File(pdfUrl);
//
//		String url = htmFile.toURI().toURL().toString();
//
//		OutputStream os = new FileOutputStream(pdfFile);
//
//		org.xhtmlrenderer.pdf.ITextRenderer renderer = new ITextRenderer();
//		renderer.setDocument(url);
//
//		org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer.getFontResolver();
//		// 解决中文支持问题
//		fontResolver.addFont(ftlPath+"simsun.ttc",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//
//		renderer.layout();
//		renderer.createPDF(os);
//		os.close();
//	}
	
	/**
	 * 删除文件
	 */
	private static void deleteFile(String fileUrl) {
		File file = new File(fileUrl);
		file.delete();
	}

}
