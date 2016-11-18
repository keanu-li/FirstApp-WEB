package me.firstapp.module.reply;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import me.firstapp.module.base.BaseEntity;
import me.firstapp.module.member.Member;
import me.firstapp.module.topic.Topic;

/**
 * 话题回复模型
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
@Entity
@Table(name = "TOPIC_REPLY")
public class Reply extends BaseEntity {
	private static final long serialVersionUID = -5718526874621956870L;

	// 回复的内容
	@Column(name = "CONTENT", columnDefinition = "text", nullable = false)
	private String content;

	// 创建时间
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	// 关联的话题
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TOPIC_ID", referencedColumnName = "ID", nullable = false)
	private Topic topic;

	// 关联的用户
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "ID", nullable = false)
	private Member member;

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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
