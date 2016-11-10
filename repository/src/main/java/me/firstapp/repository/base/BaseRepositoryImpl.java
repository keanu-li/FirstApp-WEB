package me.firstapp.repository.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseRepositoryImpl implements BaseRepository {

	@Autowired
	public HibernateTemplate hibernateTemplate;

	public void save(Object obj) {
		hibernateTemplate.save(obj);
	}

	public void refresh(Object obj) {
		hibernateTemplate.refresh(obj);
	}

	public void delete(Object obj) {
		hibernateTemplate.delete(obj);
	}

	public void delete(Class<?> cls, Serializable[] ids) {
		Object obj = null;
		for (Serializable id : ids) {
			obj = hibernateTemplate.get(cls, id);
			hibernateTemplate.delete(obj);
		}
	}

	public void deleteById(Class<?> cls, Serializable id) {
		Object obj = hibernateTemplate.get(cls, id);
		hibernateTemplate.delete(obj);
	}

	public void update(Object obj) {
		hibernateTemplate.merge(obj);
	}

	public List<?> getAll(Class<?> cls) {
		String sql = " FROM " + cls.getSimpleName();
		return query(sql);
	}

	public Object getById(Class<?> cls, Serializable id) {
		try {
			return hibernateTemplate.get(cls, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<?> query(String sql) {
		return hibernateTemplate.find(sql);
	}

	public List<?> query(String sql, Map<String, Object> parmeters) {
		if (parmeters == null || parmeters.isEmpty()) {
			return hibernateTemplate.find(sql);
		} else {
			// 带条件查询
			int length = parmeters.size();
			String[] paramNames = new String[length];
			Object[] values = new Object[length];

			int i = 0;
			for (String key : parmeters.keySet()) {
				paramNames[i] = key;
				values[i] = parmeters.get(key);
				i++;
			}

			return hibernateTemplate.findByNamedParam(sql, paramNames, values);
		}
	}

	public int queryForCount(final String sql, final Map<String, Object> parmeters) {
		Object result = hibernateTemplate.execute(new HibernateCallback<Object>() {

			@SuppressWarnings("rawtypes")
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);
				if (parmeters != null) {
					for (String key : parmeters.keySet()) {
						if (parmeters.get(key) instanceof Collection) {
							query.setParameterList(key, (Collection) parmeters.get(key));
						} else {
							query.setParameter(key, parmeters.get(key));
						}
					}
				}
				return query.getSingleResult();
			}
		});
		if (result == null) {
			return 0;
		}
		Long longResult = (Long) result;
		return longResult.intValue();
	}

	public List<?> queryForLimit(final String sql, final Map<String, Object> parmeters, final int limitCount) {
		List<?> result = hibernateTemplate.execute(new HibernateCallback<List<?>>() {
			@SuppressWarnings("rawtypes")
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);
				if (parmeters != null) {
					for (String key : parmeters.keySet()) {
						query.setParameter(key, parmeters.get(key));
					}
				}
				query.setMaxResults(limitCount);
				return query.getResultList();
			}
		});
		return result;
	}

	public <N> List<N> queryForData(Class<N> cls, final String sql, final Map<String, Object> parmeters,
			final int pageSize, final int pageNo) {
		List<N> result = hibernateTemplate.execute(new HibernateCallback<List<N>>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public List<N> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);

				if (parmeters != null) {
					for (String key : parmeters.keySet()) {
						if (parmeters.get(key) instanceof Collection) {
							query.setParameterList(key, (Collection) parmeters.get(key));
						} else {
							query.setParameter(key, parmeters.get(key));
						}
					}
				}
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
				return query.getResultList();
			}
		});
		return result;
	}

	public List<?> createSQLQuery(final String sql) {
		List<?> result = hibernateTemplate.execute(new HibernateCallback<List<?>>() {
			public List<?> doInHibernate(Session session) throws HibernateException {
				return session.createNativeQuery(sql).getResultList();
			}
		});
		return result;
	}

	public int createSQLUpdate(final String sql) {
		Integer result = hibernateTemplate.execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				return session.createNativeQuery(sql).executeUpdate();
			}
		});
		return result;
	}

}
