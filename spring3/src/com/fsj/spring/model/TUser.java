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
@Table(name="t_user")
public class TUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer id;
	private String password;
	private String name;
	private Integer age;
	private Date birthday;
	private Integer deptId;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** full constructor */
	public TUser(String password, String name, Integer age, Date birthday,
			Integer deptId) {
		this.password = password;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.deptId = deptId;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name="dept_id")  
	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

}