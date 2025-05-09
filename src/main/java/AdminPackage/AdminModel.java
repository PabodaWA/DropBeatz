package AdminPackage;

public class AdminModel {
	
	
	private String Admin_id;
	private String Ad_first_name;
	private String Ad_last_name;
	private String Ad_username;
	private String Ad_contry;
	private String Ad_phone_no;
	private String Ad_email;
	private String Ad_password;
	
	public AdminModel(String admin_id, String ad_first_name, String ad_last_name, String ad_username, String ad_contry,
			String ad_phone_no, String ad_email, String ad_password) {
		super();
		Admin_id = admin_id;
		Ad_first_name = ad_first_name;
		Ad_last_name = ad_last_name;
		Ad_username = ad_username;
		Ad_contry = ad_contry;
		Ad_phone_no = ad_phone_no;
		Ad_email = ad_email;
		Ad_password = ad_password;
	}

	public String getAdmin_id() {
		return Admin_id;
	}

	public String getAd_first_name() {
		return Ad_first_name;
	}

	public String getAd_last_name() {
		return Ad_last_name;
	}

	public String getAd_username() {
		return Ad_username;
	}

	public String getAd_contry() {
		return Ad_contry;
	}

	public String getAd_phone_no() {
		return Ad_phone_no;
	}

	public String getAd_email() {
		return Ad_email;
	}

	public String getAd_password() {
		return Ad_password;
	}

	public void setAdmin_id(String admin_id) {
		Admin_id = admin_id;
	}

	public void setAd_first_name(String ad_first_name) {
		Ad_first_name = ad_first_name;
	}

	public void setAd_last_name(String ad_last_name) {
		Ad_last_name = ad_last_name;
	}

	public void setAd_username(String ad_username) {
		Ad_username = ad_username;
	}

	public void setAd_contry(String ad_contry) {
		Ad_contry = ad_contry;
	}

	public void setAd_phone_no(String ad_phone_no) {
		Ad_phone_no = ad_phone_no;
	}

	public void setAd_email(String ad_email) {
		Ad_email = ad_email;
	}

	public void setAd_password(String ad_password) {
		Ad_password = ad_password;
	}
	
	
		
	

}
