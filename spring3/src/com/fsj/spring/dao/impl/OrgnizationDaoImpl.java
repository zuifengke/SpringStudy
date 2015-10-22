package com.fsj.spring.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IOrgnizationDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
import com.mysql.jdbc.log.Log;

@Repository("orgnizationDao")
@SuppressWarnings("rawtypes")
public class OrgnizationDaoImpl extends HibernateDaoSupport implements IOrgnizationDao {
	
	private static final Logger log = LoggerFactory.getLogger(OrgnizationDaoImpl.class);

	protected void initDao() {
		
	}


	public List findAll() {
		log.debug("finding all TOrgnization instances");
		try {
			String queryString = "from TOrgnization orgnization";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllForGrid() {
		log.debug("finding all Menu instances");
		try {
			String queryString = "select new map(orgnization.id as id,orgnization.orgname as orgname " +
					",orgnization.roletype as roletype,orgnization.description as description,orgnization.parentID as parentID" +
					",orgnization.parentID as _parentId) from TOrgnization orgnization";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void addOrUpdate(TOrgnization orgnization) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(orgnization);
	}


	public void deleteOrgnizations(List<Integer> Ids) throws Exception {
		// TODO Auto-generated method stub
		if(Ids != null && Ids.size() > 0) {
			for (Integer id : Ids) {
				getHibernateTemplate().delete(findById(id));
			}
		}
	}


	public TOrgnization findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		log.debug("getting TUser instance with id: " + id);
		try {
			TOrgnization instance = (TOrgnization) getHibernateTemplate().get(
					"com.fsj.spring.model.TOrgnization", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}