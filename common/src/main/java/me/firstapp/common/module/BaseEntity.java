package me.firstapp.common.module;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 实体模型基类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
@JsonIgnoreProperties(value = { "insertedAt", "updatedAt", "password", "hibernateLazyInitializer", "handler" }) // 添加hibernateLazyInitializer和handler可解决SerializationFeature.FAIL_ON_EMPTY_BEANS异常
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Entity<Long> {
	private static final long serialVersionUID = 3391281495908400659L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue
	protected Long id;

	// 创建时间
	@Column(name = "INSERTED_AT", columnDefinition = "DATETIME", updatable = false, nullable = false)
	protected Date insertedAt = new Date();

	// 更新时间
	@Column(name = "UPDATED_AT", columnDefinition = "DATETIME")
	protected Date updatedAt = new Date();

	public BaseEntity() {

	}

	public Date getInsertedAt() {
		return insertedAt;
	}

	public void setInsertedAt(Date insertedAt) {
		this.insertedAt = insertedAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
