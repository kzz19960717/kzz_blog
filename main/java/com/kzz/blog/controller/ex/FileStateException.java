package com.kzz.blog.controller.ex;

/**
 * 上传的文件的状态异常，例如文件已经被移动或删除，导致文件不可以被访问
 */
public class FileStateException extends FileUploadException {

	private static final long serialVersionUID = -7150153238399934786L;

	public FileStateException() {
		super();
	}

	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStateException(String message) {
		super(message);
	}

	public FileStateException(Throwable cause) {
		super(cause);
	}

}
