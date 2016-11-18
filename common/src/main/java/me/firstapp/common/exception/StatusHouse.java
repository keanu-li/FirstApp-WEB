package me.firstapp.common.exception;

/**
 * 状态库
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class StatusHouse {

	public static StatusObject COMMON_STATUS_OK = new StatusObject("ok", "成功！");
	public static StatusObject COMMON_STATUS_ERROR = new StatusObject("error", "发生未知错误！");
	public static StatusObject COMMON_STATUS_PARAM_REQUIRED_ERROR = new StatusObject("p000", "参数[%s]为必填项");
	public static StatusObject COMMON_STATUS_REQUEST_TIMEOUT = new StatusObject("t000", "服务请求超时!");
	// ====================用户相关异常状态===================================================
	public static StatusObject MEMBER_NOT_FOUND = new StatusObject("m001", "用户不存在");
	// ====================板块相关异常状态===================================================
	public static StatusObject SECTION_NOT_FOUND = new StatusObject("s001", "板块不存在");
}
