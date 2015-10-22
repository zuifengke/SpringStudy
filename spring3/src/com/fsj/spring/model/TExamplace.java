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
@Table(name="examplace")
public class TExamplace implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8032736954376548825L;
	private Integer id;
	private String placename;
	private Integer parentID;
	private String description; 
	private String placetype;
	private Integer _parentId;
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	/** default constructor */
	public TExamplace() {
	}
	/** full constructor */
	public TExamplace(Integer id,String placename ,String placetype,String description,Integer parentID,Integer _parentId)  {
		this.id = id;
		this.placename = placename;
		this._parentId=_parentId;
		this.description=description;
		this.placetype=placetype;
		this.parentID=parentID;
	}
	/** full constructor */
	public TExamplace(Integer id,String placename ,String placetype,String description,Integer parentID)  {
		this.id = id;
		this.placename = placename;
		this._parentId=parentID;
		this.description=description;
		this.placetype=placetype;
		this.parentID=parentID;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="PlaceName")
	public String getPlacename() {
		return placename;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}
	@Column(name="ParentID")
	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public String getDescription() {
		return description;
	}
	@Column(name="Description")
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="Placetype")
	public String getPlacetype() {
		return placetype;
	}

	public void setPlacetype(String placetype) {
		this.placetype = placetype;
	}

	
	@Column(name="ParentID",updatable=false,insertable=false)
	public Integer get_parentId() {
		return _parentId;
	}
	
	public void set_parentId(Integer parentId) {
		_parentId = parentId;
	}
	
}