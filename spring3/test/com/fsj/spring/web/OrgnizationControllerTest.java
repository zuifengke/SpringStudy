package com.fsj.spring.web;

import java.util.List;

import test.JUnitActionBase;
import org.junit.Assert;    
import org.junit.Test;    
import org.springframework.mock.web.MockHttpServletRequest;    
import org.springframework.mock.web.MockHttpServletResponse;    
import org.springframework.web.servlet.ModelAndView; 



public class OrgnizationControllerTest  extends JUnitActionBase  {
	 @Test    
	    public void testqueryList() throws Exception {    
		    System.out.println("1");
		    MockHttpServletRequest request = new MockHttpServletRequest();    
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/orgnization/queryList");    
	        request.setMethod("POST"); 
	        System.out.println("1");
	        // 执行URI对应的action    
	        final ModelAndView mav = this.excuteAction(request, response);    
	        // Assert logic    
	        //Assert.assertEquals("user/list", mav.getViewName());    
	        String msg=(String)request.getAttribute("msg");    
	        System.out.println(response.getContentAsString());    
	    }
	 @Test    
	    public void testgetComboTree() throws Exception {    
		    System.out.println("1");
		    MockHttpServletRequest request = new MockHttpServletRequest();    
	        MockHttpServletResponse response = new MockHttpServletResponse();    
	        request.setRequestURI("/orgnization/getComboTree");    
	        request.setMethod("POST"); 
	        System.out.println("1");
	        // 执行URI对应的action    
	        final ModelAndView mav = this.excuteAction(request, response);    
	        // Assert logic    
	        //Assert.assertEquals("user/list", mav.getViewName());    
	        String msg=(String)request.getAttribute("msg");    
	        System.out.println(response.getContentAsString());    
	    }
}
