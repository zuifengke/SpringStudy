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

import sun.util.logging.resources.logging;

import com.fsj.spring.vo.JsonTree;
import com.fsj.spring.vo.PageMenu;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IOrgnizationService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Controller
@RequestMapping("/orgnization")
public class OrgnizationController {

	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		
		return "orgnization/list";
	}
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
	    Map<String, Object> orgnizationMap =new HashMap<String, Object>();
	    List<TOrgnization> lstOrgnizations = this.orgnizationService.getOrgnizationListForGrid();
	    orgnizationMap.put("rows", lstOrgnizations);
	    orgnizationMap.put("total", lstOrgnizations.size());
	    return orgnizationMap;
	}
	
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(TOrgnization orgnization) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			orgnizationService.addOrUpdate(orgnization);
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
	public Map<String, String> delete(@RequestParam("id") List<Integer> Ids)throws Exception{
		//spring mvc 还可以将参数绑定为list类型
		Map<String, String> map = new HashMap<String, String>();
		try {
			orgnizationService.deleteOrgnizations(Ids);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}
	private IOrgnizationService orgnizationService;
	
	public IOrgnizationService getOrgnizationService() {
		return orgnizationService;
	}

	public void setOrgnizationService(IOrgnizationService orgnizationService) {
		this.orgnizationService = orgnizationService;
	}


	@RequestMapping(value="/getComboTree",method=RequestMethod.POST)
	@ResponseBody         
	public List<JsonTree> getComboTree() throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
		Map<String,Object> mapUsers=new HashMap<String, Object> ();
		List<TOrgnization> lstList=this.orgnizationService.getOrgnizationList();
		JsonTree root=new JsonTree();
		root.setId("0");
		root.setText("父节点");
		root.setState("open");
		for (TOrgnization tOrgnization : lstList) {
			if(tOrgnization.getParentID()==0)
			{
				JsonTree orgnization=new JsonTree();
				orgnization.setId(tOrgnization.getId().toString());
				orgnization.setText(tOrgnization.getOrgname());
				orgnization.setChecked(false);
				orgnization.setState("open");
				for (TOrgnization ttOrgnization : lstList) {
					
					if(ttOrgnization.getParentID().toString().equals(tOrgnization.getId().toString()))
					{
						JsonTree childmenu=new JsonTree();
						childmenu.setId(ttOrgnization.getId().toString());
						childmenu.setText(ttOrgnization.getOrgname());
						childmenu.setChecked(false);
						childmenu.setState("open");
						orgnization.getChildren().add(childmenu);
					}
				}
				 root.getChildren().add(orgnization);
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
}
