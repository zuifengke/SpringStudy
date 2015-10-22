package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IExamplaceDao;
import com.fsj.spring.model.TExamplace;

import com.fsj.spring.service.IExamplaceService;
import com.fsj.spring.util.DataGridModel;

@Service("examplaceService")
public class ExamplaceServiceImpl implements IExamplaceService{

	private IExamplaceDao examplaceDao;
	
	public IExamplaceDao getExamplaceDao() {
		return examplaceDao;
	}
	
	public void setExamplaceDao(IExamplaceDao examplaceDao) {
		this.examplaceDao = examplaceDao;
	}

	@SuppressWarnings("unchecked")
	public List<TExamplace> getExamplaces() throws Exception {
		return examplaceDao.findAll();
	}

	public void addOrUpdate(TExamplace examplace) throws Exception {
		// TODO Auto-generated method stub
		this.examplaceDao.addOrUpdate(examplace);
	}

	public void deleteExamplaces(List<Integer> Ids) throws Exception {
		// TODO Auto-generated method stub
		this.examplaceDao.deleteExamplaces(Ids);
	}

	public List<TExamplace> getExamplaceList() throws Exception {
		// TODO Auto-generated method stub
		return this.examplaceDao.findAll();
	}
	@SuppressWarnings("unchecked")
	public List<TExamplace> getExamplaceListForGrid() throws Exception {
		// TODO Auto-generated method stub
		return examplaceDao.findAllForGrid();
	}


	
}
