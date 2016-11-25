package me.firstapp.common.repository.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ClassUtils;

import me.firstapp.common.module.BaseEntity;
import me.firstapp.common.repository.BaseRepository;
import me.firstapp.common.repository.EntityRepository;
import me.firstapp.common.utils.Page;

@Repository
public abstract class EntityRepositoryImpl<T> implements EntityRepository<T> {
	protected abstract Class<T> getPersistentClass();

	@Autowired
	protected BaseRepository baseRepository;

	public void save(T obj) {
		if (obj instanceof BaseEntity) {
			BaseEntity entity = (BaseEntity) obj;
			if (entity.getInsertedAt() == null) {
				entity.setInsertedAt(new Date());
			}
		}
		baseRepository.save(obj);
	}

	public void refresh(T obj) {
		baseRepository.refresh(obj);
	}

	public void update(T obj) {
		Class<?> clazz = ClassUtils.getUserClass(obj);
		if (BaseEntity.class.isAssignableFrom(clazz)) {
			BaseEntity entity = (BaseEntity) obj;
			entity.setUpdatedAt(new Date());
//			BaseEntity persist = (BaseEntity) baseRepository.getById(ClassUtils.getUserClass(obj.getClass()),
//					entity.getId());
//			entity.setInsertedAt(persist.getInsertedAt());
		}
		baseRepository.update(obj);
	}

	public void delete(Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(id);
		}
	}

	public void deleteById(Serializable id) {
		baseRepository.deleteById(getPersistentClass(), id);
	}

	public Object getById(Serializable id) {
		return baseRepository.getById(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> entities = (List<T>) baseRepository.getAll(getPersistentClass());
		return entities;
	}

	@SuppressWarnings("unchecked")
	public <N> List<N> query(Class<N> clazz, String sql, Map<String, Object> parameters) {
		List<N> entities = (List<N>) baseRepository.query(sql, parameters);
		return entities;
	}

	@SuppressWarnings("unchecked")
	public <N> List<N> queryForLimit(Class<N> clazz, String sql, Map<String, Object> parameters, int limitCount) {
		List<N> entities = (List<N>) baseRepository.queryForLimit(sql, parameters, limitCount);
		return entities;
	}

	public <N> List<N> queryForPageList(Class<N> clazz, String sql, Map<String, Object> parameters, int pageNo,
			int pageSize) {
		sql = sql.trim();
		int fromIndex = sql.toUpperCase().indexOf("FROM");
		if (fromIndex == -1) {
			throw new RuntimeException(" Sql error ! ");
		}
		List<N> entities = (List<N>) baseRepository.queryForData(clazz, sql, parameters, pageSize, pageNo);
		return entities;
	}

	public <N> Page<N> queryForPage(Class<N> clazz, String sql, Map<String, Object> parameters, int pageNo,
			int pageSize) {
		sql = sql.trim();

		int fromIndex = sql.toUpperCase().indexOf("FROM");

		if (fromIndex == -1) {
			throw new RuntimeException(" Sql error ! ");
		}

		String countSql = sql.substring(fromIndex);
		countSql = "SELECT COUNT(*) " + countSql;

		Page<N> page = new Page<N>(pageSize, pageNo);
		int entityCount = baseRepository.queryForCount(countSql, parameters);
		List<N> entities = baseRepository.queryForData(clazz, sql, parameters, pageSize, pageNo);

		page.setEntityCount(entityCount);
		page.setEntities(entities);

		return page;
	}

	public <N> Page<N> queryForPage(Class<N> clazz, String selectSql, String countSql, Map<String, Object> parameters,
			int pageNo, int pageSize) {
		selectSql = selectSql.trim();
		countSql = countSql.trim();

		Page<N> page = new Page<N>(pageSize, pageNo);
		int entityCount = baseRepository.queryForCount(countSql, parameters);
		List<N> entities = baseRepository.queryForData(clazz, selectSql, parameters, pageSize, pageNo);

		page.setEntityCount(entityCount);
		page.setEntities(entities);

		return page;
	}

	@SuppressWarnings("unchecked")
	public <N> N getFristData(Class<N> clazz, String sql, Map<String, Object> parameters) {
		List<N> entities = (List<N>) baseRepository.queryForLimit(sql, parameters, 1);
		if (entities.size() == 0) {
			return null;
		}
		N obj = entities.get(0);
		return obj;
	}

	public List<?> createSQLQuery(String sql) {
		return baseRepository.createSQLQuery(sql);
	}

	public int createSQLUpdate(String sql) {
		return baseRepository.createSQLUpdate(sql);
	}
}
