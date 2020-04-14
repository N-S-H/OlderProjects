package com.nit.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nit.beans.User;
import com.nit.dao.UserDAO;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class RegistrationController extends HttpServlet {
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	/*Map map=req.getParameterMap();
	User user=new User();
	user.setRole("USER");
	user.setStatus("0");
	try {
		BeanUtils.populate(user,map);
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	UserDAO dao=new UserDAO();
	int i=0;
	String target=new String();
	if(req.getParameter("update")!=null)
	{
		i=dao.updateProfile(user);
		if(i>0)
		 target="profile.jsp?status=Successfully Updated";
		else
			 target="profile.jsp?status=Something went wrong";
		
	}
	else
	{
			i = dao.register(user);

			if (i > 0) {
				target="login.jsp?status=Successfully Registered";
			} else {
				target="registration.jsp?status="
						+ req.getParameter("userName") + " already Registered";
			}
	}
	
	resp.sendRedirect(target.toString());
	
	*/
}
	

}
