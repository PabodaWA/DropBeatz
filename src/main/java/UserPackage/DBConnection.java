package UserPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	

	private static String url = "jdbc:mysql://127.0.0.1:3306/dropbeatz";
	private static String user = "DropBeatZ";
	private static String pass = "DropBeatZ123#";
	private static Connection con;
	
	public static Connection getConnection(){
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,user,pass);
			
			
		}
		
		catch(Exception e){
			
			System.out.println("Database Not Connect!");
			
			
		}
		
		return con;
	}
	
	

}
