package me.firstapp.module.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.firstapp.module.base.BaseEntity;

@Entity
@Table(name = "MEMBER_MEMBER")
public class Member extends BaseEntity {
	private static final long serialVersionUID = 7975950237828106264L;

	// 用户名
	@Column(name = "NAME", unique = true, length = 20, nullable = false)
	private String name;

	// 密码
	@Column(name = "PASSWORD", length = 100, nullable = false)
	private String password;

	// 头像地址
	@Column(name = "AVATAR", length = 200, nullable = true)
	private String avatar;

	// 邮箱
	@Column(name = "EMAIL", length = 100, unique = true, nullable = false)
	private String email;

	// 手机号
	@Column(name = "MOBILE", length = 11, unique = true, nullable = true)
	private String mobile;

	// 个性签名
	@Column(name = "SIGNATURE", length = 100, nullable = true)
	private String signature;

	// 个人主页
	@Column(name = "URL", length = 200, unique = true, nullable = true)
	private String url;

	// 注册时间
	@Column(name = "REGISTER_TIME", columnDefinition = "DATETIME", nullable = false)
	private Date registerTime;

	// 状态，0：不正常，1：正常
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
