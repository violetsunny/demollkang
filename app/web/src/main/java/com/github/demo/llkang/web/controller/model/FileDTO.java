package com.github.demo.llkang.web.controller.model;

/**
 * 
 * 
 * @author lwf47686
 * @version $Id: FileDTO.java, v 0.1 2017年4月18日 下午1:55:03 lwf47686 Exp $
 */
public class FileDTO {
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件key
	 */
	private String fileKey;
	/**
	 * 文件url
	 */
	private String fileUrl;
	
	public String getFileKey() {
		return fileKey;
	}
	
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
