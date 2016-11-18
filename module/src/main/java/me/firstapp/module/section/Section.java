package me.firstapp.module.section;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.firstapp.module.base.BaseEntity;

/**
 * 板块模型
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
@Entity
@Table(name = "TOPIC_SECTION")
public class Section extends BaseEntity {
	private static final long serialVersionUID = -6666677725869983110L;

	// 板块名称
	@Column(name = "NAME", length = 10, nullable = false)
	private String name;

	// 板块描述
	@Column(name = "DESCRIPTION", length = 200, nullable = true)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
