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
@Table(name="rolemenu")
public class TRoleMenu implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer menuid;
	private Integer roleid; 
	private Integer id;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	/** full constructor */
	public TRoleMenu(Integer id,Integer menuid,Integer roleid) {
		this.id=id;
		this.menuid=menuid;
		this.roleid = roleid;
	}

	
	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	/** default constructor */
	public TRoleMenu() {
	}
	@Column(name="menuid")  
	public Integer getMenuid() {
		return menuid;
	}
	@Column(name="roleid")  
	public Integer getRoleid() {
		return roleid;
	}

}