package cn.wxn.example.webapp.utils;

/**
 * Created by wangxn on 2016/9/18.
 */
public class StringUtils {

    public static boolean isBlank(String str){
        if (str == null) {
            return true;
        }
        if ("".equals(str) || "".equals(str.trim())){
            return true;
        }
        return false;
    }
}
