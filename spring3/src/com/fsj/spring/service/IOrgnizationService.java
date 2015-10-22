package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IOrgnizationService {
	
	List<TOrgnization> getOrgnizationList() throws Exception;
	List<TOrgnization> getOrgnizationListForGrid() throws Exception;
	void addOrUpdate(TOrgnization orgnization) throws Exception;
	void deleteOrgnizations(List<Integer> Ids) throws Exception;
}
