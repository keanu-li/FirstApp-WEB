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
	public static StatusObject COMMON_STATUS_PARAM_REQUIRED_ERROR = new StatusObject("0001", "参数[%s]为必填项");
	public static StatusObject COMMON_STATUS_REQUEST_TIMEOUT = new StatusObject("0002", "服务请求超时!");
	public static StatusObject ACCOUNT_VERIFY_FAILED = new StatusObject("0003", "未授权的请求,错误码：(a000)");
	public static StatusObject VERIFY_CODE_WRONG = new StatusObject("0004", "验证码不正确");
	public static StatusObject EMAIL_FORMAT_WRONG = new StatusObject("0005", "邮箱格式不正确");
	public static StatusObject MOBILE_FORMAT_WRONG = new StatusObject("0006", "手机号格式不正确");
	// ====================用户相关异常状态===================================================
	public static StatusObject MEMBER_NOT_FOUND = new StatusObject("1001", "用户不存在");
	public static StatusObject MOBILE_IS_EXIST = new StatusObject("1002", "手机号已被注册");
	public static StatusObject EMAIL_IS_EXIST = new StatusObject("1003", "邮箱已被注册");
	public static StatusObject NAME_IS_EXIST = new StatusObject("1004", "用户名已存在");
	// ====================板块相关异常状态===================================================
	public static StatusObject SECTION_NOT_FOUND = new StatusObject("2001", "板块不存在");
}
