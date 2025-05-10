package Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AdminController {
	
	private static boolean isSuccess;  
	private static Connection con = null;
	    private static Statement stmt = null;
	    private static ResultSet rs = null;
	    
	  //insert data
	    
	    public static boolean insertdata(String fullname,String username,String country,String contactnumber,String password,String email,String role) {
	    	
	    	boolean isSuccess = false;
	    	
	    	try {
				
				//DB connection
			
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			
			//SQL QUERY
			
			String sql = "INSERT INTO user VALUES (0, '" + fullname + "', '" + username + "', '" + country + "', '" + contactnumber + "', '" + password + "', '" + email + "', '" + role + "')";
			
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
	  		public static List<AdminModel> getById (String id){
	  			
	  			ArrayList <AdminModel> admin = new ArrayList<>();
	  			
	  			
	  			try {
	  				
	  			
	  			//DB connection
	  			
	  			con = DBConnection.getConnection();
	  			stmt = con.createStatement();
	  			
	  			//Query
	  			
	  			String sql = "select * from admin where id = '"+id+ "'";
	  			
	  			rs = stmt.executeQuery(sql);
	  			
	  			while (rs.next()) {
	  				
	  				String Id = rs.getString(1);
	                String fullname = rs.getString(2);
	                String Username = rs.getString(3);
	                String country = rs.getString(4);
	                String contactnumber = rs.getString(5);
	                String email = rs.getString(6);
	                String Password = rs.getString(7);
	                String role = rs.getString(8);;
	  				
	  				AdminModel ap = new AdminModel(Id, fullname, Username, country, contactnumber, Password, email, role);
	  				
	  				admin.add(ap);
	  				
	  			};
	  			
	  		}
	  		catch(Exception e) {
	  			
	  			e.printStackTrace();
	  		}
	  		
	  		return admin;
	  	}

	  //GetAll DATA

	  public static List<AdminModel> getAllAdmin(){
	  	
	  	ArrayList <AdminModel> admin = new ArrayList<>();
	  	
	  	
	  	try {
	  		
	  	
	  	//DB connection
	  	
	  	con = DBConnection.getConnection();
	  	stmt = con.createStatement();
	  	
	  	//Query
	  	
	  	String sql = "select * from user where role = 'admin'";
	  	
	  	rs = stmt.executeQuery(sql);
	  	
	  	while (rs.next()) {
	  		
	  		String id = rs.getString(1);
            String fullname = rs.getString(2);
            String Username = rs.getString(3);
            String country = rs.getString(4);
            String contactnumber = rs.getString(5);
            String email = rs.getString(6);
            String Password = rs.getString(7);
            String role = rs.getString(8);
           
	  		
	  		AdminModel ap = new AdminModel(id, fullname, Username, country, contactnumber, Password, email, role);
	  		
	  		admin.add(ap);
	  		
	  	}
	  	
	  }
	  catch(Exception e) {
	  	
	  	e.printStackTrace();
	  }

	  return admin;
	  	
	  }

	  //update data

	  public static boolean updatedata(String  id,String fullname,String username,String country,String contactnumber,String password,String email,String role)  {
	  	

	  	try {
	  		
	  	
	  	//DB connection
	  	
	  	con = DBConnection.getConnection();
	  	stmt = con.createStatement();
	  	
	  	//sql Query
	  	
	  	String sql = "update user set fullname= '"+fullname+ "',username ='"+username+"',country = '"+country+"',contactnumber ='"+contactnumber+"',password = '"+password+"',email ='"+email+"'"
	  			+"where id = '"+id+"' ";
	  	
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

	  public static boolean deletedata(String id) {
	  	
	  	int convID = Integer.parseInt(id);
	  	
	  	try {
	  		
	  		//DBConnection
	  		
	  		con = DBConnection.getConnection();
	  		stmt = con.createStatement();
	  		
	  		String sql = "delete from user where id = '"+convID+"'";
	  		
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
	  
	  
	  //check username exist
	  public static boolean isUsernameTaken(String username) {
		    boolean exists = false;

		    try {
		        con = DBConnection.getConnection();
		        stmt = con.createStatement();
		        String sql = "SELECT * FROM user WHERE username = '" + username + "'";
		        rs = stmt.executeQuery(sql);
		        if (rs.next()) {
		            exists = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return exists;
		}




	  }
  	
	    	
	    
	    



