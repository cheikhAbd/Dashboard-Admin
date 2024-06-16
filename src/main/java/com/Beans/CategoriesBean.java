package com.Beans;

import java.io.Serializable;

public class CategoriesBean implements Serializable {
	private Long categorieId;
	private String categorieName;
	
	
	
	
	public CategoriesBean(String categorieName) {
		super();
		this.categorieName = categorieName;
	}


	public CategoriesBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(Long categorieId) {
		this.categorieId = categorieId;
	}
	public String getCategorieName() {
		return categorieName;
	}
	public void setCategorieName(String categorieName) {
		this.categorieName = categorieName;
	}


	@Override
	public String toString() {
		return "CategoriesBean [categorieId=" + categorieId + ", categorieName=" + categorieName + "]";
	}
	
	
	
}
