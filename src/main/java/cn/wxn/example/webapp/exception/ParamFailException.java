package cn.wxn.example.webapp.exception;

/**
 * Created by wangxn on 2016/9/18.
 */
public class ParamFailException extends RuntimeException {
    public ParamFailException() {
    }

    public ParamFailException(String message) {
        super(message);
    }

    public ParamFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamFailException(Throwable cause) {
        super(cause);
    }
}
