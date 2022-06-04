package com.book_store.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.book_store.domain.Book;
import com.book_store.utils.C3P0Utils;

public class BookDao {
	static QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
	//查询每一页显示的所有书籍
	public static List<Book> getBooksList(String sql) {
		ArrayList<Book> list=null;
		try {
			list=(ArrayList<Book>)runner.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//查询数据库表title中所有记录条数
	public static int getBooksCount(String sql) {
		Object object=null;
		try {
			object=runner.query(sql, new ScalarHandler());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Integer.parseInt(object.toString());
	}
	//根据isbn查找一本书
	public static Book getBook(String sql,String isbn) {
		Book book=null;
		try {
			book=runner.query(sql, new BeanHandler<Book>(Book.class),isbn);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return book;
	}
}
