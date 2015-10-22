package com.fsj.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="t_dept")
public class TDept implements java.io.Serializable {

	private static final long serialVersionUID = -5986823583396628465L;
	// Fields

	private Integer id;
	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public TDept() {
	}

	/** full constructor */
	public TDept(String code, String name) {
		this.code = code;
		this.name = name;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}