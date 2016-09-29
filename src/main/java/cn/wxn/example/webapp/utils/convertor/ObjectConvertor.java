/**
 * @Title:  ObjectConvertor.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 *  YWX CONFIDENTIAL AND TRADE SECRET
 * @author:  
 * @data:    
 */
package cn.wxn.example.webapp.utils.convertor;


/**
 * 把源对象的数据转换到目标对象里
 * @author fangyi
 */
public interface ObjectConvertor {
	
	/**
	 * 把源对象转换成目标对象，并实现同名属性数据复制
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public Object convert(Object source, Class<?> targetClass);
	
	
	public abstract Object convert(Object source, Object target);
	
}
