package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IRoleDao {
	public static final String NAME = "name";
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TRole role) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TRole role) throws Exception;
	void addOrUpdate(TRole role) throws Exception;
	void deleteRoles(List<Integer> ids) throws Exception;
	public abstract List findAll();
	public abstract TRole findById(java.lang.Integer id) throws Exception;
}