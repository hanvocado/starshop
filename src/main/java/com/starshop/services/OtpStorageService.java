package com.starshop.services;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpStorageService {

    private Map<String, String> otpData = new HashMap<>();

    public void storeOtp(String email, String otp) {
        otpData.put(email, otp);
    }

    public boolean validateOtp(String email, String otp) {
        return otpData.containsKey(email) && otpData.get(email).equals(otp);
    }

    public void clearOtp(String email) {
        otpData.remove(email);
    }
}
