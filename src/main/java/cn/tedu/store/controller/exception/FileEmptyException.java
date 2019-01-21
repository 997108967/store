package cn.tedu.store.controller.exception;

/**
 * 文件为空异常
 * @author soft01
 *
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 4129473556582473255L;

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
