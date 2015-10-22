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
import com.fsj.spring.dao.IRoleMenuDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
import com.mysql.jdbc.log.Log;

@Repository("roleMenuDao")
@SuppressWarnings("rawtypes")
public class RoleMenuDaoImpl extends HibernateDaoSupport implements IRoleMenuDao {
	
	private static final Logger log = LoggerFactory.getLogger(RoleMenuDaoImpl.class);

	protected void initDao() {
	}
	public void addOrUpdate(TRoleMenu roleMenu) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(roleMenu);
	}

	public void deleteRoleMenus(List<TRoleMenu> lstRoleMenus) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(lstRoleMenus);
	}
	
	@SuppressWarnings("rawtypes")
	public List getRoleMenuByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		System.out.println("hello world2");
		Map<String, Object> result = new HashMap<String, Object>(1); 
		String fullQuery = " from TRoleMenu rolemenu where 1=1 "; 
		String orderString = "";
		StringBuffer sb = new StringBuffer();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append(" and roleid = :roleid");
		params.put("roleid", roleid);
			
		Query queryList = getSession().createQuery(fullQuery + sb.toString() + orderString);
     	if(params!=null && !params.isEmpty()){
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){					
				String key = it.next();	
				queryList.setParameter(key, params.get(key));
			}	
		}			
		List list = queryList.list();
			
		return list;
	}


}