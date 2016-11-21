package me.firstapp.module.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import me.firstapp.module.base.BaseEntity;

@Entity
@Table(name = "SECURITY_API_CLIENT")
public class ApiClient extends BaseEntity {
	private static final long serialVersionUID = -219056940687962511L;

	@Column(name = "API_KEY", unique = true, length = 30, nullable = false)
	private String apiKey;

	@Column(name = "API_SECRET", length = 100, nullable = false)
	private String apiSecret;

	//描述
	@Column(name = "DESCRIPTION", length = 240, nullable = true)
	private String description;

	//类型，0代表web,1代表app
	@Column(name = "SOURCE_TYPE", nullable = false)
	private Integer sourceType;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

}
