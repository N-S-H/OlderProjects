package com.nit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection 
{
	public static Connection connection;
	public static Properties properties;
	
	
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		DbConnection.properties = properties;
	}
	
	
	public static Connection getConnection()
	{
	
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","unirope","unirope");
		System.out.println("connection established..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;

	}
	
	
	
	

}
