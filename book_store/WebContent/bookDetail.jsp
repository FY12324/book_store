<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书详情</title>
<link href="register.css" rel="stylesheet">
</head>
<body>
<div align="center" class="container">
	<h1 style="color: white;">欢迎你，${sessionScope.username }</h1>
	<div class="form-cs" style="margin-top: 100px;margin-left:29%; width:550px;height:400px;background-color: rgba(255,255,255, 0.7);position: absolute;">
	<table style="text-align: center; color: white;">
		<tr height="100">
			<td colspan="3">
				<h3 style="color: white;">${sessionScope.book.title}</h3>
			</td>
		</tr>
		<tr>
			<td rowspan="5">
				<img alt="${sessionScope.book.title }" src="images/${sessionScope.book.imageFile }">
			</td>
			
			<td class="bold" align="left">图书编号：</td>
			<td align="left">${sessionScope.book.ISBN }</td>
		</tr>
		<tr align="left">	
			<td class="bold" align="left">价格：</td>
			<td align="left">${sessionScope.book.price }</td>
		</tr>
		<tr align="left">	
			<td class="bold" align="left">版本号：</td>
			<td align="left">${sessionScope.book.editionNumber }</td>
		</tr>
		<tr align="left">	
			<td class="bold" align="left">版权：</td>
			<td align="left">${sessionScope.book.copyright }</td>
		</tr>
		
		<tr align="left">
			<td>
				<form action="CartController?flag=0" method="post">
					<p>
						<input type="submit" value="加入购物车">
					</p>
				</form>
			</td>
			<td>
				<form action="viewCart.jsp" method="get">
					<p>
						<input type="submit" value="查看购物车">
					</p>
				</form>
			</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>