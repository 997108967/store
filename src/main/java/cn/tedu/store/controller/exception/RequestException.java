package cn.tedu.store.controller.exception;

/**
 * 控制器异常
 * @author soft01
 *
 */
public class RequestException extends RuntimeException {

	private static final long serialVersionUID = 5468532658359073347L;

	public RequestException() {
		super();
	}

	public RequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestException(String message) {
		super(message);
	}

	public RequestException(Throwable cause) {
		super(cause);
	}
	
}
