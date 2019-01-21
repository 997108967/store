package cn.tedu.store.controller.exception;

/**
 * 文件超出大小限制异常
 * @author soft01
 *
 */
public class FileSizeOutOfLimitException extends FileUploadException {

	private static final long serialVersionUID = -7977834058873112725L;

	public FileSizeOutOfLimitException() {
		super();
	}

	public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileSizeOutOfLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSizeOutOfLimitException(String message) {
		super(message);
	}

	public FileSizeOutOfLimitException(Throwable cause) {
		super(cause);
	}

}
