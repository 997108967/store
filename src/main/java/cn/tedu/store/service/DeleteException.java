package cn.tedu.store.service;

import cn.tedu.store.service.exception.ServiceException;

/**
 * 删除时，数据库出现未知错误
 * @author soft01
 *
 */
public class DeleteException extends ServiceException {

	private static final long serialVersionUID = -3965479671335316923L;

	public DeleteException() {
		super();
	}

	public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Throwable cause) {
		super(cause);
	}

}
