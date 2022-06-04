<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>您的账单</title>
<link href="register.css" rel="stylesheet">
<link href="boots/bootstrap.css" rel="stylesheet">
</head>
<body>
<%
	long orderid=new Date().getTime();
%>
<div align="center" class="container">
<div class="form-cs" style="width:400px;height:600px;background-color: rgba(255,255,255, 0.7); ">
	<h2 align="center" style="color: gray; margin-top: 50px;">您的账单</h2>
	欢迎你，${sessionScope.username }<br>
	<form action="OrderContorllerServlet?orderid=<%=orderid%>" method="post">
		<div align="center">
		 	<table border="1" cellpadding="1" cellspacing="1" bodercolor="#FFFFFF" bgcolor="CCCCCC" class="table table-bordered">
		 		<tr>
		 			<td>
		 				订单单号：
		 				<input type="text" readonly="readonly" name="orderid" value="<%=orderid%>">
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				订单价格：
		 				<input type="text" name="price" value="${sessionScope.total }"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				收货地址：
		 				<input type="text" name="address"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				收货姓名：
		 				<input type="text" name="name"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				联系电话：
		 				<input type="text" name="tel"/>
		 			</td>
		 		</tr>
		 		<tr>
		 			<td>
		 				生成时间：
		 				<input type="text" name="time" readonly="readonly" value="<%=new Date().toString()%> ">
		 			</td>
		 		</tr>
		 		<tr>
		 			<td style="padding-left: 100px">
		 				<input type="submit" value="结账">
		 				<input type="button" value="返回" onclick="javascript:history.go(-1)">
		 			</td>
		 		</tr>
		 		
		 	</table>
		 	
		</div>	
	</form>
	</div>
</div>
</body>
</html>