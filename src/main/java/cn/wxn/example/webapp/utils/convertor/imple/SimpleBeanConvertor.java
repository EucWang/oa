/**
 * @Title:  SimpleBeanConvertor.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 *  YWX CONFIDENTIAL AND TRADE SECRET
 * @author:  
 * @data:    
 */
package cn.wxn.example.webapp.utils.convertor.imple;

import cn.wxn.example.webapp.utils.convertor.AbstractObjectConvertor;
import cn.wxn.example.webapp.exception.ConvertException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

/**
 * 简单对象数据拷贝,支持基本类型和包装类型
 * @author fangyi
 */
@Component("simpleBeanConvertor")
public class SimpleBeanConvertor extends AbstractObjectConvertor {
	
	SimpleBeanConvertor(){
		
	}
	
	
	/**
	 * 复制不同对象相同名称的属性,支持基本类型和包装类型
	 * @param source
	 * @param target
	 * @return
	 */
	public Object convert(Object source, Object target) {
		if (source == null || target==null){
			return null;
		}
		
		try {
			PropertyUtils.copyProperties(target, source);
		} catch (Exception e) {
			log.error(e, e);
			String s = new StringBuffer()
			.append("转换对象")
			.append(source.getClass().getName())
			.append("到")
			.append(target.getClass().getName())
			.append("出错").toString();
			throw new ConvertException(s);
		}
		
		return target;
	}
	
}
