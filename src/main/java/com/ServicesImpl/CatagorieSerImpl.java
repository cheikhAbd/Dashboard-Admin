package com.ServicesImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Beans.CategoriesBean;
import com.Services.CategorieServices;
import com.Utility.DBUtil;

public class CatagorieSerImpl implements CategorieServices {

	@Override
	public String createCat(CategoriesBean cat) {
		String status = "Somthing is Wrong !";
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("insert into categories (category_name_fr) values (?)");
			ps.setString(1,cat.getCategorieName());
			
			ps.executeUpdate();
			status = "Added Successful";
			PreparedStatement ps2 = cnx.prepareStatement("select max(category_id) as MAX_ID from categories ");
			ResultSet res = ps2.executeQuery();
			if(res.next()) {
				cat.setCategorieId(res.getLong("MAX_ID"));
			}
			
			cnx.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<CategoriesBean> getAllCategorie() {
		Connection cnx = DBUtil.provideConnection();
		List<CategoriesBean> categories = new ArrayList<CategoriesBean>();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from categories");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				CategoriesBean cat = new CategoriesBean();
				cat.setCategorieId(res.getLong("category_id"));
				cat.setCategorieName(res.getString("category_name_fr"));
				
				categories.add(cat);
			}
			
			cnx.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public String deleteCategorie(Long id) {
		String status = "Somthing is Wrong !";
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("delete from categories where category_id=? ");
			ps.setLong(1, id);
			
			ps.executeUpdate();
			status = "Delete Successful";
			
			cnx.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public CategoriesBean editCatgorie(CategoriesBean cat) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("UPDATE categories SET category_name_fr = ? WHERE category_id = ?");
			ps.setString(1,cat.getCategorieName());
			ps.setLong(2, cat.getCategorieId());
			
			ps.executeUpdate();
			
			cnx.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

	@Override
	public CategoriesBean getCatById(Long id) {
		Connection cnx = DBUtil.provideConnection();
		CategoriesBean cat = new CategoriesBean();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from categories where category_id =? ");
			ps.setLong(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				cat.setCategorieId(res.getLong("category_id"));
				cat.setCategorieName(res.getString("category_name_fr"));
			}
			cnx.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cat;
	}

}
