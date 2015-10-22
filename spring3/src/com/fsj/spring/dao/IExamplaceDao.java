package com.fsj.spring.dao;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TExamplace;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;
@SuppressWarnings("rawtypes")
public interface IExamplaceDao {

	public static final String CODE = "code";
	public static final String NAME = "name";


	public abstract List findAll();

	public abstract List findAllForGrid();
	void addOrUpdate(TExamplace examplace) throws Exception;
	void deleteExamplaces(List<Integer> Ids) throws Exception;
	public abstract TExamplace findById(java.lang.Integer id) throws Exception;

}