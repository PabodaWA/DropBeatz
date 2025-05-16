package com.dropbeatzadmin.control;

//interfaces

import com.dropbeatzadmin.model.AdminModel;
import java.util.List;
public interface AdminService {
	
	 	boolean insertData(String fullname, String username, String country, String contactnumber, String email, String password, String role);
	    List<AdminModel> getById(String id);
	    List<AdminModel> getAllAdmin();
	    boolean updateData(String id, String fullname, String username, String country, String contactnumber, String password, String email, String role);
	    boolean deleteData(String id);
	    boolean isUsernameTaken(String username);
	}