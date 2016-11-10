package exception;

/**
 * api异常类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -3459455225253612032L;
	private Status status;

	public ApiException(Status status) {
		super();
		this.status = status;
	}

	public ApiException(Status status, Object... args) {
		super();
		this.status = new Status(status.getCode(), String.format(status.getMsg(), args));
	}

	public void print() {
		System.out.print(toString());
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
