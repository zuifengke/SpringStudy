package com.fsj.spring.web;

import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.registry.JAXRException;
import javax.xml.registry.LifeCycleManager;
import javax.xml.registry.infomodel.Association;
import javax.xml.registry.infomodel.Classification;
import javax.xml.registry.infomodel.Concept;
import javax.xml.registry.infomodel.ExternalIdentifier;
import javax.xml.registry.infomodel.ExternalLink;
import javax.xml.registry.infomodel.InternationalString;
import javax.xml.registry.infomodel.Key;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.PersonName;
import javax.xml.registry.infomodel.Slot;
import javax.xml.registry.infomodel.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TEmployee;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TEmpOrg;
import com.fsj.spring.service.IEmpOrgService;
import com.fsj.spring.service.IEmployeeService;
import com.fsj.spring.service.IExamplaceService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IEmployeeService;
import com.fsj.spring.service.IOrgnizationService;
import com.fsj.spring.util.DataGridModel;
import com.fsj.spring.vo.JsonTree;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		return "employee/list";
	}
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm,TEmployee employee) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return employeeService.getPageListByExemple(dgm, employee); 
	    return employeeService.getPageList(dgm, employee);
	}
	
	@RequestMapping(value="/popWindow",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "employee/popWindow";
	}
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(TEmployee employee) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			employeeService.addOrUpdate(employee);
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map; 
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(@RequestParam("id") List<Integer> ids)throws Exception{
		//spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			employeeService.deleteEmployees(ids);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}

	private IEmployeeService employeeService;
	
	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	private IOrgnizationService orgnizationService;
	
	public IOrgnizationService getOrgnizationService() {
		return orgnizationService;
	}

	public void setOrgnizationService(IOrgnizationService orgnizationService) {
		this.orgnizationService = orgnizationService;
	}
	

	private IEmpOrgService empOrgService;
	
	public IEmpOrgService getEmpOrgService() {
		return empOrgService;
	}

	public void setEmpOrgService(IEmpOrgService empOrgService) {
		this.empOrgService = empOrgService;
	}
	//角色功能授权
		@RequestMapping(value="/getEmpOrgByEmpid",method=RequestMethod.POST)
		@ResponseBody         
		public List<JsonTree> getEmpOrgByEmpid(Integer empid) throws Exception{
			//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
			//string strJson = "[{\"id\":\"1\",\"text\":\"hello1\",\"checked\":
			//\"true\",\"state\":\"open\",\"children\":[{\"id\":\"2\",\"text\":\"hello2\",
			//\"state\":\"open\"}]},{\"id\":\"1\",\"text\":\"hello1\",\"state\":\"open\",
			//\"children\":[{\"id\":\"2\",\"text\":\"hello2\",\"state\":\"open\"}]}]";
		    //return userService.getPageListByExemple(dgm, user); 
			List<TEmpOrg> lsTEmpOrgs=this.empOrgService.getEmpOrgByEmpid(empid);
			
			Map<String,Object> mapUsers=new HashMap<String, Object> ();
			List<TOrgnization> lstList=this.orgnizationService.getOrgnizationList();
			System.out.println("empid "+empid);
			JsonTree root=new JsonTree();
			root.setId("9");
			root.setText("总公司");
			root.setState("open");
			for (TOrgnization tMenu : lstList) {
				if(tMenu.getParentID().toString().equals(root.getId()))
				{
					JsonTree menu=new JsonTree();
					menu.setId(tMenu.getId().toString());
					menu.setText(tMenu.getOrgname());
					menu.setState("open");
					for (TOrgnization ttMenu : lstList) {
						if(ttMenu.getParentID().toString().equals(tMenu.getId().toString()))
						{
							JsonTree childmenu=new JsonTree();
							childmenu.setId(ttMenu.getId().toString());
							childmenu.setText(ttMenu.getOrgname());
							if(this.IsChecked(lsTEmpOrgs, ttMenu.getId()))
								childmenu.setChecked(true);
							
							childmenu.setState("open");
							for (TOrgnization tttOrgnization : lstList) {
								if(tttOrgnization.getParentID().toString().equals(ttMenu.getId().toString()))
								{
									JsonTree childmenu2=new JsonTree();
									childmenu2.setId(tttOrgnization.getId().toString());
									childmenu2.setText(tttOrgnization.getOrgname());
									if(this.IsChecked(lsTEmpOrgs, tttOrgnization.getId()))
										childmenu2.setChecked(true);
									childmenu2.setState("open");
									childmenu.getChildren().add(childmenu2);
								}
							}
							menu.getChildren().add(childmenu);
						}
					}
					 root.getChildren().add(menu);
				}
			}
			/*
			PageMenu menu1=new PageMenu() ;
			menu1.setMenuid("1");
			menu1.setMenuname("系统设置");
			menu1.setUrl("");
			menu1.setIcon("icon-sys");
			
			PageMenu menu2=new PageMenu() ;
			menu2.setMenuid("11");
			menu2.setMenuname("员工管理");
			menu2.setUrl("user/list");
			menu2.setIcon("icon-sys");
			
			menu1.getMenus().add(menu2);
			lstMenus.add(menu1);
			
			PageMenu menu3=new PageMenu() ;
			menu3.setMenuid("2");
			menu3.setMenuname("状元乐业务");
			menu3.setUrl("");
			menu3.setIcon("icon-sys");
			
			lstMenus.add(menu3);*/
			List<JsonTree> lstJsonTrees=new ArrayList<JsonTree>();
			lstJsonTrees.add(root);
		    return lstJsonTrees;
		}
		private boolean IsChecked(List<TEmpOrg> lsTEmpOrgs, Integer orgid) {
			if(lsTEmpOrgs.size()<=0)
				return false;
			for (TEmpOrg tEmpOrg : lsTEmpOrgs) {
				System.out.println(tEmpOrg.getOrgid()+" : " +orgid);
				if(tEmpOrg.getOrgid().equals(orgid))
				{
					return true;
				}
			}
			return false;
		}

		@RequestMapping(value="/saveByEmpID",method=RequestMethod.POST)
		@ResponseBody
		public Map<String, String> saveByEmpID(@RequestParam("orgids")List<Integer> orgids,@RequestParam("empid")Integer empid) throws Exception{
			//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
			Map<String, String> map = new HashMap<String, String>();
			
			try {
				empOrgService.deleteEmpOrgs(empid);
				List<TEmpOrg> lsEmpOrges=new ArrayList<TEmpOrg>();
				for (Integer orgid : orgids) {

					TEmpOrg empOrg=new TEmpOrg();
					empOrg.setOrgid(orgid);
					empOrg.setEmpid(empid);
					lsEmpOrges.add(empOrg);
				} 
				empOrgService.addOrUpdate(lsEmpOrges);
				
				map.put("mes", "操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("mes", "操作失败");
				throw e;
			}
			return map; 
		}
}
