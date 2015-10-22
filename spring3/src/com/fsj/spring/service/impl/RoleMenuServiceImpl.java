package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IRoleMenuDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IRoleMenuService;
import com.fsj.spring.util.DataGridModel;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements IRoleMenuService{

	private IRoleMenuDao roleMenuDao;
	
	public IRoleMenuDao getRoleMenuDao() {
		return roleMenuDao;
	}
	
	public void setRoleMenuDao(IRoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
	}

	public void addOrUpdate(List<TRoleMenu> lsRoleMenues) throws Exception {
		
		// TODO Auto-generated method stub
		for (TRoleMenu tRoleMenu : lsRoleMenues) {
			this.roleMenuDao.addOrUpdate(tRoleMenu);
		}
	}

	public void deleteRoleMenus(Integer roleid) throws Exception {
		// TODO Auto-generated method stub
		List<TRoleMenu> lstRoleMenus= this.roleMenuDao.getRoleMenuByRoleid(roleid);
		if(lstRoleMenus==null || lstRoleMenus.size()<=0)
			return;
		this.roleMenuDao.deleteRoleMenus(lstRoleMenus);
	}

	public List<TRoleMenu> getRoleMenuByRoleid(Integer roleid) {
		// TODO Auto-generated method stub
		return this.roleMenuDao.getRoleMenuByRoleid(roleid);
	}

	
}
