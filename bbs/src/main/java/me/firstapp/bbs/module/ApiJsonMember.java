package me.firstapp.bbs.module;

import java.util.Date;

public class ApiJsonMember extends ApiJsonBase {

	// 用户名
	private String name;

	// 头像地址
	private String avatar;

	// 邮箱
	private String email;

	// 手机号
	private String mobile;

	// 个性签名
	private String signature;

	// 个人主页
	private String url;

	// 注册时间
	private Date registerTime;

	// 状态，0：不正常，1：正常
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
