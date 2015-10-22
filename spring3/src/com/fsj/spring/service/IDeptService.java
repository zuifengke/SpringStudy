package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IDeptService {
	void addDept(TDept dept) throws Exception;
	void updateDept(TDept dept) throws Exception;
	void deleteDept(int id) throws Exception;
	TDept getDeptById(int id) throws Exception;
	List<TDept> getDeptList() throws Exception;
	Map<String, Object> getAllJson() throws Exception ;
	Map<String, Object> getPageList(DataGridModel dgm,TDept user) throws Exception;
	void addOrUpdate(TDept dept) throws Exception;
	void deleteDepts(List<Integer> deptsId) throws Exception;
}
