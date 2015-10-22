package com.fsj.spring.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IEmployeeDao;
import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TEmployee;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IEmployeeService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDao employeeDao;
	
	public IEmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Map<String, Object> getPageListByExemple(DataGridModel dgm,TEmployee employee) throws Exception {
		return employeeDao.getPageListByExemple(dgm, employee);
	}
	
	public Map<String, Object> getPageList(DataGridModel dgm,TEmployee employee) throws Exception{
		return employeeDao.getPageList(dgm,employee);
	}

	public TEmployee getEmployeeById(int id) throws Exception {
		return employeeDao.findById(id);
	}
	

	public void addOrUpdate(TEmployee employee) throws Exception {
		employeeDao.addOrUpdate(employee);
	}

	public void deleteEmployees(List<Integer> employeesId) throws Exception {
		employeeDao.deleteEmployees(employeesId);
	}

	public TEmployee getEmployeeByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
