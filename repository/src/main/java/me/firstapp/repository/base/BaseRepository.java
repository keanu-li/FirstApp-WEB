package me.firstapp.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Dao仓促层基础接口
 * 
 * @Created by keanu.
 * @Copyright (c) 2016, All Rights Reserved.
 * @website http://firstapp.me
 */
public interface BaseRepository {

	/**
	 * 保存
	 * 
	 * @param obj
	 */
	public void save(Object obj);

	/**
	 * 刷新
	 * 
	 * @param obj
	 */
	public void refresh(Object obj);

	/**
	 * 删除
	 * 
	 * @param obj
	 */
	public void delete(Object obj);

	/**
	 * 批量删除
	 * 
	 * @param cls
	 * @param ids
	 */
	public void delete(Class<?> cls, Serializable[] ids);

	/**
	 * 根据ID删除
	 * 
	 * @param cls
	 * @param Id
	 */
	public void deleteById(Class<?> cls, Serializable id);

	/**
	 * 更新
	 * 
	 * @param obj
	 */
	public void update(Object obj);

	/**
	 * 获得所有记录
	 * 
	 * @param cls
	 * @return
	 */
	public List<?> getAll(Class<?> cls);

	/**
	 * 按ID号查询
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public Object getById(Class<?> cls, Serializable id);

	/**
	 * 原始查询
	 * 
	 * @param sql
	 * @return
	 */
	public List<?> query(String sql);

	/**
	 * 带条件查询
	 * 
	 * @param sql
	 * @param parmeters
	 * @return
	 */
	public List<?> query(String sql, Map<String, Object> parmeters);

	/**
	 * count聚合查询
	 * 
	 * @param sql
	 * @param parmeters
	 */
	public int queryForCount(final String sql, final Map<String, Object> parmeters);

	/**
	 * 查询limitCount条数据
	 * 
	 * @param sql
	 * @param parmeters
	 * @param limitCount
	 * @return
	 */
	public List<?> queryForLimit(String sql, Map<String, Object> parmeters, int limitCount);

	/**
	 * 查询指定页数据
	 * 
	 * @param cls
	 * @param sql
	 * @param parmeters
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public <N> List<N> queryForData(Class<N> cls, final String sql, final Map<String, Object> parmeters,
			final int pageSize, final int pageNo);

	/**
	 * 原生SQL查询
	 * 
	 * @param cls
	 * @param sql
	 * @return
	 */
	public List<?> createSQLQuery(String sql);

	/**
	 * 原生SQL insert/update/delete
	 * 
	 * @param sql
	 * @return
	 */
	public int createSQLUpdate(String sql);
}
