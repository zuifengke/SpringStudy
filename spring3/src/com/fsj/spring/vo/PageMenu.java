package com.fsj.spring.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fsj.spring.model.TMenu;
import com.fsj.spring.util.CustomDateSerializer;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
public class PageMenu implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private String menuid;
	private String menuname;
	private String icon;
	private String url;
	private List<PageMenu> menus;
	// Constructors
	
	/** default constructor */
	public PageMenu() {
	}
	
	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PageMenu> getMenus() {
		if(menus==null)
			menus=new ArrayList<PageMenu>();
		return menus;
	}

	public void setMenus(List<PageMenu> menus) {
		this.menus = menus;
	}
	public TMenu convertToMenu() {
		TMenu menu=new TMenu();
		menu.setMenuid(Integer.parseInt(this.menuid));
		return menu;
	}
}