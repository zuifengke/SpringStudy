package com.fsj.spring.web;

import test.JUnitActionBase;
import org.junit.Assert;    
import org.junit.Test;    
import org.springframework.mock.web.MockHttpServletRequest;    
import org.springframework.mock.web.MockHttpServletResponse;    
import org.springframework.web.servlet.ModelAndView; 



public class DeptControllerTest  extends JUnitActionBase  {
	 @Test    
	    public void testqueryAll() throws Exception {    
		    System.out.println("1");
	    MockHttpServletRequest request = new MockHttpServletRequest();    
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/dept/queryAll");    
	        request.setMethod("GET"); 
	        // 执行URI对应的action    
	        final ModelAndView mav = this.excuteAction(request, response);    
	        // Assert logic    
	        Assert.assertEquals("", mav.getViewName());    
	        String msg=(String)request.getAttribute("msg");    
	        System.out.println(msg);    
	    }
	 @Test    
	    public void testList() throws Exception {    
		    System.out.println("1");
	    MockHttpServletRequest request = new MockHttpServletRequest();    
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/dept/queryList");    
	        request.setMethod("POST"); 
	        System.out.println("1");
	        // 执行URI对应的action    
	        final ModelAndView mav = this.excuteAction(request, response);    
	        // Assert logic    
	        Assert.assertEquals("dept/queryList", mav.getViewName());    
	        String msg=(String)request.getAttribute("msg");    
	        System.out.println(msg);    
	    }
}
