<%@page import="com.book_store.dao.OrderItemDao"%>
<%@page import="com.book_store.domain.OrderItem"%>
<%@page import="com.book_store.dao.OrderDao"%>
<%@page import="com.book_store.domain.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<link rel="stylesheet" href="boots/bootstrap.css">
<link href="register.css" rel="stylesheet">
<script type="text/javascript">
		alert("结算成功😀");
</script>
</head>
<body>
	
	<div class="panel panel-info">
		<div class="panel-heading text-center">
		<h2>欢迎你，${sessionScope.username }</h2> <br/>
		</div>
		<%
		int userid=(Integer)session.getAttribute("userid");
		ArrayList<Order> orders=OrderDao.getOrderList(userid);
		if(orders==null){
			out.print("订单为空<br/>");
		}else{
			ArrayList<OrderItem> orderItems=null;
			for(Order order:orders){
				String orderid=order.getOrderid();
				orderItems=OrderItemDao.getOrderList(orderid);
		%>
		<div class="panel-body">
			订单编号：<%=order.getOrderid() %><br>
			订单姓名：<%=order.getName() %><br>
			订单地址：<%=order.getAddress() %><br>
			订单电话：<%=order.getTel() %><br>
		</div>
			
			 <% 
			for(OrderItem item:orderItems){
				%>
				<table class="table">
				<tr>
				<th>书籍isbn</th>
				<th>购买数量</th>
				</tr>
				<tr>
				<td><%=item.getISBN() %></td>
				<td><%=item.getNum() %> </td>
				</tr>	
			</table>
		</div>
	<%	
				}
			%>
		
			
			
			<%
			}
		}
		%>
</body>
</html>