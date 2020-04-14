package com.nit.controller;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nit.beans.Packet;
import com.nit.dao.UserDAO;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class SendData extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Packet p=new Packet();
		Map map=req.getParameterMap();
		try {
			BeanUtils.populate(p, map);
			HttpSession hs=req.getSession();
			String userId=(String)hs.getAttribute("userId");
			p.setSender(userId);
			int i=new UserDAO().sendData(p);
			if(i>0)
				resp.sendRedirect("index.jsp?status=Successfully sent");
			else
				resp.sendRedirect("index.jsp?status=something went wrong");
				
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
	}

}
