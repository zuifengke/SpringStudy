package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
@SuppressWarnings("rawtypes")
public interface IMenuDao {

	public static final String CODE = "code";
	public static final String NAME = "name";


	public abstract List findAll();

	public abstract List findAllForGrid();
	void addOrUpdate(TMenu menu) throws Exception;
	void deleteMenus(List<Integer> menusId) throws Exception;
	public abstract TMenu findById(java.lang.Integer id) throws Exception;

}