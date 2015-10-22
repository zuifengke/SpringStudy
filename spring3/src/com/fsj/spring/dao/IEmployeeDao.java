package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TEmployee;
import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IEmployeeDao {
	public static final String NAME = "name";
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TEmployee employee) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TEmployee employee) throws Exception;
	void addOrUpdate(TEmployee employee) throws Exception;
	void deleteEmployees(List<Integer> ids) throws Exception;
	public abstract TEmployee findById(java.lang.Integer id) throws Exception;
}