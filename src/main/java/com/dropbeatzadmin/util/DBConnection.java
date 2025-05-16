package com.dropbeatzadmin.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	

	private static String url = "jdbc:mysql://127.0.0.1:3306/dropbeatz";
	private static String user = "dropbeatz";
	private static String pass = "12345";
	private static Connection con;
	

	public static Connection getConnection(){
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("DB connected");
			
			
		}
		
		catch(Exception e){
			
			System.out.println("Database Not Connect!");
			
			
		}
		
		return con;
	}
	
	

}

