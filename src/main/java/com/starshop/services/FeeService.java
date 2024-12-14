package com.starshop.services;

import com.starshop.entities.Address;

public interface FeeService {

	int getShippingFeeVND(Address userAddress);

}
