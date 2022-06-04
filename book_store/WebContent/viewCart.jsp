<%@page import="java.text.DecimalFormat"%>
<%@page import="com.book_store.domain.Book"%>
<%@page import="com.book_store.domain.CartItem"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="register.css" rel="stylesheet">
<link href="boots/bootstrap.css" rel="stylesheet">
<meta charset="UTF-8">
<title>我的购物车</title>
<script type="text/javascript">
	function nuamAbove(temp) {
		var numStr=/^[1-9]+([0-9]){0,3}$/;
		if (!numStr.test(temp.value)) {
			alert("商品数量必须为小于10000的正整数");
			temp.focus();
			document.getElementById("submit").disabled=true;
			return false;
		}else {
			document.getElementById("submit").disabled=false;
		}
	}
</script>
</head>
<body>
	<div align="center" class="container">
	 	<h2 style="margin-top:10px;">欢迎你，${sessionScope.username }</h2><br>
	 	<form action="CartController?flag=1" method="post">
	 		<table style="text-align: center;" class="table table-bordered" cellSpacing=0 cellPadding=0 width=590 border=0>
	 			<%
	 				Map<String,CartItem> shopcart=(Map<String,CartItem>)session.getAttribute("shopcart");
	 				double total=0;
	 				if(shopcart==null||shopcart.size()==0){
	 					out.println("<p style='color:white;'>购物车当前为空</p>");
	 					out.println("<p><a href='showbook.jsp' style='color:white;'>继续购物</a></p>");
	 				}else{
	 					//创建用于显示容量的变量
	 					Set<String> keys_isbn=shopcart.keySet();
	 					Object[] isbns=keys_isbn.toArray();
	 					
	 					Book book;
	 					CartItem cartItem;
	 					
	 					int count;
	 					double price,subtotal;
	 			%>
	 			<table class="table table-bordered">
	 				<thead>
	 					<tr style="color:white;">
	 						<th>书籍名称</th>
	 						<th>数量</th>
	 						<th>价格</th>
	 						<th>小计</th>
	 						<th>删除</th>
	 					</tr>
	 				</thead>
	 				<%
	 					int i=0;
	 					while(i<isbns.length){
	 						
	 						//计算总和
	 						cartItem=(CartItem)shopcart.get(isbns[i]);
	 						
	 						book=cartItem.getBook();
	 						count=cartItem.getCount();
	 						price=book.getPrice();
	 						subtotal=count*price;
	 						total+=subtotal;
	 						i++;
	 				%>
	 				<tr>
	 					<td height="25" bgcolor="#FFFFFF"><%=book.getTitle() %></td>
	 					
	 					<td align="center" bgcolor="#FFFFFF">
	 						<input type="text" value="<%=count%>" name="num" onblur="numAbove(this)">
	 					</td>
	 					<td bgcolor="#FFFFFF"><%=new DecimalFormat("0.00").format(price) %></td>
	 					<td bgcolor="#FFFFFF"><%=new DecimalFormat("0.00").format(subtotal) %></td>
	 					<td bgcolor="#FFFFFF"><a href="CartController?flag=3&id=<%=book.getISBN()%>">删除商品(-1)</a></td>
	 				</tr>
	 				<%
	 					}
	 				%>
	 				<tr>
	 					<td colspan="5" class="bold right" style="color:white;text-deraction:none;"><b>总计:</b><%=new DecimalFormat("0.00").format(total) %></td>
	 				</tr>
	 			</table>
	 			<table width="600" bgcolor="#999999" class="table table-bordered">
	 				<tr>
	 					<td width="102">
	 						<input type="submit" value="修改数量" name="submit">
	 					</td>
	 					<td width="189" style="color:white;">
	 						消费总金额:<%=new DecimalFormat("0.00").format(total) %>
	 					</td>
	 					<td width="108"><a href="CartController?flag=2" style="color:white;text-deraction:none;">清空购物车</a></td>
	 					<td width="50"><a href="order.jsp" style="color:white;text-deraction:none;">结算</a></td>
	 					<td width="132"><a href="showbook.jsp" style="color:white;text-deraction:none;">返回购物</a></td>
	 				</tr>
	 			</table>
	 				<%
	 					session.setAttribute("total", new Double(total));
	 				}
	 				%>
	 		</table>
	 	</form>
	</div>
</body>
</html>