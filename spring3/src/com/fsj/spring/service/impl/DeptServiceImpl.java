package com.fsj.spring.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.util.DataGridModel;

@Service("deptService")
public class DeptServiceImpl implements IDeptService{

	private IDeptDao deptDao;
	
	public IDeptDao getDeptDao() {
		return deptDao;
	}
	
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public void addDept(TDept dept) throws Exception {
		deptDao.save(dept);
	}

	public void deleteDept(int id) throws Exception {
		deptDao.delete(getDeptById(id));
	}

	public TDept getDeptById(int id) throws Exception {
		return deptDao.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<TDept> getDeptList() throws Exception {
		return deptDao.findAll();
	}

	
	public Map<String, Object> getPageList(DataGridModel dgm,TDept user) throws Exception{
		return deptDao.getPageList(dgm,user);
	}
	public void updateDept(TDept dept) throws Exception {
		deptDao.merge(dept);
	}

	public Map<String, Object> getAllJson() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<TDept> list = getDeptList();
		result.put("total", list==null ? 0 : list.size());
		result.put("rows", list);
		return result;
	}

	public void addOrUpdate(TDept dept) throws Exception {
		deptDao.addOrUpdate(dept);
	}

	public void deleteDepts(List<Integer> deptsId) throws Exception {
		deptDao.deleteDepts(deptsId);
	}
}
