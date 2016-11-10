package exception;

/**
 * 异常状态类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public class Status {

	public static final String STATUS_OK = "1";
	public static final String STATUS_ERROR = "0";

	public static Status COMMON_STATUS_OK = new Status(STATUS_OK, "访问成功");
	public static Status COMMON_STATUS_ERROR = new Status(STATUS_ERROR, "访问错误");

	public static Status COMMON_STATUS_ERROR_PARAMETER = new Status("0001", "参数错误");
	public static Status COMMON_STATUS_ERROR_PROGRAM = new Status("0002", "程序异常");

	private String code;
	private String msg;

	protected Status(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return "1".equals(code) ? msg : new StringBuffer(msg).append("[错误码：").append(code).append("]").toString();
	}

	public static Status getStatusWithArgs(Status status, Object... args) {
		return new Status(status.getCode(), String.format(status.getMsg(), args));
	}

}
