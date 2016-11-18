package me.firstapp.common.json;

import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import me.firstapp.common.exception.StatusObject;

@JsonIgnoreProperties(value = { "serialVersionUID" })
public class AbstractJsonObject implements java.io.Serializable {

	private static final long serialVersionUID = -9220795754053729481L;

	// 异常码
	private String code;

	// 异常信息
	private String msg;

	private Date time = Calendar.getInstance().getTime();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setContent(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setStatusObject(StatusObject statusObject) {
		this.code = statusObject.getCode();
		this.msg = statusObject.getMsg();
	}
}
