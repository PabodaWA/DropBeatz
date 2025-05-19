package musicStore;

public class registerModel {
	private int id;
	private String fullname;
	private String username;
	private String country;
	private String contactnumber;
	private String email;
	private String password;
	
	public registerModel(int id, String fullname, String username, String country, String contactnumber, String email,
			String password) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.country = country;
		this.contactnumber = contactnumber;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
