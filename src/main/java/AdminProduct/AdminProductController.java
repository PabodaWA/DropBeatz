package AdminProduct;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminProductController {

	//connect DB
	
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	//insert data function
	
	public static boolean insertdata (String product_name, String price,String description, String link) {
		
		boolean isSuccess = false;
		
		try {
			
			//DB connection
		
		con = AdminProductDBconection.getConnection();
		stmt = con.createStatement();
		
		//SQL QUERY
		
		String sql = "INSERT INTO product VALUES (0, '" + product_name + "', '" + price + "', '" + description + "', '" + link + "')";
		
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
		public static List<AdminProductModel> getById (int product_Id){
			
			ArrayList <AdminProductModel> a_product = new ArrayList<>();
			
			
			try {
				
			
			//DB connection
			
			con = AdminProductDBconection.getConnection();
			stmt = con.createStatement();
			
			//Query
			
			String sql = "select * from product where product_id '"+product_Id+ "'";
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				String price = rs.getString(3);
				String description = rs.getString(4);
				String link = rs.getString(5);
				
				AdminProductModel ap = new AdminProductModel(product_id, product_name, price, description, link);
				
				a_product.add(ap);
				
			}
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return a_product;
	}

//GetAll DATA

public static List<AdminProductModel> getAllproduct(){
	
	ArrayList <AdminProductModel> a_products = new ArrayList<>();
	
	
	try {
		
	
	//DB connection
	
	con = AdminProductDBconection.getConnection();
	stmt = con.createStatement();
	
	//Query
	
	String sql = "select * from product";
	
	rs = stmt.executeQuery(sql);
	
	while (rs.next()) {
		
		int product_id = rs.getInt(1);
		String product_name = rs.getString(2);
		String price = rs.getString(3);
		String description = rs.getString(4);
		String link = rs.getString(5);
		
		AdminProductModel ap = new AdminProductModel(product_id, product_name, price, description, link);
		
		a_products.add(ap);
		
	}
	
}
catch(Exception e) {
	
	e.printStackTrace();
}

return a_products;
	
}
}















