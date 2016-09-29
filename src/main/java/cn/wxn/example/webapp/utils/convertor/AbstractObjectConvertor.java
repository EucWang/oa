/**
 * @Title:  AbstractObjectConvertor.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 *  YWX CONFIDENTIAL AND TRADE SECRET
 * @author:  
 * @data:    
 */
package cn.wxn.example.webapp.utils.convertor;

import java.util.ArrayList;
import java.util.List;

import cn.wxn.example.webapp.exception.ConvertException;
import org.apache.log4j.Logger;

public abstract class AbstractObjectConvertor implements ObjectConvertor {
	protected String dateFormat = "yyyy-MM-dd";
	protected    int fraction = 2;
	protected Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * 将List中源对象转换成目标对象,同名属性数据复制
	 * @param source 
	 * @param targetClass
	 * @return 返回目标对象List
	 */
	public List<Class<?>> convert (List<Object> source, Class<?> targetClass){
		if(source==null || targetClass == null){
			return null;
		}
		
		List<Class<?>> resultList = new ArrayList<Class<?>>();
		
		for(Object obj : source){
			resultList.add((Class<?>)convert(obj,targetClass));
		}
		
		return resultList;
	}
	
	
	/**
	 * 把源对象转换成目标对象，并实现同名属性数据复制
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public Object convert(Object source, Class<?> targetClass){
		try {
			return convert(source, targetClass.newInstance());
		} catch (Exception e) {
			log.error(e, e);
			throw new ConvertException("cannot new instance : " + targetClass.getName());
		} 
	}
	
	
	public abstract Object convert(Object source, Object target);
	
	
	/**
	 * 获取转换时日期格式
	 * @return
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * 设置转换时日期格式
	 * @param dateFormat
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}


	 
	
	
}
