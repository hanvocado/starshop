package com.starshop.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private OtpStorageService otpStorageService;

    public String sendOtp(String toEmail) {
        // Generate a 6-digit OTP
        String otp = String.format("%06d", new Random().nextInt(999999));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("starshop.ute@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Mã xác thực từ StarShop");
        message.setText("Mã OTP của bạn là: " + otp);

        try {
        	mailSender.send(message);        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
        otpStorageService.storeOtp(toEmail, otp);

        return otp;
    }
}
