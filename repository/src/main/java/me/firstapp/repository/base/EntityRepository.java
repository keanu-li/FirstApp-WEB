package me.firstapp.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import me.firstapp.common.repository.Page;

public interface EntityRepository<T> {

	/**
	 * 保存
	 * 
	 * @param obj
	 */
	public void save(T obj);

	/**
	 * 刷新
	 * 
	 * @param obj
	 */
	public void refresh(T obj);

	/**
	 * 
	 * 更新
	 * 
	 * @param obj
	 */
	public void update(T obj);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delete(Serializable[] ids);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	public void deleteById(Serializable id);

	/**
	 * 通过ID查找对象
	 * 
	 * @param id
	 * @return
	 */
	public Object getById(Serializable id);

	/**
	 * 查找所有对象
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * 带条件查询
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public <N> List<N> query(Class<N> clazz, String sql, Map<String, Object> parameters);

	/**
	 * 查询limitCount条数据
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @param limitCount
	 *            条数
	 * @return
	 */
	public <N> List<N> queryForLimit(Class<N> clazz, String sql, Map<String, Object> parameters, int limitCount);

	/**
	 * 查询指定页数据
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <N> List<N> queryForPageList(Class<N> clazz, String sql, Map<String, Object> parameters, int pageNo,
			int pageSize);

	/**
	 * 分页查询
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <N> Page<N> queryForPage(Class<N> clazz, String sql, Map<String, Object> parameters, int pageNo,
			int pageSize);

	/**
	 * 分页查询(自定义聚合SQL)
	 * 
	 * @param clazz
	 * @param selectSql
	 * @param countSql
	 * @param parameters
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <N> Page<N> queryForPage(Class<N> clazz, String selectSql, String countSql, Map<String, Object> parameters,
			int pageNo, int pageSize);

	/**
	 * 查找条件所对应的首条数据
	 * 
	 * @param clazz
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public <N> N getFristData(Class<N> clazz, String sql, Map<String, Object> parameters);

	/**
	 * 原生SQL查询
	 * 
	 * @param cls
	 * @param sql
	 * @return 查询数据集
	 */
	public List<?> createSQLQuery(String sql);

	/**
	 * 原生SQL insert/update/delete
	 * 
	 * @param sql
	 * @return 1：SQL执行成功 0：SQL执行失败
	 */
	public int createSQLUpdate(String sql);
}
