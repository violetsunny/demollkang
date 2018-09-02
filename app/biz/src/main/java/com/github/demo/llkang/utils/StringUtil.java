package com.github.demo.llkang.utils;

/**
 * 字符串工具
 * @author llkang
 *
 */
public class StringUtil {
	private static StringUtil instance = new StringUtil();
	
	private StringUtil(){
		
	}
	
	public static StringUtil getInstance(){
		return instance;
	}
	
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str.trim())||str.length()==0){
			return true;
		}else{
			return false;
		}
	}
}
