package AdminPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminController {

	//connect DB
	
		private static boolean isSuccess;
		private static Connection con = null;
		private static Statement stmt = null;
		private static ResultSet rs = null;
		
		//insert data function
		
		public static boolean insertdata(String Admin_id, String Ad_first_name, String Ad_last_name, String Ad_username, String Ad_contry,String Ad_phone_no, String Ad_email, String Ad_password) {
			
			
			
			boolean isSuccess = false;
			
			
			try {
				
				//DB connection
			
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			
			//SQL QUERY
			
			String sql = "INSERT INTO admin VALUES (0, '" + Ad_first_name + "', '" + Ad_last_name+ "', '" + Ad_username + "', '" + Ad_contry + "','" + Ad_phone_no + "','" + Ad_email + "','" + Ad_password + "')";
			
			int rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				
				isSuccess = true;
			}
			
			else {
				
				isSuccess = false;
			}
			
			}catch(Exception e)
			{
				
				e.printStackTrace();
			}
			
			return isSuccess;
		}
		
		
		//GetByID
				public static List<AdminModel> getById (String Admin_id){
					
					ArrayList <AdminModel> admin = new ArrayList<>();
					
					
					try {
						
					
					//DB connection
					
					con = DBConnection.getConnection();
					stmt = con.createStatement();
					
					//Query
					
					String sql = "select * from admin where Admin_id = '"+Admin_id+ "'";
					
					rs = stmt.executeQuery(sql);
					
					while (rs.next()) {
						
						String Admin_Id = rs.getString(1);
						String Ad_first_name = rs.getString(2);
						String Ad_last_name = rs.getString(3);
						String Ad_username = rs.getString(4);
						String Ad_contry = rs.getString(5);
						String Ad_phone_no = rs.getString(6);
						String Ad_email = rs.getString(7);
						String Ad_password = rs.getString(8);
						
						AdminModel ap = new AdminModel(Admin_Id, Ad_first_name, Ad_last_name, Ad_username, Ad_contry, Ad_phone_no, Ad_email, Ad_password);
						
						admin.add(ap);
						
					}
					
				}
				catch(Exception e) {
					
					e.printStackTrace();
				}
				
				return admin;
			}

		//GetAll DATA

		public static List<AdminModel> getAlladmi (){
			
			ArrayList <AdminModel> admin = new ArrayList<>();
			
			
			try {
				
			
			//DB connection
			
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			
			//Query
			
			String sql = "select * from admin";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				String Admin_id = rs.getString(1);
				String Ad_first_name = rs.getString(2);
				String Ad_last_name = rs.getString(3);
				String Ad_username = rs.getString(4);
				String Ad_contry = rs.getString(5);
				String Ad_phone_no = rs.getString(6);
				String Ad_email = rs.getString(7);
				String Ad_password = rs.getString(8);
				
				AdminModel ap = new AdminModel(Admin_id, Ad_first_name, Ad_last_name, Ad_username, Ad_contry, Ad_phone_no, Ad_email, Ad_password);
				
				admin.add(ap);
				
			}
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}

		return admin;
			
		}

		//update data

		public static boolean updatedata(String Admin_id, String Ad_first_name, String Ad_last_name, String Ad_username, String Ad_contry,String Ad_phone_no, String Ad_email, String Ad_password) {
			

			try {
				
			
			//DB connection
			
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			
			//sql Query
			
			String sql = "update admin set Ad_first_name= '"+Ad_first_name+ "',Ad_last_name ='"+Ad_last_name+"',Ad_username = '"+Ad_username+"',Ad_contry ='"+Ad_contry+"',Ad_phone_no ='"+Ad_phone_no+"',Ad_email = '"+Ad_email+"',Ad_password='"+Ad_password+"'"
					+"where Admin_id = '"+Admin_id+"' ";
			
			int rs = stmt.executeUpdate(sql);
			
			if (rs > 0) {
				
				isSuccess = true;
			}
			
			else {
				
				isSuccess = false;
			}
			
			
			}
			catch(Exception e) {
				
				e.printStackTrace();
			}
			
			return isSuccess;
			
			
		}

		//delete data

		public static boolean deletedata(String Admin_id) {
			
			int convID = Integer.parseInt(Admin_id);
			
			try {
				
				//DBConnection
				
				con =DBConnection.getConnection();
				stmt = con.createStatement();
				
				String sql = "delete from admin where Admin_id = '"+convID+"'";
				
				int rs = stmt.executeUpdate(sql);
				
				if (rs > 0) {
					
					isSuccess = true;
				}
				
				else {
					
					isSuccess = false;
				}
			}
			catch(Exception e) {
				
				e.printStackTrace();
			}
			
			return isSuccess;
			
			
			
		}


	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}

	

