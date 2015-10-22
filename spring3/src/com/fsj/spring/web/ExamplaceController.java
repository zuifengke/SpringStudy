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
import com.fsj.spring.model.TExamplace;
import com.fsj.spring.model.TMenu;
import com.fsj.spring.model.TOrgnization;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IDeptService;
import com.fsj.spring.service.IExamplaceService;
import com.fsj.spring.service.IMenuService;
import com.fsj.spring.service.IOrgnizationService;
import com.fsj.spring.service.IUserService;
import com.fsj.spring.util.DataGridModel;

@Controller
@RequestMapping("/examplace")
public class ExamplaceController {

	private IExamplaceService examplaceService;
	
	public IExamplaceService getExamplaceService() {
		return examplaceService;
	}

	public void setExamplaceService(IExamplaceService examplaceService) {
		this.examplaceService = examplaceService;
	}
	@RequestMapping(value="/list",method=RequestMethod.GET)   
	public String list(Model model) throws Exception {
		
		return "examplace/list";
	}
	@RequestMapping(value="/queryList",method=RequestMethod.POST)
	@ResponseBody         
	public Map<String, Object> queryList(DataGridModel dgm) throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
	    Map<String, Object> examplaceMap =new HashMap<String, Object>();
	    List<TExamplace> lstExamplaces = this.examplaceService.getExamplaceListForGrid();
	    examplaceMap.put("rows", lstExamplaces);
	    examplaceMap.put("total", lstExamplaces.size());
	    return examplaceMap;
	}
	
	
	@RequestMapping(value="/addOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOrUpdate(TExamplace examplace) throws Exception{
		//spring会利用jackson自动将返回值封装成JSON对象，比struts2方便了很多
		Map<String, String> map = new HashMap<String, String>();
		try {
			
			examplaceService.addOrUpdate(examplace);
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
			examplaceService.deleteExamplaces(Ids);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
			throw e;
		}
		return map;//重定向
	}


	@RequestMapping(value="/getComboTree",method=RequestMethod.POST)
	@ResponseBody         
	public List<JsonTree> getComboTree() throws Exception{
		//spring太给力了，可以自动装配两个对象  会自动的装返回的Map转换成JSON对象
	    //return userService.getPageListByExemple(dgm, user); 
		Map<String,Object> mapUsers=new HashMap<String, Object> ();
		List<TExamplace> lstList=this.examplaceService.getExamplaceList();
		JsonTree root=new JsonTree();
		root.setId("0");
		root.setText("父节点");
		root.setState("open");
		for (TExamplace tExamplace : lstList) {
			if(tExamplace.getParentID()==0)
			{
				JsonTree node=new JsonTree();
				node.setId(tExamplace.getId().toString());
				node.setText(tExamplace.getPlacename());
				node.setChecked(false);
				node.setState("open");
				for (TExamplace ttExamplace : lstList) {
					
					if(ttExamplace.getParentID().toString().equals(tExamplace.getId().toString()))
					{
						JsonTree childmenu=new JsonTree();
						childmenu.setId(ttExamplace.getId().toString());
						childmenu.setText(ttExamplace.getPlacename());
						childmenu.setChecked(false);
						childmenu.setState("open");
						node.getChildren().add(childmenu);
					}
				}
				 root.getChildren().add(node);
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
