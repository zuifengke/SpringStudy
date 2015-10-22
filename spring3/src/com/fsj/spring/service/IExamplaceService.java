package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TExamplace;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IExamplaceService {
	
	List<TExamplace> getExamplaceList() throws Exception;
	List<TExamplace> getExamplaceListForGrid() throws Exception;
	void addOrUpdate(TExamplace examplace) throws Exception;
	void deleteExamplaces(List<Integer> Ids) throws Exception;
}
