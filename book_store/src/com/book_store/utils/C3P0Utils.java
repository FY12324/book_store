package com.book_store.utils;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	private static DataSource ds=null;
	
	static {
		ds=new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return ds;
	}
}
