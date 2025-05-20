package com.dropbeatzuser.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dropbeatzuser.model.UserModel;
import com.dropbeatzuser.util.DBConnection;

public class UserController {

//connect DB
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	//Insert Data Function
	public static boolean insertdata(String fullname,String username ,String country ,String contactnumber ,String email ,String password) {
		
		boolean isSuccess = false;
		try {
			//DB CONNECTION CALL
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			
			//SQL QUERY
			String sql = "insert into user values(0,'"+fullname+"','"+username+"','"+country+"','"+contactnumber+"','"+email+"','"+password+"')";
			int rs = stmt.executeUpdate(sql);
			

			
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
	
	//Login validate
	public static List<UserModel> loginValidate (String username , String password ){
		ArrayList<UserModel> user = new ArrayList<>();
		
		try {
			//DB CONNECTION CALL
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			String sql = "SELECT * FROM user WHERE username='" + username + "' AND password='" + password + "'";

			rs = stmt.executeQuery(sql);
			

			if(rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String uname = rs.getString(3);
				String country = rs.getString(4);
				String contactnumber = rs.getString(5);
				String email = rs.getString(6);
				String pass = rs.getString(7);
				
				UserModel u = new UserModel(id ,fullname,uname,country, contactnumber ,email ,pass);
				user.add(u);}
			
		}catch(Exception e) {
				e.printStackTrace();
			}
		
		return user;
	}
	
	public static List<UserModel> userProfile (String Id){
		int convertedID = Integer.parseInt(Id);
		
		ArrayList<UserModel>  user = new ArrayList<>();
		try {
			
			//DBCONNECTION 
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			
			//Query
			String sql ="select * from user where id= '"+convertedID+"' ";
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String username = rs.getString(3);
				String country = rs.getString(4);
				String contactnumber = rs.getString(5);
				String email = rs.getString(6);
				String password = rs.getString(7);
			
				UserModel u = new UserModel(id ,fullname,username,country, contactnumber ,email ,password);
				user.add(u);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
		
		
		
	}
	
	//user profile update
	
	public static boolean updateprofile(String id,String fullname,String username,String country,String contactnumber,String email,String password) {
		try {	
			//DBCONNECTION 
			con  = DBConnection.getConnection();
			stmt=con.createStatement();	
			
			//sql Query 
			String sql = "update user set fullname='"+fullname+"', username='"+username+"', country='"+country+"', contactnumber='"+contactnumber+"', email='"+email+"', password='"+password+"' where id='"+id+"'";


			
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
	//GetBYID
	public static List<UserModel> getById(String Id){
		
		int convertedID = Integer.parseInt(Id);
		
		ArrayList <UserModel> user = new ArrayList<>();
		
		try {
			
			//DBCONNECTION 
			con  = DBConnection.getConnection();
			stmt=con.createStatement();
			
			//Query
			
			String sql ="select * from user where id = '"+convertedID+"'";

			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String username = rs.getString(3);
				String country = rs.getString(4);
				String contactnumber = rs.getString(5);
				String email = rs.getString(6);
				String password = rs.getString(7);
			
				UserModel u = new UserModel(id ,fullname,username,country, contactnumber ,email ,password);
				user.add(u);
				
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean deleteAccount(String id) {
	    boolean isSuccess = false;
	    
	    try {
	        int convID = Integer.parseInt(id);

	        // DB CONNECTION
	        con = DBConnection.getConnection();
	        stmt = con.createStatement();
	        
	        // DELETE Query
	        String sql = "DELETE FROM user WHERE id = '" + convID + "'";
	        
	        int rs = stmt.executeUpdate(sql);
	        
	        if (rs > 0) {
	            isSuccess = true;
	        } else {
	            isSuccess = false;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return isSuccess;
	}



}