package com.Services;

import com.Beans.Admin;

public interface AdminServices {
	public String login(String userName, String password);
	public Admin getDetails(String userName, String password);
}
