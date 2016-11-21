package me.firstapp.repository.security.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import me.firstapp.module.security.ApiClient;
import me.firstapp.repository.base.EntityRepositoryImpl;
import me.firstapp.repository.security.ApiClientRepository;

@Repository
public class ApiClientRepositoryImpl extends EntityRepositoryImpl<ApiClient> implements ApiClientRepository {

	@Override
	protected Class<ApiClient> getPersistentClass() {
		return ApiClient.class;
	}

	public ApiClient findApiClientByKey(String apiKey) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(" FROM ApiClient bean WHERE 1=1 ");

		sql.append(" AND bean.apiKey=:apiKey ");
		parameters.put("apiKey", apiKey);

		List<ApiClient> results = super.query(ApiClient.class, sql.toString(), parameters);
		return results.isEmpty() ? null : results.get(0);
	}

}
