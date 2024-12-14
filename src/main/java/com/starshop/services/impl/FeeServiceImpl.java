package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Address;
import com.starshop.services.FeeService;
import com.starshop.services.GeoService;

@Service
public class FeeServiceImpl implements FeeService {
	
	@Autowired
	private GeoService geoService;
	
	@Override
	public int getShippingFeeVND(Address userAddress) {
		double distance = geoService.getDistanceKm(userAddress);

		int baseFee = 10000;		
		int shippingFee = 0;

        if (distance <= 5) {
            shippingFee = baseFee;
        } else if (distance <= 15) {
            shippingFee = (int) Math.round(baseFee + (distance - 5) * 1000);
        } else {
            shippingFee = (int) Math.round(baseFee + (10 * 1000) + (distance - 15) * 1500); 
        } 
        
        return shippingFee;
	}
}
