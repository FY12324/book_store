package com.book_store.dao;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.book_store.domain.OrderItem;
import com.book_store.utils.C3P0Utils;

public class OrderItemDao {
	public static QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
	
	//增加订单子项
	public static boolean insert(String orderid,String isbn,int count) {
	String sql="insert into orderitem(orderid,isbn,num) values(?,?,?)";
	int n=0;
	try {
		n=runner.update(sql,new Object[] {orderid,isbn,count});
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	if (n>0) {
		return true;
	}
	return false;
	}
	
	//查询订单子项（某个用户订单）
	public static ArrayList<OrderItem> getOrderList(String orderid){
		String sql="select * from orderitem where orderid=?";
		ArrayList<OrderItem> orderitemlist=null;
		try {
			orderitemlist=(ArrayList<OrderItem>)runner.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),orderid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderitemlist;
	}
}