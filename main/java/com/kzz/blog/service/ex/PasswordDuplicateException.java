package com.kzz.blog.service.ex;

public class PasswordDuplicateException extends ServiceException{

    /**
     * 密码错误
     */
    private static final long serialVersionUID = 4492649372530808510L;
    public PasswordDuplicateException() {
        super();
    }

    public PasswordDuplicateException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PasswordDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordDuplicateException(String message) {
        super(message);
    }

    public PasswordDuplicateException(Throwable cause) {
        super(cause);
    }

}