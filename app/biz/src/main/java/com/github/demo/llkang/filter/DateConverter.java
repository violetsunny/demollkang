package com.github.demo.llkang.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.github.demo.llkang.utils.StringUtil;


/**
 * 时间转换器
 * 
 * @author kllp0648
 * @version $Id: DateConverter.java, v 0.1 2017年3月17日 上午9:38:53 kllp0648 Exp $
 */
public class DateConverter implements Converter<String, Date>{

	private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","HH:mm:ss"};
	
	public Date convert(String dateStr) {
		if(!StringUtil.isEmpty(dateStr)){
			SimpleDateFormat sdf = new SimpleDateFormat(PATTERNS[1]);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
