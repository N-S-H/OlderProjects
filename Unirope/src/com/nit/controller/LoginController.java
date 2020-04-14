package com.nit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.beans.User;
import com.nit.dao.UserDAO;

public class LoginController extends HttpServlet {
	private String utype = "";
	private String username = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User rb = new User();
		session.setAttribute("userId", request.getParameter("userId"));
		rb.setUserId(request.getParameter("userId"));
		rb.setPassword(request.getParameter("password"));
		User rb1 = new User();
		rb1 = dao.loginCheck(rb);
		utype = rb1.getRole();
		username = rb.getUserId();
		
		String target = "index.jsp?status=Internal Proublem Please Try again!";
	
		
		if (utype.equals("NODE")) {
				target = "index.jsp?status=Welcome " + username;
				session.setAttribute("role", utype);
		} 
		else	
		if (utype.equals("CONTROL")) {
			target = "index.jsp?status=Welcome " + username;
			session.setAttribute("role", utype);
	} 
		else	
			if (utype.equals("HOST")) {
				target = "index.jsp?status=Welcome " + username;
				session.setAttribute("role", utype);
		} 
	
		else {
			target = "index.jsp?status=Invalid username or password";
		}

		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
		out.flush();
		out.close();
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doGet(request, response);
		out.flush();
		out.close();
	}

}
