package com.starshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starshop.entities.Address;
import com.starshop.entities.Customer;
import com.starshop.repositories.AddressRepository;
import com.starshop.repositories.CustomerRepository;
import com.starshop.services.AddressService;
import com.starshop.services.GeoService;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private GeoService geoService;

	@Override
	public Address save(Address address) {
		double[] latLong = geoService.getLatLongFromAddress(address);
		if (latLong != null) {
			address.setLatitude(latLong[0]);
			address.setLongitude(latLong[1]);			
		}
		return addressRepository.save(address);
	}
	
	@Override
	public void setDefaultAddress(Customer customer, Long addressId) {
        Address address = addressRepository.findByIdAndCustomer(addressId, customer)
                .orElseThrow(() -> new IllegalArgumentException("Địa chỉ không hợp lệ hoặc không tồn tại."));

        customer.setDefaultAddressId(address.getId());
        customerRepository.save(customer);
    }
	
	@Override
	public Address getDefaultAddress(Customer customer) {
        return addressRepository.findById(customer.getDefaultAddressId()).orElse(null);
    }
}
