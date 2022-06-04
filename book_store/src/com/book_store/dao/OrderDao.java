package com.book_store.dao;

import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.book_store.domain.Order;
import com.book_store.utils.C3P0Utils;

public class OrderDao {
	public static QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
	//增加订单
	public static boolean insert(Order order) {
		String sql="insert into book_order values(?,?,?,?,?,?,?)";
		int n=0;
		try {
			n=runner.update(sql,new Object[] {order.getOrderid(),order.getPrice(),order.getAddress(),order.getName(),order.getTel(),
					order.getTime(),order.getUserid()});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (n>0) {
			return true;
		}
		return false;
	}
	
	//查询某个用户订单
	public static ArrayList<Order> getOrderList(int userid) {
		System.out.println("userid"+userid);
		String sql="select * from book_order where userid=?";
		ArrayList<Order> orderlist=null;
		try {
			orderlist=(ArrayList<Order>) runner.query(sql, new BeanListHandler<Order>(Order.class),userid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderlist;
	}
	
	//根据订单号查询订单
	public static Order getOrder(String orderid) {
		String sql="select * from book_order where orderid=?";
		Order order=null;
		try {
			order=runner.query(sql, new BeanHandler<Order>(Order.class),orderid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return order;
	}
}
