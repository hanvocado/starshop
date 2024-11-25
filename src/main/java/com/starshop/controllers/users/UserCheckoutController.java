package com.starshop.controllers.users;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCheckoutController {

	@GetMapping("/checkout")
	public String checkout() {
		return "user/payment";
	}
	
//	@PostMapping("/checkout")
//	public String placeOrder() { }


}
