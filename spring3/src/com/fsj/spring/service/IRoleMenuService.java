package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IRoleMenuService {
	
	public abstract List<TRoleMenu> getRoleMenuByRoleid(Integer roleid);
	void addOrUpdate(List<TRoleMenu> lsRoleMenues) throws Exception;
	void deleteRoleMenus(Integer roleid) throws Exception;
}
