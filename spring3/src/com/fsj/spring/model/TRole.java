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
@Table(name="role")
public class TRole implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer id;
	private String rolename;
	@Column(name="rolename")
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name="rolecode")
	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	@Column(name="rolebak")
	public String getRolebak() {
		return rolebak;
	}

	public void setRolebak(String rolebak) {
		this.rolebak = rolebak;
	}

	private String rolecode;
	private String rolebak;

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** full constructor */
	public TRole(Integer id,String rolename, String rolecode, String rolebak) {
		this.id=id;
		this.rolename = rolename;
		this.rolecode = rolecode;
		this.rolebak = rolebak;
	}

	// Property accessors
	@Id
	@GeneratedValue
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}