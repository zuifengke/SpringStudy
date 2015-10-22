package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TEmployee;
import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IEmployeeService {
	TEmployee getEmployeeById(int id) throws Exception;
	TEmployee getEmployeeByName(String name) throws Exception;
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TEmployee employee) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TEmployee employee) throws Exception;
	void addOrUpdate(TEmployee employee) throws Exception;
	void deleteEmployees(List<Integer> Ids) throws Exception;
}
