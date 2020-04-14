package com.nit.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.dao.UserDAO;

public class RecoverNode extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String src=req.getParameter("src");
		String dest=req.getParameter("dest");
		int i=new UserDAO().recoverNode(src,dest);
		String target="setnodefail.jsp";
		if(i>0)
		{
			target=target+"?status=Node Successfully Recovered";
		}
		else
			target=target+"?status=Something went wrong..";
		
		resp.sendRedirect(target);
	}
}
