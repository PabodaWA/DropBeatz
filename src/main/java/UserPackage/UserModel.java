package UserPackage;

public class UserModel {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String user_type;
	
	public UserModel(int id, String username, String password, String email, String user_type) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.user_type = user_type;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	
	

}
