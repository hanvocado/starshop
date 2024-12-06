package com.starshop.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerCheckoutController {

	@GetMapping("/checkout")
	public String checkout() {
		return "user/payment";
	}
	
//	@PostMapping("/checkout")
//	public String placeOrder() { }


}
