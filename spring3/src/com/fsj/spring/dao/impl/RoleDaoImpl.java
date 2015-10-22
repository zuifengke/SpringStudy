package com.fsj.spring.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IRoleDao;
import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

@Repository("roleDao")
public class RoleDaoImpl extends HibernateDaoSupport implements IRoleDao {
	
	private static final Logger log = LoggerFactory.getLogger(RoleDaoImpl.class);
	protected void initDao() {
	}
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageListByExemple(DataGridModel dgm,TRole role) throws Exception{
		System.out.println("hello world");
		Map<String, Object> result = new HashMap<String, Object>(2); 
		List totalList = getHibernateTemplate().findByExample(role); 
		List pagelist = getHibernateTemplate().findByExample(role, (dgm.getPage() - 1) * dgm.getRows(), dgm.getRows());
		
		result.put("total", totalList == null ? 0 : totalList.size());
		result.put("rows", pagelist);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getPageList(DataGridModel dgm,TRole role) throws Exception{
		System.out.println("hello world2");
		Map<String, Object> result = new HashMap<String, Object>(2); 
		String countQuery = "select count(*) from TRole role  "; 
		String fullQuery = "select new map(role as role) from TRole role "; 
		String orderString = "";
		if(StringUtils.isNotBlank(dgm.getSort()))
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder(); 
		StringBuffer sb = new StringBuffer();
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		List totalList = getHibernateTemplate().findByNamedParam(countQuery, params.keySet().toArray(new String[0]), params.values().toArray());
//			int total = ((Long)totalList.iterator().next()).intValue();
		
		Query queryTotal = getSession().createQuery(countQuery + sb.toString());
		Query queryList = getSession().createQuery(fullQuery + sb.toString() + orderString)
							.setFirstResult((dgm.getPage() - 1) * dgm.getRows()).setMaxResults(dgm.getRows());
		if(params!=null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){					
				String key = it.next();	
				queryTotal.setParameter(key, params.get(key));
				queryList.setParameter(key, params.get(key));
			}	
		}			
		int total = ((Long)queryTotal.uniqueResult()).intValue();
		
		List list = queryList.list();
		result.put("total", total);
		result.put("rows", list);
			
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findById(java.lang.Integer)
	 */
	public TRole findById(java.lang.Integer id)  throws Exception {
		log.debug("getting TRole instance with id: " + id);
		try {
			TRole instance = (TRole) getHibernateTemplate().get(
					"com.fsj.spring.model.TRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<TRole> findByProperty(String propertyName, Object value) throws Exception {
		log.debug("finding TRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TRole instances");
		try {
			String queryString = "from TRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/* (non-Javadoc)
	 * @see com.fsj.spring.dao.IUserDao#findByName(java.lang.Object)
	 */
	public List<TRole> findByName(Object name) throws Exception {
		return findByProperty(NAME, name);
	}

	public static IUserDao getFromApplicationContext(ApplicationContext ctx) throws Exception {
		return (IUserDao) ctx.getBean("TUserDAO");
	}

	public void addOrUpdate(TRole role) throws Exception {
		getHibernateTemplate().saveOrUpdate(role);
	}

	public void deleteRoles(List<Integer> Ids) throws Exception {
		if(Ids != null && Ids.size() > 0) {
			for (Integer id : Ids) {
				getHibernateTemplate().delete(findById(id));
			}
		}
	}
	
	public void executeHql(final String hql) {
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				session.createQuery(hql).executeUpdate();
				return null;

			}
		});

	}
}