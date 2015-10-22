package com.fsj.spring.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.soap.Text;

import org.apache.commons.lang.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IExamplaceDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IOrgnizationDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TExamplace;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
import com.mysql.jdbc.log.Log;

@Repository("examplaceDao")
@SuppressWarnings("rawtypes")
public class ExamplaceDaoImpl extends HibernateDaoSupport implements IExamplaceDao {
	
	private static final Logger log = LoggerFactory.getLogger(ExamplaceDaoImpl.class);

	protected void initDao() {
		
	}


	public List findAll() {
		log.debug("finding all TExamplace instances");
		try {
			String queryString = "from TExamplace examplace";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllForGrid() {
		log.debug("finding all Menu instances");
		try {
			String queryString = "select new map(examplace.id as id,examplace.placename as placename " +
					",examplace.placetype as placetype,examplace.description as description,examplace.parentID as parentID" +
					",examplace.parentID as _parentId) from TExamplace examplace";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void addOrUpdate(TExamplace examplace) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(examplace);
	}


	public void deleteExamplaces(List<Integer> Ids) throws Exception {
		// TODO Auto-generated method stub
		if(Ids != null && Ids.size() > 0) {
			for (Integer id : Ids) {
				getHibernateTemplate().delete(findById(id));
			}
		}
	}


	public TExamplace findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		log.debug("getting TUser instance with id: " + id);
		try {
			TExamplace instance = (TExamplace) getHibernateTemplate().get(
					"com.fsj.spring.model.TExamplace", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}