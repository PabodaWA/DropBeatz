package SignUpPackage;

public class Admin extends User{

	
	 private String adminRole;
	    
	    public Admin() {
	        super();
	    }
	    
	    public Admin(int id, String username, String password, String email, String adminRole) {
	        super(id, username, password, email, "admin");
	        this.adminRole = adminRole;
	    }
	    
	    // Getters and Setters
	    public String getAdminRole() {
	        return adminRole;
	    }
	    
	    public void setAdminRole(String adminRole) {
	        this.adminRole = adminRole;
	    }
	    
}

