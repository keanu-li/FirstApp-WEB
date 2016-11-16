package me.firstapp.module.topic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import me.firstapp.module.base.BaseEntity;
import me.firstapp.module.member.Member;

/**
 * 话题模型
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */

@Entity
@Table(name = "TOPIC_TOPIC")
public class Topic extends BaseEntity {

	private static final long serialVersionUID = 8577491675659337535L;
	// 与版块关联
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SECTION_ID", nullable = false, referencedColumnName = "ID")
	private Section section;

	// 标题
	@Column(name = "TITLE", length = 250, unique = true, nullable = false)
	private String title;

	// 内容
	@Column(name = "CONTENT", columnDefinition = "text", nullable = false)
	private String content;

	// 发布时间
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	// 修改时间
	@Column(name = "MODIFY_TIME", nullable = true)
	private Date modifyTime;

	// 点赞数
	@Column(name = "UP_COUNTS", nullable = false)
	private Long upCounts = 0L;

	// 浏览数
	@Column(name = "VIEW_COUNTS", nullable = false)
	private Long viewCounts = 0L;

	// 与用户的关联
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", nullable = false, referencedColumnName = "ID")
	private Member member;

	// 回复数
	@Column(name = "REPLY_COUNTS", nullable = false)
	private Long replyCounts = 0L;

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getUpCounts() {
		return upCounts;
	}

	public void setUpCounts(Long upCounts) {
		this.upCounts = upCounts;
	}

	public Long getViewCounts() {
		return viewCounts;
	}

	public void setViewCounts(Long viewCounts) {
		this.viewCounts = viewCounts;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Long getReplyCounts() {
		return replyCounts;
	}

	public void setReplyCounts(Long replyCounts) {
		this.replyCounts = replyCounts;
	}

}
