package AdminOrdersPackage;

public class AdminOrderModel {
	
	private String order_id;
	private String Product_id;
	private String User_id;
	
	public AdminOrderModel(String order_id, String product_id, String user_id) {
		super();
		this.order_id = order_id;
		Product_id = product_id;
		User_id = user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public String getProduct_id() {
		return Product_id;
	}

	public String getUser_id() {
		return User_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public void setProduct_id(String product_id) {
		Product_id = product_id;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	
	

}
