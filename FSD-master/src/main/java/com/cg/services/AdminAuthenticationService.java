package com.cg.services;

import com.cg.exceptions.ResourceNotFoundException;

public interface AdminAuthenticationService {
	public String sendEmail(String toEmail) throws ResourceNotFoundException;
	public Boolean verifyOtp(String adminEmail,String otp) throws ResourceNotFoundException;
	public String verifyEmail(String adminEmail);

}
