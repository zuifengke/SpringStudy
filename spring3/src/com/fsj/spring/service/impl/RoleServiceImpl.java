package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IRoleDao;
import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IRoleService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	private IRoleDao roleDao;
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@SuppressWarnings("unchecked")
	public List<TRole> getRoleList() throws Exception {
		return roleDao.findAll();
	}
	public Map<String, Object> getPageListByExemple(DataGridModel dgm,TRole role) throws Exception {
		return roleDao.getPageListByExemple(dgm, role);
	}
	
	public Map<String, Object> getPageList(DataGridModel dgm,TRole role) throws Exception{
		return roleDao.getPageList(dgm,role);
	}

	public TRole getRoleById(int id) throws Exception {
		return roleDao.findById(id);
	}
	

	public Map<String, Object> getAllJson() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<TRole> list = getRoleList();
		result.put("total", list==null ? 0 : list.size());
		result.put("rows", list);
		return result;
	}
	public void addOrUpdate(TRole role) throws Exception {
		roleDao.addOrUpdate(role);
	}

	public void deleteRoles(List<Integer> rolesId) throws Exception {
		roleDao.deleteRoles(rolesId);
	}

	public TRole getRoleByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
