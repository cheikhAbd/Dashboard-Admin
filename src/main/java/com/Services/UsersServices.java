package com.Services;

import java.util.List;

import com.Beans.UsersBean;

public interface UsersServices {
	public List<UsersBean> getAllUsers();
	public String deleteUser(Long id);
	public String activeCompteUser(UsersBean user);
	public UsersBean getUserById(Long id);
}
