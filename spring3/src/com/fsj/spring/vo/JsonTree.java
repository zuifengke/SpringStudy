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

import com.fsj.spring.util.CustomDateSerializer;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
public class JsonTree implements java.io.Serializable {

	// Fields
	private String id;
	private String text;
	private boolean checked;
	private String state;
	private List<JsonTree> children;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<JsonTree> getChildren() {
		if(children==null)
			this.children=new ArrayList<JsonTree>() ;
		return children;
	}

	public void setChildren(List<JsonTree> children) {
		this.children = children;
	}
	// Constructors

	/** default constructor */
	public JsonTree() {
	}
	

}