package me.firstapp.common.exception;

/**
 * service异常类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 2960966760116311123L;

	private String code;

	private StatusObject statusObject;

	public ServiceException(StatusObject statusObject) {
		super(statusObject.getMsg());
		this.code = statusObject.getCode();
		this.statusObject = statusObject;
	}

	public ServiceException(StatusObject statusObject, Object... args) {
		this.statusObject = new StatusObject(statusObject.getCode(), String.format(statusObject.getMsg(), args));
	}

	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
		this.statusObject = new StatusObject(code, message);
	}

	public ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.statusObject = new StatusObject(code, message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StatusObject getStatusObject() {
		return statusObject;
	}

	public void setStatusObject(StatusObject statusObject) {
		this.statusObject = statusObject;
	}

}
