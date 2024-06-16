package com.Beans;

import java.io.Serializable;

public class UsersBean implements Serializable {
	private Long customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String customerPassword;	
    private boolean isActive;
    private int  verifyCode;
    
    
	public UsersBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UsersBean(String customerName, String customerPhone, String customerEmail, String customerPassword,
			boolean isActive, int verifyCode) {
		super();
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.isActive = isActive;
		this.verifyCode = verifyCode;
	}


	public Long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerPhone() {
		return customerPhone;
	}


	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getCustomerPassword() {
		return customerPassword;
	}


	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public int getVerifyCode() {
		return verifyCode;
	}


	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}


	@Override
	public String toString() {
		return "UsersBean [customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerEmail=" + customerEmail + ", customerPassword=" + customerPassword
				+ ", isActive=" + isActive + ", verifyCode=" + verifyCode + "]";
	}
    
    
    
}
