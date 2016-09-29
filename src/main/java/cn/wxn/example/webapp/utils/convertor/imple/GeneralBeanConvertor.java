/**
 * @Title: GeneralBeanConvertor.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 * YWX CONFIDENTIAL AND TRADE SECRET
 * @author:
 * @data:
 */
package cn.wxn.example.webapp.utils.convertor.imple;

import cn.wxn.example.webapp.exception.ConvertException;
import cn.wxn.example.webapp.utils.StringUtils;
import cn.wxn.example.webapp.utils.convertor.AbstractObjectConvertor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 *  Vo和domain 对象间的数据复制
 *  支持类型String,Integer,Long,Short,Double, java.util.Date
 * @author fangyi
 */
@Component("generalBeanConvertor")
public class GeneralBeanConvertor extends AbstractObjectConvertor {

    /**
     *  Vo和domain 对象间的数据复制
     * @param source 日期请使用java.util.Date
     * @param target
     * @return
     */
    @SuppressWarnings({"rawtypes"})
    public Object convert(Object source, Object target) {
        if (source == null || target == null) {
            return null;
        }

        Set sourcePropertys;
        Set resultPropertys;
        try {
            sourcePropertys = BeanUtils.describe(source).keySet();
            resultPropertys = BeanUtils.describe(target).keySet();
        } catch (Exception e) {
            log.error(e, e);
            throw new ConvertException(e.getMessage());
        }

        for (Iterator iterator = sourcePropertys.iterator(); iterator.hasNext(); ) {
            String propertyName = iterator.next().toString();

            try {
                if (propertyName.equals("class")) {
                    continue;
                }
                if (!resultPropertys.contains(propertyName)) {
                    continue;
                }

                Object sourceValue = PropertyUtils.getNestedProperty(source, propertyName);
                if (sourceValue == null) {
                    continue;
                }

                String sourceType = PropertyUtils.getPropertyDescriptor(source, propertyName).getPropertyType().getName();
                String targetType = PropertyUtils.getPropertyDescriptor(target, propertyName).getPropertyType().getName();
                Object targetValue = convertTargetValue(sourceType, targetType, sourceValue);

                PropertyUtils.setNestedProperty(target, propertyName, targetValue);
            } catch (Exception e) {
                log.error(e, e);
                throw new ConvertException("复制数据到属性：" + target.getClass().getName() + "." + propertyName + "出错");
            }
        }

        return target;
    }

    /**
     * 复制属性到目标对象
     * @param sourceType
     * @param targetType
     * @param sourceValue
     * @return
     * @throws ParseException
     */
    private Object convertTargetValue(String sourceType, String targetType, Object sourceValue) throws ParseException {

        if (sourceValue == null) {
            return null;
        }
        if (sourceType.equals("java.lang.String") && StringUtils.isNullOrEmpty((String) sourceValue)) {
            return null;
        }

        if (sourceType.equals(targetType)) {
            if (sourceType.equals("java.lang.String")) {
                return ((String) sourceValue).trim();
            }

            return sourceValue;
        }

        if (targetType.equals("java.lang.String") &&
                (sourceType.equals("java.lang.Short")
                        || sourceType.equals("java.lang.Integer")
                        || sourceType.equals("java.lang.Long"))) {
            return sourceValue.toString();
        }

        if (targetType.equals("java.lang.String") &&
                (sourceType.equals("java.lang.Float")
                        || sourceType.equals("java.lang.Double"))) {
            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(fraction);
            return format.format((Double) sourceValue);
        }

        if (targetType.equals("java.lang.String") &&
                (sourceType.equals("java.lang.Float")
                        || sourceType.equals("java.lang.Double"))) {
            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(fraction);
            return format.format((Double) sourceValue);
        }

        if (targetType.equals("java.lang.String") &&
                sourceType.equals("java.math.BigDecimal")) {
            BigDecimal decimal = (BigDecimal) sourceValue;
            decimal = decimal.setScale(fraction);
            return decimal.toString();
        }

        if (targetType.equals("java.lang.String") &&
                (sourceType.equals("java.util.Date")
                        || sourceType.equals("java.sql.Date")
                        || sourceType.equals("java.sql.Timestamp"))) {

            return new SimpleDateFormat(dateFormat).format((Date) sourceValue);
        }


        if (sourceType.equals("java.lang.String") && targetType.equals("java.lang.Short")) {
            return new Short(Short.parseShort((String) sourceValue));
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.lang.Integer")) {
            return new Integer(Integer.parseInt((String) sourceValue));
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.lang.Long")) {
            return new Long(Long.parseLong((String) sourceValue));
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.lang.Float")) {
            return new Float(Float.parseFloat((String) sourceValue));
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.lang.Double")) {
            return new Double(Double.parseDouble((String) sourceValue));
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.math.BigDecimal")) {
            return new BigDecimal((String) sourceValue);
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.util.Date")) {
            if (!StringUtils.isNullOrEmpty((String) sourceValue)) {
                return new SimpleDateFormat(dateFormat).parse((String) sourceValue);
            }
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.sql.Date")) {
            if (!StringUtils.isNullOrEmpty((String) sourceValue)) {
                return java.sql.Date.valueOf((String) sourceValue);
            }
        }

        if (sourceType.equals("java.lang.String") && targetType.equals("java.sql.Timestamp")) {
            if (!StringUtils.isNullOrEmpty((String) sourceValue)) {
                return java.sql.Timestamp.valueOf((String) sourceValue);
            }
        }

        return null;

    }


}
