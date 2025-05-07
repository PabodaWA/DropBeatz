package SignUpPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	private static String URL = "jdbc:mysql://127.0.0.1:3306/dropbeatz";
	private static String USER = "DropBeatZ";
	private static String PASSWORD  = "DropBeatZ123#";
	private static Connection connection = null;
	
	public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
