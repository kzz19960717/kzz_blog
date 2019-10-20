package com.kzz.blog.service.ex;

public class ArticleInsertException extends ServiceException {
    private static final long serialVersionUID = -819590722908746381L;

    public ArticleInsertException() {
    }

    public ArticleInsertException(String message) {
        super(message);
    }

    public ArticleInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleInsertException(Throwable cause) {
        super(cause);
    }

    public ArticleInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
