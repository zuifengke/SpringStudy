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

import com.fsj.spring.vo.PageMenu;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		model.addAttribute("deptList", deptService.getDeptList());
		return "user/list";
	}

	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm,TUser user) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
	    return userService.getPageList(dgm, user);
	}
	
	@RequestMapping(value="/popWindow",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "user/popWindow";
	}
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(TUser user) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.addOrUpdate(user);
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
	public Map<String, String> delete(@RequestParam("uid") List<Integer> uid)throws Exception{
		//spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			userService.deleteUsers(uid);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}
	

	private IDeptService deptService;
	
	public IDeptService getDeptService() {
		return deptService;
	}

	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}

	private IUserService userService;
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	

	private IMenuService menuService;
	
	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	@RequestMapping(value="/menuList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> menuList() throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
		Map<String,Object> mapUsers=new HashMap<String, Object> ();
		List<TMenu> lstList=this.menuService.getMenuList();
		List<PageMenu> lstMenus=new ArrayList<PageMenu>();
		for (TMenu tMenu : lstList) {
			if(tMenu.getParentID()==0)
			{
				PageMenu menu=new PageMenu();
				menu.setIcon(tMenu.getIcon());
				menu.setMenuid(tMenu.getMenuid().toString());
				menu.setMenuname(tMenu.getMenuname());
				menu.setUrl(tMenu.getUrl());
				for (TMenu ttMenu : lstList) {
					System.out.println(ttMenu.getParentID()+" "+tMenu.getMenuid());
					if(ttMenu.getParentID().toString().equals(tMenu.getMenuid().toString()))
					{
						PageMenu childmenu=new PageMenu();
						childmenu.setIcon(ttMenu.getIcon());
						childmenu.setMenuid(ttMenu.getMenuid().toString());
						childmenu.setMenuname(ttMenu.getMenuname());
						childmenu.setUrl(ttMenu.getUrl());
						
						menu.getMenus().add(childmenu);
					}
				}
				lstMenus.add(menu);
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
		mapUsers.put("menus", lstMenus);
	    return mapUsers;
	}
	
}
