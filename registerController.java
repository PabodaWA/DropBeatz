package musicStore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class registerController {
	
	//connect DB
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	//Insert Data Function
	public void name() {
		
	} static boolean insertdata(String fullname,String username ,String country ,String contactnumber ,String email ,String password) {
		
		try {
			//DB CONNECTION CALL
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			
			//SQL QUERY
			String sql = "insert into register values(0,'"+fullname+"','"+username+"','"+country+"','"+contactnumber+"','"+email+"','"+password+"')";
			int  rs = stmt.executeUpdate(sql);
			if(rs>0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public static List<registerModel> getById(String Id){
		
		int convertedID = Integer.parseInt(Id);
		
		ArrayList <registerModel> register = new ArrayList<>();
		
		try {
			
			//DBCONNECTION 
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			
			//Query
			String sql ="select * from register where id '"+convertedID+"'";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String username = rs.getString(3);
				String country = rs.getString(4);
				String contactnumber = rs.getString(5);
				String email = rs.getString(6);
				String password = rs.getString(7);
			
				registerModel rm = new registerModel(id ,fullname,username,country, contactnumber ,email ,password);
				register.add(rm);
				
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return register;
	}
	
		//GetALL Data
		public static List<registerModel> getAllregiser(){
			
			ArrayList <registerModel> registers = new ArrayList<>();
			
			try {
				
				//DBCONNECTION 
				con  = DBConnection.getConnection();
				stmt=con.createStatement();
				
				//Query
				String sql ="select * from register";
				
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					int id = rs.getInt(1);
					String fullname = rs.getString(2);
					String username = rs.getString(3);
					String country = rs.getString(4);
					String contactnumber = rs.getString(5);
					String email = rs.getString(6);
					String password = rs.getString(7);
				
					registerModel rm = new registerModel(id ,fullname,username,country, contactnumber ,email ,password);
					registers.add(rm);
					
				}
				
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return registers;
		}
		//update Data
		public static boolean updatedata(String id,String fullname,String username,String country,String contactnumber,String email,String password) {
		try {	
			//DBCONNECTION 
			con  = DBConnection.getConnection();
			stmt=con.createStatement();	
			
			//sql Query 
			String sql = "update register set fullname='"+fullname+"',username='"+username+"',country='"+country+"',contactnumber='"+contactnumber+"',email='"+email+"',password='"+password
						+"where id='"+id+"'";
			
			int rs = stmt.executeUpdate(sql);
			
			if(rs>0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
			
		}
		catch(Exception e) {
			
		}
		return isSuccess;
		
		
		}
		
	
}
