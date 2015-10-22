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
import com.fsj.spring.model.TRole;
import com.fsj.spring.model.TRoleMenu;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IRoleMenuService;
import com.fsj.spring.service.IRoleService;
import com.fsj.spring.util.DataGridModel;
import com.fsj.spring.vo.JsonTree;

@Controller
@RequestMapping("/role")
public class RoleController {
	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		return "role/list";
	}
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm,TRole role) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return roleService.getPageListByExemple(dgm, role); 
	    return roleService.getPageList(dgm, role);
	}
	@RequestMapping(value="/queryAll")
	@ResponseBody         
	public Map<String, Object> queryAll() throws Exception{
	    return roleService.getAllJson(); //可以返回map
	}
	@RequestMapping(value="/popWindow",method=RequestMethod.GET)
	public String popWindow() throws Exception{
		return "role/popWindow";
	}
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(TRole role) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			roleService.addOrUpdate(role);
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
			roleService.deleteRoles(ids);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}

	private IRoleService roleService;
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IRoleService getRoleService() {
		return roleService;
	}

	private IRoleMenuService roleMenuService;
	
	public void setRoleMenuService(IRoleMenuService roleMenuService) {
		this.roleMenuService = roleMenuService;
	}
	public IRoleMenuService getRoleMenuService() {
		return roleMenuService;
	}

	private IMenuService menuService;
	
	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	//角色功能授权
	@RequestMapping(value="/getRoleMenus",method=RequestMethod.POST)
	@ResponseBody         
	public List<JsonTree> getRoleMenus(Integer roleid) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
		//string strJson = "[{\"id\":\"1\",\"text\":\"hello1\",\"checked\":
		//\"true\",\"state\":\"open\",\"children\":[{\"id\":\"2\",\"text\":\"hello2\",
		//\"state\":\"open\"}]},{\"id\":\"1\",\"text\":\"hello1\",\"state\":\"open\",
		//\"children\":[{\"id\":\"2\",\"text\":\"hello2\",\"state\":\"open\"}]}]";
	    //return userService.getPageListByExemple(dgm, user); 
		List<TRoleMenu> lsTRoleMenus=this.roleMenuService.getRoleMenuByRoleid(roleid);
		
		Map<String,Object> mapUsers=new HashMap<String, Object> ();
		List<TMenu> lstList=this.menuService.getMenuList();
		System.out.println("roleid "+roleid);
		JsonTree root=new JsonTree();
		root.setId("0");
		root.setText("父节点");
		root.setState("open");
		
		for (TMenu tMenu : lstList) {
			if(tMenu.getParentID()==0)
			{
				JsonTree menu=new JsonTree();
				menu.setId(tMenu.getMenuid().toString());
				menu.setText(tMenu.getMenuname());
				menu.setState("open");
				for (TMenu ttMenu : lstList) {
					if(ttMenu.getParentID().toString().equals(tMenu.getMenuid().toString()))
					{
						JsonTree childmenu=new JsonTree();
						childmenu.setId(ttMenu.getMenuid().toString());
						childmenu.setText(ttMenu.getMenuname());
						if(this.IsChecked(lsTRoleMenus, ttMenu.getMenuid()))
							childmenu.setChecked(true);
						childmenu.setState("open");
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
	private boolean IsChecked(List<TRoleMenu> lsTRoleMenus, Integer menuid) {
		if(lsTRoleMenus.size()<=0)
			return false;
		for (TRoleMenu tRoleMenu : lsTRoleMenus) {
			System.out.println(tRoleMenu.getMenuid()+" : " +menuid);
			if(tRoleMenu.getMenuid().equals(menuid))
			{
				return true;
			}
		}
		return false;
	}

	@RequestMapping(value="/saveByRoleID",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> saveByRoleID(@RequestParam("menuids")List<Integer> menuids,@RequestParam("roleid")Integer roleid) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		
		try {
			roleMenuService.deleteRoleMenus(roleid);
			List<TRoleMenu> lsRoleMenues=new ArrayList<TRoleMenu>();
			for (Integer menuid : menuids) {

				TRoleMenu roleMenu=new TRoleMenu();
				roleMenu.setMenuid(menuid);
				roleMenu.setRoleid(roleid);
				lsRoleMenues.add(roleMenu);
			} 
			roleMenuService.addOrUpdate(lsRoleMenues);
			
			map.put("mes", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "操作失败");
			throw e;
		}
		return map; 
	}
    
	
}
