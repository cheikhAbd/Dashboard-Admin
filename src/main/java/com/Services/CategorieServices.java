package com.Services;

import java.util.List;

import com.Beans.CategoriesBean;



public interface CategorieServices {
	public String createCat(CategoriesBean cat);
	public List<CategoriesBean> getAllCategorie();
	public String deleteCategorie(Long id);
	public CategoriesBean editCatgorie(CategoriesBean cat);
	public CategoriesBean getCatById(Long id);
}
