package com.fsj.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fsj.spring.util.CustomDateSerializer;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee")
public class TEmployee implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer id;
	private String empno;
	private String pwd;
	private String name;
	private String roletype;
	private String orgname;
	private String tel;

	@Column(name = "EmpNo")
	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	@Column(name = "Pwd")
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RoleType")
	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	@Column(name = "OrgName")
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "Tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// Constructors

	/** default constructor */
	public TEmployee() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}