/**
 * @Title: ConvertException.java
 * @Copyright (C) 2014-2015 by ywx.co.,ltd.All Rights Reserved.
 * YWX CONFIDENTIAL AND TRADE SECRET
 * @author:
 * @data:
 */
package cn.wxn.example.webapp.exception;

/**
 * 对象转换异常
 * @author fangyi
 */
public class ConvertException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConvertException(String msg) {
        super(msg);
    }

    public ConvertException(Throwable t) {
        super(t);
    }
}
