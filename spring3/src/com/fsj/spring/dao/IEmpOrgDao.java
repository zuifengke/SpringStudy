package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
@SuppressWarnings("rawtypes")
public interface IEmpOrgDao {
	public abstract List getEmpOrgByEmpid(Integer empid);
	void addOrUpdate(TEmpOrg empOrg) throws Exception;
	void deleteEmpOrgs(List<TEmpOrg> lstEmpOrgs) throws Exception;

}