<%@page import="com.book_store.domain.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.book_store.dao.BookDao"%>
<%@page import="com.sun.org.apache.regexp.internal.recompile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书列表</title>
<link href="boots/bootstrap.css" rel="stylesheet">
<link href="register.css" rel="stylesheet">
<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
</head>
<%!public String subStr(String str){
	if(str==null||"".equals(str))
		return "";
	if(str.length()>20)
		return str.substring(0,20)+"...";
	else
		return str;
}%>
<body>
<div align="center" class="container" >
	<h2 style="margin-top: 60px">欢迎你，${sessionScope.username }</h2>
	
	<%
		//1.确定每页显示的数据数量
		int pageSize=6;
		//2.确定分页显示所需的总页数
		int total=BookDao.getBooksCount("select count(*) from titles");
		
		int totalPages=0;
		if(total%pageSize==0){
			totalPages=total/pageSize;
		}else{
			totalPages=total/pageSize+1;
		}
		
		//获取当前页面
		String currentPage=request.getParameter("pageIndex");
		if(currentPage==null){
			currentPage="1";
		}
		int pageIndex=Integer.parseInt(currentPage);
		out.print("<p style='color:white'>当前页数：["+pageIndex+"/"+totalPages+"]</p>");
	%>
	<div class="row">
	<% 
		//3.编写sql查询语句，实现数据查询
		String sql="select * from titles Limit "+(pageIndex-1)*pageSize+","+pageSize;
		ArrayList<Book> booklist=(ArrayList<Book>)BookDao.getBooksList(sql);
		
		Book currentBook;
		
		for(int i=0;i<booklist.size();i++){
			currentBook=(Book)booklist.get(i);
			if(i%3==0){
	%>
	<%} %>
				
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<a href="BookDetailServlet?isbn=<%=currentBook.getISBN()%>">
								<img src="images/<%=currentBook.getImageFile() %>" style="height:110px;width:90px; border=0">
							</a>
							<div class="caption">
									<a href="BookDetailServlet?isbn=<%=currentBook.getISBN()%>">
										&nbsp;&nbsp;&nbsp;&nbsp;<%=subStr(currentBook.getTitle()+","+currentBook.getEditionNumber())+"e" %>
									</a>
									<div class="row">
									<!-- 用mysql语句重新拿 -->
									<%-- <a href="CartController?flag=0&isbn=<%=currentBook.getISBN()%>"><input type="submit" value="加入购物车"></a> --%>
									<a href="BookDetailServlet?isbn=<%=currentBook.getISBN()%>"><input type="submit" value="查看详情"></a>
									</div>
							</div>
						</div>
					</div>
				
				
		<%
			if(i%3==2){
		%>
		<%
			}
		}
		%>
		

		<%
			if(pageIndex>1){//显示首页和下一页的超链接
		%>
		</div>
		<ul class="pager" style="display: inline;">
		<li><a href="showbook.jsp?pageIndex=1">首页</a></li>
		<li><a href="showbook.jsp?pageIndex=<%=pageIndex-1%>">上一页</a></li>
		</ul>
		<%
		} 
		if(pageIndex<totalPages){//显示上一页和末页的超链接
		%>
		<ul class="pager" style="display: inline;">
		<li><a href="showbook.jsp?pageIndex=<%=pageIndex+1%>">下一页</a></li>
		<li><a href="showbook.jsp?pageIndex=<%=totalPages%>">末页</a></li>
		</ul>
		<%
		}
		%>
</div>
</body>
</html>