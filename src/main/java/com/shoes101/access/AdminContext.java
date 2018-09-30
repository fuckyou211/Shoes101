package com.shoes101.access;


import com.shoes101.pojo.Admin;


public class AdminContext {
	
	private static ThreadLocal<Admin> adminHolder = new ThreadLocal<Admin>();
	
	public static void setAdmin(Admin admin) {
		adminHolder.set(admin);
	}
	
	public static Admin getAdmin() {
		return adminHolder.get();
	}

}
