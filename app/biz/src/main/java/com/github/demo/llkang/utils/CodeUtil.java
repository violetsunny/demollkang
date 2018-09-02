package com.github.demo.llkang.utils;

/**
 * 随机生成6位0-9之间的数
 * @author llkang
 *
 */
public class CodeUtil {

	/**
	 * 生存6个数
	 * @return
	 */
	public static String getCode(){
		String[] strs = {"0","1","2","3","4","5","6","7","8","9"};
		String codeNum = "";
		for (int i = 0; i < 6; i++) {
			int num = (int)Math.floor(Math.random() * 10);
			codeNum += strs[num];
		}
		return codeNum;
	}
}
