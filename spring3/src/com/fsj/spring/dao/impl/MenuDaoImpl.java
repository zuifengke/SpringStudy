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
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
import com.mysql.jdbc.log.Log;

@Repository("menuDao")
@SuppressWarnings("rawtypes")
public class MenuDaoImpl extends HibernateDaoSupport implements IMenuDao {
	
	private static final Logger log = LoggerFactory.getLogger(MenuDaoImpl.class);

	protected void initDao() {
		
	}


	public List findAll() {
		log.debug("finding all Menu instances");
		try {
			String queryString = "from TMenu menu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findAllForGrid() {
		log.debug("finding all Menu instances");
		try {
			String queryString = "select new map(menu.menuid as menuid,menu.menuid as id,menu.menuname as menuname,menu.description as description" +
					",menu.menutype as menutype,menu.url as url,menu.icon as icon,menu.parentID as parentID,menu.parentID as _parentId) from TMenu menu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void addOrUpdate(TMenu menu) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(menu);
	}


	public void deleteMenus(List<Integer> menusId) throws Exception {
		// TODO Auto-generated method stub
		if(menusId != null && menusId.size() > 0) {
			for (Integer id : menusId) {
				getHibernateTemplate().delete(findById(id));
			}
		}
	}


	public TMenu findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		log.debug("getting TUser instance with id: " + id);
		try {
			TMenu instance = (TMenu) getHibernateTemplate().get(
					"com.fsj.spring.model.TMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

}