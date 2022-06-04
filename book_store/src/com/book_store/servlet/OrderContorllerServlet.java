package com.book_store.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book_store.dao.OrderDao;
import com.book_store.dao.OrderItemDao;
import com.book_store.domain.Book;
import com.book_store.domain.CartItem;
import com.book_store.domain.Order;


@WebServlet("/OrderContorllerServlet")
public class OrderContorllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public OrderContorllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String orderid=request.getParameter("orderid");
		String price=request.getParameter("price");
		String address=request.getParameter("address");
		String name=request.getParameter("name");
		System.out.println(name);
		String tel=request.getParameter("tel");
		System.out.println(tel);
		String time=request.getParameter("time");
		Map<String, CartItem> shopcart=(Map<String, CartItem>)request.getSession().getAttribute("shopcart");
		int userid=(int) request.getSession().getAttribute("userid");
		Order order=new Order();
		order.setOrderid(orderid);
		order.setPrice(Double.parseDouble(price));
		order.setAddress(address);
		order.setName(name);
		order.setTel(tel);
		order.setTime(time);
		order.setUserid(userid);
		
		if (OrderDao.insert(order)) {
			Set<String> keys_isbn=shopcart.keySet();
			Object[] isbns=keys_isbn.toArray();
			System.out.println("isbns:"+isbns.length);
			Book book;
			CartItem cartItem;
			int i=0;
			while (i<isbns.length) {
				System.out.println("isbns[i]:"+isbns[i]);
				//¼ÆËã×ÜºÍ
				cartItem=(CartItem)shopcart.get(isbns[i]);
				System.out.println("cartItem:"+cartItem);
				book=cartItem.getBook();
				int count=cartItem.getCount();
				OrderItemDao.insert(orderid, isbns[i].toString(), count);
				i++;
			}
			response.sendRedirect("myorder.jsp?userid="+userid);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
