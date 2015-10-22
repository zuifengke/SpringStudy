package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IMenuService {
	
	List<TMenu> getMenuList() throws Exception;
	List<TMenu> getMenuListForGrid() throws Exception;
	void addOrUpdate(TMenu menu) throws Exception;
	void deleteMenus(List<Integer> menusId) throws Exception;
}
