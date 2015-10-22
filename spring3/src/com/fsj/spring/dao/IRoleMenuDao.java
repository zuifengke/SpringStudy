package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
@SuppressWarnings("rawtypes")
public interface IRoleMenuDao {
	public abstract List getRoleMenuByRoleid(Integer roleid);
	void addOrUpdate(TRoleMenu roleMenu) throws Exception;
	void deleteRoleMenus(List<TRoleMenu> lstRoleMenus) throws Exception;

}