package com.ServicesImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.Beans.Admin;
import com.Services.AdminServices;
import com.Utility.DBUtil;

public class AdminSerImp implements AdminServices {
	private static final Logger logger = Logger.getLogger(AdminSerImp.class.getName());

	@Override
	public String login(String userName, String password) {
		String status = "Connexion refusée ! Identifiant ou mot de passe incorrect";
		
		Connection cnx = DBUtil.provideConnection();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from admin where username=? and password=md5(?)");
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet res = ps.executeQuery();
			if (res.next()){
				status = "Vous avez été connecté avec succès";
				logger.info("Login successful for username: " + userName);
			}else {
				logger.info("Login failed for username: " + userName);
			}
			
			ps.close();
		}catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}finally {
			try {
				
				cnx.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				logger.severe("Error closing connection: " + e2.getMessage());
			}
		}
		
		logger.info("Login status: " + status);
		return status;
	}

	
	@Override
	public Admin getDetails(String userName, String password) {
		
		Connection cnx = DBUtil.provideConnection();
		Admin admin = new Admin();
		try {
			PreparedStatement ps = cnx.prepareStatement("select * from admin where username=? and password=md5(?)");
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet res = ps.executeQuery();
			if (res.next()){
				admin.setId(res.getLong("id"));
				admin.setEmail(res.getString("email"));
				admin.setUserName(res.getString("username"));
				admin.setPassword(res.getString("password"));
			}
			
			ps.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				
				cnx.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return admin;
	}

}
