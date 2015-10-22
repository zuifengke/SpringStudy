package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IEmpOrgService {
	
	public abstract List<TEmpOrg> getEmpOrgByEmpid(Integer empid);
	void addOrUpdate(List<TEmpOrg> lsEmpOrges) throws Exception;
	void deleteEmpOrgs(Integer empid) throws Exception;
}
