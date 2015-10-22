package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IEmpOrgDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IEmpOrgService;
import com.fsj.spring.util.DataGridModel;

@Service("empOrgService")
public class EmpOrgServiceImpl implements IEmpOrgService{

	private IEmpOrgDao empOrgDao;
	
	public IEmpOrgDao getEmpOrgDao() {
		return empOrgDao;
	}
	
	public void setEmpOrgDao(IEmpOrgDao empOrgDao) {
		this.empOrgDao = empOrgDao;
	}

	public void addOrUpdate(List<TEmpOrg> lsEmpOrges) throws Exception {
		// TODO Auto-generated method stub
		for (TEmpOrg tEmpOrg : lsEmpOrges) {
			this.empOrgDao.addOrUpdate(tEmpOrg);
		}
	}

	public void deleteEmpOrgs(Integer roleid) throws Exception {
		// TODO Auto-generated method stub
		List<TEmpOrg> lstEmpOrgs= this.empOrgDao.getEmpOrgByEmpid(roleid);
		if(lstEmpOrgs==null || lstEmpOrgs.size()<=0)
			return;
		this.empOrgDao.deleteEmpOrgs(lstEmpOrgs);
	}

	public List<TEmpOrg> getEmpOrgByEmpid(Integer roleid) {
		// TODO Auto-generated method stub
		return this.empOrgDao.getEmpOrgByEmpid(roleid);
	}

	
}
