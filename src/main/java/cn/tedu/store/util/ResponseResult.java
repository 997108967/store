package cn.tedu.store.util;

import java.io.Serializable;

/**
 * 服务器端向客户端响应的结果类型
 * @author soft01
 *
 * @param <T> 向客户端响应的数据类型
 */
public class ResponseResult<T> implements Serializable{

	private static final long serialVersionUID = 1525615804802449085L;
	
	private Integer state;
	private String message;
	private T data;
	
	
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}
	

	public ResponseResult(Integer state, String message) {
		this(state);
		setMessage(message);
	}
	
	public ResponseResult(Integer state, Exception e) {
		this(state,e.getMessage());
	}
	
	public ResponseResult(Integer state, T date) {
		this(state);
		setData(date);
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T date) {
		this.data = date;
	}
}
