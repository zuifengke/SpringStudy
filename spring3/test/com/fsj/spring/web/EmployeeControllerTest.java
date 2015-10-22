package com.fsj.spring.web;

import java.util.List;

import test.JUnitActionBase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.fsj.spring.model.TUser;

public class EmployeeControllerTest extends JUnitActionBase {
	@Test
	public void testList() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/queryList");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		// Assert.assertEquals("employee/list", mav.getViewName());
		String msg = (String) request.getAttribute("msg");
		System.out.println(msg);
		System.out.println(response.getContentAsString());
	}

	@Test
	public void testDelete() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/delete");
		request.setParameter("uid", "8");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		Assert.assertEquals("删除成功", "");
		String msg = (String) request.getAttribute("mes");
		System.out.println(msg);
	}

	@Test
	public void testAddOrUpdate() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/addOrUpdate");
		request.setParameter("employee",
				"id=27&name=%E6%B5%8B%E8%AF%952&password=fff&age=&birthday=1970-01-01&deptId=1");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		// Assert.assertEquals("删除成功", "");
		String msg = (String) request.getAttribute("mes");
		System.out.println(msg);
	}

	@Test
	public void testmenuList() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/menuList");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		// Assert.assertEquals("employee/list", mav.getViewName());
		String msg = (String) request.getAttribute("msg");
		System.out.println(response.getContentAsString());
	}

	@Test
	public void testgetRoleMenus() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/getRoleMenus");
		request.setParameter("employeeid", "1");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		// Assert.assertEquals("employee/list", mav.getViewName());
		String msg = (String) request.getAttribute("msg");
		System.out.println(response.getContentAsString());
	}

	@Test
	public void testsaveByRoleID() throws Exception {
		System.out.println("1");
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setRequestURI("/employee/saveByRoleID");
		request.setParameter("employeeid", "1");
		request.setParameter("menuids", "1");
		request.setParameter("menuids", "2");
		request.setParameter("menuids", "3");
		request.setMethod("POST");
		System.out.println("1");
		// 执行URI对应的action
		final ModelAndView mav = this.excuteAction(request, response);
		// Assert logic
		// Assert.assertEquals("employee/list", mav.getViewName());
		String msg = (String) request.getAttribute("msg");
		System.out.println(response.getContentAsString());
	}
	@Test    
    public void testgetEmpOrgByEmpid() throws Exception {    
	    System.out.println("1");
        MockHttpServletRequest request = new MockHttpServletRequest();    
        MockHttpServletResponse response = new MockHttpServletResponse();    
        request.setRequestURI("/employee/getEmpOrgByEmpid");    
        request.setParameter("empid", "1");
        request.setMethod("POST"); 
        System.out.println("1");
        // 执行URI对应的action    
        final ModelAndView mav = this.excuteAction(request, response);    
        // Assert logic    
        // Assert.assertEquals("role/list", mav.getViewName());    
        String msg=(String)request.getAttribute("msg");    
        System.out.println(response.getContentAsString());    
    }
}
