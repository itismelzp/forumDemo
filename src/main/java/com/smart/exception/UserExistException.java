package com.smart.exception;

/**
 * Created by lizhiping03 on 2018/9/12.
 */
public class UserExistException extends Exception {
    public UserExistException(String errorMsg) {
        super(errorMsg);
    }
}
