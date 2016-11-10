package me.firstapp.module.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 实体模型基类
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Entity<Long> {
	private static final long serialVersionUID = 3391281495908400659L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue
	protected Long id;

	// 创建时间
	@Column(name = "CREATED_AT", columnDefinition = "DATETIME", nullable = false)
	protected Date createdAt = new Date();

	// 更新时间
	@Column(name = "UPDATED_AT", columnDefinition = "DATETIME")
	protected Date updatedAt = new Date();

	// 插入者
	@Column(name = "INSERTER", nullable = false)
	protected Long inserter;

	// 更新者
	@Column(name = "UPDATER")
	protected Long updater;

	public BaseEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getInserter() {
		return inserter;
	}

	public void setInserter(Long inserter) {
		this.inserter = inserter;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

}
