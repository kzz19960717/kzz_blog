package com.kzz.blog.controller.ex;

/**
 * 上传的文件为空，例如没有选择上传的文件，或选择的文件是0字节的
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 5216328143694529891L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}

}
