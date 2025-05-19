package com.dropbeatzuser.util;

import java.util.regex.Pattern;

public class NewUserValidation {
	
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
    
    // Method to validate phone number (should be exactly 10 digits)
    public static boolean validatePhoneNumber(String phoneNumber) {
        // Check if the phone number is 10 digits
        if (phoneNumber != null && phoneNumber.matches("\\d{10}")) {
            return true;
        }
        return false;
    }
}
