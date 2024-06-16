package com.ServicesImpl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.Beans.BrandsBean;
import com.Beans.CategoriesBean;
import com.Beans.UsersBean;
import com.Utility.DBUtil;

public class Test {
	
	public static void main(String[] args) {
//		CatagorieSerImpl catSer = new CatagorieSerImpl();
//		try {
//            Connection conn = DBUtil.provideConnection();
//            if (conn != null) {
//                System.out.println("Connection successful!");
//            } else {
//                System.out.println("Connection failed!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
		
		// cree une nouvelle 
		
//		CategoriesBean cateUpdate = new CategoriesBean();
//		cateUpdate.setCategorieId(6L);
//		cateUpdate.setCategorieName("test2");
//		String s = catSer.createCat(cateUpdate);
//	    System.out.print(s.toString());

	    // list by id 
		
//		CategoriesBean catId = catSer.getCatById(cateUpdate.getCategorieId());
//	    System.out.print(cateUpdate.toString());
	     
	    // modifier 
		
//	 	cateUpdate.setCategorieId(4L);
//	 	cateUpdate.setCategorieName("Cars");
//	    CategoriesBean catId = catSer.editCatgorie(cateUpdate);
//	    System.out.print(cateUpdate.toString());
	     
	    // Supprimer
	 	
//	    String s = catSer.deleteCategorie(cateUpdate.getCategorieId());
//	    System.out.print(s);
	    
	     
	    // list  all 
		
//		List<CategoriesBean> cats = catSer.getAllCategorie();
//		for(CategoriesBean c : cats) {
//				System.out.print(c.toString()+"\n");
//		}
		
		// test les utilisateurs 
		UserSerImpl userSer = new UserSerImpl();
		
		// list all 
		List<UsersBean> users = userSer.getAllUsers();
		for(UsersBean u : users) {
			System.out.print(u.toString()+"\n");
		}
		
		// delete user 
		UsersBean us = new UsersBean();
		us.setCustomerId(16L);
		us.setActive(false);
//		String s = userSer.deleteUser(us.getCustomerId());
//		System.out.println(s);
		
		// Activer compte
		String us1 = userSer.activeCompteUser(us);
		System.out.println(us1);
		
	}
}
