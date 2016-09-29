package cn.wxn.example.webapp.utils;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxn on 2016/9/18.
 */
public class StringUtils {

    private static Logger logger = Logger.getLogger(StringUtils.class);

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        if ("".equals(str) || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为null或是为空串（空格也算空串）
     *
     * @param    s    要校验的字符串
     * @return true 是，false 否
     */
    public static boolean isNullOrEmpty(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        if (compareString(s, "null")) {
            return true;
        }
        return false;
    }


    /**
     * 字符串相等 null和空字符串认为相等，忽略字符串前后空格
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        if (null == str1) {
            str1 = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (str1.trim().equals(str2.trim())) {
            return true;
        }
        return false;
    }

    /**
     * @param value 一个可以按照split分割的字符串
     * @param split
     * @return List<String> 分割之后的字符串集合
     */
    public static List<String> split2List(String value, String split) {
        if (value != null && value.length() > 0) {
            List<String> retVal = new ArrayList<String>();
            String[] strArr = value.split(split);
            if (strArr.length > 1) {
                for (String str : strArr) {
                    retVal.add(str);
                }
            } else {
                retVal.add(value);
            }
            return retVal;
        }
        return null;
    }
}
