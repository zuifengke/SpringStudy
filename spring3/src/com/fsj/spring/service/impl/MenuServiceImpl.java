package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.util.DataGridModel;

@Service("menuService")
public class MenuServiceImpl implements IMenuService{

	private IMenuDao menuDao;
	
	public IMenuDao getMenuDao() {
		return menuDao;
	}
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@SuppressWarnings("unchecked")
	public List<TMenu> getMenuList() throws Exception {
		return menuDao.findAll();
	}

	@SuppressWarnings("unchecked")
	public List<TMenu> getMenuListForGrid() throws Exception {
		return menuDao.findAllForGrid();
	}

	public void addOrUpdate(TMenu menu) throws Exception {
		// TODO Auto-generated method stub
		this.menuDao.addOrUpdate(menu);
	}

	public void deleteMenus(List<Integer> menusId) throws Exception {
		// TODO Auto-generated method stub
		this.menuDao.deleteMenus(menusId);
	}

	
}
