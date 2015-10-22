package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.dao.IMenuDao;
import com.fsj.spring.dao.IOrgnizationDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IOrgnizationService;
import com.fsj.spring.util.DataGridModel;

@Service("orgnizationService")
public class OrgnizationServiceImpl implements IOrgnizationService{

	private IOrgnizationDao orgnizationDao;
	
	public IOrgnizationDao getOrgnizationDao() {
		return orgnizationDao;
	}
	
	public void setOrgnizationDao(IOrgnizationDao orgnizationDao) {
		this.orgnizationDao = orgnizationDao;
	}

	@SuppressWarnings("unchecked")
	public List<TOrgnization> getOrgnizations() throws Exception {
		return orgnizationDao.findAll();
	}

	public void addOrUpdate(TOrgnization orgnization) throws Exception {
		// TODO Auto-generated method stub
		this.orgnizationDao.addOrUpdate(orgnization);
	}

	public void deleteOrgnizations(List<Integer> Ids) throws Exception {
		// TODO Auto-generated method stub
		this.orgnizationDao.deleteOrgnizations(Ids);
	}

	public List<TOrgnization> getOrgnizationList() throws Exception {
		// TODO Auto-generated method stub
		return this.orgnizationDao.findAll();
	}
	@SuppressWarnings("unchecked")
	public List<TOrgnization> getOrgnizationListForGrid() throws Exception {
		// TODO Auto-generated method stub
		return orgnizationDao.findAllForGrid();
	}


	
}
