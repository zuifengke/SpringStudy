package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
@SuppressWarnings("rawtypes")
public interface IOrgnizationDao {

	public static final String CODE = "code";
	public static final String NAME = "name";


	public abstract List findAll();

	public abstract List findAllForGrid();
	void addOrUpdate(TOrgnization orgnization) throws Exception;
	void deleteOrgnizations(List<Integer> Ids) throws Exception;
	public abstract TOrgnization findById(java.lang.Integer id) throws Exception;

}