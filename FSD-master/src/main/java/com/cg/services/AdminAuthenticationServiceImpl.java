package com.cg.services;

import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cg.entity.AdminVerification;
import com.cg.entity.TeamLead;
import com.cg.exceptions.ResourceNotFoundException;
import com.cg.repository.AdminVerificationRepository;
import com.cg.repository.TeamLeadrepository;

@Service
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService{

	@Autowired
	private  TeamLeadrepository repo;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private AdminVerificationRepository verificationRepository;
	
	
	


	@Override
	public String sendEmail(String toEmail) throws ResourceNotFoundException {
		
		
		Optional<TeamLead> u=repo.findByEmail(toEmail);
				if(!u.isPresent())
				{
					throw new ResourceNotFoundException("Enter a valid email");
				}
		       verificationRepository.deleteAll();
			  SimpleMailMessage message = new SimpleMailMessage();		
			    message.setTo(toEmail);
	            message.setSubject("Email Verification for  Application");
			    String code = RandomStringUtils.randomNumeric(4);
			    String text = "A password change has been requested for your account."
			    		+ " If this was you, use the below code to reset your password:\n\n" + code;
			    message.setText(text); 
			    javaMailSender.send(message);
			    
		        AdminVerification verification=new AdminVerification();
		        verification.setEmail(toEmail);
		        verification.setOtp(code);
		        verificationRepository.save(verification);
		        return "mail sent successfully";
	    				
		  }	


	@Override
	public Boolean verifyOtp(String adminEmail, String otp) throws ResourceNotFoundException {
		Optional<AdminVerification> obj= verificationRepository.findByEmail(adminEmail);
		
		if(obj.isPresent() ) {
		AdminVerification obj1=obj.get();
					if(obj1.getOtp().equals(otp)) 
					{
						verificationRepository.delete(obj1);
						return true;
					}
					else {
					throw new ResourceNotFoundException("Otp did not matched");
					}
		
			}
		else {
			throw new ResourceNotFoundException("Enter Valid Email ID");
		}	
	}
	


	@Override
	public String verifyEmail(String adminEmail) {
		Optional<TeamLead> admin=repo.findByEmail(adminEmail);
		if(admin.isEmpty())
		{
			return "Admin Not Registered with this Email Id";
		}
		else
		{
			return null;
		}
	}	







	


	}		


