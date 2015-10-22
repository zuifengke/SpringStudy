package com.fsj.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fsj.spring.util.CustomDateSerializer;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Menu")
public class TMenu implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer menuid;
	private String menuname;
	private String icon;
	private String url;
	private Integer parentID;
	private String description; 
	private String menutype;

	private Integer _parentId;
	
	public Integer get_parentId() {
		return _parentId;
	}
	
	public void set_parentId(Integer parentId) {
		_parentId = parentId;
	}
	
	@Column(name="MenuType")
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menuType) {
		this.menutype = menuType;
	}
	
	@Column(name="Description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	// Constructors
	@Column(name="ParentID")
	public Integer getParentID() {
		return parentID;
	}
	
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	/** default constructor */
	public TMenu() {
	}
	@Id
	@GeneratedValue
	@Column(name="ID")  
	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	@Column(name="MenuName")  
	public String getMenuname() {
		return menuname;
	}
	
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	@Column(name="Icon")
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Column(name="Url")  
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}