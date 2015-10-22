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
import com.fsj.spring.dao.IEmpOrgDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IEmpOrgDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
import com.mysql.jdbc.log.Log;

@Repository("empOrgDao")
@SuppressWarnings("rawtypes")
public class EmpOrgDaoImpl extends HibernateDaoSupport implements IEmpOrgDao {
	
	private static final Logger log = LoggerFactory.getLogger(EmpOrgDaoImpl.class);

	protected void initDao() {
	}
	public void addOrUpdate(TEmpOrg EmpOrg) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(EmpOrg);
	}

	public void deleteEmpOrgs(List<TEmpOrg> lstEmpOrgs) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().deleteAll(lstEmpOrgs);
	}
	
	@SuppressWarnings("rawtypes")
	public List getEmpOrgByEmpid(Integer empid) {
		// TODO Auto-generated method stub
		System.out.println("hello world2");
		Map<String, Object> result = new HashMap<String, Object>(1); 
		String fullQuery = " from TEmpOrg emporg where 1=1 "; 
		String orderString = "";
		StringBuffer sb = new StringBuffer();
		Map<String,Object> params = new HashMap<String,Object>();
		
		sb.append(" and empid = :empid");
		params.put("empid", empid);
			
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