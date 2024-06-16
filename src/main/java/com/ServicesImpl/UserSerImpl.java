package com.ServicesImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Beans.UsersBean;
import com.Services.UsersServices;
import com.Utility.DBUtil;

public class UserSerImpl implements UsersServices {

	@Override
	public List<UsersBean> getAllUsers() {
		List<UsersBean> users = new ArrayList<UsersBean>();
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from customers");
			ResultSet res = ps.executeQuery();
			while(res.next()) {
				UsersBean u = new UsersBean();
				u.setCustomerId(res.getLong("customer_id"));
				u.setCustomerName(res.getString("customer_name"));
				u.setCustomerPhone(res.getString("customer_phone"));
				u.setCustomerEmail(res.getString("customer_email"));
				u.setActive(res.getBoolean("is_active"));
				u.setVerifyCode(res.getInt("verify_code"));
				
				users.add(u);
			}
		
			cnx.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public String deleteUser(Long idU) {
		String status = "Something is wrong !";
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("delete from customers where customer_id = ?");
			ps.setLong(1, idU);
			ps.executeUpdate();
			
			status = "Delete Successful";
			
			cnx.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String activeCompteUser(UsersBean user) {
		String status = null;
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("UPDATE customers SET is_active = ? WHERE customer_id = ?");
			ps.setBoolean(1,user.isActive());
			ps.setLong(2, user.getCustomerId());
			
			ps.executeUpdate();
			if (user.isActive()==true) {
			status = "Le compte est Activer ";
			}else {
				status = "Le compte est désactiver ";
			}
			cnx.close();
			ps.close();
		} catch (SQLException e) {
			
		}
		return status;
	}
	
	@Override
	public UsersBean getUserById(Long id) {
	    UsersBean user = null;
	    Connection cnx = DBUtil.provideConnection();
	    try {
	        PreparedStatement ps = cnx.prepareStatement("select * from customers where customer_id = ?");
	        ps.setLong(1, id);
	        ResultSet res = ps.executeQuery();
	        if (res.next()) {
	            user = new UsersBean();
	            user.setCustomerId(res.getLong("customer_id"));
	            user.setCustomerName(res.getString("customer_name"));
	            user.setCustomerPhone(res.getString("customer_phone"));
	            user.setCustomerEmail(res.getString("customer_email"));
	            user.setActive(res.getBoolean("is_active"));
	            user.setVerifyCode(res.getInt("verify_code"));
	        }
	        cnx.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}

}
