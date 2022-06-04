package com.book_store.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book_store.dao.BookDao;
import com.book_store.domain.Book;
import com.book_store.domain.CartItem;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (Integer.parseInt(request.getParameter("flag"))) {
		case 0:
			add(request,response);
			break;
		case 1:
			changeNum(request,response);
			break;
		case 2:
			clearCart(request,response);
			break;
		case 3:
			removeCart(request,response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void add(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
			Map<String,CartItem> shopcart=(Map<String,CartItem>)session.getAttribute("shopcart");
			if (shopcart==null) {
				shopcart=new HashMap<String, CartItem>();
				session.setAttribute("shopcart", shopcart);
			}
			//bookdao从数据库拿
//			String isbn=request.getParameter("isbn");
//			String sql="select * from titles where isbn=?";
//			Book book=BookDao.getBook(sql, isbn);
			//bookdao已经查到从session拿
			Book book=(Book)session.getAttribute("book");
			
			CartItem cartItem=shopcart.get(book.getISBN());
			if (cartItem!=null) {
				cartItem.setCount(cartItem.getCount()+1);
			}else {
				cartItem=new CartItem(book, 1);
				shopcart.put(book.getISBN(), cartItem);
			}
			
			request.getRequestDispatcher("viewCart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void changeNum(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		Map<String,CartItem> shopcart=(Map<String,CartItem>)session.getAttribute("shopcart");
		List<CartItem> items=new ArrayList<CartItem>();
		try {
			String[] number=request.getParameterValues("num");
			Set<String> keys=shopcart.keySet();
			for(String isbn:keys) {
				CartItem citem=shopcart.get(isbn);
				if (citem!=null) {
					items.add(citem);
				}
			}
			for (int i = 0; i < items.size(); i++) {
				items.get(i).setCount(Integer.parseInt(number[i]));
			}
			RequestDispatcher rd=request.getRequestDispatcher("viewCart.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void clearCart(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
			Map<String,CartItem> shopcart=(Map<String,CartItem>)session.getAttribute("shopcart");
			if (shopcart!=null) {
				shopcart.clear();
			}
			RequestDispatcher rd=request.getRequestDispatcher("viewCart.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	protected void removeCart(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		try {
			Map<String,CartItem> shopcart=(Map<String,CartItem>)session.getAttribute("shopcart");
			String isbn=request.getParameter("id");
			CartItem citem=shopcart.get(isbn);
			if (citem.getCount()>1) {
				citem.setCount((citem.getCount()-1));
			}else {
				shopcart.remove(isbn);
			}
			RequestDispatcher rd=request.getRequestDispatcher("viewCart.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
