package com.starshop.services;

import java.util.UUID;

public interface CustomerService {

	void addVoucherToCustomer(UUID userId, String voucherCode);

}
