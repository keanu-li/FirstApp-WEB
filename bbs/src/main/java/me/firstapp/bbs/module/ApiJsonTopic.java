package me.firstapp.bbs.module;

import java.util.Date;

public class ApiJsonTopic extends ApiJsonBase {
	// 与版块关联
	private ApiJsonSection section;

	// 标题
	private String title;

	// 内容
	private String content;

	// 发布时间
	private Date createTime;

	// 修改时间
	private Date modifyTime;

	// 点赞数
	private Long upCounts = 0L;

	// 浏览数
	private Long viewCounts = 0L;

	// 与用户的关联
	private ApiJsonMember member;

	// 回复数
	private Long replyCounts = 0L;

	public ApiJsonSection getSection() {
		return section;
	}

	public void setSection(ApiJsonSection section) {
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

	public ApiJsonMember getMember() {
		return member;
	}

	public void setMember(ApiJsonMember member) {
		this.member = member;
	}

	public Long getReplyCounts() {
		return replyCounts;
	}

	public void setReplyCounts(Long replyCounts) {
		this.replyCounts = replyCounts;
	}

}
