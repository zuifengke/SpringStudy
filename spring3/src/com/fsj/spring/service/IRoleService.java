package com.fsj.spring.service;

import java.util.List;
import java.util.Map;

import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TUser;
import com.fsj.spring.util.DataGridModel;

public interface IRoleService {
	TRole getRoleById(int id) throws Exception;
	TRole getRoleByName(String name) throws Exception;
	Map<String, Object> getPageListByExemple(DataGridModel dgm,TRole role) throws Exception;
	Map<String, Object> getPageList(DataGridModel dgm,TRole role) throws Exception;
	Map<String, Object> getAllJson() throws Exception ;
	void addOrUpdate(TRole role) throws Exception;
	void deleteRoles(List<Integer> Ids) throws Exception;
}
