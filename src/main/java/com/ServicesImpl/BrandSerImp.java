package com.ServicesImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Beans.BrandsBean;
import com.Services.BrandServices;
import com.Utility.DBUtil;

public class BrandSerImp implements BrandServices {

	@Override
	public String createBrand(BrandsBean brand) {
		String status = "Somthing is Wrong !";
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("insert into brands (brand_name_fr) values (?)");
			ps.setString(1,brand.getBrandName());
			
			ps.executeUpdate();
			status = "Added Successful";
			PreparedStatement ps2 = cnx.prepareStatement("select max(brand_id) as MAX_ID from brands ");
			ResultSet res = ps2.executeQuery();
			if(res.next()) {
				brand.setBrandId(res.getLong("MAX_ID"));
			}
			
			cnx.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<BrandsBean> getAllBrands() {
		Connection cnx = DBUtil.provideConnection();
		List<BrandsBean> brands = new ArrayList<BrandsBean>();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from brands");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				BrandsBean brand = new BrandsBean();
				brand.setBrandId(res.getLong("brand_id"));
				brand.setBrandName(res.getString("brand_name_fr"));
				
				brands.add(brand);
			}
			
			cnx.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return brands;
	}

	@Override
	public String deleteBrand(Long id) {
		String status = "Somthing is Wrong !";
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("delete from brands where brand_id=? ");
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
	public BrandsBean editBrand(BrandsBean brand) {
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("UPDATE brands SET brand_name_fr = ? WHERE brand_id = ?");
			ps.setString(1,brand.getBrandName());
			ps.setLong(2, brand.getBrandId());
			
			ps.executeUpdate();
			
			cnx.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return brand;
	}


	@Override
	public BrandsBean getBrandById(Long id) {
		Connection cnx = DBUtil.provideConnection();
		BrandsBean brand = new BrandsBean();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from brands where brand_id = ?");
			ps.setLong(1, id);
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				brand.setBrandId(res.getLong("brand_id"));
				brand.setBrandName(res.getString("brand_name_fr"));
			}
			cnx.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return brand;
	}

}
