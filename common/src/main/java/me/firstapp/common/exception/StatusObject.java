package me.firstapp.common.exception;

import java.io.Serializable;

/**
 * 状态对象
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class StatusObject implements Serializable {

	private static final long serialVersionUID = 1L;

	// 状态码
	private String code;

	// 状态信息
	private String msg;

	public StatusObject(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
