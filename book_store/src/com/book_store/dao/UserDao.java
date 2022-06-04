package com.book_store.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.book_store.domain.User;
import com.book_store.utils.C3P0Utils;
public class UserDao {
	public static QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
	public static User find(String sql,String loginname,String password) {
		User user=null;
		try {
			user=runner.query(sql, new BeanHandler<User>(User.class),new Object[] {loginname,password});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean insert(User user) {
		String sql="insert into userinfo(loginname,password) values(?,?)";
		int n=0;
		try {
			n=runner.update(sql,new Object[] {user.getLoginname(),user.getPassword()});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (n>0) {
			return true;
		}
		return false;
	}

}
