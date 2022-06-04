package com.book_store.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book_store.domain.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username=request.getParameter("loginName");
		String password=request.getParameter("password");
		String checkcode=request.getParameter("checkcode");
		
		HttpSession session=request.getSession();
		String code=(String)session.getAttribute("checkCode");
		
		User user=User.validate(username, password);
		
		if (user!=null&&checkcode.equalsIgnoreCase(code)) {
			session.setAttribute("userid", user.getUserId());
			session.setAttribute("username", user.getLoginname());
			
			Cookie cookiename=new Cookie("username", username);
			response.addCookie(cookiename);
			Cookie cookiepass=new Cookie("password", password);
			response.addCookie(cookiepass);
			
			response.sendRedirect("showbook.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
