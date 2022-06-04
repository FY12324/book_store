package com.book_store.domain;

import com.book_store.dao.UserDao;

public class User {
	private int userId;
	private String loginname;
	private String password;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static User validate(String loginname,String password) {
		String sql="select * from userinfo where loginname = ? and password=?";
		User user=UserDao.find(sql, loginname, password);
		
		return user;
	}
}
